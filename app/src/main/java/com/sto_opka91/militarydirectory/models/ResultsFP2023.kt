package com.sto_opka91.militarydirectory.models

import androidx.room.ColumnInfo
import androidx.room.Entity

import androidx.room.PrimaryKey

@Entity(tableName = "results")
data class ResultsFP2023(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val lesson_id: Int? = null,
    @ColumnInfo(typeAffinity = ColumnInfo.TEXT)
    val result: String? = null,
    val balls: Int? = null
)
