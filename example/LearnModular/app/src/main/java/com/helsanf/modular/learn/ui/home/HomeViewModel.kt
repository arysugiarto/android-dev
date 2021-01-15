package com.helsanf.modular.learn.ui.home

import androidx.lifecycle.*
import com.helsanf.modular.core.domain.usecase.SportUseCase
import javax.inject.Inject


class HomeViewModel @Inject constructor(sportUseCase: SportUseCase): ViewModel() {
    val fetchSportData = sportUseCase.getAllTeam().asLiveData()
}