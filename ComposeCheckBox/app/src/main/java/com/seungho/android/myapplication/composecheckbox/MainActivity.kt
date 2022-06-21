package com.seungho.android.myapplication.composecheckbox

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.seungho.android.myapplication.composecheckbox.ui.theme.ComposeCheckBoxTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeCheckBoxTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CheckBoxContainer()
                }
            }
        }
    }
}

//checked: Boolean,
//onCheckedChange: ((Boolean) -> Unit)?,
//modifier: Modifier = Modifier,
//enabled: Boolean = true,
//interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
//colors: CheckboxColors = CheckboxDefaults.colors()

// checked : 체크 상태
// onCheckedChange : 체크 상태 변경 콜백 이벤트
// enabled : 체크 가능 여부
// colors : 체크 박스에 대한 색 변경

@Composable
fun CheckBoxContainer() {

    val checkedStatusForFirst = remember { mutableStateOf(false)}
    val checkedStatusForSecond = remember { mutableStateOf(false)}
    val checkedStatusForThird = remember { mutableStateOf(false)}
//    val checkedStatusForFourth = remember { mutableStateOf(false)}

    val checkedStatesArray = listOf(
                                checkedStatusForFirst,
                                checkedStatusForSecond,
                                checkedStatusForThird,
                            )

    val allBoxChecked: (Boolean) -> Unit = { isAllBoxChecked ->
        Log.d("TAG", "CheckBoxContainer: isAllBoxChecked: $isAllBoxChecked")
        checkedStatesArray.forEach { it.value =  isAllBoxChecked }
    }

//    val checkedStatusForFourth : Boolean = checkedStatesArray.all { it.value == true }
    val checkedStatusForFourth : Boolean = checkedStatesArray.all { it.value }

//    var checkedStatusForSecond by remember { mutableStateOf(false)}
//    val (checkedStatusForThird, setCheckedStatusForThird) = remember { mutableStateOf(false)}
//    val (checkedStatusForFourth, setCheckedStatusForFourth) = remember { mutableStateOf(false)}
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        
        CheckBoxWithTitle(title = "1번 확인사항", checkedStatusForFirst)
        CheckBoxWithTitle(title = "2번 확인사항", checkedStatusForSecond)
        CheckBoxWithTitle(title = "3번 확인사항", checkedStatusForThird)

//        Checkbox(
//            enabled = true,
//            checked = checkedStatusForSecond,
//            onCheckedChange = { isChecked ->
//                Log.d("TAG", "CheckBoxContainer: isChecked: $isChecked")
//                checkedStatusForSecond = isChecked
//            })
//        Checkbox(
//            enabled = true,
//            checked = checkedStatusForThird,
//            onCheckedChange = {
//                Log.d("TAG", "CheckBoxContainer: isChecked: $it")
//                setCheckedStatusForThird.invoke(it)
//            })
        Spacer(modifier = Modifier.height(10.dp))
        AllAgreeCheckBox("모두 동의하십니까?", checkedStatusForFourth, allBoxChecked)
        Spacer(modifier = Modifier.height(10.dp))
        MyCustomCheckBox(title = "커스텀 체크박스입니다 리플 O", withRipple = true)
        MyCustomCheckBox(title = "커스텀 체크박스입니다 리플 X", withRipple = false)
//        Checkbox(
//            enabled = true,
//            checked = checkedStatusForFourth,
//            colors = CheckboxDefaults.colors(
//                checkedColor = Color(0xFFEF9A9A),
//                uncheckedColor = Color.Black,
//                checkmarkColor = Color.White,
//                disabledColor = Color(0xFFEF9A9A)
//            ),
//            onCheckedChange = {
//                Log.d("TAG", "CheckBoxContainer: isChecked: $it")
//                setCheckedStatusForFourth.invoke(it)
//            })
    }
}

//checkedColor: Color = MaterialTheme.colors.secondary, 체크가 된 컬러색깔
//uncheckedColor: Color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f), 체크가 안된 컬러색깔
//checkmarkColor: Color = MaterialTheme.colors.surface, 체크 v자 컬러색깔
//disabledColor: Color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled), enabled = false 일때 색깔

@Composable
fun CheckBoxWithTitle(title: String, isCheckedState: MutableState<Boolean>) {
    Row(
        modifier = Modifier
//            .background(Color.Yellow)
            .padding(horizontal = 30.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Checkbox(
            enabled = true,
            checked = isCheckedState.value,
            onCheckedChange = { isChecked ->
                Log.d("TAG", "CheckBoxContainer: isChecked: $isChecked")
                isCheckedState.value = isChecked
            })
        Text(text = title)
    }
}

@Composable
fun AllAgreeCheckBox(title: String,
                     shouldChecked: Boolean,
                     allBoxChecked: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
//            .background(Color.Yellow)
            .padding(horizontal = 30.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Checkbox(
            enabled = true,
            checked = shouldChecked,
            colors = CheckboxDefaults.colors(
                checkedColor = Color.Red,
                uncheckedColor = Color(0xFFEF9A9A),
                checkmarkColor = Color.White,
                disabledColor = Color(0xFF90CAF9)
            ),
            onCheckedChange = { isChecked ->
                Log.d("TAG", "CheckBoxContainer: isChecked: $isChecked")
//                isCheckedState.value = isChecked
                allBoxChecked(isChecked)
            })
        Text(text = title)
    }
}

@Composable
fun MyCustomCheckBox(title: String, withRipple: Boolean = false) {

//    var isCheckedState by remember { mutableStateOf(false) }
//    var isChecked = remember { mutableStateOf(false)}
    var (isChecked, setIsChecked) = remember { mutableStateOf(false)}

    var togglePainter = if (isChecked) R.drawable.ic_checked else R.drawable.ic_unchecked

    var checkedInfoString = if (isChecked) "체크됨" else "체크안됨"

    var rippleEffect = if (withRipple) rememberRipple(
        radius = 30.dp,
        bounded = false,
        color = Color.Blue
    ) else null

    Row(
        modifier = Modifier
            .padding(horizontal = 30.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(60.dp)
                .clickable(
                    indication = rippleEffect,
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    setIsChecked.invoke(!isChecked)
                    Log.d("TAG", "MyCustomCheckBox: 클릭이 되었다 / $isChecked")
                }) {
            Image(
                painter = painterResource(id = togglePainter),
                contentDescription = null
            )
        }
        Text(text = "$title / $checkedInfoString")
    }
//  rememberRipple 처리
//        bounded: Boolean = true,
//        radius: Dp = Dp.Unspecified,
//        color: Color = Color.Unspecified
//  Clickable 처리
//        enabled = enabled,
//        onClickLabel = onClickLabel,
//        onClick = onClick,
//        role = role,
//        indication = LocalIndication.current,
//        interactionSource = remember { MutableInteractionSource() }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeCheckBoxTheme {
        CheckBoxContainer()
    }
}