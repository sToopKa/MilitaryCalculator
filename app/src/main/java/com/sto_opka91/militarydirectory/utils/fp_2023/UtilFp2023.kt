package com.sto_opka91.militarydirectory.utils.fp_2023

import android.widget.EditText
import com.sto_opka91.militarydirectory.LIST_RESULT_DECIMAL
import com.sto_opka91.militarydirectory.LIST_RESULT_WEIGHT
import com.sto_opka91.militarydirectory.models.BallsFP2023
import com.sto_opka91.militarydirectory.models.LessonsIdFP2023
import com.sto_opka91.militarydirectory.models.ResultsFP2023
import com.sto_opka91.militarydirectory.models.vt4.VT4Lessons
import com.sto_opka91.militarydirectory.models.vt4.VT4Results


object UtilFp2023 {

    fun extractExerciseName(input: String): String {
        val exerciseNumber = "\\d+".toRegex().find(input)?.value ?: ""
        return input.substringAfter("Упр $exerciseNumber ", "").trim()
    }

    fun setArrayBoolResult(val1: Int, val2: Int, val3: Int, val4: Int):List<Boolean>{
        val listBool = ArrayList<Boolean>()
        if(val1==0)
            listBool.add(false)
        else
            listBool.add(true)
        if(val2==0)
            listBool.add(false)
        else
            listBool.add(true)
        if(val3==0)
            listBool.add(false)
        else
            listBool.add(true)
        if(val4==0)
            listBool.add(false)
        else
            listBool.add(true)
        return listBool
    }

    fun setArrayBoolResult(val1: Int, val2: Int, val3: Int, val4: Int, val5: Int):List<Boolean>{
        val listBool = ArrayList<Boolean>()
        if(val1==0)
            listBool.add(false)
        else
            listBool.add(true)
        if(val2==0)
            listBool.add(false)
        else
            listBool.add(true)
        if(val3==0)
            listBool.add(false)
        else
            listBool.add(true)
        if(val4==0)
            listBool.add(false)
        else
            listBool.add(true)
        if(val5==0)
            listBool.add(false)
        else
            listBool.add(true)

        return listBool
    }

    fun getLessonsIdForVT4(lesson: String?, list: List<VT4Lessons>): Int{
            var id = 10
            for(i in 0 until list.size){
                if(lesson == list[i].lesson)
                    id = list[i].id!!
            }
            return id
    }
    fun getLessonId(lesson: String?, list: List<LessonsIdFP2023>, age: Int, weight: Int): Int{
        return if(lesson == null){
            0
        }else{
            val listId = ArrayList<LessonsIdFP2023>()
            for(i in 0 until list.size){
                if(lesson == list[i].lesson)
                    listId.add(list[i])
            }
            when(listId.size) {
                 1 -> listId[0].id!!
                 2 -> getLessonAge(listId, age)
                 3 -> getLessonWeight(listId, weight)
                 else -> 0
            }

        }
    }
    private fun getLessonAge(list: List<LessonsIdFP2023>,age: Int): Int{
       for(i in 0 until list.size){
           if(age== list[i].prim)
           {
               return list[i].id!!
           }
       }
        return 0
    }

    private fun getLessonWeight(list: List<LessonsIdFP2023>,weight: Int): Int{
        for(i in 0 until list.size){
            if(weight== list[i].prim)
            {
                return list[i].id!!
            }
        }
        return 0
    }

    fun getBallByResult(list: List<ResultsFP2023>, result: Float): Int {
        if(list.size!=0 ){
            if(result.toInt() != 0 ){
                // если результаты уменьшаются
                if(list[0].result!!.replace(",", ".").toFloat() > list[1].result!!.replace(",", ".").toFloat()){
                    if(result>=list[0].result!!.replace(",", ".").toFloat())
                        return 100
                    else if(result<list.last().result!!.replace(",", ".").toFloat())
                        return 0
                    else{
                        for(i in 1 until list.size){
                            if(result>=list[i].result!!.replace(",", ".").toFloat()){
                                return list[i].balls!!
                            }
                        }
                        return 0
                    }
                }else{ // если результаты увеличиваются
                    if(result<=list[0].result!!.replace(",", ".").toFloat())
                        return 100
                    else if(result>list.last().result!!.replace(",", ".").toFloat())
                        return 0
                    else{
                        for(i in 1 until list.size){
                            if(result<=list[i].result!!.replace(",", ".").toFloat()){
                                return list[i].balls!!
                            }
                        }
                        return 0
                    }
                }
            }else{
                return 0
            }
        }else{
            return 0
        }

    }

