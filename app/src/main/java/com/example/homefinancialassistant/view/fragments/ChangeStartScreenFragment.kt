package com.example.homefinancialassistant.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.example.homefinancialassistant.compose.screens.ChangeStartScreenView
import com.example.homefinancialassistant.compose.ui.HomeFinancialAssistantTheme
import com.example.homefinancialassistant.view.MainActivity

class ChangeStartScreenFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                HomeFinancialAssistantTheme() {
                    Surface(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        ChangeStartScreenView(activity as MainActivity)
                    }
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}