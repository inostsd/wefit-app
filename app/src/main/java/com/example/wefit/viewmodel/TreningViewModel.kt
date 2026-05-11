package com.example.wefit.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wefit.model.Trening
import com.example.wefit.repository.TreningRepository
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate

class TreningViewModel(private val repository: TreningRepository) : ViewModel() {

    val svi_treninzi: StateFlow<List<Trening>> = repository.svi_treninzi()
        .stateIn(viewModelScope, kotlinx.coroutines.flow.SharingStarted.WhileSubscribed(5000), emptyList())

    fun ubaciTrening(trening: Trening) {
        viewModelScope.launch {
            repository.ubaciTrening(trening)
        }
    }

    fun azurirajTrening(trening: Trening) {
        viewModelScope.launch {
            repository.azurirajTrening(trening)
        }
    }

    fun obrisiTrening(trening: Trening) {
        viewModelScope.launch {
            repository.obrisiTrening(trening)
        }
    }

    fun treninciNaDan(datum: LocalDate): StateFlow<List<Trening>> =
        repository.treninciNaDan(datum)
            .stateIn(viewModelScope, kotlinx.coroutines.flow.SharingStarted.WhileSubscribed(5000), emptyList())
}