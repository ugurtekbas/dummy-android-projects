package com.ugurtekbas.composesample

import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.LayoutModifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.ugurtekbas.composesample.ui.Message
import com.ugurtekbas.composesample.ui.aMessage
import com.ugurtekbas.composesample.ui.messageList
import com.ugurtekbas.composesample.ui.theme.ComposeSampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeSampleTheme {
                Scaffold(
                    topBar = { actionBar() },
                    bottomBar = { navigationBar() }
                ) {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        color = MaterialTheme.colors.background
                    ) {
                        Conversation(messageList)
                    }
                }
            }
        }
    }
}

private fun listItemClick(isOverFlowed: Boolean, isExpandable: Boolean): Boolean {
    return if(isOverFlowed || isExpandable) {
        !isExpandable
    } else {
        isExpandable
    }
}

@Composable
fun Greeting(msg: Message) {
    var isOverFlowed by rememberSaveable { mutableStateOf(false) }
    var isExpandable by rememberSaveable { mutableStateOf(false) }
    val rowColor by animateColorAsState(targetValue = if(isExpandable) MaterialTheme.colors.primary else MaterialTheme.colors.secondary)
    LayoutModifier
    PaddingModifier
    Row (
        modifier = Modifier
            //.height(80.dp)
            .clickable {
                isExpandable = listItemClick(isOverFlowed, isExpandable)
            }
            .padding(4.dp)
            .clip(RoundedCornerShape(4.dp))
            .fillMaxWidth()
            .background(color = rowColor)
    ) {
        Image(
            painter = rememberImagePainter(data = msg.thumbnail),
            contentDescription = "My first pic",
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                //.align(Alignment.CenterVertically)
                .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape),
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 8.dp)
        ) {
            Text(
                text = "${msg.author} says: ",
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = msg.message,
                onTextLayout = { textLayoutResult: TextLayoutResult ->
                    isOverFlowed = textLayoutResult.hasVisualOverflow
                },
                maxLines = if(isExpandable) Int.MAX_VALUE else 2,
                modifier = Modifier.animateContentSize()
            )
        }
        IconButton(onClick = {
            isExpandable = listItemClick(isOverFlowed, isExpandable)
        }) {
            Icon (
                imageVector = if (isExpandable) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                contentDescription = ""
            )
        }
    }
}

@Composable
fun Conversation(messages: List<Message>){
    LazyColumn {
        items(messages) { message ->
            Greeting(msg = message)
        }
    }
}

@Composable
fun actionBar() {
    TopAppBar(
        title = {Text(text = "What up!")},
        actions = {
            iconAsAButton(Icons.Filled.Favorite)
        }
    )
}

@Composable
fun navigationBar() {
    BottomAppBar(){
        iconAsAButton(Icons.Filled.Home)
        iconAsAButton(Icons.Filled.Message)
        iconAsAButton(Icons.Filled.Search)
        iconAsAButton(Icons.Filled.VerifiedUser)
        iconAsAButton(Icons.Filled.Settings)
    }
}

@Composable
fun iconAsAButton(iconSource: ImageVector) {
    IconButton(onClick = { /*TODO*/ }) {
        Icon(iconSource, contentDescription = "")
    }
}

@Preview(widthDp = 320)
@Composable
fun ConversationPreview() {
    ComposeSampleTheme {
        Conversation(messageList)
    }
}

@Preview(showBackground = true, widthDp = 320, uiMode = UI_MODE_NIGHT_YES,)
@Composable
fun DefaultPreview() {
    ComposeSampleTheme {
        Greeting(aMessage)
    }
}
