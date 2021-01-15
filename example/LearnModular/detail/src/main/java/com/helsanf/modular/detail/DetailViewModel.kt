package com.helsanf.modular.detail


import androidx.lifecycle.ViewModel
import androidx.lifecycle.*
import com.helsanf.modular.core.domain.model.SportBall
import com.helsanf.modular.core.domain.usecase.SportUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn

import kotlinx.coroutines.launch

import javax.inject.Inject

class DetailViewModel @Inject constructor(private val sportUseCase: SportUseCase) : ViewModel() {
     private var _detailLiveData : MutableLiveData<SportBall> = MutableLiveData()
     val detailLiveData : LiveData<SportBall> get() = _detailLiveData
     fun updateTeam(sportBall: SportBall,stateFav : Boolean) = viewModelScope.launch(Dispatchers.IO) {
          sportUseCase.updateFavoriteTeam(sportBall,stateFav)
     }

     fun fetchDetailTeam(idTeam: String) = viewModelScope.launch{
          sportUseCase.getDetailTeam(idTeam).flowOn(Dispatchers.IO).collect {
               _detailLiveData.postValue(it)
          }
     }
}