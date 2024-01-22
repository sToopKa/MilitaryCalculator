package com.sto_opka91.militarydirectory.domain

import com.sto_opka91.militarydirectory.data.repository_fp2023.ItemRepository
import com.sto_opka91.militarydirectory.database.DaoFP_2023_db
import com.sto_opka91.militarydirectory.models.BallsFP2023
import com.sto_opka91.militarydirectory.models.LessonsIdFP2023
import com.sto_opka91.militarydirectory.models.ResultsFP2023
import com.sto_opka91.militarydirectory.utils.fp_2023.UtilFp2023
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ItemInteractor @Inject constructor (
    private val repository: ItemRepository,
    private val dbRepository : DaoFP_2023_db) {

    fun getKurs(): List<String> {
        return repository.getKurs()
    }

    fun getItemsKat(): List<String> {
        return repository.getItemsKat()
    }
    fun getItemsLesson1(): List<String> {
        return repository.getItemsLesson1()
    }
    fun getItemsLesson2(): List<String> {
        return repository.getItemsLesson2()
    }
    fun getItemsLesson3(): List<String> {
        return repository.getItemsLesson3()
    }
    fun getItemsLesson4(): List<String> {
        return repository.getItemsLesson4()
    }
    fun getItemsGenre(): List<String> {
        return repository.getItemsGenre()
    }
    fun getAllLessonsId(): List<LessonsIdFP2023>{
       return dbRepository.getAllFromLessonsId()
    }
    fun getResultsById(id: Int): List<ResultsFP2023>{
        return dbRepository.getResultsByid(id)
    }
    suspend fun getDescribe(age: Int, genre: String, kategory: String, result: Int, listBool: List<Boolean>, listResult: List<Int>): String{
        val listDescribe =  repository.getItemsDescribeOfResult()
        val listBalls = ArrayList<BallsFP2023>()
        val ageForBalls = UtilFp2023.getAgeForBalls(age, genre)
        if(genre == "женский"){
             withContext(Dispatchers.IO) {
                listBalls.addAll(dbRepository.getListBalls(ageForBalls,genre,"нет"))
            }
        }else{
            withContext(Dispatchers.IO){
                listBalls.addAll(dbRepository.getListBalls(ageForBalls,genre,kategory))
            }
        }
        for(i in listBool.indices){
            if(listBool[i]){
                if(listResult[i] < listBalls[0].min_balls!!){
                    return listDescribe[7]
                }
            }
        }
        return UtilFp2023.getAnswers(listBalls[0], result, listDescribe)

    }
}