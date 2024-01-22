package com.sto_opka91.militarydirectory.ui.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sto_opka91.militarydirectory.R
import com.sto_opka91.militarydirectory.databinding.FragmentFp2023Binding
import com.sto_opka91.militarydirectory.ui.dialogs.CategoryDialog
import com.sto_opka91.militarydirectory.utils.fp_2023.UtilFp2023
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentFp2023Binding? = null
    private val viewModel: HomeViewModel by viewModels()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFp2023Binding.inflate(inflater, container, false)

        val root: View = binding.root

        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAutotext()
        btnGetResult()
        initViewModelData()
        initAutoCompleteTextViewTitle()
        initImage()
    }

    private fun initViewModelData(){
        viewModel.loadItemsLesson1()
        viewModel.loadItemsLesson2()
        viewModel.loadItemsLesson3()
        viewModel.loadItemsLesson4()
    }
    private fun initImage() = with(binding){
        imHelpCategory.setOnClickListener {
            val customDialog = CategoryDialog(requireContext())
            customDialog.show()
        }
    }
    private fun initAutoCompleteTextViewTitle() = with(binding){
        acTvGenre.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                val ageString = edAgeValue.text.toString()
                val age = ageString.toIntOrNull() ?: 0
                checkCombination(acTvGenre.text.toString(), acTvCat.text.toString(), age)
                if(position==1){
                    tvKatTitle.visibility = View.GONE
                    tilKat.visibility = View.GONE
                    acTvCat.text = Editable.Factory.getInstance().newEditable("нет")
                }else{
                    tvKatTitle.visibility = View.VISIBLE
                    tilKat.visibility = View.VISIBLE
                    acTvCat.text.clear()
                }
            }
        acTvCat.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, _, _ ->
                val ageString = edAgeValue.text.toString()
                val age = ageString.toIntOrNull() ?: 0
                checkCombination(acTvGenre.text.toString(), acTvCat.text.toString(), age)

            }
        edAgeValue.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {
                val ageString = edAgeValue.text.toString()
                val age = ageString.toIntOrNull() ?: 0
                checkCombination(acTvGenre.text.toString(), acTvCat.text.toString(), age)
                linearResult.visibility = View.GONE
            }

            override fun afterTextChanged(editable: Editable?) {

            }
        })
        acTvLesson1.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                val selectedValue = acTvLesson1.adapter.getItem(position).toString()
                val flag = UtilFp2023.getFlagForTipOfLessons(selectedValue)
                if(flag){
                    edLesson1.visibility = View.VISIBLE
                    linearLesson1Result.visibility = View.GONE
                    edLesson1cek.text.clear()
                    edLesson1min.text.clear()
                }else{
                    edLesson1.visibility = View.GONE
                    linearLesson1Result.visibility = View.VISIBLE
                    edLesson1.text.clear()
                }
                viewModel.setFlagOfLessons(0,flag)

            }
        acTvLesson2.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                val selectedValue = acTvLesson2.adapter.getItem(position).toString()
                val flag = UtilFp2023.getFlagForTipOfLessons(selectedValue)
                val flagWeight = UtilFp2023.getFlagForWeight(selectedValue)
                if(flag){
                    edLesson2.visibility = View.VISIBLE
                    linearLesson2Result.visibility = View.GONE
                    edLesson2cek.text.clear()
                    edLesson2min.text.clear()
                }else{
                    edLesson2.visibility = View.GONE
                    linearLesson2Result.visibility = View.VISIBLE
                    edLesson2.text.clear()
                }
                if(flagWeight)
                    edLesson2Weight.visibility = View.VISIBLE
                else{
                    edLesson2Weight.visibility = View.GONE
                    edLesson2Weight.text.clear()
                }
                viewModel.setFlagOfLessons(1,flag)

            }
        acTvLesson3.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                val selectedValue = acTvLesson3.adapter.getItem(position).toString()
                val flag = UtilFp2023.getFlagForTipOfLessons(selectedValue)
                if(flag){
                    edLesson3.visibility = View.VISIBLE
                    linearLesson3Result.visibility = View.GONE
                    edLesson3cek.text.clear()
                    edLesson3min.text.clear()
                }else{
                    edLesson3.visibility = View.GONE
                    linearLesson3Result.visibility = View.VISIBLE
                    edLesson3.text.clear()
                }
                viewModel.setFlagOfLessons(2,flag)

            }
        acTvLesson4.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                val selectedValue = acTvLesson4.adapter.getItem(position).toString()
                val flag = UtilFp2023.getFlagForTipOfLessons(selectedValue)
                if(flag){
                    edLesson4.visibility = View.VISIBLE
                    linearLesson4Result.visibility = View.GONE
                    edLesson4cek.text.clear()
                    edLesson4min.text.clear()
                }else{
                    edLesson4.visibility = View.GONE
                    linearLesson4Result.visibility = View.VISIBLE
                    edLesson4.text.clear()
                }
                viewModel.setFlagOfLessons(3,flag)
            }

    }

    private fun checkCombination(valueGenre: String, valueKat: String, valueAge: Int ) = with(binding) {
        if(valueGenre == "мужской" && valueKat == "1 категория" && valueAge<49){
            tvLesson1Title.text= getString(R.string.less_1_fp2023_for_2)
            tvLesson2Title.text= getString(R.string.less_2_fp2023_for_2)
            tvLesson3Title.text= getString(R.string.less_3_fp2023_for_2)
            tvLesson4Title.text= getString(R.string.less_4_fp2023)
            tilLesson3.hint = getString(R.string.less_3_hint)
            tilLesson4.hint = getString(R.string.less_4_hint)
            edLesson2Weight.text.clear()


            val adapterLesson1 = ArrayAdapter<String>(requireContext(), android.R.layout.simple_dropdown_item_1line)
            acTvLesson1.setAdapter(adapterLesson1)
            viewModel.itemsLesson1.observe(viewLifecycleOwner) { items ->
                adapterLesson1.addAll(items)
            }
            viewModel.itemsLesson3.observe(viewLifecycleOwner) { items ->
                adapterLesson1.addAll(items)
            }

            val adapterLesson2 = ArrayAdapter<String>(requireContext(), android.R.layout.simple_dropdown_item_1line)
            acTvLesson2.setAdapter(adapterLesson2)
            viewModel.itemsLesson2.observe(viewLifecycleOwner) { items ->
                adapterLesson2.addAll(items)
            }
            viewModel.itemsLesson3.observe(viewLifecycleOwner) { items ->
                adapterLesson2.addAll(items)
            }

            val adapterLesson3 = ArrayAdapter<String>(requireContext(), android.R.layout.simple_dropdown_item_1line)
            acTvLesson3.setAdapter(adapterLesson3)
            viewModel.loadItemsLesson4()
            viewModel.itemsLesson4.observe(viewLifecycleOwner) { items ->
                adapterLesson3.addAll(items)
            }
            val adapterLesson4 = ArrayAdapter<String>(requireContext(), android.R.layout.simple_dropdown_item_1line)
            acTvLesson4.setAdapter(adapterLesson4)
            viewModel.loadItemsLesson4()
            viewModel.itemsLesson4.observe(viewLifecycleOwner) { items ->
                adapterLesson4.addAll(items)
            }

            linearLesson1.visibility = View.VISIBLE
            linearLesson2.visibility = View.VISIBLE
            linearLesson3.visibility = View.VISIBLE
            linearLesson4.visibility = View.VISIBLE
            edLesson2Weight.visibility = View.GONE

        }else if(valueGenre == "мужской" && valueKat == "2 категория" && valueAge<=49){
            tvLesson1Title.text= getString(R.string.less_1_fp2023_for_2)
            tvLesson2Title.text= getString(R.string.less_2_fp2023_for_2)
            tvLesson4Title.text= getString(R.string.less_3_fp2023_for_2)
            tilLesson4.hint = getString(R.string.less_3_hint)
            edLesson3.text.clear()
            edLesson3min.text.clear()
            edLesson3cek.text.clear()
            edLesson2Weight.text.clear()

            val adapterLesson1 = ArrayAdapter<String>(requireContext(), android.R.layout.simple_dropdown_item_1line)
            acTvLesson1.setAdapter(adapterLesson1)
            viewModel.itemsLesson1.observe(viewLifecycleOwner) { items ->
                adapterLesson1.addAll(items)
            }
            viewModel.itemsLesson3.observe(viewLifecycleOwner) { items ->
                adapterLesson1.addAll(items)
            }

            val adapterLesson2 = ArrayAdapter<String>(requireContext(), android.R.layout.simple_dropdown_item_1line)
            acTvLesson2.setAdapter(adapterLesson2)
            viewModel.itemsLesson2.observe(viewLifecycleOwner) { items ->
                adapterLesson2.addAll(items)
            }
            viewModel.itemsLesson3.observe(viewLifecycleOwner) { items ->
                adapterLesson2.addAll(items)
            }

            val adapterLesson3 = ArrayAdapter<String>(requireContext(), android.R.layout.simple_dropdown_item_1line)
            acTvLesson4.setAdapter(adapterLesson3)
            viewModel.itemsLesson4.observe(viewLifecycleOwner) { items ->
                adapterLesson3.addAll(items)
            }

            linearLesson3.visibility = View.GONE
            linearLesson1.visibility = View.VISIBLE
            linearLesson2.visibility = View.VISIBLE
            linearLesson4.visibility = View.VISIBLE
            edLesson2Weight.visibility = View.GONE
        }else if((valueGenre == "мужской" && valueKat == "3 категория" && valueAge<=49) || (valueGenre == "женский" && valueAge<=45)){

            tvLesson1Title.text= getString(R.string.less_1_fp2023)
            tvLesson2Title.text= getString(R.string.less_2_fp2023)
            tvLesson3Title.text= getString(R.string.less_3_fp2023)
            tilLesson3.hint = getString(R.string.less_3_hint)
            edLesson4.text.clear()
            edLesson4min.text.clear()
            edLesson4cek.text.clear()
            edLesson2Weight.text.clear()


            val adapterLesson1 = ArrayAdapter<String>(requireContext(), android.R.layout.simple_dropdown_item_1line)
            acTvLesson1.setAdapter(adapterLesson1)
            viewModel.itemsLesson1.observe(viewLifecycleOwner) { items ->
                adapterLesson1.addAll(items)
            }

            val adapterLesson2 = ArrayAdapter<String>(requireContext(), android.R.layout.simple_dropdown_item_1line)
            acTvLesson2.setAdapter(adapterLesson2)
            viewModel.itemsLesson2.observe(viewLifecycleOwner) { items ->
                adapterLesson2.addAll(items)
            }

            val adapterLesson3 = ArrayAdapter<String>(requireContext(), android.R.layout.simple_dropdown_item_1line)
            acTvLesson3.setAdapter(adapterLesson3)
            viewModel.itemsLesson3.observe(viewLifecycleOwner) { items ->
                adapterLesson3.addAll(items)
            }

            linearLesson1.visibility = View.VISIBLE
            linearLesson2.visibility = View.VISIBLE
            linearLesson3.visibility = View.VISIBLE
            linearLesson4.visibility = View.GONE
            edLesson2Weight.visibility = View.GONE
        }else if((valueGenre == "женский" && valueAge > 45) || (valueGenre == "мужской" && valueAge> 49)){

            tvLesson1Title.text= getString(R.string.less_1_fp2023_for_2)
            tvLesson2Title.text= getString(R.string.less_2_fp2023_for_2)
            edLesson3.text.clear()
            edLesson3min.text.clear()
            edLesson3cek.text.clear()
            edLesson4.text.clear()
            edLesson4min.text.clear()
            edLesson4cek.text.clear()
            edLesson2Weight.text.clear()

            val adapterLesson1 = ArrayAdapter<String>(requireContext(), android.R.layout.simple_dropdown_item_1line)
            acTvLesson1.setAdapter(adapterLesson1)
            viewModel.itemsLesson1.observe(viewLifecycleOwner) { items ->
                adapterLesson1.addAll(items)
            }
            viewModel.itemsLesson3.observe(viewLifecycleOwner) { items ->
                adapterLesson1.addAll(items)
            }
            val adapterLesson2 = ArrayAdapter<String>(requireContext(), android.R.layout.simple_dropdown_item_1line)
            acTvLesson2.setAdapter(adapterLesson2)
            viewModel.itemsLesson2.observe(viewLifecycleOwner) { items ->
                adapterLesson2.addAll(items)
            }
            viewModel.itemsLesson3.observe(viewLifecycleOwner) { items ->
                adapterLesson2.addAll(items)
            }
            tvLesson1Title.text= getString(R.string.less_1_fp2023_for_2)
            tvLesson2Title.text= getString(R.string.less_2_fp2023_for_2)
            linearLesson3.visibility = View.GONE
            linearLesson4.visibility = View.GONE
            edLesson2Weight.visibility = View.GONE
        }

    }

    private fun initAutotext() = with(binding){
        tilKat.setEndIconOnClickListener  {
            val adapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_dropdown_item_1line)
            acTvCat.setAdapter(adapter)
            viewModel.loadItemsKat()
            viewModel.itemsKat.observe(viewLifecycleOwner) { items ->
                adapter.addAll(items)
            }
            linearResult.visibility = View.GONE
            acTvCat.postDelayed({ acTvCat.showDropDown() }, 100)
        }
        tilLesson1.setEndIconOnClickListener  {
            linearResult.visibility = View.GONE
            acTvLesson1.postDelayed({ acTvLesson1.showDropDown() }, 100)
        }
        tilLesson2.setEndIconOnClickListener  {
            linearResult.visibility = View.GONE
            acTvLesson2.postDelayed({ acTvLesson2.showDropDown() }, 100)
        }
        tilLesson3.setEndIconOnClickListener  {
            linearResult.visibility = View.GONE
            acTvLesson3.postDelayed({ acTvLesson3.showDropDown() }, 100)
        }
        tilLesson4.setEndIconOnClickListener  {
            linearResult.visibility = View.GONE
            acTvLesson4.postDelayed({ acTvLesson4.showDropDown() }, 100)
        }
        tilGenre.setEndIconOnClickListener  {
            val adapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_dropdown_item_1line)
            acTvGenre.setAdapter(adapter)
            viewModel.loadItemsGenre()
            viewModel.itemsGenre.observe(viewLifecycleOwner) { items ->
                adapter.addAll(items)
            }
            linearResult.visibility = View.GONE
            acTvGenre.postDelayed({ acTvGenre.showDropDown() }, 100)
        }
    }

    private fun btnGetResult() = with(binding){

            btnGradeFP2023.setOnClickListener{
                if(acTvCat.text.toString()==""|| acTvGenre.text.toString() == ""|| edAgeValue.text.toString()==""){
                    Toast.makeText(requireContext(), getString(R.string.o_cebe),Toast.LENGTH_LONG).show()
                }
                else{
                    if(acTvLesson1.text.toString()==""||acTvLesson2.text.toString()==""){
                        Toast.makeText(requireContext(), "Упражнения не выставлены",Toast.LENGTH_LONG).show()
                    }else{
                        viewModel.clearAllResults()
                        viewModel.clearAllNamesOfLessons()
                        viewModel.setNameOfLessons(0,UtilFp2023.extractExerciseName(acTvLesson1.text.toString()))
                        viewModel.setNameOfLessons(1,UtilFp2023.extractExerciseName(acTvLesson2.text.toString()))
                        viewModel.setNameOfLessons(2,UtilFp2023.extractExerciseName(acTvLesson3.text.toString()))
                        viewModel.setNameOfLessons(3,UtilFp2023.extractExerciseName(acTvLesson4.text.toString()))

                        if (!viewModel.getFlagOfLessons(0)) {
                            viewModel.setResultOfLessons(0,UtilFp2023.getResultBySekAndMin(edLesson1min, edLesson1cek))
                        } else {
                            viewModel.setResultOfLessons(0,UtilFp2023.getResultbyOneCell(edLesson1.text?.toString()))
                        }
                        if (!viewModel.getFlagOfLessons(1)) {
                            viewModel.setResultOfLessons(1,UtilFp2023.getResultBySekAndMin(edLesson2min, edLesson2cek))
                        } else {
                            viewModel.setResultOfLessons(1,UtilFp2023.getResultbyOneCell(edLesson2.text?.toString()))
                        }
                        if (!viewModel.getFlagOfLessons(2)) {
                            viewModel.setResultOfLessons(2,UtilFp2023.getResultBySekAndMin(edLesson3min, edLesson3cek))
                        } else {
                            viewModel.setResultOfLessons(2,UtilFp2023.getResultbyOneCell(edLesson3.text?.toString()))
                        }
                        if (!viewModel.getFlagOfLessons(3)) {
                            viewModel.setResultOfLessons(3,UtilFp2023.getResultBySekAndMin(edLesson4min, edLesson4cek))
                        } else {
                            viewModel.setResultOfLessons(3,UtilFp2023.getResultbyOneCell(edLesson4.text?.toString()))
                        }

                        val genre = acTvGenre.text.toString()
                        val age = edAgeValue.text.toString().toFloat().toInt()
                        val weight = UtilFp2023.getWeight(edLesson2Weight.text.toString(), genre)
                        val kategory = acTvCat.text.toString()
                        viewModel.getResult(age, genre, weight, kategory)
                        viewModel.resultOfPassing.observe(viewLifecycleOwner){value ->
                            linearResult.visibility = View.VISIBLE
                            tvResultOfPassing.text = value.toString()
                        }
                        viewModel.resultDescribe.observe(viewLifecycleOwner){value ->
                            linearResult.visibility = View.VISIBLE
                            tvDescribeResults.text = value.toString()
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