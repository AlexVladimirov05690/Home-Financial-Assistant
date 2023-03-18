package com.example.homefinancialassistant.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.homefinancialassistant.R
import com.example.homefinancialassistant.databinding.ActivityMainBinding
import com.example.homefinancialassistant.view.fragments.CreditCalculatorFragment
import com.example.homefinancialassistant.view.fragments.ExchangeRatesFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initButtons()
    }





    private fun initButtons(){
        binding.bottomMainMenu.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.credit_calculator -> {
                    val tag = "credit_calculator"
                    val fragment = checkFragmentExistence(tag)
                    changeFragment(fragment?: CreditCalculatorFragment(), tag)
                    true
                }
                R.id.rate -> {
                    val tag = "rate"
                    val fragment = checkFragmentExistence(tag)
                    changeFragment(fragment?: ExchangeRatesFragment(), tag)
                    true
                }
                else -> false
            }

        }
    }
    private fun checkFragmentExistence(tag: String): Fragment? =
        supportFragmentManager.findFragmentByTag(tag)

    private fun changeFragment(fragment: Fragment, tag: String) {
        supportFragmentManager
            .beginTransaction()
            .replace(binding.fragmentPlace.id, fragment, tag)
            .commit()
    }
}