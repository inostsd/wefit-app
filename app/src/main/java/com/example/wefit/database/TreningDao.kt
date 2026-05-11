package com.example.wefit.database

import androidx.room.*
import com.example.wefit.model.Trening
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

@Dao
interface TreningDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun ubaciTrening(trening: Trening)

    @Update
    suspend fun azurirajTrening(trening: Trening)

    @Delete
    suspend fun obrisiTrening(trening: Trening)

    @Query("SELECT * FROM treninzi ORDER BY datum DESC")
    fun svi_treninzi(): Flow<List<Trening>>

    @Query("SELECT * FROM treninzi WHERE datum = :datum")
    fun treninciNaDan(datum: LocalDate): Flow<List<Trening>>
}