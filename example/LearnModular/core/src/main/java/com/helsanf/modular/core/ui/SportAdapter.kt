package com.helsanf.modular.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.helsanf.modular.core.R
import com.helsanf.modular.core.databinding.ItemListSportBinding
import com.helsanf.modular.core.domain.model.SportBall

class SportAdapter : RecyclerView.Adapter<SportAdapter.HomeViewHolder>() {

    var items: List<SportBall>
        get() = differ.currentList
        set(value) = differ.submitList(value)
    var onItemClick: ((SportBall) -> Unit)? = null


    inner class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListSportBinding.bind(itemView)

        fun bind(item: SportBall) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(item.logoTeam)
                    .into(binding.imgLogo)
                tvTitleClub.text = item.titleTeam
            }
            binding.root.setOnClickListener {
                onItemClick?.invoke(item)
            }
        }

    }
    fun setOnItemClickListener(listener: (SportBall) -> Unit) {
        onItemClick = listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_sport, parent, false)
        )
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemId(position: Int): Long {
        val sportBall: SportBall = differ.currentList[position]
        return sportBall.idTeam.toLong()
    }

    private val differCallBack = object : DiffUtil.ItemCallback<SportBall>() {
        override fun areItemsTheSame(
            oldModel: SportBall,
            newModel: SportBall
        ) = oldModel.idTeam == newModel.idTeam

        override fun areContentsTheSame(
            oldModel: SportBall,
            newModel: SportBall
        ) = oldModel == newModel
    }
    private val differ = AsyncListDiffer(this, differCallBack)

}