package com.example.wefit.repository

import com.example.wefit.database.TreningVezbaDao
import com.example.wefit.model.TreningVezba
import kotlinx.coroutines.flow.Flow

class TreningVezbaRepository(private val treningVezbaDao: TreningVezbaDao) {

    fun vezbeZaTrening(treningId: Int): Flow<List<TreningVezba>> =
        treningVezbaDao.vezbeZaTrening(treningId)

    suspend fun ubaciTreningVezbu(treningVezba: TreningVezba) {
        treningVezbaDao.ubaciTreningVezbu(treningVezba)
    }

    suspend fun azurirajTreningVezbu(treningVezba: TreningVezba) {
        treningVezbaDao.azurirajTreningVezbu(treningVezba)
    }

    suspend fun obrisiTreningVezbu(treningVezba: TreningVezba) {
        treningVezbaDao.obrisiTreningVezbu(treningVezba)
    }
}