package com.example.wefit.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "treninzi")
data class Trening(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val naziv: String,
    val datum: LocalDate,
    val trajanje_minuta: Int,
    val calos: Int = 0
)