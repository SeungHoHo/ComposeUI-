package com.seungho.android.myapplication.composelazycolumn

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.seungho.android.myapplication.composelazycolumn.ui.theme.ComposeLazyColumnTheme
import com.seungho.android.myapplication.composelazycolumn.utils.DummyDataProvider
import com.seungho.android.myapplication.composelazycolumn.utils.RandomUser

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeLazyColumnTheme {
                ContentView()
            }
        }
    }
}

@Composable
fun ContentView() {
    Surface(color = MaterialTheme.colors.background) {
        Scaffold(backgroundColor = Color.White,
            topBar = { MyAppBar() }
            ) {
                RandomUserListView(randomUsers = DummyDataProvider.userList)
        }        
    }
}

@Composable
fun MyAppBar() {
    TopAppBar(elevation = 10.dp,
        backgroundColor = MaterialTheme.colors.primary,
//        backgroundColor = MyGreen,
        modifier = Modifier.height(58.dp)
        ) {
        Text(
            text = stringResource(id = R.string.my_app_name),
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.CenterVertically),
            fontSize = 18.sp,
            fontWeight = FontWeight.Black,
        )
    }
}

@Composable
fun RandomUserListView(randomUsers: List<RandomUser>) {
    // 메모리 관리가 들어간 LazyColumn
    LazyColumn(){
        items(randomUsers){ RandomUserView(it) }
    }
}

@Composable
fun RandomUserView(randomUser: RandomUser) {
    val typography = MaterialTheme.typography
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        elevation = 10.dp,
        shape = RoundedCornerShape(12.dp)
    ) {

        Row(modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
//            Box(modifier =
//            Modifier
//                .size(width = 60.dp, height = 60.dp)
//                .clip(CircleShape)
//                .background(Color.Red)
//            )
            ProfileImg(imgUrl = randomUser.profileImage)
            Column() {
                Text(text = randomUser.name,
                    style = typography.subtitle1)
                Text(text = randomUser.description,
                    style = typography.body1)
            }
        }
        Row(modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            ThumbImg()
        }
    }
}

@Composable
fun ProfileImg(imgUrl: String, modifier: Modifier = Modifier) {
    // 이미지 비트맵
    val bitmap : MutableState<Bitmap?> = remember { mutableStateOf(null)}

    // 이미지 모디파이어
    val imageModifier = modifier
        .size(50.dp, 50.dp)
        .clip(CircleShape)


    Glide.with(LocalContext.current)
        .asBitmap()
        .load(imgUrl)
        .into(object: CustomTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                bitmap.value = resource
            }
            override fun onLoadCleared(placeholder: Drawable?) { }
        })
    
    
    // 비트 맵이 있다면
    bitmap.value?.asImageBitmap()?.let { fetchedBitmap ->
        Image(bitmap = fetchedBitmap,
            contentScale = ContentScale.Fit,
            contentDescription = null,
            modifier = imageModifier
        )
    } ?: Image(painter = painterResource(id = R.drawable.ic_empty_user_image),
        contentScale = ContentScale.Fit,
        contentDescription = null,
        modifier = imageModifier
    )
}

@Composable
fun ThumbImg(modifier: Modifier = Modifier) {
    val bitmap : MutableState<Bitmap?> = remember { mutableStateOf(null)}

    val imageModifier = modifier
        .size(50.dp, 50.dp)
        .clip(CircleShape)

    Glide.with(LocalContext.current)
        .asBitmap()
        .load(R.drawable.ic_thumb_up)
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                bitmap.value = resource
            }
            override fun onLoadCleared(placeholder: Drawable?) {}
        })

    // 비트 맵이 있다면
    bitmap.value?.asImageBitmap()?.let { fetchedBitmap ->
        Image(bitmap = fetchedBitmap,
            contentScale = ContentScale.Fit,
            contentDescription = null,
            modifier = imageModifier
        )
    } ?: Image(painter = painterResource(id = R.drawable.ic_empty_user_image),
        contentScale = ContentScale.Fit,
        contentDescription = null,
        modifier = imageModifier
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeLazyColumnTheme {
        ContentView()
    }
}