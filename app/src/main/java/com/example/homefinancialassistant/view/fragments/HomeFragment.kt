package com.example.homefinancialassistant.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homefinancialassistant.R
import com.example.homefinancialassistant.databinding.FragmentHomeBinding
import com.example.homefinancialassistant.view.adapters.HomeCategoryColorsAdapter
import com.example.homefinancialassistant.view.adapters.TopSpacingItemDecoration
import com.example.homefinancialassistant.viewmodels.HomeFragmentViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeFragmentViewModel by viewModels()
    private lateinit var homeCategoryColorsAdapter: HomeCategoryColorsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        val scope = CoroutineScope(Dispatchers.Main)
        scope.launch {
            withContext(Dispatchers.IO) {
                val totalPriceFromDb = viewModel.getTotalConsumptionPrice()
                withContext(Dispatchers.Main) {
                    binding.text.text = getString(R.string.all_consumption_home, totalPriceFromDb.toString())

                }
            }
            withContext(Dispatchers.IO) {
                val map = viewModel.consumptionToMap()
                if (map.isNotEmpty()) {
                    withContext(Dispatchers.Main) {
                        binding.costChartView.setCost(map)
                        val list = binding.costChartView.getCategoryAndColors()
                        println("!!!$list")
                    }
                }
            }


            homeCategoryColorsAdapter.submitList(binding.costChartView.getCategoryAndColors())
        }
    }

    private fun initAdapter() {
        binding.consumptionRecyclerViewHome.apply {
            homeCategoryColorsAdapter = HomeCategoryColorsAdapter()
            adapter = homeCategoryColorsAdapter
            layoutManager = LinearLayoutManager(requireContext())
            val decorator = TopSpacingItemDecoration(8)
            addItemDecoration(decorator)
        }
    }
}



