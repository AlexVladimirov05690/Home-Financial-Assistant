package com.example.homefinancialassistant.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import com.example.homefinancialassistant.R
import com.example.homefinancialassistant.view.MainActivity
import com.example.homefinancialassistant.viewmodels.SettingFragmentViewModel

class SettingsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    SettingScreen(activity as MainActivity)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}




@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SettingScreen(activity: MainActivity) {
    val viewModel: SettingFragmentViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
    LazyColumn(
        content = {
            stickyHeader { Toolbar(viewModel = viewModel) }
            item { DarkTheme(viewModel = viewModel) }
            item { ChangeStartScreen(viewModel = viewModel, activity) }
        },
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    )

}

@Composable
fun Toolbar(viewModel: SettingFragmentViewModel) {
    val nameProfile by viewModel.nameProfile.collectAsState()
    Column(
        modifier = Modifier
            .height(90.dp)
            .fillMaxWidth()
            .padding(top = 10.dp)
    ) {
        Text(
            "Настройки", modifier = Modifier
                .fillMaxWidth(), textAlign = TextAlign.Center,
            style = TextStyle(color = Color.Black, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        )
        Row(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            Text(
                nameProfile, modifier = Modifier
                    .weight(0.7f)
                    .padding(start = 16.dp, top = 10.dp),
                style = TextStyle(color = Color.Black, fontSize = 20.sp)
            )
            Text(
                "Сменить профиль", modifier = Modifier
                    .weight(0.3f)
                    .padding(end = 10.dp, top = 10.dp)
            )
        }
    }
}

@Composable
fun DarkTheme(viewModel: SettingFragmentViewModel) {
    val darkTheme by viewModel.darkTheme.collectAsState()
    Row(
        modifier = Modifier
            .height(50.dp)
            .fillMaxWidth()
            .padding(top = 10.dp)
    ) {
        Text(
            changeTextTheme(darkTheme),
            modifier = Modifier
                .weight(0.6f)
                .padding(start = 10.dp, top = 7.dp),
            textAlign = TextAlign.Start,
            style = TextStyle(color = Color.Black, fontSize = 20.sp)
        )

        Button(onClick = {
            viewModel.changeTheme()
        }, modifier = Modifier.padding(end = 20.dp)) {

        }
    }
}

@Composable
fun ChangeStartScreen(viewModel: SettingFragmentViewModel, activity: MainActivity) {
    val startScreen by viewModel.starScreen.collectAsState()
    Row(
        modifier = Modifier
            .height(50.dp)
            .fillMaxWidth()
            .padding(top = 10.dp)
    ) {
        Column(modifier = Modifier.weight(0.7f)) {
            Text(
                "Выбрать стартовый экран",
                modifier = Modifier
                    .weight(0.6f)
                    .padding(start = 10.dp),
                textAlign = TextAlign.Start,
                style = TextStyle(color = Color.Black, fontSize = 20.sp)
            )
            Text(
                startScreen,
                modifier = Modifier
                    .weight(0.3f)
                    .padding(start = 20.dp),
                style = TextStyle(color = Color.Black, fontSize = 12.sp)
            )
        }

        Button(onClick = {
                         activity.navController.navigate(R.id.changeStartScreenFragment)
        }, modifier = Modifier.padding(end = 20.dp)) {

        }
    }
}

fun changeTextTheme(boolean: Boolean): String {
    return if (boolean) {
        "Включить тёмную тему"
    } else "Включить светлую тему"
}

fun openChangeStartScreenFragment() {

}

