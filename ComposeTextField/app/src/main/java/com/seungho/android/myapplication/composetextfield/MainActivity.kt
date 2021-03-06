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
            label = { Text("????????? ??????") },
            placeholder = { Text("????????? ?????????") }
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = phoneNumberInput,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            onValueChange = { newValue -> phoneNumberInput = newValue},
            label = { Text("????????????") },
            placeholder = { Text("010-1234-1234") }
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = emailInput,
            singleLine = true,
            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = null) },
//            trailingIcon = { Icon(imageVector = Icons.Default.CheckCircle, contentDescription = null) },
            trailingIcon = { IconButton(onClick = { Log.d("TAG", "TextFieldTest: ???????????? ??????")}) {
                                Icon(imageVector = Icons.Default.CheckCircle, contentDescription = null)
                            }
                        },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            onValueChange = { newValue -> emailInput = newValue},
            label = { Text("????????? ??????") },
            placeholder = { Text("????????? ????????? ????????? ?????????") }
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = passwordInput,
            singleLine = true,
            leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = null) },
            trailingIcon = { IconButton(onClick = {
                Log.d("TAG", "TextFieldTest: ???????????? visible ?????? ??????")
                shouldShowPassword.value = !shouldShowPassword.value
            }) {
                Icon(painter = painterResource(id = passwordResource(shouldShowPassword.value)), contentDescription = null )
                }
            },
            visualTransformation = if (shouldShowPassword.value) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = { newValue -> passwordInput = newValue},
            label = { Text("????????????") },
            placeholder = { Text("??????????????? ??????????????????") }
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = idInput,
            singleLine = true,
            leadingIcon = { Icon(imageVector = Icons.Default.Info, contentDescription = null) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            onValueChange = { newValue -> idInput = newValue},
            label = { Text("?????????") },
            placeholder = { Text("???????????? ??????????????????") }
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
//    leadingIcon: @Composable (() -> Unit)? = null, ???????????????
//    trailingIcon: @Composable (() -> Unit)? = null, ??????????????????
//    isError: Boolean = false,
//    visualTransformation: VisualTransformation = VisualTransformation.None, ?????????????????? ??????????????? ??????????????????
//    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
//    keyboardActions: KeyboardActions = KeyboardActions(),
//    singleLine: Boolean = false, ????????? ??????
//    maxLines: Int = Int.MAX_VALUE, MAX_VALUE ?????? ??????
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