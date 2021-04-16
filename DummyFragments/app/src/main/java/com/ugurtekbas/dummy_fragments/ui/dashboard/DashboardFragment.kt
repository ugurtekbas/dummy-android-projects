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

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

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

        Log.w("Fragment C: ", "onCreateView")
        return root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.w("Fragment C: ", "onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.w("Fragment C: ", "onCreate")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.w("Fragment C: ", "onActivityCreated")
    }

    override fun onPause() {
        super.onPause()
        Log.w("Fragment C: ", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.w("Fragment C: ", "onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.w("Fragment C: ", "onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.w("Fragment C: ", "onDestroy")
    }
}
