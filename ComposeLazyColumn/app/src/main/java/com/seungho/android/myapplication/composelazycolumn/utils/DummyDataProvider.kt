package com.seungho.android.myapplication.composelazycolumn.utils

data class RandomUser(
    val name : String = "개발하는 송승호 🐱",
    val description: String = "오늘도 빡코딩 했습니다!",
    val profileImage: String = "https://randomuser.me/api/portraits/women/95.jpg",
    val profileImage2: String = "https://randomuser.me/api/portraits/women/37.jpg"
)

object DummyDataProvider {

    val userList = List<RandomUser>(200) { RandomUser() }
}