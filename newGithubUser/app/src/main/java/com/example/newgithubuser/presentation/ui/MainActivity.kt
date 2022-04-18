package com.example.newgithubuser.presentation.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newgithubuser.NewGithubUserApplication
import com.example.newgithubuser.databinding.ActivityMainBinding
import com.example.newgithubuser.di.ListComponent
import com.example.newgithubuser.di.ViewModelFactory
import com.example.newgithubuser.presentation.ui.utils.ImageService
import com.example.newgithubuser.domain.RepoItem
import com.example.newgithubuser.presentation.ui.utils.showIfElseHide
import com.example.newgithubuser.presentation.ui.utils.InfiniteScrollListener
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val DIALOG_TAG = "LongClickDialog"
    lateinit var listComponent: ListComponent

    @Inject lateinit var viewModelFactory: ViewModelFactory
    @Inject lateinit var imageService: ImageService

    private lateinit var listViewModel: ListViewModel

    private lateinit var binding: ActivityMainBinding
    private val repoAdapter by lazy {
        MainAdapter(
            { showDialog(it) },
            imageService
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listComponent = (application as NewGithubUserApplication).appComponent.listComponent().create()
        listComponent.inject(this)

        listViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(ListViewModel::class.java)

        val recLayoutManager = LinearLayoutManager(this)
        binding.repoList.apply {
            layoutManager = recLayoutManager
            binding.repoList.setHasFixedSize(true)
            adapter = repoAdapter
            addOnScrollListener(
                InfiniteScrollListener(
                    {
                        Log.i("HEY HEY-Activity: ", "Scroll Listener Load More")
                        listViewModel.loadMore()
                    },
                    recLayoutManager
                )
            )
        }

        listViewModel.viewState.observe(this, Observer {
            Log.i("HEY HEY-Activity: ", "Live data observe - " + it.repoList.size)
            with(binding) {
                repoAdapter.submitList(it.repoList)
                loading.showIfElseHide{ it.shouldShowLoading }
            }
        })
        
        listViewModel.viewEvent.observe(this, Observer {
            when(it) {
                is ViewEvent.ShowErrorMessage -> {
                    Log.i("HEY HEY-Activity: ", "viewEvent observe - ShowErrorMessage")
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
                is ViewEvent.ShowConnectionDialog -> {
                    Log.i("HEY HEY-Activity: ", "viewEvent observe - ShowConnectionDialog")
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun showDialog(it: RepoItem) {
        LongClickDialog.newInstance("https://github.com/ugurtekbas", it.url)
            .show(supportFragmentManager, DIALOG_TAG)
    }
}
