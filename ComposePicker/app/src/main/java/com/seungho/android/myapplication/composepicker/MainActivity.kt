package com.seungho.android.myapplication.composepicker

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.seungho.android.myapplication.composepicker.ui.theme.ComposePickerTheme
import java.time.LocalDate

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePickerTheme {
                // A surface container using the 'background' color from the theme

                val isDialogOpen = remember { mutableStateOf(false)}
                val selectedDate : MutableState<LocalDate?> = remember { mutableStateOf(null)}

                val isTimePickerOpen = remember { mutableStateOf(false)}
                val selectedTime : MutableState<TimeState?> = remember { mutableStateOf(null)}
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column() {
                        SelectedDateLabel(selectedDate.value.toString())

                        selectedTime.value?.getLabelText()?.let { SelectedDateLabel(it)}

                        TextButton(onClick = {
                            isDialogOpen.value = !isDialogOpen.value
                        }) {
                            Text(text = "데이트 파커 열기")
                        }

                        TextButton(onClick = {
                            isTimePickerOpen.value = !isTimePickerOpen.value
                        }) {
                            Text(text = "타임피커 열기")
                        }

                        if (isDialogOpen.value) {
                            MyDatePickerDialog(onDateSelected = {
                                Log.d("TAG", "MyDatePicker: 선택한 날짜: $it")
                                selectedDate.value = it
                            }, onDismissRequest = {
                                Log.d("TAG", "MyDatePicker: 닫아짐")
                                isDialogOpen.value = false
                            })
                        }

                        if (isTimePickerOpen.value) {
                            MyTimePickerDialog(onTimeSelected = {
                                Log.d("TAG", "MyTimePicker: 선택한 날짜: $it")
                                selectedTime.value = it
                            }, onDismissRequest = {
                                Log.d("TAG", "MyDatePicker: 닫아짐")
                                isTimePickerOpen.value = false
                            })
                        }

                    }
                }
            }
        }
    }
}

@Composable
fun SelectedDateLabel(name: String) {
    Text(text = "선택한 날짜는: $name 입니다")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposePickerTheme {
        SelectedDateLabel("Android")
    }
}