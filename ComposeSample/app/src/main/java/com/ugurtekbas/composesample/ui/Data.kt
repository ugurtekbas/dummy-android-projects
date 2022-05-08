package com.ugurtekbas.composesample.ui

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.ugurtekbas.composesample.R

data class Message(
    val author: String,
    val message: String,
    val thumbnail: String = "https://github.com/ugurtekbas/ugurtekbas.github.io/blob/master/assets/img/logo.png"
)

val aMessage = Message(author= "Ugur", message= "Hello there!")
val anotherMessage = Message(author= "Jack", message= "What up dude!", thumbnail = "https://github.com/ugurtekbas/ugurtekbas.github.io/blob/master/assets/img/we-can-do-it.jpg")
val differentMessage = Message(author= "Michael", message= "Hellooooo what's going ooonnnn whaat", thumbnail = "https://github.com/ugurtekbas/ugurtekbas.github.io/blob/master/assets/img/questions.jpg")
val bradMessage = Message(author= "Brad Pitt", message= LoremIpsum(55).values.joinToString(), thumbnail = "https://github.com/ugurtekbas/ugurtekbas.github.io/blob/master/assets/img/simple.jpeg")
val leoMessage = Message(author= "Leonardo DiCaprio", message= LoremIpsum(20).values.shuffled().joinToString(), thumbnail = "https://github.com/ugurtekbas/ugurtekbas.github.io/blob/master/assets/img/ingredients.png")

val messageList = listOf(aMessage, anotherMessage, differentMessage, bradMessage, leoMessage)
