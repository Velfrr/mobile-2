package com.lab2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lab2.ui.theme.Lab2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab2Theme {
                MainActivityScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainActivityScreen() {
    val itemList = remember { mutableStateListOf<Item>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.White)
            .clip(RoundedCornerShape(20.dp))
    ) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .weight(7.0f)
                .background(Color.White)
                .padding(10.dp)
        ) {
            if (itemList.isEmpty()) {
                item {
                    Text(
                        text = "Поки немає завдань",
                        modifier = Modifier.padding(end = 130.dp),
                        color = Color.Black,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            } else {
                items(itemList) { item ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(15.dp))
                            .background(Color.Black)
                            .padding(10.dp)
                    ) {
                        Text(text = "Завдання: ${item.name}", color = Color.White)
                        Text(text = "Опис: ${item.task}", color = Color.White)

                        Spacer(modifier = Modifier.height(10.dp))

                        Button(
                            onClick = { itemList.remove(item) },
                            modifier = Modifier.align(Alignment.End),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Red
                            ),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Text(text = "Видалити", color = Color.White)
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxSize()
                .weight(3.0f)
                .background(Color.White)
                .clip(RoundedCornerShape(0.dp))
        ) {
            val textFieldName = remember { mutableStateOf("") }
            val textFieldTask = remember { mutableStateOf("") }

            TextField(
                value = textFieldName.value,
                onValueChange = { newName -> textFieldName.value = newName },
                modifier = Modifier
                    .size(385.dp, 60.dp)
                    .padding(1.dp),
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.DarkGray,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    unfocusedTextColor = Color.White,
                    focusedTextColor = Color.White
                ),
                placeholder = {
                    Text(
                        text = "Назва нотатки",
                        color = Color.White,
                        fontSize = 14.sp
                    )
                }
            )

            TextField(
                value = textFieldTask.value,
                onValueChange = { newTask -> textFieldTask.value = newTask },
                modifier = Modifier
                    .size(385.dp, 60.dp)
                    .padding(1.dp),
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.DarkGray,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    unfocusedTextColor = Color.White,
                    focusedTextColor = Color.White
                ),
                placeholder = {
                    Text(
                        text = "Завдання",
                        color = Color.White,
                        fontSize = 14.sp
                    )
                }
            )

            Button(
                onClick = {
                    if (textFieldName.value.isNotBlank() && textFieldTask.value.isNotBlank()) {
                        itemList.add(
                            Item(
                                name = textFieldName.value,
                                task = textFieldTask.value
                            )
                        )
                        textFieldName.value = ""
                        textFieldTask.value = ""
                    }
                },
                modifier = Modifier
                    .size(300.dp, 80.dp)
                    .padding(10.dp),
                shape = RoundedCornerShape(25.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.DarkGray
                )
            ) {
                Text(
                    text = "Додати завдання",
                    fontSize = 17.sp,
                    color = Color.White
                )
            }
        }
    }
}

data class Item(val name: String, val task: String)

@Preview
@Composable
fun MainActivityPreview() {
    Lab2Theme {
        MainActivityScreen()
    }
}
