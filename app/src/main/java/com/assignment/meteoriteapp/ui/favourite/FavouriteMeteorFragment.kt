package com.assignment.meteoriteapp.ui.favourite


/**
 * Created by Sibaprasad Mohanty on 14/07/21.
 * Spm Limited
 * sp.dobest@gmail.com
 */

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.assignment.meteoriteapp.R
import com.assignment.meteoriteapp.controllers.OnMeteorClickListener
import com.assignment.meteoriteapp.testData.Meteor
import com.assignment.meteoriteapp.databinding.FragmentFavouriteMeteorBinding
import com.assignment.meteoriteapp.network.Status
import com.assignment.meteoriteapp.ui.MeteorActivity
import com.assignment.meteoriteapp.ui.adapter.MeteorAdapter
import com.assignment.meteoriteapp.ui.map.MeteorMapDialogFragment
import com.assignment.meteoriteapp.utils.CommonUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouriteMeteorFragment : Fragment(), OnMeteorClickListener,
    SwipeRefreshLayout.OnRefreshListener {

    private val viewModel by viewModels<FavouriteMeteorViewModel>()
    lateinit var binding: FragmentFavouriteMeteorBinding

    private val adapter: MeteorAdapter by lazy {
        MeteorAdapter(this, true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_favourite_meteor, container, false
        )
        val view: View = binding.root
        setupViewModel()
        binding.viewModel = viewModel
        binding.meteorAdapter = adapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObserver()
    }

    private fun setupViewModel() {
//        viewModel = ViewModelProvider(
//            this,
//            ViewModelFactory()
//        ).get(
//            FavouriteMeteorViewModel::class.java
//        )
//        viewModel.context = activity as FragmentActivity
    }


    private fun setObserver() {
        binding.swipeLayoutFev.setOnRefreshListener(this)
        viewModel.fetchMeteorsByPage()

        viewModel.favouriteMeteorList.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
//                    progressLoadFevMeteors.visibility = View.GONE
//                    binding.recyclerViewFevMeteor.visibility = View.VISIBLE
                    binding.tvErrorFev.visibility = View.GONE
                    adapter.setMeteorList(it.data as ArrayList<Meteor>)
                    binding.swipeLayoutFev.isRefreshing = false
                }
                Status.LOADING -> {
//                    progressLoadFevMeteors.visibility = View.VISIBLE
//                    binding.swipeLayoutFev.isRefreshing = true
                }
                Status.ERROR -> {
//                    binding.tvErrorFev.visibility = View.VISIBLE
//                    binding.tvErrorFev.setText("Error ${it.message}")
//                    binding.recyclerViewFevMeteor.visibility = View.GONE
//                    progressLoadFevMeteors.visibility = View.GONE
                    Toast.makeText(
                        activity as FragmentActivity,
                        "Error ${it.message}",
                        Toast.LENGTH_LONG
                    ).show()
                    binding.swipeLayoutFev.isRefreshing = false
                }
            }
        })
    }

    override fun onMeteorMapClick(meteor: Meteor) {
        if (CommonUtils.checkPermission(activity as FragmentActivity)) {
            if (meteor.reclat != null && meteor.reclong != null) {
                MeteorMapDialogFragment.newInstance(meteor).show(childFragmentManager, "TAG")
            } else {
                Toast.makeText(activity, "Lattitude and Longitude not found", Toast.LENGTH_SHORT)
                    .show()
            }
        } else {
            Toast.makeText(activity, "Permission Not granted", Toast.LENGTH_SHORT).show()
            (activity as MeteorActivity).requestPermission()
        }
    }

    override fun onFevoriteClick(meteor: Meteor) {
        viewModel.removeFavoriteMeteors(meteor)
    }

    override fun onRefresh() {
        viewModel.fetchMeteorsByPage()
    }
}