package com.example.wefit.repository

import com.example.wefit.database.TreningDao
import com.example.wefit.model.Trening
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

class TreningRepository(private val treningDao: TreningDao) {

    fun svi_treninzi(): Flow<List<Trening>> = treningDao.svi_treninzi()

    fun treninciNaDan(datum: LocalDate): Flow<List<Trening>> =
        treningDao.treninciNaDan(datum)

    suspend fun ubaciTrening(trening: Trening) {
        treningDao.ubaciTrening(trening)
    }

    suspend fun azurirajTrening(trening: Trening) {
        treningDao.azurirajTrening(trening)
    }

    suspend fun obrisiTrening(trening: Trening) {
        treningDao.obrisiTrening(trening)
    }
}