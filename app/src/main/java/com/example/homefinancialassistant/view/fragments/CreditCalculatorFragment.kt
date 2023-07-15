package com.example.homefinancialassistant.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.example.homefinancialassistant.R
import com.example.homefinancialassistant.databinding.FragmentCreditCalculatorBinding
import com.example.homefinancialassistant.viewmodels.CreditCalculatorFragmentViewModel

class CreditCalculatorFragment : Fragment() {
    private lateinit var binding: FragmentCreditCalculatorBinding
    private val viewModel: CreditCalculatorFragmentViewModel by viewModels()
    private var change: Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreditCalculatorBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFields(change)
        initButtons()
    }

    private fun initButtons(){

        binding.btnCalculation.setOnClickListener {
            if(change == 0) {
                viewModel.amount.value = textToDouble(binding.enterAmountOfCreditContainer.editText?.text.toString())
                viewModel.tern.value = textToDouble(binding.enterTernOfCreditContainer.editText?.text.toString())
                viewModel.percent.value = textToDouble(binding.enterPercentOfCreditContainer.editText?.text.toString())
                viewModel.calculation()
            } else {
                viewModel.amount.value = textToDouble(binding.enterAmountOfCreditContainer.editText?.text.toString())
                viewModel.tern.value = textToDouble(binding.enterTernOfCreditContainer.editText?.text.toString())
                viewModel.monthlyPaymentBack.value = textToDouble(binding.monthlyPaymentContainerBack.editText?.text.toString())
                viewModel.calculationBack()
            }
        }
        binding.btnSwitchButton.setOnClickListener {
            changeId(change)
            initFields(change)
        }
    }

    private fun initFields(change: Int) {
        if(change == 0) {
            binding.topHeadline.text = getString(R.string.credit_calculator)
            binding.enterPercentOfCreditContainer.isVisible = true
            binding.monthlyPaymentContainerBack.isVisible = false
            binding.enterPercentOfCredit.isVisible = true
            binding.monthlyPaymentField.text = getString(R.string.monthly_payment)
            binding.monthlyPaymentValue.text = getString(R.string.null_number)
            binding.overpayValue.text = getString(R.string.null_number)

            viewModel.monthlyPayment.observe(viewLifecycleOwner) {
                binding.monthlyPaymentValue.text = it.toString()
            }
            viewModel.overpayment.observe(viewLifecycleOwner) {
                binding.overpayValue.text = it.toString()
            }
        } else {
            binding.topHeadline.text = getString(R.string.credit_calculator_back)
            binding.enterPercentOfCreditContainer.isVisible = false
            binding.monthlyPaymentContainerBack.isVisible = true
            binding.enterPercentOfCredit.isVisible = false
            binding.monthlyPaymentField.text = getString(R.string.percent_back)
            binding.monthlyPaymentValue.text = getString(R.string.null_number)
            binding.overpayValue.text = getString(R.string.null_number)

            viewModel.percentBack.observe(viewLifecycleOwner) {
                binding.monthlyPaymentValue.text = it.toString()
            }
            viewModel.overpayment.observe(viewLifecycleOwner) {
                binding.overpayValue.text = it.toString()
            }
        }
    }

    private fun textToDouble(string: String) : Double {
        return string.toDouble()
    }

    private fun changeId(change: Int) {
        if(change == 0) {
            this.change = 1
        } else this.change = 0
    }

}