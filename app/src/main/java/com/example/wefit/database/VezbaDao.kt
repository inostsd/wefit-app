package com.example.wefit.database

import androidx.room.*
import com.example.wefit.model.Vezba
import kotlinx.coroutines.flow.Flow

@Dao
interface VezbaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun ubaciVezbu(vezba: Vezba)

    @Update
    suspend fun azurirajVezbu(vezba: Vezba)

    @Delete
    suspend fun obrisiVezbu(vezba: Vezba)

    @Query("SELECT * FROM vezbe")
    fun sve_vezbe(): Flow<List<Vezba>>

    @Query("SELECT * FROM vezbe WHERE id = :id")
    fun vezbaPoId(id: Int): Flow<Vezba>
}