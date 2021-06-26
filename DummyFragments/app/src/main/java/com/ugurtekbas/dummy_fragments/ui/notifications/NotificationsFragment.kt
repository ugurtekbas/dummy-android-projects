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

    companion object {
        val TAG = NotificationsFragment::class.simpleName
        fun newInstant() = NotificationsFragment()
    }

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

        Log.w("NotificationsFragment: ", "onCreateView")
        return root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.w("NotificationsFragment: ", "onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.w("NotificationsFragment: ", "onCreate")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.w("NotificationsFragment: ", "onActivityCreated")
    }

    override fun onPause() {
        super.onPause()
        Log.w("NotificationsFragment: ", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.w("NotificationsFragment: ", "onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.w("NotificationsFragment: ", "onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.w("NotificationsFragment: ", "onDestroy")
    }
}
