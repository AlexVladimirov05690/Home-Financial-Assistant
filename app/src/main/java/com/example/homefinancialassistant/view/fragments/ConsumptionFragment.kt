package com.example.homefinancialassistant.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.homefinancialassistant.R
import com.example.homefinancialassistant.data.Consumption
import com.example.homefinancialassistant.databinding.FragmentConsumptionBinding
import com.example.homefinancialassistant.view.MainActivity
import com.example.homefinancialassistant.viewmodels.ConsumptionFragmentViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class ConsumptionFragment: Fragment() {
    private lateinit var binding: FragmentConsumptionBinding
    private val viewModel: ConsumptionFragmentViewModel by viewModels()
    private lateinit var consumption: Consumption

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentConsumptionBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        consumption = arguments?.getParcelable("consumption") ?: consumptionDefault
        binding.categoryConsumption.text = consumption.category
        binding.dateConsumption.text = calendarToString(consumption.date)
        binding.descConsumption.text = consumption.description
        binding.priceConsumption.text = getString(R.string.all_consumption_home, consumption.price.toString())
        initButton()
    }

    private fun calendarToString(calendar: Calendar): String {
        val sdf = SimpleDateFormat(DATE_FORMAT, Locale.ROOT)
        val date = calendar.time
        return sdf.format(date)
    }

    private fun initButton() {
        binding.buttonDelete.setOnClickListener{
            viewModel.deleteConsumption(consumption)
            (requireActivity() as MainActivity).navController.popBackStack()
        }
        binding.buttonCancel.setOnClickListener {
            (requireActivity() as MainActivity).navController.popBackStack()
        }
    }

    companion object {
        val consumptionDefault = Consumption(0, Calendar.getInstance(), "", 0.0, "")
        const val DATE_FORMAT = "dd.MM.yyyy"
    }
}