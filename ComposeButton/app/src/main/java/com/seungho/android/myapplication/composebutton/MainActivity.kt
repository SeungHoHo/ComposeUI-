package com.seungho.android.myapplication.composebutton

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.seungho.android.myapplication.composebutton.R.color
import com.seungho.android.myapplication.composebutton.ui.theme.ComposeButtonTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeButtonTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ButtonsContainer()
                }
            }
        }
    }
}
//onClick: () -> Unit,
//modifier: Modifier = Modifier,
//enabled: Boolean = true,
//interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
//elevation: ButtonElevation? = ButtonDefaults.elevation(),
//shape: Shape = MaterialTheme.shapes.small,
//border: BorderStroke? = null,
//colors: ButtonColors = ButtonDefaults.buttonColors(),
//contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
//content: @Composable RowScope.() -> Unit

// Button
// enable : ???????????? ??????
// interactionSource : ???????????? ????????????(?????????,?????? ???) ??????
// elevation : ?????????, ??? ????????? ?????? ???????????? ????????? ?????????
// ????????? ????????? ?????? ???
// shape : ??????
// border : ?????????
// colors : ?????? ???
// contentPadding : ????????? ???????????? ??????


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ButtonsContainer() {

    val buttonBorderGradient = Brush.horizontalGradient(listOf(Color.Yellow, Color.Red))
    val buttonBorderGradientSecond = Brush.horizontalGradient(listOf(Color.Red, Color.Yellow))

    val interactionSource = remember { MutableInteractionSource()}

    val isPressed by interactionSource.collectIsPressedAsState()

    val pressStatusTitle = if (isPressed) "????????? ????????? ??????." else "???????????? ?????? ??????."

    val interactionSourceForSecondBtn = remember { MutableInteractionSource() }

    val isPressedForSecondBtn by interactionSourceForSecondBtn.collectIsPressedAsState()

    val pressedBtnRadius = if (isPressedForSecondBtn) 0.dp else 20.dp
    
    val pressedBtnRadiusWithAnim : Dp by animateDpAsState(
        if (isPressedForSecondBtn) 0.dp else 20.dp
    )



    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            elevation = ButtonDefaults.elevation(
                defaultElevation = 0.dp
            ),
            enabled = true,
            onClick = {
            Log.d("TAG", "ButtonsContainer: ?????? 1 ??????")
        }) {
            Text(text = "?????? 1")
        }
        Button(
            elevation = ButtonDefaults.elevation(
                defaultElevation = 10.dp,
                pressedElevation = 0.dp,
                disabledElevation = 0.dp
            ),
            enabled = true,
            onClick = {
                Log.d("TAG", "ButtonsContainer: ?????? 2 ??????")
            }) {
            Text(text = "?????? 2")
        }
        Button(
            elevation = ButtonDefaults.elevation(
                defaultElevation = 10.dp,
                pressedElevation = 0.dp,
                disabledElevation = 0.dp
            ),
            enabled = true,
            shape = CircleShape,
            onClick = {
                Log.d("TAG", "ButtonsContainer: ?????? 3 ??????")
            }) {
            Text(text = "?????? 3")
        }
        Button(
            elevation = ButtonDefaults.elevation(
                defaultElevation = 10.dp,
                pressedElevation = 0.dp,
                disabledElevation = 0.dp
            ),
            enabled = true,
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(4.dp, Color.Yellow),
            contentPadding = PaddingValues(top = 50.dp, bottom = 20.dp, start = 20.dp, end = 10.dp),
            onClick = {
                Log.d("TAG", "ButtonsContainer: ?????? 4 ??????")
            }) {
            Text(text = "?????? 4")
        }
        Button(
            elevation = ButtonDefaults.elevation(
                defaultElevation = 10.dp,
                pressedElevation = 0.dp,
                disabledElevation = 0.dp
            ),
            enabled = true,
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(4.dp, buttonBorderGradient),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Black,
                disabledBackgroundColor = Color.LightGray
            ),
            interactionSource = interactionSource,
            onClick = {
                Log.d("TAG", "ButtonsContainer: ?????? 5 ??????")
            }) {
            Text(text = "?????? 5", color = Color.White)
        }

        Text(text = "$pressStatusTitle")

        Button(
            elevation = ButtonDefaults.elevation(
                defaultElevation = 0.dp,
                pressedElevation = 0.dp,
                disabledElevation = 0.dp
            ),
            enabled = true,
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(4.dp, buttonBorderGradientSecond),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Black,
                disabledBackgroundColor = Color.LightGray
            ),
            interactionSource = interactionSourceForSecondBtn,
            modifier = Modifier.drawColoredShadow(
                color = Color.Red,
                alpha = 0.5f,
                borderRadius = 10.dp,
                shadowRadius = pressedBtnRadiusWithAnim,
                offsetX = 0.dp,
                offsetY = 0.dp
            ),
            onClick = {
                Log.d("TAG", "ButtonsContainer: ?????? 6 ??????")
            }) {
            Text(text = "?????? 6", color = Color.White)
        }

//        color: Color,
//        alpha: Float = 0.2f, // ?????????
//        borderRadius: Dp = 0.dp,
//        shadowRadius: Dp = 20.dp,  // ????????????
//        offsetY: Dp = 0.dp, // offset = X/Y ???????????? ?????? ????????? ????????????
//        offsetX: Dp = 0.dp
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeButtonTheme {
        ButtonsContainer()
    }
}