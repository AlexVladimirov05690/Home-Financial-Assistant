package com.example.homefinancialassistant.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.homefinancialassistant.databinding.FragmentCreditCalculatorBinding
import com.example.homefinancialassistant.viewmodels.CreditCalculatorFragmentViewModel

class CreditCalculatorFragment : Fragment() {
    private lateinit var binding: FragmentCreditCalculatorBinding
    private val viewModel: CreditCalculatorFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreditCalculatorBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.monthlyPayment.observe(viewLifecycleOwner) {
            binding.monthlyPaymentContainer.hint = it.toString()
        }
        viewModel.overpayment.observe(viewLifecycleOwner) {
            binding.overpaymentContainer.hint = it.toString()
        }
        initButtons()


    }

    private fun initButtons(){
        binding.calculation.setOnClickListener {
            viewModel.amount.value = textToDouble(binding.enterAmountOfCreditContainer.editText?.text.toString())
            viewModel.tern.value = textToDouble(binding.enterTernOfCreditContainer.editText?.text.toString())
            viewModel.percent.value = textToDouble(binding.enterPercentOfCreditContainer.editText?.text.toString())
            viewModel.calculation()
        }
    }

    private fun textToDouble(string: String) : Double {
        return string.toDouble()
    }

}