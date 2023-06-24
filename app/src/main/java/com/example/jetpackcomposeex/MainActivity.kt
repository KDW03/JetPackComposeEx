package com.example.jetpackcomposeex

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeex.ui.SampleData
import com.example.jetpackcomposeex.ui.theme.JetPackComposeExTheme

var counter = 0


/**
 *  로컬 UI 상태를 저장하려면 메시지가 확장되었는지 추적
 *  이 상태 변경을 추적하려면 remember와 mutableStateOf 함수를 사용
 *
 *  구성 가능한 함수는 remember를 사용하여 메모리에 로컬 상태를 저장
 *  mutableStateOf에 전달된 값의 변경사항을 추적
 *
 * 이 상태를 사용하는 컴포저블 및 하위 요소는 값이 업데이트되면 자동으로 다시 그려짐 = 재구성
 * remember 및 mutableStateOf와 같은 Compose의 상태 API를 사용하여 상태를 변경하면 UI가 자동으로 업데이트
 * */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            // 테마 설정
            JetPackComposeExTheme {
                // 배경색, 모양, 그림자 등을 지정하는 데 사용되는 컴포저블
                Surface(modifier = Modifier.fillMaxSize()) {
                    Conversation(message = SampleData.conversationSample)
                }
            }
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
        /** Modifider라는 수정자를 통해 컴포저블의 크기, 레이아웃, 모양을 변경하거나 요소를 클릭 가능하게 만드는 등의 상위 수준 상호작용 추가
         *  size는 width와 hieght를 동시에 설정 */
        Image(
            painter = painterResource(id = R.drawable.profile_picture),
            contentDescription = " Contact profile picture",
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
        )

        // Add a horizontal space between the image and the column
        Spacer(modifier = Modifier.width(8.dp))

        // We keep track if the message is expanded or not in this
        //variable
        var isExpanded by remember { mutableStateOf(false) }

        // surfaceColor will be updated gradually from one color to the other
        val surfaceColor by animateColorAsState(
            if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface
        )


        // we toggle the isExpanded variable when we click on this Column
        Column(

        ) {
            Text(
                text = msg.author,
                color = MaterialTheme.colors.secondaryVariant,
                style = MaterialTheme.typography.subtitle2
            )
            Spacer(modifier = Modifier.height(4.dp))
            // 도형을 적용해주기 위해 surface로 래핑
            Surface(
                shape = MaterialTheme.shapes.medium, elevation = 1.dp,
                // surfaceColor color will be changing gradually from primary to surface
                color = surfaceColor,
                // animateContentSize will change the Surface size gradually
                modifier = Modifier
                    .clickable { isExpanded = !isExpanded }
                    .animateContentSize()
                    .padding(1.dp)
            ) {
                Text(
                    text = msg.body,
                    modifier = Modifier
                        .padding(all = 4.dp),
                    // If the message is expanded, we display all its content
                    // otherwise we only display the first line
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.body2
                )
            }

        }
    }
}

/** LazyColumn과 LazyRow를 사용합니다. 이러한 컴포저블은 화면에 표시되는 요소만 렌더링하므로 긴 목록에 매우 효율적으로 설계 */
@Composable
fun Conversation(message: List<Message>) {
    LazyColumn {
        //items = LazyColumn 하위 요소
        // List의 항목 마다 호출되는 람다
        items(message) {
            MessageCard(msg = it)
        }
    }
}


/** @Preview 주석을 사용하여 앱을 빌드해서 Android 기기나 애뮬레이터에 설치하지 않고 구성 가능한 함수를 미리 볼 수 있다.
 *  이 주석은 매개변수를 사용하지 않는 구성 가능한 함수에 사용
 * */
@Preview
@Composable
fun PreviewMessageCard() {
    MessageCard(name = "Android")
}

/** DarkMode 사용 Compose 기보적으로 Dark Theme 지원*/
/** Preview도 여러개 가능 */
@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark MODE"
)
@Composable
fun preViewMessageCard() {
    JetPackComposeExTheme {
        Surface {
            MessageCard(msg = Message("Colleague", "Hey, take a look at Jetpack Compose"))
        }
    }
}

data class Message(val author: String, val body: String)





