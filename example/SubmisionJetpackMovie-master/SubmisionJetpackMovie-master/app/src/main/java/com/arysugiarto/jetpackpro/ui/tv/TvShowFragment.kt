package com.arysugiarto.jetpackpro.ui.tv

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
import kotlinx.android.synthetic.main.fragment_tv_show.*
import com.arysugiarto.jetpackpro.R
import com.arysugiarto.jetpackpro.adapter.TvShowAdapter
import com.arysugiarto.jetpackpro.data.repo.local.entity.TvEntity
import com.arysugiarto.jetpackpro.ui.detail.DetailActivity
import com.arysugiarto.jetpackpro.viewmodel.TvShowViewModel
import com.arysugiarto.jetpackpro.viewmodel.ViewModelFactory
import com.arysugiarto.jetpackpro.vo.Status

class TvShowFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_tv_show, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())

            val viewModel = ViewModelProvider(
                this,
                factory
            )[TvShowViewModel::class.java]
            viewModel.getTvShows().observe(viewLifecycleOwner, Observer { tvShows ->
                if (tvShows != null) {
                    when (tvShows.status) {
                        Status.LOADING -> progress_bar_tv_show.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            progress_bar_tv_show.visibility = View.GONE
                            val tvShowAdapter = TvShowAdapter(requireContext(), tvShows.data)
                            { item: TvEntity -> getItemClicked(item) }

                            with(rv_tv_show) {
                                layoutManager = GridLayoutManager(context,3)
                                setHasFixedSize(true)
                                adapter = tvShowAdapter
                            }
                        }
                        Status.ERROR -> {
                            progress_bar_tv_show.visibility = View.GONE
                            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

            })
        }
    }

    private fun getItemClicked(item: TvEntity) {
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra("tvId", item.id)
        startActivity(intent)
    }
}