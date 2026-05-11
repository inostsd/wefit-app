package com.example.wefit.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wefit.model.Vezba
import com.example.wefit.repository.VezbaRepository
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class VezbaViewModel(private val repository: VezbaRepository) : ViewModel() {

    val sve_vezbe: StateFlow<List<Vezba>> = repository.sve_vezbe()
        .stateIn(viewModelScope, kotlinx.coroutines.flow.SharingStarted.WhileSubscribed(5000), emptyList())

    fun ubaciVezbu(vezba: Vezba) {
        viewModelScope.launch {
            repository.ubaciVezbu(vezba)
        }
    }

    fun azurirajVezbu(vezba: Vezba) {
        viewModelScope.launch {
            repository.azurirajVezbu(vezba)
        }
    }

    fun obrisiVezbu(vezba: Vezba) {
        viewModelScope.launch {
            repository.obrisiVezbu(vezba)
        }
    }
}