package com.assignment.meteoriteapp.ui.map


import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.app.ActivityCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import com.assignment.meteoriteapp.R
import com.assignment.meteoriteapp.testData.Meteor
import com.google.android.gms.maps.*
import com.google.android.gms.maps.GoogleMap.MAP_TYPE_NORMAL
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


/**
 * Created by Sibaprasad Mohanty on 15/07/21.
 * Spm Limited
 * sp.dobest@gmail.com
 */


class MeteorMapDialogFragment : DialogFragment() {

    lateinit var mapView: MapView
    lateinit var imageViewBack: AppCompatImageView
    lateinit var textViewTitle: AppCompatTextView
    private var googleMap: GoogleMap? = null

    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if (dialog != null) {
            dialog.window!!.setWindowAnimations(
                R.style.styleDialogFragment
            )
            dialog.window!!.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_meteor_map, container, false)
        mapView = view.findViewById(R.id.meteorMapView)
        imageViewBack = view.findViewById<AppCompatImageView>(R.id.imageViewBack)
        textViewTitle = view.findViewById<AppCompatTextView>(R.id.tvTitle)
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val meteor = arguments?.getParcelable<Meteor>(MAPDATA)
            ?: throw IllegalStateException("No args provided")
        mapView.onCreate(savedInstanceState)
        textViewTitle.setText(meteor.name)
        imageViewBack.setOnClickListener {
            dismissAllowingStateLoss()
        }

        setupMap(meteor)
    }

    companion object {
        val MAPDATA = "Meteor"
        fun newInstance(
            item: Meteor
        ): MeteorMapDialogFragment = MeteorMapDialogFragment().apply {
            arguments = Bundle().apply {
                putParcelable(MAPDATA, item)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mapView.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.fragment_map_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_home -> {
                dismissAllowingStateLoss()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun setupMap(meteor: Meteor) {
        mapView.visibility = View.VISIBLE
        mapView.onResume() // needed to get the map to display immediately
        try {
            MapsInitializer.initialize(activity?.applicationContext)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        mapView.getMapAsync(OnMapReadyCallback { mMap ->
            googleMap = mMap

            // For showing a move to my location button
            if (ActivityCompat.checkSelfPermission(
                    activity as FragmentActivity,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    activity as FragmentActivity,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
            }
            googleMap?.isMyLocationEnabled = true
            googleMap?.mapType = MAP_TYPE_NORMAL

            val loc = meteor.let { it ->
                LatLng(it.reclat.toDouble(), it.reclong.toDouble())
            } ?: run {
                LatLng(-34.00, 151.00)
            }
            googleMap?.uiSettings?.isZoomControlsEnabled = true
            googleMap?.uiSettings?.isCompassEnabled = true
            googleMap?.addMarker(
                MarkerOptions().position(loc).title(meteor.name)
                    .snippet(
                        "Weight: ${meteor.mass} Kg" + " Date : ${meteor.getDateTime()}"
                    )
            )
            // For zooming automatically to the location of the marker
            val cameraPosition = CameraPosition.Builder().target(loc).zoom(12f).build()
            googleMap?.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
        })
    }
}