package com.ugurtekbas.dummy_fragments.ui.home

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

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        Log.w("Fragment B: ", "onCreateView")
        return root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.w("Fragment B: ", "onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.w("Fragment B: ", "onCreate")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.w("Fragment B: ", "onActivityCreated")
    }

    override fun onPause() {
        super.onPause()
        Log.w("Fragment B: ", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.w("Fragment B: ", "onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.w("Fragment B: ", "onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.w("Fragment B: ", "onDestroy")
    }
}
