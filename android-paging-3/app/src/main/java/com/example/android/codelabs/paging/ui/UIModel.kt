package com.example.android.codelabs.paging.ui

import com.example.android.codelabs.paging.model.Repo

sealed class UIModel {
    data class RepoItem(val repo: Repo) : UIModel()
    data class SparatorItem(val descriptiong : String): UIModel()
}