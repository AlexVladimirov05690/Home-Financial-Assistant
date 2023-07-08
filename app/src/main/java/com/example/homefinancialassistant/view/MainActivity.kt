package com.example.homefinancialassistant.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.homefinancialassistant.App
import com.example.homefinancialassistant.R
import com.example.homefinancialassistant.databinding.ActivityMainBinding
import com.example.homefinancialassistant.domain.Interactor
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var interactor: Interactor
    init {
        App.instance.dagger.inject(this)
    }
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
        navController = Navigation.findNavController(this, R.id.fragmentPlace)
        navController.navigate(showStartScreen(interactor.getDefaultScreen()))
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

                R.id.profile -> {
                    navController.navigate(R.id.settingsFragment)
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

    private fun showStartScreen(screen: String): Int {
        val screenInt: Int
        when(screen) {
            "Главный экран" -> screenInt = R.id.homeFragment
            "Кредитный калькулятор" -> screenInt = R.id.creditCalculatorFragment
            "Конвертёр валют" -> screenInt = R.id.exchangeRatesFragment
            "Журнал расходов" -> screenInt = R.id.expenseJournalFragment
            else -> screenInt = R.id.homeFragment
        }
        return screenInt
    }
}
