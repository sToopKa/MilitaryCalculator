package com.sto_opka91.militarydirectory.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "lesson_id")
data class LessonsIdFP2023(
    @PrimaryKey(autoGenerate = false)
    val id: Int? = null,

    val lesson: String? = null,

    val prim: Int? = null
)