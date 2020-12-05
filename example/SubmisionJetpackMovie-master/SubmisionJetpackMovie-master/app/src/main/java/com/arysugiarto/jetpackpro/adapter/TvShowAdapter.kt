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
import com.arysugiarto.jetpackpro.data.repo.local.entity.TvEntity

class TvShowAdapter(
    private val context: Context,
    private val items: List<TvEntity>?,
    private val listener: (TvEntity) -> Unit
) : RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        return TvShowViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_list,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = items!!.size

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        holder.bind(items!![position], listener)
    }

    class TvShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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