    fun getBallByResultVT4(list: List<VT4Results>, result: Float): Int {
        if(list.size!=0 ){
            if(result.toInt() != 0 ){
                // если результаты уменьшаются
                if(list[0].results!!.replace(",", ".").toFloat() > list[1].results!!.replace(",", ".").toFloat()){
                    if(result>=list[0].results!!.replace(",", ".").toFloat())
                        return 100
                    else if(result<list.last().results!!.replace(",", ".").toFloat())
                        return 0
                    else{
                        for(i in 1 until list.size){
                            if(result>=list[i].results!!.replace(",", ".").toFloat()){
                                return list[i].balls!!
                            }
                        }
                        return 0
                    }
                }else{ // если результаты увеличиваются
                    if(result<=list[0].results!!.replace(",", ".").toFloat())
                        return 100
                    else if(result>list.last().results!!.replace(",", ".").toFloat())
                        return 0
                    else{
                        for(i in 1 until list.size){
                            if(result<=list[i].results!!.replace(",", ".").toFloat()){
                                return list[i].balls!!
                            }
                        }
                        return 0
                    }
                }
            }else{
                return 0
            }
        }else{
            return 0
        }

    }

    fun getResultBySekAndMin(min: EditText, sek: EditText) : Float{
        val minText = min.text?.toString() ?: ""
        val cekText = sek.text?.toString() ?: ""
        val minFloat = if (minText.isNotEmpty()) minText.toFloat() else 0.0f
        val cekFloat = if (cekText.isNotEmpty()) cekText.toFloat() else 0.0f
       return minFloat * 60 + cekFloat
    }
    fun getFlagForTipOfLessons(lesson :String):Boolean{
        var flag = false
        for(i in 0 until LIST_RESULT_DECIMAL.size){
            if(lesson == LIST_RESULT_DECIMAL[i])
            {
                flag = true
                break
            }
        }
        return flag
    }
    fun getFlagForWeight(lesson :String):Boolean{
        var flag = false
        for(i in 0 until LIST_RESULT_WEIGHT.size){
            if(lesson == LIST_RESULT_WEIGHT[i])
            {
                flag = true
                break
            }
        }
        return flag
    }
    fun getResultbyOneCell(value: String?): Float {
        return value?.toFloatOrNull() ?: 0.0f
    }
    fun getWeight(weight: String, genre: String):Int{
        if(weight==""){
            return 0
        }else{
            val weightToInt = weight.toFloat().toInt()
            if(genre == "женский"){
                when {
                    weightToInt < 60 -> return 60
                    weightToInt in 61..70 ->return 70
                    weightToInt > 70 -> return 80
                }
            }else{
                when {
                    weightToInt < 70 -> return 60
                    weightToInt in 71..80 ->return 70
                    weightToInt > 80 -> return 80
                }

            }
        }
        return 0
    }
    fun getAgeToInt(age: Int):Int{
        return if(age>=35)
            35
        else
            0
    }
    fun getAgeForBalls(age:Int, genre: String): Int{
        if(genre=="мужской"){
            return when{
                age<25 -> 24
                age in 25..29 -> 29
                age in 30..34 -> 34
                age in 35..39 -> 39
                age in 40..44 -> 44
                age in 45..49 -> 49
                else -> 54
            }
        }else{
            return when{
                age<25 -> 24
                age in 25..29 -> 29
                age in 30..34 -> 34
                age in 35..39 -> 39
                age in 40..44 -> 44
                age in 45..49 -> 49
                else -> 50
            }
        }
    }
    fun getAnswers(list: BallsFP2023, result: Int, answers: List<String>): String {
      return  when{
            result>= list.high_level!! -> answers[0]
            result in list.first_level!! until list.high_level -> answers[1]
            result in list.second_level!! until list.first_level -> answers[2]
            result in list.five!! until list.second_level -> answers[3]
            result in list.four!! until list.five -> answers[4]
            result in list.three!! until list.four -> answers[5]
            else -> answers[6]
      }
    }
}