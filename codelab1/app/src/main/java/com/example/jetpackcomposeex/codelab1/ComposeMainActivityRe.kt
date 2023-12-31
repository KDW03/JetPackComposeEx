package com.example.jetpackcomposeex.codelab1

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeex.ui.theme.JetPackComposeExTheme

private class ComposeMainActivityRe : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContent {
            JetPackComposeExTheme {
                MyApp(modifier = Modifier.fillMaxSize())
            }
        }

    }
}

@Composable
private fun MyApp2(modifier: Modifier = Modifier) {
    var shouldShowOnBoarding by rememberSaveable { mutableStateOf(true) }

    Surface(modifier, color = MaterialTheme.colorScheme.background) {
        if (shouldShowOnBoarding) {
            OnboardingScreen2(onContinueClicked = { shouldShowOnBoarding = false })
        } else {
            Greetings2()
        }
    }
}

@Composable
private fun OnboardingScreen2(
    onContinueClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier.fillMaxSize(),
        Arrangement.Center,
        Alignment.CenterHorizontally
    ) {
        Text(text = "Welcom to the Basics Codelab!")
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = onContinueClicked
        ) {
            Text(text = "Continue")
        }
    }
}

@Composable
private fun Greetings2(
    modifier: Modifier = Modifier,
    names: List<String> = List(1000) { "$it" }
) {
    LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
        items(items = names) {
            Greeting2(it)
        }
    }
}


@Composable
private fun Greeting2(name: String) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        CardContent2(name)
    }
}

@Composable
private fun CardContent2(name: String) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .padding(12.dp)
            .animateContentSize(
                spring(
                    Spring.DampingRatioMediumBouncy,
                    Spring.StiffnessLow
                )
            )
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(12.dp)
        ) {
            Text(text = "Hello, ")
            Text(
                text = name, style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )

            if (expanded) {
                Text(
                    text = ("Composem ipsum color sit lazy, " +
                            "padding theme elit, sed do bouncy. ").repeat(4)
                )
            }
        }

        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                contentDescription = null
            )
        }
    }
}


@Preview
@Composable
fun MyAppPreview2() {
    JetPackComposeExTheme {
        MyApp2(Modifier.fillMaxSize())
    }
}




