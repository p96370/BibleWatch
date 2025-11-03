package com.example.biblewatch.presentation.story

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

/**
 * A composable that displays an image with a pulsing color overlay when clicked.
 * Clicking the image area triggers a pulsing color effect over the image.
 *
 * @param modifier The modifier to be applied to the container Box.
 * @param onClick The lambda to be executed when the image area is clicked.
 */
@Composable
fun ImageWithPulsingOverlay(
    modifier: Modifier = Modifier,
    // In a real app, you'd pass a painter or image data,
    // using resource ID for simplicity here.
    // Replace with your actual image resource type if needed.
    // For demonstration, we'll use a placeholder text instead of a real image resource.
    // imageResource: Int,
    onClick: () -> Unit,
    tint: Color,
    paddingValues: PaddingValues,
    size: Dp,

    ) {
    // State to control the alpha of the pulsing color overlay
    val overlayAlphaAnimation = remember { Animatable(0.1f) }

    // Use LaunchedEffect to start the animation when the composable enters composition
    LaunchedEffect(Unit) {
        // Start the pulsing alpha animation loop
        overlayAlphaAnimation.animateTo(
            targetValue = 0.25f, // Animate to a subtle semi-transparent state (e.g., 0.2f)
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = 2500, easing = FastOutLinearInEasing
                ), // Duration and easing for one half of the pulse
                repeatMode = RepeatMode.Reverse // Reverse the animation back to the start value
            )
        )
    }

    Box(
        modifier = modifier
            .padding(paddingValues)
            .size(size)

            // Clip the entire Box (image + overlay) to a circle shape
            .clip(CircleShape)
            // Keep the clickable modifier here so the area is still interactive
            .clickable {
                onClick() // Trigger the parent's onClick lambda when clicked
            }) {
        // The pulsing color overlay
        // This Box is drawn on top of the image Box
        Box(
            modifier = Modifier.Companion
                .fillMaxSize()
                // Apply a background color with animated alpha
                // Using a light gray or white for subtlety
                .background(tint.copy(alpha = overlayAlphaAnimation.value))
//                .animatedBorder(
//                    borderColors = listOf(
//                        tint,
//                        tint,
//                    ),
//                    backgroundColor = Color.Companion.Transparent,
//                    shape = RoundedCornerShape(200.dp),
//                    borderWidth = 3.dp
//                )
        ) {
            // No content inside this Box, it's purely for the color overlay effect
        }
    }
}