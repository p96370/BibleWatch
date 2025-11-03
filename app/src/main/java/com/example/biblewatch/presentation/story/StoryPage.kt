package com.example.biblewatch.presentation.story

import android.widget.FrameLayout
import androidx.annotation.OptIn
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.zIndex
import androidx.core.net.toUri
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import com.example.biblewatch.presentation.components.ScaleOutAnimatedText
import com.example.biblewatch.presentation.model.Sound
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@Composable
fun StoryPage(
    pageData: StoryPageData,
    playSound: (Sound) -> Unit,
    stopSound: () -> Unit,
    isCurrent: Boolean,
) {
    var showVideo: String? by remember { mutableStateOf(null) }

    LaunchedEffect(showVideo) {
        if (showVideo != null) {
            delay(8.seconds)
            showVideo = null
            stopSound()
        }
    }

    Box(
        modifier = Modifier.Companion
            .fillMaxSize()
            .background(Color(0xFFFFF8E1)),
        contentAlignment = Alignment.Companion.Center
    ) {

        ScaleOutAnimatedText(
            text = pageData.title,
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Companion.SemiBold,
                color = Color.Companion.White
            ),
            modifier = Modifier.Companion
                .align(Alignment.Companion.TopCenter)
                .alpha(0.9f)
                .zIndex(2f)
                .padding(top = 100.dp)
        )
        Image(
            painter = painterResource(id = pageData.imageRes),
            contentDescription = pageData.title,
            contentScale = ContentScale.Companion.FillHeight,
            modifier = Modifier.Companion.fillMaxSize()
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.3f))  // adjust alpha as needed
        )
        AnimatedVisibility(
            visible = showVideo != null, enter = EnterTransition.None, exit = slideOutVertically(
                targetOffsetY = { fullHeight -> fullHeight }, // slide out to bottom
                animationSpec = tween(700)
            ) + fadeOut(animationSpec = tween(700))
        ) {
            showVideo?.let { VideoPlayerFillHeight(it) }
        }

        if (isCurrent && showVideo == null) {
            pageData.interactiveIcons.forEach { button ->
                ImageWithPulsingOverlay(
                    onClick = {
                        showVideo = button.videoName
                        playSound(button.sound)
                    }, tint = button.tint, size = button.size, paddingValues = button.padding
                )
            }
        }
    }
}

@OptIn(UnstableApi::class)
@Composable
fun VideoPlayerFillHeight(filename: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var isVideoReady by remember { mutableStateOf(false) }

    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            val videoUri = "android.resource://${context.packageName}/$filename".toUri()
            val mediaItem = MediaItem.fromUri(videoUri)
            setMediaItem(mediaItem)
            prepare()
            playWhenReady = true
        }
    }

    DisposableEffect(Unit) {
        val listener = object : Player.Listener {
            override fun onRenderedFirstFrame() {
                isVideoReady = true
            }
        }
        exoPlayer.addListener(listener)

        onDispose {
            exoPlayer.removeListener(listener)
            exoPlayer.release()
        }
    }

    // Fade-in animation when ready
    val targetAlpha = if (isVideoReady) 1f else 0f
    val animatedAlpha by animateFloatAsState(
        targetValue = targetAlpha, animationSpec = tween(durationMillis = 500)
    )

    AndroidView(
        factory = {
            PlayerView(context).apply {
                player = exoPlayer
                useController = false
                resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
                layoutParams = FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT
                ).apply {
                    gravity = android.view.Gravity.CENTER
                }
            }
        },
        modifier = modifier
            .fillMaxHeight()
            .wrapContentWidth(Alignment.CenterHorizontally)
            .alpha(animatedAlpha)
    )
}
