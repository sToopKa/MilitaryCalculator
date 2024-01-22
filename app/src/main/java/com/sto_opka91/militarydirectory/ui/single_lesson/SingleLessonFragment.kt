package com.sto_opka91.militarydirectory.ui.single_lesson


import android.R
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.sto_opka91.militarydirectory.databinding.FragmentSingleLessonBinding
import com.sto_opka91.militarydirectory.ui.home.HomeViewModel
import com.sto_opka91.militarydirectory.utils.fp_2023.UtilFp2023
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SingleLessonFragment : Fragment() {

    private var _binding: FragmentSingleLessonBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SingleLessonViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSingleLessonBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
        initAutotext()
        initAutoCompleteTextViewTitle()
        btnResult()
    }
    private fun initAutotext() = with(binding) {
        tilLesson1Single.setEndIconOnClickListener  {
            val adapter = ArrayAdapter<String>(requireContext(), R.layout.simple_dropdown_item_1line)
            acTvLesson1Single.setAdapter(adapter)

            viewModel.itemsLesson2.observe(viewLifecycleOwner) { items ->

                adapter.addAll(items)
            }
            acTvLesson1Single.postDelayed({ acTvLesson1Single.showDropDown() }, 100)

            tvBalls1Single.visibility = View.GONE

        }
        tilLesson2Single.setEndIconOnClickListener  {
            val adapter = ArrayAdapter<String>(requireContext(), R.layout.simple_dropdown_item_1line)
            acTvLesson2Single.setAdapter(adapter)
            viewModel.itemsLesson1.observe(viewLifecycleOwner) { items ->
                adapter.addAll(items)
            }
            viewModel.itemsLesson3.observe(viewLifecycleOwner) { items ->
                adapter.addAll(items)
            }
            acTvLesson2Single.postDelayed({ acTvLesson2Single.showDropDown() }, 100)
            tvBalls2Single.visibility = View.GONE

        }
        tilLesson3Single.setEndIconOnClickListener  {
            val adapter = ArrayAdapter<String>(requireContext(), R.layout.simple_dropdown_item_1line)
            acTvLesson3Single.setAdapter(adapter)
            viewModel.itemsLesson4.observe(viewLifecycleOwner) { items ->
                adapter.addAll(items)
            }
            acTvLesson3Single.postDelayed({ acTvLesson3Single.showDropDown() }, 100)
            tvBalls3Single.visibility = View.GONE
        }
        tilGenreSingle.setEndIconOnClickListener  {
            val adapter = ArrayAdapter<String>(requireContext(), R.layout.simple_dropdown_item_1line)
            acTvGenreSingle.setAdapter(adapter)
            viewModel.itemsGenre.observe(viewLifecycleOwner) { items ->
                adapter.addAll(items)
            }
            acTvGenreSingle.postDelayed({ acTvGenreSingle.showDropDown() }, 100)
        }
    }

    private fun loadData(){
        viewModel.loadItemsLesson1()
        viewModel.loadItemsLesson2()
        viewModel.loadItemsLesson3()
        viewModel.loadItemsLesson4()
        viewModel.loadItemsGenre()
    }

    private fun initAutoCompleteTextViewTitle() = with(binding){

        acTvLesson1Single.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                val selectedValue = acTvLesson1Single.adapter.getItem(position).toString()
                val flagWeight = UtilFp2023.getFlagForWeight(selectedValue)
                if(flagWeight)
                    edLesson1WeightSingle.visibility = View.VISIBLE
                else{
                    edLesson1WeightSingle.visibility = View.GONE
                    edLesson1WeightSingle.text.clear()
                }
            }
        acTvLesson2Single.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                val selectedValue = acTvLesson2Single.adapter.getItem(position).toString()
                val flag = UtilFp2023.getFlagForTipOfLessons(selectedValue)
                if(flag){
                    edLesson2Single.visibility = View.VISIBLE
                    linearLesson2ResultSingle.visibility = View.GONE
                    edLesson2cekSingle.text.clear()
                    edLesson2minSingle.text.clear()
                }else{
                    edLesson2Single.visibility = View.GONE
                    linearLesson2ResultSingle.visibility = View.VISIBLE
                    edLesson2Single.text.clear()
                }
            }
        acTvLesson3Single.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                val selectedValue = acTvLesson3Single.adapter.getItem(position).toString()
                val flag = UtilFp2023.getFlagForTipOfLessons(selectedValue)
                if(flag){
                    edLesson3Single.visibility = View.VISIBLE
                    linearLesson3ResultSingle.visibility = View.GONE
                    edLesson3cekSingle.text.clear()
                    edLesson3minSingle.text.clear()
                }else{
                    edLesson3Single.visibility = View.GONE
                    linearLesson3ResultSingle.visibility = View.VISIBLE
                    edLesson3Single.text.clear()
                }
            }
    }

    private fun btnResult() = with(binding){
        btnGradeLesson.setOnClickListener{
            if(edAgeValueSingle.text.toString()=="" || acTvGenreSingle.text.toString()==""){
                Toast.makeText(requireContext(), getString(com.sto_opka91.militarydirectory.R.string.o_cebe),
                    Toast.LENGTH_LONG).show()
            }else{
                val age = edAgeValueSingle.text.toString().toFloat().toInt()
                val genre = acTvGenreSingle.text.toString()
                if(acTvLesson1Single.text.toString()!=""){
                    val lesson = UtilFp2023.extractExerciseName(acTvLesson1Single.text.toString())
                    if(edLesson1Single.text.toString()!=""){
                        val flag = UtilFp2023.getFlagForWeight(acTvLesson1Single.text.toString())
                        val result = UtilFp2023.getResultbyOneCell(edLesson1Single.text?.toString())
                        if(flag){
                            val valueW = UtilFp2023.getWeight(edLesson1WeightSingle.text.toString(), genre)
                            viewModel.getResult(lesson, result, age, valueW,1)
                            viewModel.resultOflesson1.observe(viewLifecycleOwner){valueR ->
                                tvBalls1Single.visibility = View.VISIBLE
                                tvBalls1Single.text = "баллы: $valueR"
                            }
                        }else{
                            viewModel.getResult(lesson, result, age, 0,1)
                            viewModel.resultOflesson1.observe(viewLifecycleOwner){valueR ->
                                tvBalls1Single.visibility = View.VISIBLE
                                tvBalls1Single.text = "баллы: $valueR"
                            }
                        }
                    }else{
                        Toast.makeText(requireContext(), getString(com.sto_opka91.militarydirectory.R.string.result_lesson_1),
                            Toast.LENGTH_LONG).show()
                    }

                }
                if(acTvLesson2Single.text.toString()!=""){
                    val lesson = UtilFp2023.extractExerciseName(acTvLesson2Single.text.toString())
                    val flag = UtilFp2023.getFlagForTipOfLessons(acTvLesson2Single.text.toString())
                    if(flag){
                        if(edLesson2Single.text.toString()!=""){
                            val result = UtilFp2023.getResultbyOneCell(edLesson2Single.text?.toString())
                            viewModel.getResult(lesson, result, age, 0,2)
                            viewModel.resultOflesson2.observe(viewLifecycleOwner){valueR ->
                                tvBalls2Single.visibility = View.VISIBLE
                                tvBalls2Single.text = "баллы: $valueR"
                            }
                        }else{
                            Toast.makeText(requireContext(), getString(com.sto_opka91.militarydirectory.R.string.result_lesson_2),
                                Toast.LENGTH_LONG).show()
                        }

                    }else{
                        if(edLesson2minSingle.text.toString()!="" && edLesson2cekSingle.text.toString()!=""){
                            val result = UtilFp2023.getResultBySekAndMin(edLesson2minSingle, edLesson2cekSingle)
                            viewModel.getResult(lesson, result, age, 0,2)
                            viewModel.resultOflesson2.observe(viewLifecycleOwner){valueR ->
                                tvBalls2Single.visibility = View.VISIBLE
                                tvBalls2Single.text = "баллы: $valueR"
                            }
                        }else{
                            Toast.makeText(requireContext(), getString(com.sto_opka91.militarydirectory.R.string.result_lesson_2),
                                Toast.LENGTH_LONG).show()
                        }
                    }
                }
                if(acTvLesson3Single.text.toString()!=""){
                    val lesson = UtilFp2023.extractExerciseName(acTvLesson3Single.text.toString())
                    val flag = UtilFp2023.getFlagForTipOfLessons(acTvLesson3Single.text.toString())
                    if(flag){
                        if(edLesson3Single.text.toString()!=""){
                            val result = UtilFp2023.getResultbyOneCell(edLesson3Single.text?.toString())
                            viewModel.getResult(lesson, result, age, 0,3)
                            viewModel.resultOflesson3.observe(viewLifecycleOwner){valueR ->
                                tvBalls3Single.visibility = View.VISIBLE
                                tvBalls3Single.text = "баллы: $valueR"
                            }
                        }else{
                            Toast.makeText(requireContext(), getString(com.sto_opka91.militarydirectory.R.string.result_lesson_3),
                                Toast.LENGTH_LONG).show()
                        }

                    }else{
                        if(edLesson3minSingle.text.toString()!="" && edLesson3cekSingle.text.toString()!=""){
                            val result = UtilFp2023.getResultBySekAndMin(edLesson3minSingle, edLesson3cekSingle)
                            viewModel.getResult(lesson, result, age, 0,3)
                            viewModel.resultOflesson3.observe(viewLifecycleOwner){valueR ->
                                tvBalls3Single.visibility = View.VISIBLE
                                tvBalls3Single.text = "баллы: $valueR"
                            }
                        }else{
                            Toast.makeText(requireContext(), getString(com.sto_opka91.militarydirectory.R.string.result_lesson_3),
                                Toast.LENGTH_LONG).show()
                        }
                    }
                }

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}