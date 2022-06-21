package com.seungho.android.myapplication.composetextfield

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.seungho.android.myapplication.composetextfield.ui.theme.ComposeTextFieldTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTextFieldTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    TextFieldTest()
                }
            }
        }
    }
}

@Composable
fun TextFieldTest() {

    var userInput by remember { mutableStateOf(TextFieldValue()) }

    var phoneNumberInput by remember { mutableStateOf(TextFieldValue()) }

    var emailInput by remember { mutableStateOf(TextFieldValue()) }

    var shouldShowPassword = remember { mutableStateOf(false)}

    var passwordInput by remember { mutableStateOf(TextFieldValue()) }

    val passwordResource: (Boolean) -> Int = {
        if (it) {
            R.drawable.ic_baseline_visibility_24
        } else {
            R.drawable.ic_baseline_visibility_off_24
        }
    }

    var idInput by remember { mutableStateOf(TextFieldValue()) }

    Column(
        Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = userInput,
            singleLine = true,
            maxLines = 2,
            onValueChange = { newValue -> userInput = newValue},
            label = { Text("사용자 입력") },
            placeholder = { Text("작성해 주세요") }
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = phoneNumberInput,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            onValueChange = { newValue -> phoneNumberInput = newValue},
            label = { Text("전화번호") },
            placeholder = { Text("010-1234-1234") }
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = emailInput,
            singleLine = true,
            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = null) },
//            trailingIcon = { Icon(imageVector = Icons.Default.CheckCircle, contentDescription = null) },
            trailingIcon = { IconButton(onClick = { Log.d("TAG", "TextFieldTest: 체크버튼 클릭")}) {
                                Icon(imageVector = Icons.Default.CheckCircle, contentDescription = null)
                            }
                        },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            onValueChange = { newValue -> emailInput = newValue},
            label = { Text("이메일 주소") },
            placeholder = { Text("이메일 주소를 입력해 주세요") }
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = passwordInput,
            singleLine = true,
            leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = null) },
            trailingIcon = { IconButton(onClick = {
                Log.d("TAG", "TextFieldTest: 비밀번호 visible 버튼 클릭")
                shouldShowPassword.value = !shouldShowPassword.value
            }) {
                Icon(painter = painterResource(id = passwordResource(shouldShowPassword.value)), contentDescription = null )
                }
            },
            visualTransformation = if (shouldShowPassword.value) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = { newValue -> passwordInput = newValue},
            label = { Text("비밀번호") },
            placeholder = { Text("비밀번호를 입력해주세요") }
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = idInput,
            singleLine = true,
            leadingIcon = { Icon(imageVector = Icons.Default.Info, contentDescription = null) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            onValueChange = { newValue -> idInput = newValue},
            label = { Text("아이디") },
            placeholder = { Text("아이디를 입력해주세요") }
        )
    }
}

//fun TextField(
//    value: TextFieldValue,
//    onValueChange: (TextFieldValue) -> Unit,
//    modifier: Modifier = Modifier,
//    enabled: Boolean = true,
//    readOnly: Boolean = false,
//    textStyle: TextStyle = LocalTextStyle.current,
//    label: @Composable (() -> Unit)? = null,
//    placeholder: @Composable (() -> Unit)? = null,
//    leadingIcon: @Composable (() -> Unit)? = null, 왼쪽아이콘
//    trailingIcon: @Composable (() -> Unit)? = null, 오른쪽아이콘
//    isError: Boolean = false,
//    visualTransformation: VisualTransformation = VisualTransformation.None, 비밀번호처럼 가릴것인가 보여줄것인가
//    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
//    keyboardActions: KeyboardActions = KeyboardActions(),
//    singleLine: Boolean = false, 한줄만 입력
//    maxLines: Int = Int.MAX_VALUE, MAX_VALUE 만큼 입력
//    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
//    shape: Shape =
//        MaterialTheme.shapes.small.copy(bottomEnd = ZeroCornerSize, bottomStart = ZeroCornerSize),
//    colors: TextFieldColors = TextFieldDefaults.textFieldColors()
//) {


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeTextFieldTheme {
        TextFieldTest()
    }
}