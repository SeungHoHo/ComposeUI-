package com.seungho.android.myapplication.composekakaologin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seungho.android.myapplication.composekakaologin.ui.theme.ComposeKakaoLoginTheme

class MainActivity : ComponentActivity() {

    private val kakaoAuthViewModel: KakaoAuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeKakaoLoginTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    KakaoLoginView(kakaoAuthViewModel)
                }
            }
        }
    }
}

@Composable
fun KakaoLoginView(viewModel: KakaoAuthViewModel) {

    val isLoggedIn = viewModel.isLoggedIn.collectAsState()

    val loginStatusInfoTitle = if (isLoggedIn.value) "로그인 상태" else "로그아웃 상태"

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(top = 80.dp)
    ) {
        Text(text = "안녕하세요!", fontSize = 20.sp , fontWeight = FontWeight.Bold)
        Text(text = "다시 만나서 반가워요 :)", fontSize = 20.sp, fontWeight = FontWeight.Bold)
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = {
             viewModel.kakaoLogin()
        }) {
            Text(text = "카카오 로그인하기")
        }
        Button(onClick = {
            viewModel.kakaoLogout()
        }) {
            Text(text = "카카오 로그아웃하기")
        }
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier.padding(bottom = 180.dp)
        ) {
        Text(text = loginStatusInfoTitle, textAlign = TextAlign.Center, fontSize = 20.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeKakaoLoginTheme {

    }
}