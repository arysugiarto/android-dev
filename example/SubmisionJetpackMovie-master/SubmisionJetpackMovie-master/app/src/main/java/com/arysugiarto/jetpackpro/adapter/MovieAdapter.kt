package com.arysugiarto.jetpackpro.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arysugiarto.jetpackpro.BuildConfig
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_list.view.*
import com.arysugiarto.jetpackpro.R
import com.arysugiarto.jetpackpro.data.repo.local.entity.MovieEntity

class MovieAdapter(
    private val context: Context,
    private val items: List<MovieEntity>?,
    private val listener: (MovieEntity) -> Unit
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_list,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = items!!.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(items!![position], listener)
    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: MovieEntity, listener: (MovieEntity) -> Unit) {
            with(itemView) {
                Glide.with(context)
                    .load(BuildConfig.IMG_URL + movie.poster_path)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(img_cover_item)

                setOnClickListener { listener(movie) }
            }
        }
    }
}