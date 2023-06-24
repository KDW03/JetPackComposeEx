package com.example.jetpackcomposeex

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeex.ui.theme.JetPackComposeExTheme

var counter = 0

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MessageCard(Message("Android", "Jetpack Compose"))
        }
    }
}


/** 함수를 구성 가능하게 하려면 @Composable 주석을 추가해야 한다. */
@Composable
fun MessageCard(name: String) {
    Text(text = "Hello $name")
}

/** 함수 이름 같더라도 변수 타입 및 개수가 다르면 가능*/
@Composable
fun MessageCard(msg: Message) {
    /** 요소를 수직으로 정렬 Column*/
    /** 요소를 수평으로 정렬 Row*/
    /** 요소를 쌓으려면 Box w*/
    Row(modifier = Modifier.padding(all = 8.dp)) {
        /** Modifider라는 수정자를 통해 컴포저블의 크기, 레이아웃, 모양을 변경하거나 요소를 클릭 가능하게 만드는 등의 상위 수준 상호작용 추가*/
        Image(
            painter = painterResource(id = R.drawable.profile_picture),
            contentDescription = " Contact profile picture",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        )

        // Add a horizontal space between the image and the column
        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text(text = msg.author)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = msg.body)
        }
    }
}


data class Message(val author: String, val body: String)

/** @Preview 주석을 사용하여 앱을 빌드해서 Android 기기나 애뮬레이터에 설치하지 않고 구성 가능한 함수를 미리 볼 수 있다.
 *  이 주석은 매개변수를 사용하지 않는 구성 가능한 함수에 사용
 * */
@Preview
@Composable
fun PreviewMessageCard() {
    MessageCard(name = "Android")
}

/** Preview도 여러개 가능 */
@Preview
@Composable
fun preViewMessageCard() {
    MessageCard(msg = Message("Colleague", "Hey, take a look at Jetpack Compose"))
}




