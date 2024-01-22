package com.sto_opka91.militarydirectory.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sto_opka91.militarydirectory.models.BallsFP2023
import com.sto_opka91.militarydirectory.models.LessonsIdFP2023
import com.sto_opka91.militarydirectory.models.ResultsFP2023

@Dao
interface DaoFP_2023_db {


    @Query("SELECT * FROM lesson_id")
    fun getAllFromLessonsId(): List<LessonsIdFP2023>

    @Query("SELECT * FROM results WHERE lesson_id = :id")
    fun getResultsByid(id: Int): List<ResultsFP2023>

    @Query("SELECT * FROM balls WHERE age = :age AND genre = :genre AND kategory = :kategory")
    fun getListBalls(age: Int, genre: String, kategory: String): List<BallsFP2023>
}