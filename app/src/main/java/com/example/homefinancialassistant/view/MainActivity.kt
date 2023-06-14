package com.example.homefinancialassistant.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.homefinancialassistant.R
import com.example.homefinancialassistant.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController
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
        binding.bottomMainMenu.isVisible = false
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        navController.navigate(R.id.authorizationFragment)
        initButtons()
        onBackPressedDispatcher.addCallback(this, onBackPressedCallBack)
    }

    private fun initButtons() {
        binding.bottomMainMenu.itemActiveIndicatorColor
        binding.bottomMainMenu.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.creditCalculator -> {
                    navController.navigate(R.id.creditCalculatorFragment)
                    true
                }

                R.id.rate -> {
                    navController.navigate(R.id.exchangeRatesFragment)
                    true
                }

                R.id.main -> {
                    navController.navigate(R.id.homeFragment)
                    true
                }

                R.id.expenseJournal -> {
                    navController.navigate(R.id.expenseJournalFragment)
                    true
                }

                else -> false
            }

        }
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
