package ru.karpenko.bookcirculation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.karpenko.bookcirculation.ui.theme.BookCirculationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val messages = listOf(
                Message("Me", "Hello!"),
                Message("Natali", "Hi!"),
                Message("Me", "How are you?"),
                Message(
                    "Natali",
                    "I'm fine! And you? Quality control of outsourced translations had been expanded since 1996."
                ),
                Message("Me", "Great")
            )

            BookCirculationTheme {
                Conversation(messages)
            }
            /*BookCirculationTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }
            } */

        }
    }
}

@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn {
        items(messages) { message ->
            MessageCard(message)
        }
    }
}

@Composable
fun MessageCard(message: Message) {
    Row {
        Column {
            Text(text = message.author)
        }
        Spacer(
            modifier = Modifier.width(8.dp)
        )

        var isExpanded by remember {
            mutableStateOf(false)
        }
        val surfaceColor: Color by animateColorAsState(
            if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface
        )

        Column(modifier = Modifier.clickable {
            isExpanded = !isExpanded
        }) {
            Surface(
                color = surfaceColor,
                modifier = Modifier
                    .animateContentSize()
            ) {
                Text(
                    text = message.text,
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

    val messages = listOf(
        Message("Me", "Hello!"),
        Message("Natali", "Hi!"),
        Message("Me", "How are you?"),
        Message(
            "Natali",
            "I'm fine! And you? Quality control of outsourced translations had been expanded since 1996."
        ),
        Message("Me", "Great")
    )

    BookCirculationTheme {
        Conversation(messages)
    }

}

data class Message(
    val author: String,
    val text: String
)