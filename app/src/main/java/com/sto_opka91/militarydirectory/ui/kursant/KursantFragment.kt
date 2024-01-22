package com.sto_opka91.militarydirectory.ui.kursant

import android.R
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sto_opka91.militarydirectory.LIST_KURS
import com.sto_opka91.militarydirectory.databinding.FragmentKursantBinding
import com.sto_opka91.militarydirectory.ui.home.HomeViewModel
import com.sto_opka91.militarydirectory.utils.fp_2023.UtilFp2023
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class KursantFragment : Fragment() {

    private var _binding: FragmentKursantBinding? = null

    private val binding get() = _binding!!
    private val viewModel: KursantViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentKursantBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModelData()
        initAutotext()
        initAutoCompleteTextViewTitle()
        btnResult()
    }
    private fun initViewModelData(){
        viewModel.loadItemsLesson1()
        viewModel.loadItemsLesson2()
        viewModel.loadItemsLesson3()
        viewModel.loadItemsLesson4()
        viewModel.loadKurs()
    }

    private fun initAutoCompleteTextViewTitle() = with(binding){
        acTvGenreKursant.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                val selectedValue = acTvGenreKursant.adapter.getItem(position).toString()
                checkCombination(selectedValue)
                }
        acTvLesson1Kursant.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                val selectedValue = acTvLesson1Kursant.adapter.getItem(position).toString()
                val flag = UtilFp2023.getFlagForTipOfLessons(selectedValue)
                if(flag){
                    edLesson1Kursant.visibility = View.VISIBLE
                    linearLesson1ResultKursant.visibility = View.GONE
                    edLesson1cekKursant.text.clear()
                    edLesson2minKursant.text.clear()
                }else{
                    edLesson1Kursant.visibility = View.GONE
                    linearLesson1ResultKursant.visibility = View.VISIBLE
                    edLesson1Kursant.text.clear()
                }
                viewModel.setFlagOfLessons(0,flag)
            }
        acTvLesson2Kursant.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                val selectedValue = acTvLesson2Kursant.adapter.getItem(position).toString()
                val flag = UtilFp2023.getFlagForTipOfLessons(selectedValue)
                val flagWeight = UtilFp2023.getFlagForWeight(selectedValue)
                if(flag){
                    edLesson2Kursant.visibility = View.VISIBLE
                    linearLesson2ResultKursant.visibility = View.GONE
                    edLesson2cekKursant.text.clear()
                    edLesson2minKursant.text.clear()
                }else{
                    edLesson2Kursant.visibility = View.GONE
                    linearLesson2ResultKursant.visibility = View.VISIBLE
                    edLesson2Kursant.text.clear()
                }
                if(flagWeight)
                    edLesson2WeightKursant.visibility = View.VISIBLE
                else{
                    edLesson2WeightKursant.visibility = View.GONE
                    edLesson2WeightKursant.text.clear()
                }
                viewModel.setFlagOfLessons(1,flag)
            }
        acTvLesson3Kursant.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                val selectedValue = acTvLesson3Kursant.adapter.getItem(position).toString()
                val flag = UtilFp2023.getFlagForTipOfLessons(selectedValue)
                if(flag){
                    edLesson3Kursant.visibility = View.VISIBLE
                    linearLesson3ResultKursant.visibility = View.GONE
                    edLesson3cekKursant.text.clear()
                    edLesson3minKursant.text.clear()
                }else{
                    edLesson3Kursant.visibility = View.GONE
                    linearLesson3ResultKursant.visibility = View.VISIBLE
                    edLesson3Kursant.text.clear()
                }
                viewModel.setFlagOfLessons(2,flag)
            }
        acTvLesson4Kursant.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                val selectedValue = acTvLesson4Kursant.adapter.getItem(position).toString()
                val flag = UtilFp2023.getFlagForTipOfLessons(selectedValue)
                if(flag){
                    edLesson4Kursant.visibility = View.VISIBLE
                    linearLesson4ResultKursant.visibility = View.GONE
                    edLesson4cekKursant.text.clear()
                    edLesson4minKursant.text.clear()
                }else{
                    edLesson4Kursant.visibility = View.GONE
                    linearLesson4ResultKursant.visibility = View.VISIBLE
                    edLesson4Kursant.text.clear()
                }
                viewModel.setFlagOfLessons(3,flag)
            }
        acTvLesson5Kursant.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                val selectedValue = acTvLesson5Kursant.adapter.getItem(position).toString()
                val flag = UtilFp2023.getFlagForTipOfLessons(selectedValue)
                if(flag){
                    edLesson5Kursant.visibility = View.VISIBLE
                    linearLesson5ResultKursant.visibility = View.GONE
                    edLesson5cekKursant.text.clear()
                    edLesson5minKursant.text.clear()
                }else{
                    edLesson5Kursant.visibility = View.GONE
                    linearLesson5ResultKursant.visibility = View.VISIBLE
                    edLesson5Kursant.text.clear()
                }
                viewModel.setFlagOfLessons(4,flag)
            }
            }
    private fun initAutotext() = with(binding){
        tilGenreKursant.setEndIconOnClickListener  {
            val adapter = ArrayAdapter<String>(requireContext(), R.layout.simple_dropdown_item_1line)
            acTvGenreKursant.setAdapter(adapter)
            viewModel.itemsKurs.observe(viewLifecycleOwner) { items ->
                adapter.addAll(items)
            }
            acTvGenreKursant.postDelayed({ acTvGenreKursant.showDropDown() }, 100)
        }
        tilLesson1Kursant.setEndIconOnClickListener  {
            val adapterLesson1 = ArrayAdapter<String>(requireContext(), R.layout.simple_dropdown_item_1line)
            acTvLesson1Kursant.setAdapter(adapterLesson1)
            viewModel.itemsLesson1.observe(viewLifecycleOwner) { items ->
                adapterLesson1.addAll(items)
            }
            linearResultKursant.visibility = View.GONE
            acTvLesson1Kursant.postDelayed({ acTvLesson1Kursant.showDropDown() }, 100)
        }
        tilLesson2Kursant.setEndIconOnClickListener  {
            val adapterLesson2 = ArrayAdapter<String>(requireContext(), R.layout.simple_dropdown_item_1line)
            acTvLesson2Kursant.setAdapter(adapterLesson2)
            viewModel.itemsLesson2.observe(viewLifecycleOwner) { items ->
                adapterLesson2.addAll(items)
            }
            linearResultKursant.visibility = View.GONE
            acTvLesson2Kursant.postDelayed({ acTvLesson2Kursant.showDropDown() }, 100)
        }
        tilLesson3Kursant.setEndIconOnClickListener  {
            val adapterLesson3 = ArrayAdapter<String>(requireContext(), android.R.layout.simple_dropdown_item_1line)
            acTvLesson3Kursant.setAdapter(adapterLesson3)
            viewModel.itemsLesson3.observe(viewLifecycleOwner) { items ->
                adapterLesson3.addAll(items)
            }
            linearResultKursant.visibility = View.GONE
            acTvLesson3Kursant.postDelayed({ acTvLesson3Kursant.showDropDown() }, 100)
        }
        tilLesson4Kursant.setEndIconOnClickListener  {
            val adapterLesson4 = ArrayAdapter<String>(requireContext(), android.R.layout.simple_dropdown_item_1line)
            acTvLesson4Kursant.setAdapter(adapterLesson4)
            viewModel.loadItemsLesson4()
            viewModel.itemsLesson4.observe(viewLifecycleOwner) { items ->
                adapterLesson4.addAll(items)
            }
            linearResultKursant.visibility = View.GONE
            acTvLesson4Kursant.postDelayed({ acTvLesson4Kursant.showDropDown() }, 100)
        }
        tilLesson5Kursant.setEndIconOnClickListener  {
            val adapterLesson5 = ArrayAdapter<String>(requireContext(), android.R.layout.simple_dropdown_item_1line)
            acTvLesson5Kursant.setAdapter(adapterLesson5)
            viewModel.itemsLesson4.observe(viewLifecycleOwner) { items ->
                adapterLesson5.addAll(items)
            }
            linearResultKursant.visibility = View.GONE
            acTvLesson5Kursant.postDelayed({ acTvLesson5Kursant.showDropDown() }, 100)
        }
    }
    private fun checkCombination(kurs: String)= with(binding){
        if(kurs == LIST_KURS[0]){
            linearLesson5Kursant.visibility = View.GONE
            edLesson5Kursant.text.clear()
            edLesson5cekKursant.text.clear()
            edLesson5minKursant.text.clear()
        }else{
            linearLesson5Kursant.visibility = View.VISIBLE
        }
    }

    private fun btnResult() = with(binding){
       btnGradeFP2023Kursant.setOnClickListener{
            if(acTvGenreKursant.text.toString()==""){
                Toast.makeText(requireContext(), getString(com.sto_opka91.militarydirectory.R.string.o_cebe),
                    Toast.LENGTH_LONG).show()
            }else{
                viewModel.clearAllResults()
                viewModel.clearAllNamesOfLessons()
                viewModel.setNameOfLessons(0,UtilFp2023.extractExerciseName(acTvLesson1Kursant.text.toString()))
                viewModel.setNameOfLessons(1,UtilFp2023.extractExerciseName(acTvLesson2Kursant.text.toString()))
                viewModel.setNameOfLessons(2,UtilFp2023.extractExerciseName(acTvLesson3Kursant.text.toString()))
                viewModel.setNameOfLessons(3,UtilFp2023.extractExerciseName(acTvLesson4Kursant.text.toString()))
                viewModel.setNameOfLessons(4,UtilFp2023.extractExerciseName(acTvLesson5Kursant.text.toString()))

                if (!viewModel.getFlagOfLessons(0)) {
                    viewModel.setResultOfLessons(0,UtilFp2023.getResultBySekAndMin(edLesson1minKursant, edLesson1cekKursant))
                } else {
                    viewModel.setResultOfLessons(0,UtilFp2023.getResultbyOneCell(edLesson1Kursant.text?.toString()))
                }
                if (!viewModel.getFlagOfLessons(1)) {
                    viewModel.setResultOfLessons(1,UtilFp2023.getResultBySekAndMin(edLesson2minKursant, edLesson2cekKursant))
                } else {
                    viewModel.setResultOfLessons(1,UtilFp2023.getResultbyOneCell(edLesson2Kursant.text?.toString()))
                }
                if (!viewModel.getFlagOfLessons(2)) {
                    viewModel.setResultOfLessons(2,UtilFp2023.getResultBySekAndMin(edLesson3minKursant, edLesson3cekKursant))
                } else {
                    viewModel.setResultOfLessons(2,UtilFp2023.getResultbyOneCell(edLesson3Kursant.text?.toString()))
                }
                if (!viewModel.getFlagOfLessons(3)) {
                    viewModel.setResultOfLessons(3,UtilFp2023.getResultBySekAndMin(edLesson4minKursant, edLesson4cekKursant))
                } else {
                    viewModel.setResultOfLessons(3,UtilFp2023.getResultbyOneCell(edLesson4Kursant.text?.toString()))
                }
                if (!viewModel.getFlagOfLessons(4)) {
                    viewModel.setResultOfLessons(4,UtilFp2023.getResultBySekAndMin(edLesson5minKursant, edLesson5cekKursant))
                } else {
                    viewModel.setResultOfLessons(4,UtilFp2023.getResultbyOneCell(edLesson5Kursant.text?.toString()))
                }
                val category = acTvGenreKursant.text.toString()
                val weight = UtilFp2023.getWeight(edLesson2WeightKursant.text.toString(), "мужской")
                viewModel.getResult(category, weight)
                viewModel.resultOfPassing.observe(viewLifecycleOwner){value ->
                    linearResultKursant.visibility = View.VISIBLE
                    tvResultOfPassingKursant.text = value.toString()
                }
                viewModel.resultDescribe.observe(viewLifecycleOwner){value ->
                    linearResultKursant.visibility = View.VISIBLE
                    tvDescribeResultsKursant.text = value.toString()
                }

            }
       }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}