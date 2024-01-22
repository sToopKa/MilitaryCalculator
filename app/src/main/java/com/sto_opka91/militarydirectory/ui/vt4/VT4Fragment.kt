package com.sto_opka91.militarydirectory.ui.vt4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sto_opka91.militarydirectory.R
import com.sto_opka91.militarydirectory.databinding.FragmentVt4Binding
import com.sto_opka91.militarydirectory.ui.home.HomeViewModel
import com.sto_opka91.militarydirectory.utils.fp_2023.UtilFp2023
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VT4Fragment : Fragment() {

    private var _binding: FragmentVt4Binding? = null
    private val viewModel: VT4ViewModel by viewModels()


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVt4Binding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAutotext()
        btnResult()
    }

    private fun initAutotext() = with(binding) {
        tilLesson1VT4.setEndIconOnClickListener  {
            val adapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_dropdown_item_1line)
            acTvLesson1VT4.setAdapter(adapter)
            viewModel.loadItemsLesson1VT4()
            viewModel.itemsLesson1VT4.observe(viewLifecycleOwner) { items ->

                adapter.addAll(items)
            }
            acTvLesson1VT4.postDelayed({ acTvLesson1VT4.showDropDown() }, 100)

            tvResultLesson1.visibility = View.GONE
            tvResultLesson2.visibility = View.GONE
            tvResultLesson3.visibility = View.GONE
            tvResultLesson4.visibility = View.GONE
            tvResultLesson1Title.visibility = View.GONE
            tvResultLesson2Title.visibility = View.GONE
            tvResultLesson3Title.visibility = View.GONE
            tvResultLesson4Title.visibility = View.GONE
        }
        tilLesson2VT4.setEndIconOnClickListener  {
            val adapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_dropdown_item_1line)
            acTvLesson2VT4.setAdapter(adapter)
            viewModel.loadItemsLesson2VT4()
            viewModel.itemsLesson2VT4.observe(viewLifecycleOwner) { items ->
                adapter.addAll(items)
            }
            acTvLesson2VT4.postDelayed({ acTvLesson2VT4.showDropDown() }, 100)
            tvResultLesson1.visibility = View.GONE
            tvResultLesson2.visibility = View.GONE
            tvResultLesson3.visibility = View.GONE
            tvResultLesson4.visibility = View.GONE
            tvResultLesson1Title.visibility = View.GONE
            tvResultLesson2Title.visibility = View.GONE
            tvResultLesson3Title.visibility = View.GONE
            tvResultLesson4Title.visibility = View.GONE
        }
        tilLesson3VT4.setEndIconOnClickListener  {
            val adapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_dropdown_item_1line)
            acTvLesson3VT4.setAdapter(adapter)
            viewModel.loadItemsLesson3VT4()
            viewModel.itemsLesson3VT4.observe(viewLifecycleOwner) { items ->
                adapter.clear()
                adapter.addAll(items)
            }
            acTvLesson3VT4.postDelayed({ acTvLesson3VT4.showDropDown() }, 100)
            tvResultLesson1.visibility = View.GONE
            tvResultLesson2.visibility = View.GONE
            tvResultLesson3.visibility = View.GONE
            tvResultLesson4.visibility = View.GONE
            tvResultLesson1Title.visibility = View.GONE
            tvResultLesson2Title.visibility = View.GONE
            tvResultLesson3Title.visibility = View.GONE
            tvResultLesson4Title.visibility = View.GONE
        }

    }
    private fun btnResult()= with(binding){
        btnGradeVT4.setOnClickListener{
        if(acTvLesson1VT4.text.toString()==""||acTvLesson2VT4.text.toString()=="" || acTvLesson3VT4.text.toString()==""){
            Toast.makeText(requireContext(), getString(R.string.no_lessons), Toast.LENGTH_LONG).show()
        }else if(edLesson1VT.text.toString()==""|| edLesson2VT.text.toString() == ""||edLesson3cek.text.toString() == ""|| edLesson3min.text.toString() == ""|| edLesson4min.text.toString() == "" || edLesson4cek.text.toString() == ""){
            Toast.makeText(requireContext(), getString(R.string.no_result), Toast.LENGTH_LONG).show()
        }else{

                viewModel.clearAllNamesOfLessons()
                viewModel.clearAllResults()
                viewModel.setNameOfLessons(0, acTvLesson1VT4.text.toString())
                viewModel.setNameOfLessons(1, acTvLesson2VT4.text.toString())
                viewModel.setNameOfLessons(2, acTvLesson3VT4.text.toString())
                viewModel.setResultOfLessons(0,UtilFp2023.getResultbyOneCell(edLesson1VT.text?.toString()))
                viewModel.setResultOfLessons(1,UtilFp2023.getResultbyOneCell(edLesson2VT.text?.toString()))
                viewModel.setResultOfLessons(2,UtilFp2023.getResultBySekAndMin(edLesson3min, edLesson3cek))
                viewModel.setResultOfLessons(3,UtilFp2023.getResultBySekAndMin(edLesson4min, edLesson4cek))

                val flagGrenade = cbGrenade.isChecked

                viewModel.getResult(flagGrenade)
                viewModel.resultOfPassing.observe(viewLifecycleOwner){value ->
                    tvresultValueVT4.text = value.toString()
                }
            viewModel.resultDescribe.observe(viewLifecycleOwner){value ->
                tvResultDescribe.text = value.toString()
            }
            tvResultLesson1.visibility = View.VISIBLE
            tvResultLesson2.visibility = View.VISIBLE
            tvResultLesson3.visibility = View.VISIBLE
            tvResultLesson4.visibility = View.VISIBLE
            tvResultLesson1Title.visibility = View.VISIBLE
            tvResultLesson2Title.visibility = View.VISIBLE
            tvResultLesson3Title.visibility = View.VISIBLE
            tvResultLesson4Title.visibility = View.VISIBLE
            viewModel.listBallsOfLessonsVT4.observe(viewLifecycleOwner) { updatedList ->
                // Обновление интерфейса с использованием данных из updatedList
                tvResultLesson1.text = updatedList[0]?.toString() ?: ""
                tvResultLesson2.text = updatedList[1]?.toString() ?: ""
                tvResultLesson3.text = updatedList[2]?.toString() ?: ""
                tvResultLesson4.text = updatedList[3]?.toString() ?: ""
            }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}