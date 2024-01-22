package com.sto_opka91.militarydirectory.ui.home


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
class HomeViewModel @Inject constructor(private val itemInteractor: ItemInteractor) : ViewModel() {

    private val _itemsKat = MutableLiveData<List<String>>()
    val itemsKat: LiveData<List<String>> get() = _itemsKat

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

    private val _resultOfPassing: MutableLiveData<Int> = MutableLiveData(0)
    val resultOfPassing: LiveData<Int> get() = _resultOfPassing

    private val _resultDescribe: MutableLiveData<String> = MutableLiveData("")
    val resultDescribe: LiveData<String> get() = _resultDescribe



    private val listFlagOfLessons: MutableList<Boolean> = MutableList(4) { false }
    private val listResultOfLessons: MutableList<Float?> = MutableList(4) { null }
    private val listNamesOfLessons: MutableList<String?> = MutableList(4) { null }

    private fun setDescribe(describe: String) {
        _resultDescribe.value = describe
    }

    private fun setResultOfPassing(result: Int) {
        _resultOfPassing.value = result
    }

    fun setNameOfLessons(index: Int, value: String?) {
        listNamesOfLessons[index] = value
    }
    fun clearAllNamesOfLessons(){
        listNamesOfLessons[0] = null
        listNamesOfLessons[1] = null
        listNamesOfLessons[2] = null
        listNamesOfLessons[3] = null
    }

    fun clearAllResults(){
        listResultOfLessons[0] = null
        listResultOfLessons[1] = null
        listResultOfLessons[2] = null
        listResultOfLessons[3] = null
    }
    fun setResultOfLessons(index: Int, value: Float?) {
        listResultOfLessons[index] = value
    }


    fun setFlagOfLessons(index: Int, value: Boolean) {
        listFlagOfLessons[index] = value
    }


    fun getFlagOfLessons(index: Int): Boolean {
        return listFlagOfLessons[index]
    }

    fun loadItemsKat() {
        _itemsKat.value = itemInteractor.getItemsKat()
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

    fun loadItemsGenre() {
        _itemsGenre.value = itemInteractor.getItemsGenre()
    }

    fun getResult(age: Int, genre: String, weight: Int, kategory: String) {
        val resultLessons = ArrayList<LessonsIdFP2023>()
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                itemInteractor.getAllLessonsId()
            }
            resultLessons.addAll(result)

            val ageToPrim = UtilFp2023.getAgeToInt(age)

            val lessonId1 = UtilFp2023.getLessonId(listNamesOfLessons[0], resultLessons, ageToPrim, weight)
            val lessonId2 = UtilFp2023.getLessonId(listNamesOfLessons[1], resultLessons, ageToPrim, weight)
            val lessonId3 = UtilFp2023.getLessonId(listNamesOfLessons[2], resultLessons, ageToPrim, weight)
            val lessonId4 = UtilFp2023.getLessonId(listNamesOfLessons[3], resultLessons, ageToPrim, weight)
            val listBool = UtilFp2023.setArrayBoolResult(lessonId1, lessonId2, lessonId3, lessonId4)

            val listResults1 = withContext(Dispatchers.IO) {
                itemInteractor.getResultsById(lessonId1)
            }
            val listResults2 = withContext(Dispatchers.IO) {
                itemInteractor.getResultsById(lessonId2)
            }
            val listResults3 = withContext(Dispatchers.IO) {
                itemInteractor.getResultsById(lessonId3)
            }
            val listResults4 = withContext(Dispatchers.IO) {
                itemInteractor.getResultsById(lessonId4)
            }

            val ballsForLesson1 = UtilFp2023.getBallByResult(listResults1, listResultOfLessons[0]!!)
            val ballsForLesson2 = UtilFp2023.getBallByResult(listResults2, listResultOfLessons[1]!!)
            val ballsForLesson3 = UtilFp2023.getBallByResult(listResults3, listResultOfLessons[2]!!)
            val ballsForLesson4 = UtilFp2023.getBallByResult(listResults4, listResultOfLessons[3]!!)
            setResultOfPassing(ballsForLesson1+ballsForLesson2+ballsForLesson3+ballsForLesson4)
            val listResult = listOf(ballsForLesson1, ballsForLesson2, ballsForLesson3, ballsForLesson4)

            val describe = itemInteractor.getDescribe(age, genre, kategory, resultOfPassing.value!!, listBool, listResult)
            setDescribe(describe)

        }
    }
}