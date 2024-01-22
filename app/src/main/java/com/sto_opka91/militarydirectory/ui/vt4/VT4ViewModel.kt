package com.sto_opka91.militarydirectory.ui.vt4

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sto_opka91.militarydirectory.domain.ItemInteractor
import com.sto_opka91.militarydirectory.domain.ItemVT4Interactor
import com.sto_opka91.militarydirectory.models.LessonsIdFP2023
import com.sto_opka91.militarydirectory.models.vt4.VT4Lessons
import com.sto_opka91.militarydirectory.utils.fp_2023.UtilFp2023
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class VT4ViewModel @Inject constructor(private val itemVT4Interactor: ItemVT4Interactor) : ViewModel() {

    private val _itemsLesson1VT4 = MutableLiveData<List<String>>()
    val itemsLesson1VT4: LiveData<List<String>> get() = _itemsLesson1VT4

    private val _itemsLesson2VT4 = MutableLiveData<List<String>>()
    val itemsLesson2VT4: LiveData<List<String>> get() = _itemsLesson2VT4

    private val _itemsLesson3VT4 = MutableLiveData<List<String>>()
    val itemsLesson3VT4: LiveData<List<String>> get() = _itemsLesson3VT4

    fun loadItemsLesson1VT4() {
        _itemsLesson1VT4.value = itemVT4Interactor.getItemsLesson1VT4()
    }
    fun loadItemsLesson2VT4() {
        _itemsLesson2VT4.value = itemVT4Interactor.getItemsLesson2VT4()
    }
    fun loadItemsLesson3VT4() {
        _itemsLesson3VT4.value = itemVT4Interactor.getItemsLesson3VT4()
    }

    private val _resultOfPassing: MutableLiveData<Int> = MutableLiveData(0)
    val resultOfPassing: LiveData<Int> get() = _resultOfPassing

    private val _resultDescribe: MutableLiveData<String> = MutableLiveData("")
    val resultDescribe: LiveData<String> get() = _resultDescribe

    private val _listBallsOfLessonsVT4 = MutableLiveData<List<Int?>>()
    val listBallsOfLessonsVT4: LiveData<List<Int?>>
        get() = _listBallsOfLessonsVT4


    private fun setBallsOfLessonsVT4(index: Int, value: Int?) {
        val currentList: MutableList<Int?> = _listBallsOfLessonsVT4.value?.toMutableList() ?: MutableList(4) { null }
        currentList[index] = value
        _listBallsOfLessonsVT4.value = currentList.toList()
    }


    private fun setDescribe(describe: String) {
        _resultDescribe.value = describe
    }

    private fun setResultOfPassing(result: Int) {
        _resultOfPassing.value = result
    }

    private val listNamesOfLessons: MutableList<String?> = MutableList(3) { null }
    private val listResultOfLessons: MutableList<Float?> = MutableList(4) { null }

    fun clearAllResults(){
        listResultOfLessons[0] = null
        listResultOfLessons[1] = null
        listResultOfLessons[2] = null
        listResultOfLessons[3] = null
    }
    fun setResultOfLessons(index: Int, value: Float?) {
        listResultOfLessons[index] = value
    }

    fun setNameOfLessons(index: Int, value: String?) {
        listNamesOfLessons[index] = value
    }
    fun clearAllNamesOfLessons(){
        listNamesOfLessons[0] = null
        listNamesOfLessons[1] = null
        listNamesOfLessons[2] = null
    }

    fun getResult(flageGrenade: Boolean) {

        val resultLessons = ArrayList<VT4Lessons>()
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                itemVT4Interactor.getIdLessonsVT4()
            }
            resultLessons.addAll(result)
            val lessonId1 = UtilFp2023.getLessonsIdForVT4(listNamesOfLessons[0], resultLessons)
            val lessonId2 = UtilFp2023.getLessonsIdForVT4(listNamesOfLessons[1], resultLessons)
            val lessonId3 = UtilFp2023.getLessonsIdForVT4(listNamesOfLessons[2], resultLessons)

            val listResults1 = withContext(Dispatchers.IO) {
                itemVT4Interactor.getResultsByIdVT4(lessonId1)
            }
            val listResults2 = withContext(Dispatchers.IO) {
                itemVT4Interactor.getResultsByIdVT4(lessonId2)
            }
            val listResults3 = withContext(Dispatchers.IO) {
                itemVT4Interactor.getResultsByIdVT4(lessonId3)
            }
            val listResults4 = withContext(Dispatchers.IO) {
                itemVT4Interactor.getResultsByIdVT4(10)
            }

            val ballsForLesson1 = UtilFp2023.getBallByResultVT4(listResults1, listResultOfLessons[0]!!)
            val ballsForLesson2 = UtilFp2023.getBallByResultVT4(listResults2, listResultOfLessons[1]!!)
            val ballsForLesson3 = UtilFp2023.getBallByResultVT4(listResults3, listResultOfLessons[2]!!)
            var ballsForLesson4 = UtilFp2023.getBallByResultVT4(listResults4, listResultOfLessons[3]!!)

            if(!flageGrenade){
                ballsForLesson4-=80
                if(ballsForLesson4<0)
                    ballsForLesson4=0
            }
            setBallsOfLessonsVT4(0, ballsForLesson1)
            setBallsOfLessonsVT4(1, ballsForLesson2)
            setBallsOfLessonsVT4(2, ballsForLesson3)
            setBallsOfLessonsVT4(3, ballsForLesson4)
            val resultBalls = ballsForLesson1+ballsForLesson2+ballsForLesson3+ballsForLesson4
            setResultOfPassing(resultBalls)
            val describe = itemVT4Interactor.getDescribe(resultBalls)
            setDescribe(describe)
        }

    }

}