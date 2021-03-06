/*
 * Created by Muhammad Utsman on 8/18/19 3:55 AM
 * Copyright (c) 2019 . All rights reserved.
 * Last modified 8/18/19 3:55 AM
 */

package com.utsman.recycling.extentions

import android.content.Context
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.utsman.recycling.adapter.RecyclingAdapter
import com.utsman.recycling.javabuilder.BindBuilder
import com.utsman.recycling.listener.EndlessScrollListener

@Suppress("UNCHECKED_CAST")
class Recycling<T>(layout: Int, val recyclerView: RecyclerView) {
    internal val adapter = RecyclingAdapter<T>(layout)
    internal val context: Context = recyclerView.context

    fun getList(): List<T?>? = adapter.getCurrentList()

    fun setLayoutManager(layoutManager: RecyclerView.LayoutManager)  {
        recyclerView.layoutManager = layoutManager
    }

    fun submitList(list: List<T?>?) {
        Log.i("utsman", "start submit list")
        adapter.addList(list)
    }

    fun submitNetworkState(networkState: NetworkState?) {
        adapter.submitNetwork(networkState)
    }

    fun fixGridSpan(column: Int) {
        (recyclerView.layoutManager as GridLayoutManager).spanSizeLookup = adapter.setGridSpan(column)
    }

    @Suppress("UNCHECKED_CAST")
    fun submitItem(item: T?) {
        adapter.addItem(item)
    }

    fun onPagingListener(layoutManager: LinearLayoutManager, onPaging: EndlessScrollListener.(page: Int, itemCount: Int) -> Unit) {
        recyclerView.addOnScrollListener(object : EndlessScrollListener(layoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                onPaging(this, page, totalItemsCount)
            }
        })
    }

    fun onPagingListener(layoutManager: GridLayoutManager, onPaging: EndlessScrollListener.(page: Int, itemCount: Int) -> Unit) {
        recyclerView.addOnScrollListener(object : EndlessScrollListener(layoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                onPaging(this, page, totalItemsCount)
            }
        })
    }

    fun onPagingListener(layoutManager: StaggeredGridLayoutManager, onPaging: EndlessScrollListener.(page: Int, itemCount: Int) -> Unit) {
        recyclerView.addOnScrollListener(object : EndlessScrollListener(layoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                onPaging(this, page, totalItemsCount)
            }
        })
    }

    // fun building(recycling: Builder): RecyclingBuilder<T> {
    //        val setupAdapter = Recycling<T>(layout!!, recyclerView!!)
    //        recycling(setupAdapter, setupAdapter.adapter, setupAdapter.context,
    //            setupAdapter.getList() as MutableList<out T>
    //        )
    //
    //        return this.building(recycling)
    //    }

    fun bind(bind: Binding<T>.(itemView: View, position: Int, item: T?) -> Unit) {
        adapter.setBinding(bind as Binding<*>.(view: View, position: Int, item: Any?) -> Unit)
    }

    fun bindJava(bind: BindBuilder<*>) {
        adapter.setBinding(bind as Binding<*>.(view: View, position: Int, item: Any?) -> Unit)
        //adapter.setBinding(bind as BindBuilder<*>.(view: View, position: Int, item: Any?) -> Unit)
    }

    /*fun bindJava(bind: BindBuilder<T>) {
        //adapter.setBinding(bind as Binding<*>.(view: View, position: Int, item: Any?) -> Unit)
        //adapter.setBinding(bind)


    }*/

    fun addLoader(layoutRes: Int, loader: LoaderIdentifierId.() -> Unit) {
        val loaderIdentifierId = LoaderIdentifierId(layoutRes = layoutRes)
        loader(loaderIdentifierId)
        adapter.addIdentifierId(loaderIdentifierId)
    }

    init {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
    }

    data class Builder<T>(internal var layout: Int? = null, internal var recyclerView: RecyclerView? = null) {
        fun setLayout(layout: Int) = apply { this.layout = layout }
        fun setRecyclerView(recyclerView: RecyclerView) = apply { this.recyclerView = recyclerView }

        fun build() = Recycling<T>(layout!!, recyclerView!!)
    }
}

/*
open class RecyclingBuilder {
    val layout: Int? = null
    private val recyclerView: RecyclerView? = null

    class Builder(private var layout: Int, private var recyclerView: RecyclerView) {
        fun setLayout(layout: Int) = apply { this.layout = layout }
        fun setRecyclerView(recyclerView: RecyclerView) = apply { this.recyclerView = recyclerView }

        fun build() = RecyclingBuilder
    }
}*/
