package com.example.homefinancialassistant.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homefinancialassistant.App
import com.example.homefinancialassistant.R
import com.example.homefinancialassistant.databinding.FragmentHomeBinding
import com.example.homefinancialassistant.utils.MathHelper
import com.example.homefinancialassistant.view.MainActivity
import com.example.homefinancialassistant.view.adapters.HomeCategoryColorsAdapter
import com.example.homefinancialassistant.view.adapters.TopSpacingItemDecoration
import com.example.homefinancialassistant.viewmodels.HomeFragmentViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeFragmentViewModel by viewModels()
    private lateinit var homeCategoryColorsAdapter: HomeCategoryColorsAdapter

    @Inject
    lateinit var mathHelper: MathHelper

    init {
        App.instance.dagger.inject(this)
    }

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
        binding.launchNewHomeScreen.setOnClickListener {
            (activity as MainActivity).navController.navigate(R.id.homeScreenFragment)
        }
        val scope = CoroutineScope(Dispatchers.Main)
        scope.launch {
            withContext(Dispatchers.IO) {
                val totalPriceFromDb = viewModel.getTotalConsumptionPrice()
                val balance = viewModel.getBalance()
                viewModel.updateSpendingByCategory()
                withContext(Dispatchers.Main) {
                    binding.text.text =
                        getString(R.string.add_rub_in_string, totalPriceFromDb.toString())
                    binding.balance.text =
                        getString(R.string.add_rub_in_string, balance.toString())
                }
            }
            withContext(Dispatchers.IO) {
                val map = viewModel.consumptionToMap()
                if (map.isNotEmpty()) {
                    withContext(Dispatchers.Main) {
                        binding.costChartView.setCost(map)
                    }
                }
            }
            withContext(Dispatchers.IO) {
                val listWithAdapter = binding.costChartView.getCategoryAndColors()
                    .sortedBy {
                        it.categoryPercent
                    }
                    .reversed()
                listWithAdapter.forEach {
                    it.categoryPercent = mathHelper.rounding(it.categoryPercent)
                    it.categoryPrice = mathHelper.rounding(viewModel.categoryPrice(it.categoryName))
                }
                withContext(Dispatchers.Main) {
                    homeCategoryColorsAdapter.submitList(listWithAdapter)
                }
            }
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



