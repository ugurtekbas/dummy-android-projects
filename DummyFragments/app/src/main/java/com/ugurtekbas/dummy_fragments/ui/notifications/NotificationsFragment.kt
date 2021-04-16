package com.ugurtekbas.dummy_fragments.ui.notifications

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

class NotificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
                ViewModelProvider(this).get(NotificationsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications)
        notificationsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        Log.w("Fragment A: ", "onCreateView")
        return root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.w("Fragment A: ", "onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.w("Fragment A: ", "onCreate")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.w("Fragment A: ", "onActivityCreated")
    }

    override fun onPause() {
        super.onPause()
        Log.w("Fragment A: ", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.w("Fragment A: ", "onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.w("Fragment A: ", "onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.w("Fragment A: ", "onDestroy")
    }
}
