package com.assignment.meteoriteapp.ui

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.assignment.meteoriteapp.R
import com.assignment.meteoriteapp.databinding.ActivityMeteorBinding
import com.assignment.meteoriteapp.utils.CommonUtils
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_meteor.*

@AndroidEntryPoint
class MeteorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMeteorBinding

    private val PERMISSION_REQUEST_CODE = 111

    lateinit var navView: BottomNavigationView

    var requiredPermission = arrayOf("android.permission.ACCESS_FINE_LOCATION")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMeteorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navView = binding.navView
        setUpNavigation()
        requestPermission()

    }

    fun setUpNavigation() {
        val navController = findNavController(R.id.nav_host_fragment_activity_meteor)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_favorites
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() &&
                            grantResults[0] == PackageManager.PERMISSION_GRANTED)
                ) {
                    // Permission is granted. Continue the action or workflow
                    // in your app.
                    Snackbar.make(
                        container,
                        "Permission Granted, Now you can access location data and camera.",
                        Snackbar.LENGTH_LONG
                    ).show();
                } else {
                    Snackbar.make(
                        container,
                        "Permission Denied, You cannot access location data",
                        Snackbar.LENGTH_LONG
                    ).show()

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (!shouldShowRequestPermissionRationale(ACCESS_FINE_LOCATION)) {
                            showMessageOKCancel(
                                "Permission Granted, Now you can access location data and camera."
                            ) { dialog, which ->
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                    requestPermissions(
                                        arrayOf(ACCESS_FINE_LOCATION),
                                        PERMISSION_REQUEST_CODE
                                    )
                                }
                            }
                        }
                    }
                }
                return
            }
            else -> {

            }
        }
    }

    fun showMessageOKCancel(message: String, okListener: DialogInterface.OnClickListener) {
        AlertDialog.Builder(this@MeteorActivity)
            .setMessage(message)
            .setPositiveButton("OK", okListener)
            .setNegativeButton("Cancel", null)
            .create()
            .show()
    }

    fun requestPermission() {
        CommonUtils.requestPermission(this, requiredPermission, PERMISSION_REQUEST_CODE)
    }

}