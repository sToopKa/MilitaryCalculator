package com.sto_opka91.militarydirectory.domain

import com.sto_opka91.militarydirectory.data.repository_vt4.ItemVT4Repository
import com.sto_opka91.militarydirectory.database.vt4.DaoVT4
import com.sto_opka91.militarydirectory.models.vt4.VT4Lessons
import com.sto_opka91.militarydirectory.models.vt4.VT4Results
import javax.inject.Inject

class ItemVT4Interactor @Inject constructor (
    private val repository: ItemVT4Repository,
    private val dbRepository : DaoVT4) {

    fun getItemsLesson1VT4(): List<String> {
        return repository.getItemsLesson1VT4()
    }
    fun getItemsLesson2VT4(): List<String> {
        return repository.getItemsLesson2VT4()
    }
    fun getItemsLesson3VT4(): List<String> {
        return repository.getItemsLesson3VT4()
    }


    fun getIdLessonsVT4(): List<VT4Lessons> {
        return dbRepository.getAllFromLessonsIdVT4()
    }
    fun getResultsByIdVT4(lessonId: Int): List<VT4Results> {
        return dbRepository.getResultsByIdVT4(lessonId)
    }

    fun getDescribe(result: Int):String{
        val listDescribe =  repository.getItemsDescribeVT4()
        return when{
            result >=2900 -> listDescribe[0]
            result in 2300..2899 -> listDescribe[1]
            result in 1800..2299 -> listDescribe[2]
            result in 1400..1799 -> listDescribe[3]
            else -> listDescribe[4]
        }
    }

}