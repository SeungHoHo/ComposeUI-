package com.seungho.android.myapplication.composebasic

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.seungho.android.myapplication.composebasic.ui.theme.ComposeBasicTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBasicTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("안드입니다?")
                }
            }
        }
    }
}

// Scaffold = Material 지원
// 뷰
@Composable
fun Greeting(name: String) {
    Scaffold(topBar = {
        TopAppBar(title = {Text("컴포즈 안드 앱 사용해보기")}
        ,backgroundColor = Color(0xfff25287))  },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = { FloatingActionButton(onClick = {}){
            Text("버튼 클릭")
        } },
    ) {
//        Text(text = "안녕하세요?! $name!")
        MyComposableView()
    }
}

@Composable
fun MyRowItem() {
    Log.d("TAG", "MyRowItem: ")
    // Horizontal Linear
    Row(
        Modifier
            .padding(10.dp)
            .background(Color(0xffeaffd0))
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "안녕하세요?!",
            Modifier
                .padding(all = 10.dp)
                .background(Color.Yellow))
        Spacer(modifier = Modifier.size(10.dp))
        Text(text = "안녕하세요?!")
        Spacer(modifier = Modifier.size(10.dp))
        Text(text = "안녕하세요?!")
    }
}

@Composable
fun MyComposableView() {
    Log.d("TAG", "MyComposableView: ")

    // Vertical Linear
    Column(
        Modifier
            .background(Color.Green)
            .verticalScroll(rememberScrollState())
    ) {
        for (i in 0..30) {
            MyRowItem()
        }
    }
}


// 미리보기
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeBasicTheme {
        Greeting("안드입니다?")
    }
}