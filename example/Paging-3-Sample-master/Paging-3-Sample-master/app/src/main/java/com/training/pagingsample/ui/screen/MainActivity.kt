package com.training.pagingsample.ui.screen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.training.pagingsample.ui.adapters.MovieLoadStateAdapter
import com.training.pagingsample.ui.adapters.MovieAdapter
import com.training.pagingsample.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    private lateinit var movieAdapter: MovieAdapter
    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        movieAdapter = MovieAdapter()

        mainBinding.movieRecycler.apply {
            layoutManager = GridLayoutManager(context,2)
            setHasFixedSize(true)
            adapter = movieAdapter
//                .withLoadStateFooter(
//                         MovieLoadStateAdapter { movieAdapter.retry() }
//                 )

        }

        lifecycleScope.launch {
            viewModel.movies.collectLatest {
                movieAdapter.submitData(it)
            }
        }

        mainBinding.btnRetry.setOnClickListener{
            movieAdapter.retry()
        }

        // show the loading state for te first load
        movieAdapter.addLoadStateListener { loadState ->

            if (loadState.refresh is LoadState.Loading || loadState.append is LoadState.Loading) {
                mainBinding.btnRetry.visibility = View.GONE
//                // Show ProgressBar
//                mainBinding.progressBar.visibility = View.VISIBLE
                showProgressBar(true)
            }
            else {
                // Hide ProgressBar
//                mainBinding.progressBar.visibility = View.GONE
                showProgressBar(false)
                // If we have an error, show a toast
                val errorState = when {
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.refresh is LoadState.Error -> {
                        mainBinding.btnRetry.visibility = View.VISIBLE
                        loadState.refresh as LoadState.Error
                    }
                    else -> null
                }
                errorState?.let {
                    Toast.makeText(this, it.error.message, Toast.LENGTH_LONG).show()
                    mainBinding.btnRetry.visibility = View.VISIBLE
                }
            }


        }
    }
    private fun showProgressBar(display : Boolean)
    {
        if(!display)
            mainBinding.loadStateProgress.visibility = View.GONE
        else
            mainBinding.loadStateProgress.visibility = View.VISIBLE

    }


}