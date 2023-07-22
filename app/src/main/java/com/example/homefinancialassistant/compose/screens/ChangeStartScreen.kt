package com.example.homefinancialassistant.compose.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.homefinancialassistant.R
import com.example.homefinancialassistant.view.MainActivity
import com.example.homefinancialassistant.viewmodels.ChangeStartScreenViewModel

@Composable
fun ChangeStartScreen(activity: MainActivity) {
    val viewModel: ChangeStartScreenViewModel = viewModel()
    val listScreens by viewModel.listOfScreen.collectAsState()
    LazyColumn(
        content = {
            item {
                Text(
                    "Выбор стартового экрана", modifier = Modifier
                        .fillMaxWidth(), textAlign = TextAlign.Center,
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.outline,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
            item {

                Column(Modifier.selectableGroup()) {
                    val (selectedOption, onOptionSelect) = remember {
                        mutableStateOf(listScreens[viewModel.numberOfListScreensDefault()])
                    }
                    listScreens.forEach { screen ->
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .height(40.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = (screen == selectedOption),
                                onClick = {
                                    onOptionSelect(screen)
                                    viewModel.changedSelectedScreen(screen)
                                })
                            Text(text = screen, style = TextStyle(
                                color = MaterialTheme.colorScheme.outline
                            ))
                        }
                    }
                }
            }
            item {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    FloatingActionButton(modifier = Modifier
                        .padding(end = 20.dp),
                        onClick = {
                            viewModel.changedDefaultScreen()
                            activity.navController.navigate(R.id.settingsFragment)
                        }) {
                        Icon(Icons.Filled.Done, contentDescription = null)
                    }

                }
            }

        }, modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize(), horizontalAlignment = Alignment.End
    )
}

