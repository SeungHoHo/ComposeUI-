package com.seungho.android.myapplication.composepicker

data class TimeState(
    val hour: Int = 0,
    val minute: Int = 0
)

fun TimeState.getLabelText() : String {
    return "$hour 시 $minute 분"
}