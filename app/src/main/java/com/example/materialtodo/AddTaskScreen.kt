package com.example.materialtodo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.materialtodo.components.TopBar
import com.example.materialtodo.data.Task
import com.example.materialtodo.ui.theme.Typography

@ExperimentalMaterial3Api
@Composable
fun AddTaskScreen(navController: NavController) {
    val selectedPriority = remember {
        mutableStateOf(0)
    }

    var newTask by remember {
        mutableStateOf(Task())
    }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            "Create new task",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Localized description"
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = { /* TODO */ }) {
                            Icon(
                                imageVector = Icons.Filled.DateRange,
                                contentDescription = "Localized description"
                            )
                        }
                        IconButton(onClick = { /* TODO */ }) {
                            Icon(
                                imageVector = Icons.Filled.Add,
                                contentDescription = "Localized description"
                            )
                        }
                    }
                )
            },
            content = { innerPadding ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .background(
                            color = MaterialTheme.colorScheme.background,
                            shape = RoundedCornerShape(16.dp)
                        )
                        .padding(innerPadding)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp, 0.dp)
                    ) {
                        OutlinedTextField(value = newTask.title, onValueChange = {
                            newTask = newTask.copy(title = it)
                        },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp),
                            label = {
                                Text(text = "Name")
                            }
                        )
                        OutlinedTextField(value = newTask.description, onValueChange = {
                            newTask = newTask.copy(description = it)
                        },
                            modifier = Modifier
                                .fillMaxWidth(),
                            label = {
                                Text(text = "Description")
                            }
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp)
                        ) {
                            Text(
                                text = "Priority",
                                style = TextStyle(
                                    fontFamily = FontFamily.Default,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 8.dp)
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp)
                        ){
                            Button(
                                onClick = { selectedPriority.value = 0 },
                                modifier = Modifier
                                    .fillMaxWidth(0.33f)
                                    .padding(end = 8.dp),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text(text = "Low")
                            }
                            Button(onClick = { selectedPriority.value = 1},
                                modifier = Modifier
                                    .fillMaxWidth(0.5f)
                                    .padding(end = 8.dp),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text(text = "Medium")
                            }
                            Button(onClick = { selectedPriority.value = 2 },
                                modifier = Modifier
                                    .fillMaxWidth(),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text(text = "High")
                            }
                        }
                    }
                }
            },
            bottomBar = {
                //TODO Navigation bar
            }
        )
    }
}