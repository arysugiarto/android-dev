package com.arysugiarto.jetpackpro.ui.favorite


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_favorite_movie.*

import com.arysugiarto.jetpackpro.R
import com.arysugiarto.jetpackpro.adapter.FavoriteMovieAdapter
import com.arysugiarto.jetpackpro.data.repo.local.entity.MovieEntity
import com.arysugiarto.jetpackpro.ui.detail.DetailActivity
import com.arysugiarto.jetpackpro.viewmodel.MovieViewModel
import com.arysugiarto.jetpackpro.viewmodel.ViewModelFactory


class FavoriteMovieFragment : Fragment() {

    private lateinit var movieAdapter: FavoriteMovieAdapter
    private lateinit var viewModel: MovieViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_favorite_movie, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(
                this,
                factory
            )[MovieViewModel::class.java]
            initFavorite(viewModel)
        }
    }

    private fun getItemClicked(item: MovieEntity) {
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra("movie_id", item.id)
        startActivity(intent)
    }

    private fun initFavorite(viewModel: MovieViewModel) {
        viewModel.getFavoriteMovies().observe(viewLifecycleOwner, Observer { movie ->
            if (movie != null) {

                movieAdapter = FavoriteMovieAdapter()
                { item: MovieEntity -> getItemClicked(item) }

                movieAdapter.submitList(movie)

                with(rv_favorite_movie) {
                    layoutManager = GridLayoutManager(context,3)
                    setHasFixedSize(true)
                    adapter = movieAdapter
                }
            }

        })
    }

    override fun onResume() {
        super.onResume()

        initFavorite(viewModel)
    }
}
