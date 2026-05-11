package com.example.wefit.database

import androidx.room.*
import com.example.wefit.model.TreningVezba
import kotlinx.coroutines.flow.Flow

@Dao
interface TreningVezbaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun ubaciTreningVezbu(treningVezba: TreningVezba)

    @Update
    suspend fun azurirajTreningVezbu(treningVezba: TreningVezba)

    @Delete
    suspend fun obrisiTreningVezbu(treningVezba: TreningVezba)

    @Query("SELECT * FROM trening_vezbe WHERE trening_id = :treningId")
    fun vezbeZaTrening(treningId: Int): Flow<List<TreningVezba>>
}