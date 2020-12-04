package com.arysugiarto.jetpackpro.ui.detail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.arysugiarto.jetpackpro.BuildConfig
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.arysugiarto.jetpackpro.R

import kotlinx.android.synthetic.main.activity_detail.*
import com.arysugiarto.jetpackpro.data.repo.local.entity.MovieEntity
import com.arysugiarto.jetpackpro.data.repo.local.entity.TvEntity
import com.arysugiarto.jetpackpro.viewmodel.DetailViewModel
import com.arysugiarto.jetpackpro.viewmodel.ViewModelFactory
import com.arysugiarto.jetpackpro.vo.Status

class DetailActivity : AppCompatActivity() {

    private lateinit var viewModel: DetailViewModel
    private var menu: Menu? = null
    private var movieId: String? = null
    private var tvShowId: String? = null
    private var movieEntity: MovieEntity? = null
    private var tvEntity: TvEntity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)
        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        val extras: Bundle? = intent.extras

        movieId = extras?.getString("movieId")
        tvShowId = extras?.getString("tvId")

        if (movieId != null) {
            viewModel.setSelectedMovie(movieId)
            viewModel.getMovies().observe(this, Observer { detailMovie ->
                if (detailMovie != null) {
                    when (detailMovie.status) {
                        Status.LOADING -> detail_progress_bar.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            detail_progress_bar.visibility = View.GONE
                            movieEntity = detailMovie.data
                            DataMovie(movieEntity)
                        }
                        Status.ERROR -> {
                            detail_progress_bar.visibility = View.GONE
                            Toast.makeText(
                                applicationContext,
                                "Error",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }

            })
        } else if (tvShowId != null) {
            viewModel.setSelectedTvShow(tvShowId)
            viewModel.getTvShows().observe(this, Observer { detailTvShow ->
                if (detailTvShow != null) {
                    when (detailTvShow.status) {
                        Status.LOADING -> detail_progress_bar.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            detail_progress_bar.visibility = View.GONE
                            tvEntity = detailTvShow.data
                            DataTv(tvEntity)
                        }
                        Status.ERROR -> {
                            detail_progress_bar.visibility = View.GONE
                            Toast.makeText(
                                applicationContext, "Error", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun DataMovie(movie: MovieEntity?) {
        tv_title.text = movie?.title
        tv_rating_item.text = movie?.vote_average
        tv_desc.text = movie?.overview
        tv_release_date.text = movie?.release_date
        Glide.with(this)
            .load(BuildConfig.IMG_URL + movie?.poster_path)
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
            .into(poster)
        supportActionBar?.title = movie?.title
    }

    private fun DataTv(tv: TvEntity?) {
        tv_title.text = tv?.name
        tv_rating_item.text = tv?.vote_average
        tv_desc.text = tv?.overview
        tv_release_date.text = tv?.first_air_date
        Glide.with(this)
            .load(BuildConfig.IMG_URL + tv?.poster_path)
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
            .into(poster)
        supportActionBar?.title = tv?.name
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_fav, menu)
        this.menu = menu
        if (movieId != null) {
            viewModel.getMovies().observe(this, Observer { detailMovie ->
                if (detailMovie != null) {
                    when (detailMovie.status) {
                        Status.LOADING -> detail_progress_bar.visibility = View.VISIBLE
                        Status.SUCCESS -> if (detailMovie.data != null) {
                            detail_progress_bar.visibility = View.GONE
                            val state = detailMovie.data.bookmarked
                            setBookmarkState(state)
                        }
                        Status.ERROR -> {
                            detail_progress_bar.visibility = View.GONE
                            Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

            })
        } else if (tvShowId != null) {
            viewModel.getTvShows().observe(this, Observer { detailTvShow ->
                if (detailTvShow != null) {
                    when (detailTvShow.status) {
                        Status.LOADING -> detail_progress_bar.visibility = View.VISIBLE
                        Status.SUCCESS -> if (detailTvShow.data != null) {
                            detail_progress_bar.visibility = View.GONE
                            val state = detailTvShow.data.bookmarked
                            setBookmarkState(state)
                        }
                        Status.ERROR -> {
                            detail_progress_bar.visibility = View.GONE
                            Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

            })
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_bookmark) {
            if (movieId != null) {
                viewModel.setFavoriteMovie(movieEntity)
                return true
            } else if (tvShowId != null) {
                viewModel.setFavoriteTvShow(tvEntity)
                return true
            }
        }
        return super.onOptionsItemSelected(item)

    }

    private fun setBookmarkState(state: Boolean?) {
        if (menu == null) return
        val menuItem = menu?.findItem(R.id.action_bookmark)
        if (state!!) {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_star_black_24dp)
        } else {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_star_border_black_24dp)
        }
    }

}
