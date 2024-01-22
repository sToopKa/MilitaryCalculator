package com.sto_opka91.militarydirectory.database.vt4

import androidx.room.Dao
import androidx.room.Query
import com.sto_opka91.militarydirectory.models.ResultsFP2023
import com.sto_opka91.militarydirectory.models.vt4.VT4Lessons
import com.sto_opka91.militarydirectory.models.vt4.VT4Results

@Dao
interface DaoVT4 {
    @Query("SELECT * FROM vt4_lessons")
    fun getAllFromLessonsIdVT4(): List<VT4Lessons>
    @Query("SELECT * FROM vt4_results WHERE lesson_id = :id")
    fun getResultsByIdVT4(id: Int): List<VT4Results>
}