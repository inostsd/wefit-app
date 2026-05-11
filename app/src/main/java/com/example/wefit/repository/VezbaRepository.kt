package com.example.wefit.repository

import com.example.wefit.database.VezbaDao
import com.example.wefit.model.Vezba
import kotlinx.coroutines.flow.Flow

class VezbaRepository(private val vezbaDao: VezbaDao) {

    fun sve_vezbe(): Flow<List<Vezba>> = vezbaDao.sve_vezbe()

    fun vezbaPoId(id: Int): Flow<Vezba> = vezbaDao.vezbaPoId(id)

    suspend fun ubaciVezbu(vezba: Vezba) {
        vezbaDao.ubaciVezbu(vezba)
    }

    suspend fun azurirajVezbu(vezba: Vezba) {
        vezbaDao.azurirajVezbu(vezba)
    }

    suspend fun obrisiVezbu(vezba: Vezba) {
        vezbaDao.obrisiVezbu(vezba)
    }
}