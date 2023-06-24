package com.example.jetpackcomposeex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeex.ui.theme.JetPackComposeExTheme

var counter = 0

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Text(text = "Hello World")
        }
    }
}


/** 함수를 구성 가능하게 하려면 @Composable 주석을 추가해야 한다. */
@Composable
fun MessageCard(name: String) {
    Text(text = "Hello $name")
}


/** @Preview 주석을 사용하여 앱을 빌드해서 Android 기기나 애뮬레이터에 설치하지 않고 구성 가능한 함수를 미리 볼 수 있다.
 *  이 주석은 매개변수를 사용하지 않는 구성 가능한 함수에 사용
 * */
@Preview
@Composable
fun PreviewMessageCard() {
    MessageCard(name = "Android")
}
