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
import kotlinx.android.synthetic.main.fragment_favorite_tv_show.*

import com.arysugiarto.jetpackpro.R
import com.arysugiarto.jetpackpro.adapter.FavoriteTvAdapter
import com.arysugiarto.jetpackpro.data.repo.local.entity.TvEntity
import com.arysugiarto.jetpackpro.ui.detail.DetailActivity
import com.arysugiarto.jetpackpro.viewmodel.TvShowViewModel
import com.arysugiarto.jetpackpro.viewmodel.ViewModelFactory

class FavoriteTvShowFragment : Fragment() {

    private lateinit var viewModel: TvShowViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_favorite_tv_show, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(
                this,
                factory
            )[TvShowViewModel::class.java]
            initFavorite(viewModel)
        }
    }

    private fun initFavorite(viewModel: TvShowViewModel) {
        viewModel.getFavoriteTvShows().observe(viewLifecycleOwner, Observer { tvShow ->
            if (tvShow != null) {
                val tvShowAdapter = FavoriteTvAdapter()
                { item: TvEntity -> getItemClicked(item) }

                tvShowAdapter.submitList(tvShow)

                with(rv_favorite_tv_show) {
                    layoutManager = GridLayoutManager(context,3)
                    setHasFixedSize(true)
                    adapter = tvShowAdapter
                }
            }

        })
    }

    private fun getItemClicked(item: TvEntity) {
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra("tv_show_id", item.id)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()

        initFavorite(viewModel)
    }
}
