package com.example.wefit.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vezbe")
data class Vezba(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val naziv: String,
    val opis: String,
    val broj_setova: Int,
    val broj_ponavljanja: Int,
    val slika_url: String = ""
)