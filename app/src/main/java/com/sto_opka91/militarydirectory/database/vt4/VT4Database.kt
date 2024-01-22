package com.sto_opka91.militarydirectory.database.vt4

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sto_opka91.militarydirectory.models.BallsFP2023
import com.sto_opka91.militarydirectory.models.LessonsIdFP2023
import com.sto_opka91.militarydirectory.models.ResultsFP2023
import com.sto_opka91.militarydirectory.models.vt4.VT4Lessons
import com.sto_opka91.militarydirectory.models.vt4.VT4Results

@Database(entities = [VT4Lessons::class, VT4Results::class, ], version = 4, exportSchema = true)
abstract class VT4Database: RoomDatabase() {
    abstract fun daoVT4(): DaoVT4
}