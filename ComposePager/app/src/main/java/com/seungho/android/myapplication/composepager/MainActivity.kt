package com.seungho.android.myapplication.composepager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.*
import com.seungho.android.myapplication.composepager.MainActivity.Companion.MAX_COUNT
import com.seungho.android.myapplication.composepager.MainActivity.Companion.last_page
import com.seungho.android.myapplication.composepager.ui.theme.ComposePagerTheme
import kotlinx.coroutines.launch

enum class MyTab(val title : String) {
    HOME("홈"),
    PROFILE("프로필"),
    LIST("리스트"),
    SETTING("세팅")
}

class MainActivity : ComponentActivity() {

    companion object {
        const val last_page = 9
        const val MAX_COUNT = 10
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePagerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column {
                        MyPagerView()
                        MyTabPagerView()
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MyTabPagerView() {

    val tabPagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    val tabs = MyTab.values().toList()

    Column() {

        TabRow(
            // Our selected tab is our current page
            selectedTabIndex = tabPagerState.currentPage,
            // Override the indicator, using the provided pagerTabIndicatorOffset modifier
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(tabPagerState, tabPositions)
                )
            }
        ) {
            // Add tabs for all of our pages
            tabs.forEachIndexed { index, tabItem ->
                Tab(
                    text = { Text(tabItem.title) },
                    selected = tabPagerState.currentPage == index,
                    onClick = {
                        coroutineScope.launch {
                            tabPagerState.animateScrollToPage(index)
                        }
                    },
                )
            }
        }
        // Display 10 items
        HorizontalPager(
            count = tabs.size,
            state = tabPagerState
        ) { page ->
            Card(
                modifier = Modifier.padding(50.dp).fillMaxSize(),
                backgroundColor = Color.Yellow,
                elevation = 10.dp
            ) {
                Text(
                    text = tabs[page].title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MyPagerView() {

    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    Column() {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextButton(onClick = {
                coroutineScope.launch {
                    if (pagerState.currentPage > 0) {
                        pagerState.animateScrollToPage(pagerState.currentPage - 1)
                    } else if (pagerState.currentPage == 0) {
                        pagerState.animateScrollToPage(pagerState.currentPage + last_page)
                    }
                }
            }) {
                Text(text = "이전 페이지")
            }
            TextButton(onClick = {
                coroutineScope.launch {
                    if (pagerState.currentPage < MAX_COUNT-1 ) {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    } else if (pagerState.currentPage == MAX_COUNT-1) {
                        pagerState.animateScrollToPage(pagerState.currentPage - last_page)
                    }
//                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                }
            }
            ) {
                Text(text = "다음 페이지")
            }
        }

        // Display 10 items
        HorizontalPager(
            count = MAX_COUNT,
            state = pagerState
        ) { page ->
            Card(
                modifier = Modifier.padding(50.dp),
                backgroundColor = Color.Yellow,
                elevation = 10.dp
            ) {
                Text(
                    text = "어컴퍼너스트 페이저 $page",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(100.dp)
                )
                Text(
                    text = "페이지: $page",
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp),
        )
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposePagerTheme {
        Greeting("Android")
    }
}