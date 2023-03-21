package com.example.homefinancialassistant.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.homefinancialassistant.R
import com.example.homefinancialassistant.databinding.ActivityMainBinding
import com.example.homefinancialassistant.view.fragments.AuthorizationFragment
import com.example.homefinancialassistant.view.fragments.CreditCalculatorFragment
import com.example.homefinancialassistant.view.fragments.ExchangeRatesFragment
import com.example.homefinancialassistant.view.fragments.HomeFragment

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showAuthorizationFragment(AuthorizationFragment(), "authorization")
        initButtons()
    }





    private fun initButtons(){
        binding.bottomMainMenu.itemActiveIndicatorColor
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
                R.id.main -> {
                    val tag = "home"
                    val fragment = checkFragmentExistence(tag)
                    changeFragment(fragment?: HomeFragment(), tag)
                    true
                }
                else -> false
            }

        }
    }
    private fun checkFragmentExistence(tag: String): Fragment? =
        supportFragmentManager.findFragmentByTag(tag)

    fun changeFragment(fragment: Fragment, tag: String) {
        supportFragmentManager
            .beginTransaction()
            .replace(binding.fragmentPlace.id, fragment, tag)
            .commit()
    }

    private fun showAuthorizationFragment(fragment: AuthorizationFragment, tag: String) {
        supportFragmentManager
            .beginTransaction()
            .replace(binding.fragmentPlace.id, fragment, tag)
            .commit()
        binding.bottomMainMenu.isVisible = false
    }
}