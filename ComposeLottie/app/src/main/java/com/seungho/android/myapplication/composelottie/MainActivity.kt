package com.seungho.android.myapplication.composelottie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.compose.*
import com.seungho.android.myapplication.composelottie.ui.theme.ComposeLottieTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeLottieTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Box(modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.TopCenter
                        ) {
                        StarAnimation()
                        Loader()
                    }
                    Box(modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.BottomCenter
                        ) {
                        StarAnimation()
                        Star()
                    }

                }
            }
        }
    }
}

@Composable
fun Star() {
    val composition by rememberLottieComposition(LottieCompositionSpec.Asset("dev_star.json"))

    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever,
        clipSpec = LottieClipSpec.Progress(0f, 0.8f)
    )

    Column() {
        // Lottie 애니메이션 뷰
        LottieAnimation(
            composition,
            progress,
            modifier = Modifier
                .size(300.dp)
        )
    }
}

@Composable
fun StarAnimation() {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.Url("https://assets1.lottiefiles.com/packages/lf20_byBsNp.json")
    )

    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever
    )
    LottieAnimation(
        composition,
        progress,
        modifier = Modifier
            .fillMaxWidth(fraction = 0.8f)
            .height(300.dp)
//            .size(300.dp)
//            .background(Color.Yellow)
    )
}

@Composable
fun Loader() {

    // Lottie Json 파일 가져오기
    val composition by rememberLottieComposition(LottieCompositionSpec.Asset("dev_compose.json"))

    var isAnimationPlaying by remember{ mutableStateOf(true)}

//    composition: LottieComposition?,
//    isPlaying: Boolean = true,
//    restartOnPlay: Boolean = true,
//    clipSpec: LottieClipSpec? = null,
//    speed: Float = 1f,
//    iterations: Int = 1,
//    cancellationBehavior: LottieCancellationBehavior = LottieCancellationBehavior.Immediately,
//    ignoreSystemAnimatorScale: Boolean = false,

    // Lottie 애니메이션 설정정
   val progress by animateLottieCompositionAsState(
       composition,
       iterations = LottieConstants.IterateForever,
       clipSpec = LottieClipSpec.Progress(0f, 0.6f),
       isPlaying = isAnimationPlaying
   )
    
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        // Lottie 애니메이션 뷰
        LottieAnimation(
            composition,
            progress,
            modifier = Modifier
                .size(300.dp)
//                .background(Color.Yellow)
        )

//        Text("progress : $progress")
//        Button(onClick = {
//            isAnimationPlaying = !isAnimationPlaying
//        }) {
//            Text("Lottie Play/Pause")
//        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeLottieTheme {
        Greeting("Android")
    }
}