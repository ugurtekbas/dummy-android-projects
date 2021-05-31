package com.example.newgithubuser.presentation.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.example.newgithubuser.NewGithubUserApplication
import com.example.newgithubuser.R
import com.example.newgithubuser.databinding.DialogBinding
import com.example.newgithubuser.di.DialogComponent
import com.example.newgithubuser.navigation.Navigator
import com.example.newgithubuser.navigation.RepoNavigator
import kotlinx.android.synthetic.main.dialog.view.*
import javax.inject.Inject

class LongClickDialog : DialogFragment() {

    @Inject lateinit var navigator: Navigator
    private val resultListener: OnDialogResultListener? = null
    private lateinit var binding: DialogBinding

    companion object {
        private val profileTag = "profileTag"
        private val repoTag = "repoTag"

        fun newInstance(profileUrl: String, repoUrl: String): LongClickDialog {
            val args = Bundle()
            args.putString(profileTag, profileUrl)
            args.putString(repoTag, repoUrl)

            return LongClickDialog().apply {
                arguments = args
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navigator = RepoNavigator(context)
        //DialogComponent.create().inject(this)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog, container);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(view) {
            btnUser.setOnClickListener {
                navigator.navigateToSite(arguments?.getString(profileTag).orEmpty())
            }

            btnRepo.setOnClickListener {
                navigator.navigateToSite(arguments?.getString(repoTag).orEmpty())
            }

            btnCancel.setOnClickListener {
                this@LongClickDialog.dismiss()
            }
        }
    }

    interface OnDialogResultListener {
        fun onDialogResult(requestCode: Int)
    }
}
