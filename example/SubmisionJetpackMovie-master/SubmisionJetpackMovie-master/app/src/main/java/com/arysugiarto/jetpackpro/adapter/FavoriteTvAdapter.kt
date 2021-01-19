package com.arysugiarto.jetpackpro.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.arysugiarto.jetpackpro.BuildConfig
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_list.view.*
import com.arysugiarto.jetpackpro.R
import com.arysugiarto.jetpackpro.data.repo.local.entity.TvEntity

class FavoriteTvAdapter internal constructor(
    private val listener: (TvEntity) -> Unit
) :
    PagedListAdapter<TvEntity, FavoriteTvAdapter.FavoriteTvShowViewHolder>(
        DIFF_CALLBACK
    ) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvEntity>() {
            override fun areItemsTheSame(oldItem: TvEntity, newItem: TvEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TvEntity, newItem: TvEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteTvShowViewHolder {
        return FavoriteTvShowViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_list,
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holderFavorite: FavoriteTvShowViewHolder, position: Int) {
        val tvShow = getItem(position)
        if (tvShow != null) {
            holderFavorite.bind(tvShow, listener)
        }
    }

    class FavoriteTvShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(tv: TvEntity, listener: (TvEntity) -> Unit) {
            with(itemView) {
                Glide.with(context)
                    .load(BuildConfig.IMG_URL + tv.poster_path)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(img_cover_item)

                setOnClickListener { listener(tv) }
            }
        }
    }
}