package com.ugurtekbas.dummy_fragments

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.ugurtekbas.dummy_fragments.databinding.ActivityMainBinding
import com.ugurtekbas.dummy_fragments.ui.dashboard.DashboardFragment
import com.ugurtekbas.dummy_fragments.ui.home.HomeFragment
import com.ugurtekbas.dummy_fragments.ui.notifications.NotificationsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val homeFragment = HomeFragment.newInstant()
    private val dashboardFragment = DashboardFragment.newInstant()
    private val notFragment = NotificationsFragment.newInstant()
    private var activeFragment: Fragment = homeFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        binding.navView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    changeFragment(homeFragment)
                    activeFragment = homeFragment
                }

                R.id.navigation_dashboard -> {
                    changeTab(dashboardFragment, DashboardFragment.TAG)
                }

                R.id.navigation_notifications -> {
                    changeTab(notFragment, NotificationsFragment.TAG)
                }
            }
            true
        }

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(binding.navContainer.id, homeFragment, HomeFragment.TAG)
                .addToBackStack(HomeFragment.TAG)
                .commit()
            activeFragment = homeFragment
        }
        Log.w("HostActivity: ", "onCreate")
    }

    private fun changeTab(fragment: Fragment, tag: String?) {
        val isFragmentAddedBefore = supportFragmentManager.findFragmentByTag(tag)
        if (isFragmentAddedBefore == null) {
            supportFragmentManager.beginTransaction()
                .hide(activeFragment)
                .add(binding.navContainer.id, fragment, tag)
                .commit()
        } else {
            changeFragment(fragment)
        }
        activeFragment = fragment
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .hide(activeFragment)
            .show(fragment)
            .commit()
    }

    override fun onBackPressed() {
        if (activeFragment is HomeFragment) {
            Log.w("onBackPressed: ", "true")
            supportFinishAfterTransition()
            super.onBackPressed()
        } else {
            Log.w("onBackPressed: ", "false")
            binding.navView.menu.getItem(0).isChecked = true
            supportFragmentManager.beginTransaction()
                .hide(activeFragment)
                .show(homeFragment)
                .commit()
            activeFragment = homeFragment
        }
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
