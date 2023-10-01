package com.example.homefinancialassistant.compose.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.homefinancialassistant.data.CategoryConsumption
import com.example.homefinancialassistant.viewmodels.HomeScreenViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
@Preview
fun HomeScreen() {
    val viewModel: HomeScreenViewModel = viewModel()
    val list = remember{viewModel.categoryConsumptionList}
    //viewModel.updateList()
    Column(modifier = Modifier.fillMaxSize()) {
        Title()
        ListItems(list = list.value)
        BtnChangeList(viewModel = viewModel)
    }
}



@Composable
fun Item(categoryConsumption: CategoryConsumption) {
    Row {
        Text(modifier = Modifier.weight(0.5f), text = categoryConsumption.color.toString())
        Text(modifier = Modifier.weight(2f), text = categoryConsumption.category)
        Text(modifier = Modifier.weight(1f), text = categoryConsumption.price.toString() + " rub.")
        Text(modifier = Modifier.weight(1f), text = categoryConsumption.percent.toString() + " %")
    }
}

@Composable
fun ListItems(list: List<CategoryConsumption>) {
    if(list.isNotEmpty()) {
        LazyColumn {
            item(list.size) {
                list.forEach {
                    Item(it)
                }
            }
        }
    }
    else {
        Text("Пусто")
    }
}

@Composable
fun Title() {
    Text("Домашний экран")
}

@Composable
fun BtnChangeList(viewModel: HomeScreenViewModel) {
    Button(onClick = { viewModel.changeTest() }) {
        
    }
}

