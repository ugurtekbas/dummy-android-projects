package com.ugurtekbas.dummy_fragments.ui.dashboard

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ugurtekbas.dummy_fragments.R
import com.ugurtekbas.dummy_fragments.ui.home.HomeFragment

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

    companion object {
        val TAG = DashboardFragment::class.simpleName
        fun newInstant() = DashboardFragment()
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
                ViewModelProvider(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        Log.w("DashboardFragment: ", "onCreateView")
        return root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.w("DashboardFragment: ", "onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.w("DashboardFragment: ", "onCreate")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.w("DashboardFragment: ", "onActivityCreated")
    }

    override fun onPause() {
        super.onPause()
        Log.w("DashboardFragment: ", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.w("DashboardFragment: ", "onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.w("DashboardFragment: ", "onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.w("DashboardFragment: ", "onDestroy")
    }
}
