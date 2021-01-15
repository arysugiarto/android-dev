package com.arysugiarto.latihan.stateflow_latihan1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.sql.Types

class ApiUserAdapter (private val user: ArrayList<ApiUser>):
    RecyclerView.Adapter<ApiUserAdapter.DataViewHolder>() {

    class DataViewHolder (itemview: View) : RecyclerView.ViewHolder(itemview) {
        fun bind(user: ApiUser) {
            itemView.textUsername.text = user.name
            itemView.textViewUserEmail.text = user.name
            Glide.with(itemView.imageViewAvatar.context)
                .load(user.avatar)
                .into(itemView.imageViewAvatar)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewTypes: Int): ApiUserAdapter.DataViewHolder {
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout, parent,false
            )
        )



    override fun onBindViewHolder(holder: ApiUserAdapter.DataViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {

    }
}