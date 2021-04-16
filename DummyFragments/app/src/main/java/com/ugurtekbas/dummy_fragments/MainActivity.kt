package com.ugurtekbas.dummy_fragments

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        Log.w("HostActivity: ", "onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.w("HostActivity: ", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.w("HostActivity: ", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.w("HostActivity: ", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.w("HostActivity: ", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.w("HostActivity: ", "onDestroy")
    }
}
