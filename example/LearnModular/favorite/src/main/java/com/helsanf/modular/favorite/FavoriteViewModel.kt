package com.helsanf.modular.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.helsanf.modular.core.domain.model.SportBall
import com.helsanf.modular.core.domain.usecase.SportUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(private val sportUseCase: SportUseCase) : ViewModel() {
    private val _fetchFavoriteTeam : MutableLiveData<List<SportBall>> = MutableLiveData()
    val fetchFavoriteTeam : LiveData<List<SportBall>> get() = _fetchFavoriteTeam

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text

    fun fetchFavoriteTeam() = viewModelScope.launch(Dispatchers.Main){
        sportUseCase.getAllTeamFavorite().flowOn(Dispatchers.IO).collect {
            _fetchFavoriteTeam.postValue(it)
        }
    }
}