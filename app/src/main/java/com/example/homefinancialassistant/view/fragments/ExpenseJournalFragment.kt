package com.example.homefinancialassistant.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homefinancialassistant.R
import com.example.homefinancialassistant.data.Consumption
import com.example.homefinancialassistant.databinding.FragmentExpenseJournalBinding
import com.example.homefinancialassistant.view.MainActivity
import com.example.homefinancialassistant.view.adapters.ExpenseRecyclerViewAdapter
import com.example.homefinancialassistant.view.adapters.TopSpacingItemDecoration
import com.example.homefinancialassistant.viewmodels.ExpenseJournalFragmentViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ExpenseJournalFragment : Fragment() {
    private lateinit var binding: FragmentExpenseJournalBinding
    private lateinit var consumptionAdapter: ExpenseRecyclerViewAdapter
    private lateinit var scope: CoroutineScope
    private val viewModel: ExpenseJournalFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentExpenseJournalBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initButtons()
        initAdapter()
        scope = CoroutineScope(Dispatchers.IO).also { scope ->
            scope.launch {
                viewModel.listConsumption.collect{
                    withContext(Dispatchers.Main) {
                        consumptionAdapter.submitList(it)
                    }
                }
            }
        }
    }

    private fun initButtons() {
        binding.addConsumptionFabButton.setOnClickListener {
            (activity as MainActivity).navController.navigate(R.id.addConsumptionFragment)
        }
    }

    private fun initAdapter() {
        binding.consumptionRecyclerView.apply {
            consumptionAdapter = ExpenseRecyclerViewAdapter(object : ExpenseRecyclerViewAdapter.OnOpenConsumption {
                override fun openConsumption(consumption: Consumption) {
                    //(requireActivity() as MainActivity).launchConsumptionFragment(consumption)
                    val bundle = Bundle()
                    bundle.putParcelable("consumption", consumption)
                    (activity as MainActivity).navController.navigate(R.id.consumptionFragment, bundle)
                }
            })
            adapter = consumptionAdapter
            layoutManager = LinearLayoutManager(requireContext())
            val decorator = TopSpacingItemDecoration(8)
            addItemDecoration(decorator)
        }
    }
}