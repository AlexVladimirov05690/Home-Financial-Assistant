package com.example.homefinancialassistant.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.homefinancialassistant.R
import com.example.homefinancialassistant.databinding.FragmentAddConsumptionBinding
import com.example.homefinancialassistant.view.MainActivity
import com.example.homefinancialassistant.viewmodels.AddConsumptionFragmentViewModel
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Calendar


class AddConsumptionFragment : Fragment() {

    private lateinit var binding: FragmentAddConsumptionBinding
    private val viewModel: AddConsumptionFragmentViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddConsumptionBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val scope = CoroutineScope(Dispatchers.Main)
        scope.launch {
            val list = viewModel.getCategoriesForAdapter()
            initButtons(list)
        }
    }

    private fun initButtons(listItems: List<String>) {
        viewModel.setToday()
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.simple_item, listItems)
        binding.enterCategory.setAdapter(arrayAdapter)
        binding.enterDate.setText(viewModel.calendarToString())
        binding.btnAddConsumption.setOnClickListener {
            viewModel.category.value = binding.enterCategory.text.toString()
            viewModel.description.value = binding.enterDescription.text.toString()
            viewModel.price.value = (binding.enterPrice.text.toString()).toDouble()
            viewModel.addConsumption()
            (activity as MainActivity).navController.popBackStack()
        }
        binding.btnCancel.setOnClickListener {
            (activity as MainActivity).navController.popBackStack()
        }
        binding.btnDate.setOnClickListener {
            btnDatePickerDialog()
        }
    }

    private fun btnDatePickerDialog() {
        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText(R.string.add_date)
            .build()
        datePicker.show(
            (activity as MainActivity).supportFragmentManager,
            "Date picker"
        )
        datePicker.addOnPositiveButtonClickListener {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = it
            viewModel.date.value = calendar
            binding.enterDate.setText(viewModel.calendarToString())
        }
        datePicker.addOnNegativeButtonClickListener {}
    }
}