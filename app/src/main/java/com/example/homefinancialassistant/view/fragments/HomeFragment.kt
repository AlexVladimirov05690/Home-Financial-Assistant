package com.example.homefinancialassistant.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.homefinancialassistant.databinding.FragmentHomeBinding
import com.example.homefinancialassistant.viewmodels.HomeFragmentViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeFragmentViewModel by viewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.text.text = "здесь должны быть все траты из базы данных"
        val scope = CoroutineScope(Dispatchers.Main)
        scope.launch {
            Log.e("!!!", "запущена корутина")
            binding.text.text = viewModel.totalPrice.first().toString()
            withContext(Dispatchers.IO) {
                val map = viewModel.consumptionToMap()
                println("!!!$map")
                withContext(Dispatchers.Main) {
                    binding.costChartView.setCost(map)
                }
            }

        }

    }
//        scope = CoroutineScope(Dispatchers.IO).also { scope ->
//            val listCategory = scope.async {
//                viewModel.getUniqueCategoryFromDb()
//            }
//            val map = scope.async {
//                viewModel.consumptionToMap(listCategory.await())
//            }
//
//            scope.launch { binding.costChartView.setCost(map.await(), listCategory.await()) }
//            scope.launch {
//                binding.text.text = viewModel.returnPrice()
//            }
//        }


    //binding.costChartView.setCost(map, listOf("Еда", "Квартплата", "Автомобиль", "Прочее"))
//        scope = CoroutineScope(Dispatchers.IO)
//        scope.launch {
//            viewModel.getUniqueCategoryFromDb().forEach{
//                println("!!!$it")
//            }
}



