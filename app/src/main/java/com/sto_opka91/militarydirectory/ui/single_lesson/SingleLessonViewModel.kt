package com.sto_opka91.militarydirectory.ui.single_lesson

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sto_opka91.militarydirectory.domain.ItemInteractor
import com.sto_opka91.militarydirectory.models.LessonsIdFP2023
import com.sto_opka91.militarydirectory.utils.fp_2023.UtilFp2023
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SingleLessonViewModel @Inject constructor(private val itemInteractor: ItemInteractor) : ViewModel() {

    private val _itemsLesson1 = MutableLiveData<List<String>>()
    val itemsLesson1: LiveData<List<String>> get() = _itemsLesson1

    private val _itemsLesson2 = MutableLiveData<List<String>>()
    val itemsLesson2: LiveData<List<String>> get() = _itemsLesson2

    private val _itemsLesson3 = MutableLiveData<List<String>>()
    val itemsLesson3: LiveData<List<String>> get() = _itemsLesson3

    private val _itemsLesson4 = MutableLiveData<List<String>>()
    val itemsLesson4: LiveData<List<String>> get() = _itemsLesson4

    private val _itemsGenre = MutableLiveData<List<String>>()
    val itemsGenre: LiveData<List<String>> get() = _itemsGenre

    private val _resultOflesson1: MutableLiveData<Int> = MutableLiveData(0)
    val resultOflesson1: LiveData<Int> get() = _resultOflesson1
    private val _resultOflesson2: MutableLiveData<Int> = MutableLiveData(0)
    val resultOflesson2: LiveData<Int> get() = _resultOflesson2
    private val _resultOflesson3: MutableLiveData<Int> = MutableLiveData(0)
    val resultOflesson3: LiveData<Int> get() = _resultOflesson3

    private fun setResultOfLesson1(result: Int) {
        _resultOflesson1.value = result
    }
    private fun setResultOfLesson2(result: Int) {
        _resultOflesson2.value = result
    }
    private fun setResultOfLesson3(result: Int) {
        _resultOflesson3.value = result
    }

    fun loadItemsGenre() {
        _itemsGenre.value = itemInteractor.getItemsGenre()
    }

    fun loadItemsLesson1() {
        _itemsLesson1.value = itemInteractor.getItemsLesson1()
    }

    fun loadItemsLesson2() {
        _itemsLesson2.value = itemInteractor.getItemsLesson2()
    }

    fun loadItemsLesson3() {
        _itemsLesson3.value = itemInteractor.getItemsLesson3()
    }

    fun loadItemsLesson4() {
        _itemsLesson4.value = itemInteractor.getItemsLesson4()
    }

    fun getResult(lesson: String, resultOflesson: Float, age: Int, weight: Int, flagOfLesson: Int){
        val resultLessons = ArrayList<LessonsIdFP2023>()
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                itemInteractor.getAllLessonsId()
            }
            resultLessons.addAll(result)

            val ageToPrim = UtilFp2023.getAgeToInt(age)
            val lessonId = UtilFp2023.getLessonId(lesson, resultLessons, ageToPrim, weight)
            val listResults = withContext(Dispatchers.IO) {
                itemInteractor.getResultsById(lessonId)
            }
            val ballsForLesson = UtilFp2023.getBallByResult(listResults,resultOflesson)
            when(flagOfLesson){
                1 -> setResultOfLesson1(ballsForLesson)
                2 -> setResultOfLesson2(ballsForLesson)
                3 -> setResultOfLesson3(ballsForLesson)
            }

        }
    }

}