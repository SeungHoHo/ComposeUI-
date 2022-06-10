package com.seungho.android.myapplication.composebox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.seungho.android.myapplication.composebox.ui.theme.ComposeBoxTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBoxTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
//                    BoxContainer()
                    BoxWithConstraintContainer()
                }
            }
        }
    }
}
// 박스는 겹칠수 있다.
// 기존 relative layout, constriant layout, framelayout 과 같이 뷰 겹치기가 가능
// 아래로 내려갈수록 위에 뷰를 올리는 방식

// alignment는 row, column 보다 다양하게 지원

// Alignment.BottomCenter : 컨테이너의 중앙 아래
// Alignment.BottomEnd : 컨테이너의 아래 오른쪽
// Alignment.BottomStart : 컨테이너의 아래 왼쪽

// Alignment.Center : 컨테이너의 정중앙
// Alignment.CenterStart : 컨테이너의 중앙 왼쪽
// Alignment.CenterEnd : 컨테이너의 중앙 오른쪽

// Alignment.TopCenter : 컨테이너의 위 중앙
// Alignment.TopEnd : 컨테이너의 위 오른쪽
// Alignment.TopStart : 컨테이너의 위 왼쪽

// propagateMinConstraints 해당 옵션을 true로 하면
// 박스 안에 있는 제일 작은 크기의 뷰를 컨테이너 박스의 크기 만큼 컨스트레인트를 겁니다.

@Composable
fun BoxContainer() {
    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
//        propagateMinConstraints = true
    ) {
        DummyBox(modifier = Modifier.size(400.dp), color = Color.Gray)
        DummyBox(modifier = Modifier.size(300.dp), color = Color.Red)
        DummyBox(modifier = Modifier.size(250.dp), color = Color.Green)
        DummyBox(modifier = Modifier.size(200.dp), color = Color.Blue)
        DummyBox(modifier = Modifier.size(150.dp), color = Color.White)
        DummyBox(color = Color.Black)

    }
}

@Composable
fun BoxWithConstraintContainer() {
    BoxWithConstraints(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
        propagateMinConstraints = false
    ) {

//        if (this.minHeight > 400.dp) {
//            DummyBox(modifier = Modifier.size(200.dp), color = Color.Green)
//        } else {
//            DummyBox(modifier = Modifier.size(200.dp), color = Color.Yellow)
//        }
//        Text(text = "minHeight: ${this.minHeight}")

        Column {
            BoxWithConstraintItem(modifier = Modifier
                .size(200.dp)
                .background(Color.Yellow)
            )
            BoxWithConstraintItem(modifier = Modifier
                .size(300.dp)
                .background(Color.Green)
            )
        }
    }
}

@Composable
fun BoxWithConstraintItem(modifier: Modifier = Modifier) {
    BoxWithConstraints(
        modifier = modifier,
        contentAlignment = Alignment.Center,
        propagateMinConstraints = false
    ) {
        if (this.minWidth > 200.dp) {
            Text(text = "이것은 큰 상자이다")
        } else {
            Text(text = "이것은 작은 상자이다")
        }
    }
}

@Composable
fun DummyBox(modifier: Modifier = Modifier, color: Color? = null) {

    val red = Random.nextInt(256)
    val green = Random.nextInt(256)
    val blue = Random.nextInt(256)

    // color 가 값이 있으면 해당 값을 넣어주고 값이 없다면 랜덤 값을 넣어주기
    val randomColor = color?.let { it } ?: Color(red, green, blue)
    Box(modifier = modifier
        .size(100.dp)
        .background(randomColor)) {

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeBoxTheme {
//        BoxContainer()
        BoxWithConstraintContainer()
    }
}