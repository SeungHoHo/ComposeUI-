package com.seungho.android.myapplication.composepicker

import android.util.Log
import android.widget.TimePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun MyTimePickerDialog(
    onTimeSelected: (TimeState) -> Unit,
    onDismissRequest: () -> Unit
) {

    val selectedTime: MutableState<TimeState> = remember { mutableStateOf(TimeState()) }

    Dialog(
        onDismissRequest = { onDismissRequest() },
        properties = DialogProperties()
    ) {

        Column(
            modifier = Modifier
                .wrapContentSize()
                .background(
                    color = MaterialTheme.colors.surface,
                    shape = RoundedCornerShape(size = 16.dp)
                )
        ) {

            Text(
                text = selectedTime.value.getLabelText(),
                modifier = Modifier.padding(10.dp)
            )

            CustomTimePickerView(onTimeSelected = {
                selectedTime.value = it
            })

            Spacer(modifier = Modifier.size(8.dp))

            Row(
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(bottom = 16.dp, end = 16.dp)
            ) {
                TextButton(
                    onClick = onDismissRequest
                ) {
                    Text(
                        text = "닫기"
                    )
                }

                TextButton(
                    onClick = {
                        onTimeSelected(selectedTime.value)
                        onDismissRequest
                    }
                ) {
                   Text(
                       text = "선택"
                   )
                }
            }
        }
    }
}

@Composable
fun CustomTimePickerView(onTimeSelected: (TimeState) -> Unit) {
    AndroidView(
        modifier = Modifier.wrapContentSize(),
        factory = { context ->
            TimePicker(context)
        },

        update = { view ->
            view.setOnTimeChangedListener { _, hour, min ->
                Log.d("TAG", "TimePicker: onTimeSelected : ")
                onTimeSelected(TimeState(hour, min))
            }
        }
    )
}