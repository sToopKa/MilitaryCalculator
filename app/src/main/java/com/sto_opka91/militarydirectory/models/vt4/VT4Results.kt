package com.sto_opka91.militarydirectory.models.vt4

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vt4_results")
data class VT4Results(
    @PrimaryKey(autoGenerate = false)
    val id: Int? = null,
    val lesson_id: Int? = null,
    @ColumnInfo(typeAffinity = ColumnInfo.TEXT)
    val results: String? = null,
    val balls: Int? = null
)
