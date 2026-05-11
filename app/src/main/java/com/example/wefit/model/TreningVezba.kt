package com.example.wefit.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "trening_vezbe",
    foreignKeys = [
        ForeignKey(entity = Trening::class, parentColumns = ["id"], childColumns = ["trening_id"]),
        ForeignKey(entity = Vezba::class, parentColumns = ["id"], childColumns = ["vezba_id"])
    ]
)
data class TreningVezba(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val trening_id: Int,
    val vezba_id: Int,
    val izvedeni_setovi: Int,
    val izvedena_ponavljanja: Int
)