package com.seungho.android.myapplication.composetext

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seungho.android.myapplication.composetext.ui.theme.ComposeTextTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTextTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    TextContainer()
                }
            }
        }
    }
}

//text: String,
//modifier: Modifier = Modifier,
//color: Color = Color.Unspecified,
//fontSize: TextUnit = TextUnit.Unspecified,
//fontStyle: FontStyle? = null,
//fontWeight: FontWeight? = null,
//fontFamily: FontFamily? = null,
//letterSpacing: TextUnit = TextUnit.Unspecified,
//textDecoration: TextDecoration? = null,
//textAlign: TextAlign? = null,
//lineHeight: TextUnit = TextUnit.Unspecified,
//overflow: TextOverflow = TextOverflow.Clip,
//softWrap: Boolean = true,
//maxLines: Int = Int.MAX_VALUE,
//onTextLayout: (TextLayoutResult) -> Unit = {},
//style: TextStyle = LocalTextStyle.current

@Composable
fun TextContainer() {
    val name = "송승호"

    val scrollState = rememberScrollState()

    var words = stringResource(id = R.string.dummy_short_text)
    var wordsArray = words.split(" ")

    var longwords = stringResource(id = R.string.dummy_long_text)
    var longwordsArray = longwords.split(" ")

    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        Text(text = "안녕하세요? $name",
            style = TextStyle(
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Yellow))
        Text(text = "안녕하세요? 오늘도 빡코딩~! $name",
            style = TextStyle(
                textAlign = TextAlign.Start
            ),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Yellow))
        Text(text = "안녕하세요? 오늘도 빡코딩~! $name",
            style = TextStyle(
                textAlign = TextAlign.End
            ),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Yellow))
        Text(text = stringResource(id = R.string.dummy_short_text),
            maxLines = 3,
            overflow = TextOverflow.Ellipsis, //maxLines 으로 제한된 텍스트 표시하기
            style = TextStyle(
                textAlign = TextAlign.Justify,
                textDecoration = TextDecoration.combine(
                    listOf(
                        TextDecoration.LineThrough,
                        TextDecoration.Underline,
                    )
                )
            ),
            fontWeight = FontWeight.W200,
            fontSize = 20.sp,
            fontFamily = FontFamily.Monospace,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Yellow))
        Text(text = stringResource(id = R.string.dummy_short_text),
            style = TextStyle(
                textAlign = TextAlign.Start,
                fontFamily = FontFamily(Font(R.font.donounmedium, weight = FontWeight.ExtraBold)),
                lineHeight = 40.sp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Yellow)
        )
        Text(text = buildAnnotatedString {
            append("안녕하세요?\n")

            withStyle(style = SpanStyle(color = Color.Blue,
                                fontSize = 40.sp,
                                fontWeight = FontWeight.ExtraBold
                )) {
                append("신입 개발자 송승호입니다\n")
            }
            withStyle(style = SpanStyle(color = Color.Red)) {
                append("오늘도 코딩 열심히!!")
            }
        })
        Text(text = buildAnnotatedString {
            wordsArray.forEach {
                if (it.contains("생명")) {
                    withStyle(style = SpanStyle(color = Color.Blue,
                        fontSize = 40.sp,
                        fontWeight = FontWeight.ExtraBold
                    )) {
                        append("$it ")
                    }
                } else {
                    append("$it ")
                }
            }
        })

        ClickableText(text = AnnotatedString("클릭미!"), onClick = {
            Log.d("TAG", "TextContainer : 클릭미가 클릭되었다!")
        })

//        Text(text = stringResource(id = R.string.dummy_long_text),
//            style = TextStyle(lineHeight = 20.sp)
//        )

        Text(text = buildAnnotatedString {
            longwordsArray.forEach {
                if (it.contains("다")) {
                    withStyle(style = SpanStyle(color = Color.Red,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = FontFamily(Font(R.font.donounmedium))
                    )) {
                        append("$it ")
                    }
                } else {
                    withStyle(style = SpanStyle(
                        fontSize = 15.sp,
                        fontStyle = FontStyle.Italic
                    )) {
                        append("$it ")
                    }
                }
            }
        })

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeTextTheme {
        TextContainer()
    }
}