package com.seungho.android.myapplication.composenavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.seungho.android.myapplication.composenavigation.ui.theme.ComposeNavigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeNavigationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NavigationGraph()
                }
            }
        }
    }
}

// navigation route enum
enum class NAV_ROUTE(val routeName: String, val description: String, val btnColor: Color) {
    MAIN("MAIN", "메인화면", Color(0xFF937DC2)),
    LOGIN("LOGIN", "로그인 화면", Color(0xFFC689C6)),
    REGISTER("REGISTER", "회원가입 화면", Color(0xFFE8A0BF)),
    USER_PROFILE("USER_PROFILE", "유저 프로필 화면", Color(0xFFFCC5C0)),
    SETTING("SETTING", "설정 화면", Color(0xFFFF869E)),
}

// navigation route action
class RouteAction(navHostController: NavHostController) {

    // 특정 라우트로 이동
    val navTo: (NAV_ROUTE) -> Unit = { route ->
        navHostController.navigate(route.routeName)
    }

    // 뒤로가기 이동
    val goBack: () -> Unit = {
        navHostController.navigateUp()
    }
}

@Composable
fun NavigationGraph(startRoute: NAV_ROUTE = NAV_ROUTE.MAIN) {

    // navigation controller
    val navController = rememberNavController()

    // navigation route action
    val routeAction = remember(navController) { RouteAction(navController) }

    // NavHost 로 Navigation 결정
    // navigation 연결할 것들을 설정한다
    NavHost(navController , startRoute.routeName) {

        // route 이름
        composable(NAV_ROUTE.MAIN.routeName){
            // 화면 = 값
            MainScreen(routeAction = routeAction)
        }
        // route 이름
        composable(NAV_ROUTE.LOGIN.routeName){
            // 화면 = 값
            LoginScreen(routeAction = routeAction)
        }
        // route 이름
        composable(NAV_ROUTE.REGISTER.routeName){
            // 화면 = 값
            RegisterScreen(routeAction = routeAction)
        }
        // route 이름
        composable(NAV_ROUTE.USER_PROFILE.routeName){
            // 화면 = 값
            UserProfileScreen(routeAction = routeAction)
        }
        // route 이름
        composable(NAV_ROUTE.SETTING.routeName){
            // 화면 = 값
            SettingScreen(routeAction = routeAction)
        }
    }

}

// Main 화면
@Composable
fun MainScreen(routeAction: RouteAction) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(Modifier.padding(16.dp)) {
            NavButton(route = NAV_ROUTE.LOGIN, routeAction = routeAction)
            NavButton(route = NAV_ROUTE.REGISTER, routeAction = routeAction)
            NavButton(route = NAV_ROUTE.USER_PROFILE, routeAction = routeAction)
            NavButton(route = NAV_ROUTE.SETTING, routeAction = routeAction)
        }
    }
}

// 로그인 화면
@Composable
fun LoginScreen(routeAction: RouteAction) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Box(Modifier.padding(8.dp), Alignment.Center) {
            Text(text = "로그인 화면", style = TextStyle(Color.Black, 22.sp, FontWeight.Medium))
            // 뒤로 가기 버튼
            Button(onClick = routeAction.goBack,
                    modifier = Modifier
                        .padding(16.dp)
                        .offset(y = 100.dp)) {
                Text(text = "뒤로 가기")
            }
        }
    }
}

// 회원가입 화면
@Composable
fun RegisterScreen(routeAction: RouteAction) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Box(Modifier.padding(8.dp), Alignment.Center) {
            Text(text = "회원가입 화면", style = TextStyle(Color.Black, 22.sp, FontWeight.Medium))
            // 뒤로 가기 버튼
            Button(onClick = routeAction.goBack,
                modifier = Modifier
                    .padding(16.dp)
                    .offset(y = 100.dp)) {
                Text(text = "뒤로 가기")
            }
        }
    }
}

// 로그인 화면
@Composable
fun UserProfileScreen(routeAction: RouteAction) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Box(Modifier.padding(8.dp), Alignment.Center) {
            Text(text = "유저 프로필 화면", style = TextStyle(Color.Black, 22.sp, FontWeight.Medium))
            // 뒤로 가기 버튼
            Button(onClick = routeAction.goBack,
                modifier = Modifier
                    .padding(16.dp)
                    .offset(y = 100.dp)) {
                Text(text = "뒤로 가기")
            }
        }
    }
}

// 로그인 화면
@Composable
fun SettingScreen(routeAction: RouteAction) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Box(Modifier.padding(8.dp), Alignment.Center) {
            Text(text = "설정 화면", style = TextStyle(Color.Black, 22.sp, FontWeight.Medium))
            // 뒤로 가기 버튼
            Button(onClick = routeAction.goBack,
                modifier = Modifier
                    .padding(16.dp)
                    .offset(y = 100.dp)) {
                Text(text = "뒤로 가기")
            }
        }
    }
}

// Column 에 있는 Navigation Button
@Composable
fun ColumnScope.NavButton(route: NAV_ROUTE, routeAction: RouteAction) {
    Button(onClick = {
        routeAction.navTo(route)
    }, colors = ButtonDefaults.buttonColors(backgroundColor = route.btnColor),
        modifier = Modifier
            .weight(1f)
            .padding(8.dp)
            .fillMaxSize()
    ) {
        Text(text = route.description,
        style = TextStyle(Color.White, 22.sp, FontWeight.Medium)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeNavigationTheme {

    }
}