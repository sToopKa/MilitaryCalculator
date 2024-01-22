package com.sto_opka91.militarydirectory.database


import androidx.room.Database
import androidx.room.RoomDatabase
import com.sto_opka91.militarydirectory.models.BallsFP2023
import com.sto_opka91.militarydirectory.models.LessonsIdFP2023
import com.sto_opka91.militarydirectory.models.ResultsFP2023



@Database(entities = [LessonsIdFP2023::class, ResultsFP2023::class, BallsFP2023::class], version =5, exportSchema = false)
abstract class DatabaseFP2023 : RoomDatabase() {
    abstract fun daoFP_2023_db(): DaoFP_2023_db
}