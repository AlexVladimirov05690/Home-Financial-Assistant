package com.example.homefinancialassistant.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.homefinancialassistant.R
import com.example.homefinancialassistant.databinding.ActivityMainBinding
import com.example.homefinancialassistant.view.fragments.CreditCalculatorFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initButtons()
    }


    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 0) {
            AlertDialog.Builder(this)
                .setTitle(R.string.do_you_want_exit)
                .setIcon(R.drawable.ic_launcher_foreground)
                .setPositiveButton(R.string.yes) { _, _ ->
                    finish()
                }
                .setNegativeButton(R.string.no) { _, _ ->
                }
                .show()
        } else {
            super.onBackPressed()
        }
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