package com.sto_opka91.militarydirectory.models.vt4

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vt4_lessons")
data class VT4Lessons(
    @PrimaryKey(autoGenerate = false)
    val id: Int? = null,

    val lesson: String? = null
)
