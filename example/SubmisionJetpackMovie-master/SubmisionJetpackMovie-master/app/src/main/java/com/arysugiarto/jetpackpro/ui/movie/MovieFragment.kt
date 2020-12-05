package com.arysugiarto.jetpackpro.ui.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_movie.*
import com.arysugiarto.jetpackpro.R
import com.arysugiarto.jetpackpro.adapter.MovieAdapter
import com.arysugiarto.jetpackpro.data.repo.local.entity.MovieEntity
import com.arysugiarto.jetpackpro.ui.detail.DetailActivity
import com.arysugiarto.jetpackpro.viewmodel.MovieViewModel
import com.arysugiarto.jetpackpro.viewmodel.ViewModelFactory
import com.arysugiarto.jetpackpro.vo.Status

class MovieFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_movie, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(
                this,
                factory
            )[MovieViewModel::class.java]
            viewModel.getMovies().observe(viewLifecycleOwner, Observer { movie ->
                if (movie != null) {
                    when (movie.status) {
                        Status.LOADING -> {
                            progress_bar_movie.visibility = View.VISIBLE
                        }
                        Status.SUCCESS -> {
                            progress_bar_movie.visibility = View.GONE
                            val movieAdapter = MovieAdapter(requireContext(), movie.data)
                            { item: MovieEntity -> getItemClicked(item) }

                            with(rv_movie) {
                                layoutManager = GridLayoutManager(context,3)
                                setHasFixedSize(true)
                                adapter = movieAdapter
                            }
                        }
                        Status.ERROR -> {
                            progress_bar_movie.visibility = View.GONE
                            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

            })
        }
    }

    private fun getItemClicked(item: MovieEntity) {
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra("movieId", item.id)
        startActivity(intent)
    }
}