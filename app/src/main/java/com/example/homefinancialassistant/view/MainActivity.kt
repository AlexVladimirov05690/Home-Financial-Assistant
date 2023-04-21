package com.example.homefinancialassistant.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.homefinancialassistant.R
import com.example.homefinancialassistant.databinding.ActivityMainBinding
import com.example.homefinancialassistant.view.fragments.*
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val onBackPressedCallBack: OnBackPressedCallback =
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                showAlertDialog()
            }
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showAuthorizationFragment(AuthorizationFragment(), "authorization")
        initButtons()
        onBackPressedDispatcher.addCallback(this, onBackPressedCallBack)
    }


    private fun initButtons() {
        binding.bottomMainMenu.itemActiveIndicatorColor
        binding.bottomMainMenu.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.credit_calculator -> {
                    val tag = "credit_calculator"
                    val fragment = checkFragmentExistence(tag)
                    changeFragment(fragment ?: CreditCalculatorFragment(), tag)
                    true
                }
                R.id.rate -> {
                    val tag = "rate"
                    val fragment = checkFragmentExistence(tag)
                    changeFragment(fragment ?: ExchangeRatesFragment(), tag)
                    true
                }
                R.id.main -> {
                    val tag = "home"
                    val fragment = checkFragmentExistence(tag)
                    changeFragment(fragment ?: HomeFragment(), tag)
                    true
                }
                R.id.expense_journal -> {
                    val tag = "expense_journal"
                    val fragment = checkFragmentExistence(tag)
                    changeFragment(fragment ?: ExpenseJournalFragment(), tag)
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
            .addToBackStack(null)
            .commit()
    }

    private fun showAuthorizationFragment(fragment: AuthorizationFragment, tag: String) {
        supportFragmentManager
            .beginTransaction()
            .replace(binding.fragmentPlace.id, fragment, tag)
            .commit()
        binding.bottomMainMenu.isVisible = false
    }

    fun showAddFragment() {
        val fragment = AddConsumptionFragment()
        val tag = "add consumption"
        supportFragmentManager
            .beginTransaction()
            .replace(binding.fragmentPlaceExpenseJournal.id, fragment, tag)
            .addToBackStack(null)
            .commit()
    }

    fun closeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .remove(fragment)
            .commit()
    }

    private fun showAlertDialog() {

        MaterialAlertDialogBuilder(this)
            .setTitle(R.string.do_you_want_exit)
            .setIcon(R.drawable.ic_launcher_foreground)
            .setPositiveButton(R.string.yes) { _, _ ->
                finish()
            }
            .setNegativeButton(R.string.no, null)
            .show()

    }


}
