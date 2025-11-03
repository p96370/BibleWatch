package com.example.biblewatch.presentation.story

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.biblewatch.R
import com.example.biblewatch.presentation.model.Sound
import eu.wewox.pagecurl.ExperimentalPageCurlApi
import eu.wewox.pagecurl.config.rememberPageCurlConfig
import eu.wewox.pagecurl.page.PageCurl
import eu.wewox.pagecurl.page.rememberPageCurlState

@OptIn(ExperimentalPageCurlApi::class)
@Composable
fun StoryScreenContent(
    pagesData: List<StoryPageData>,
    onBack: () -> Unit,
    playSound: (Sound) -> Unit,
    playStory: () -> Unit,
    stopSound: () -> Unit,
    extraContent: (@Composable () -> Unit)? = null
) {
    BackHandler {
        onBack()
    }

    val state = rememberPageCurlState()
    val config = rememberPageCurlConfig(
        backPageColor = Color(0xFFF5E7C1), // Light parchment color
        backPageContentAlpha = 0.9f, shadowColor = Color(0x804A2C00), // Brownish shadow
        shadowAlpha = 0.3f, shadowRadius = 16.dp, shadowOffset = DpOffset(4.dp, 4.dp)
    )

    var playEnabled by remember { mutableStateOf(true) }

    LaunchedEffect(state.current) {
        if (!playEnabled) {
        stopSound()
        playEnabled = false
            }
    }
    LaunchedEffect(Unit) {
        playStory()
    }

    Column(modifier = Modifier.Companion.fillMaxSize()) {
        Box(
            modifier = Modifier.Companion
                .weight(1f)
                .fillMaxWidth()
        ) {
            PageCurl(
                count = pagesData.size, state = state, config = config
            ) { index ->
                StoryPage(pageData = pagesData[index], playSound = {
                    playEnabled = false
                    playSound(it)
                }, isCurrent = state.current == index, stopSound = {
                    stopSound() })
                extraContent?.invoke()

            }

            Icon(
                Icons.Filled.ArrowBack,
                contentDescription = null,
                modifier = Modifier.Companion
                    .padding(16.dp)
                    .zIndex(5f)
                    .padding(start = 20.dp, top = 40.dp)
                    .alpha(0.4f)
                    .align(Alignment.Companion.TopStart)
                    .size(30.dp)
                    .clickable { onBack() },
                tint = Color.Companion.White
            )

            Image(
                if (!playEnabled) painterResource(R.drawable.sound_icon) else painterResource(R.drawable.sound_off),
                contentDescription = null,
                modifier = Modifier.Companion
                    .padding(16.dp)
                    .clickable { onBack() }
                    .zIndex(5f)
                    .padding(end = 20.dp, top = 40.dp)
                    .align(Alignment.Companion.TopEnd)
                    .size(45.dp)
                    .clickable {
                        playEnabled = !playEnabled
                        if (playEnabled) {
                            playStory()
                        } else {
                            stopSound()
                        }
                    })

        }
    }
}