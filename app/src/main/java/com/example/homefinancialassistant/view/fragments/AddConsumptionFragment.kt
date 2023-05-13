package com.example.homefinancialassistant.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.homefinancialassistant.databinding.FragmentAddConsumptionBinding
import com.example.homefinancialassistant.view.MainActivity
import com.example.homefinancialassistant.viewmodels.AddConsumptionFragmentViewModel


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
        initButtons()
    }

    private fun initButtons() {
        binding.btnAddConsumption.setOnClickListener {
            viewModel.category.value = binding.enterCategory.text.toString()
            viewModel.description.value = binding.enterDescription.text.toString()
            viewModel.price.value = (binding.enterPrice.text.toString()).toDouble()
            viewModel.addConsumption()
            (activity as MainActivity).closeFragment(this)
        }
        binding.btnCancel.setOnClickListener {
            (activity as MainActivity).closeFragment(this)
        }
    }
}