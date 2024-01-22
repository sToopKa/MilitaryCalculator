package com.sto_opka91.militarydirectory.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "balls")
data class BallsFP2023(
    @PrimaryKey(autoGenerate = false)
    val id: Int? = null,
    @ColumnInfo(typeAffinity = ColumnInfo.TEXT)
    val genre: String? = null,

    val age: Int? = null,
    @ColumnInfo(typeAffinity = ColumnInfo.TEXT)
    val kategory: String? = null,

    val min_balls: Int? = null,

    val high_level: Int? = null,

    val first_level: Int? = null,

    val second_level: Int? = null,
    val five: Int? = null,
    val four: Int? = null,
    val three: Int? = null
)
