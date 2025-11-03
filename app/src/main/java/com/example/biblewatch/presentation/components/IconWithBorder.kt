package com.example.biblewatch.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun IconWithBorder(
    iconRes: Int, onClick: () -> Unit, padding: PaddingValues = PaddingValues(16.dp)
) {
    IconWithBorder(painterResource(iconRes), onClick, padding)
}

@Composable
fun IconWithBorder(
    painter: Painter, onClick: () -> Unit, padding: PaddingValues = PaddingValues(16.dp)
) {
    IconButton(
        onClick = onClick, modifier = Modifier.Companion
            .padding(padding)
            .animatedBorder(
                borderColors = listOf(
                    Color(0xFFFFC107).copy(alpha = 0.6f),
                    Color(0xFF4CAF50).copy(alpha = 0.6f),
                    Color(0xFF03A9F4).copy(alpha = 0.6f),
                    Color(0xFFFF5722).copy(alpha = 0.6f),
                    Color(0xFFE91E63).copy(alpha = 0.6f),
                    Color(0xFFFFC107).copy(alpha = 0.6f)
                ),
                backgroundColor = Color.Companion.Transparent,
                shape = RoundedCornerShape(200.dp),
                borderWidth = 3.dp
            )
            .size(56.dp)
    ) {
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier.Companion
                .size(48.dp)
                .alpha(0.8f)
        )
    }
}

@Composable
fun IconWithBorder(
    painter: Painter,
    onClick: () -> Unit = {},
    padding: PaddingValues = PaddingValues(16.dp),
    size: Dp = 60.dp,
    tint: Color = Color.Unspecified,
    modifier: Modifier = Modifier,
    borderColors: List<Color> = listOf(
        tint.copy(alpha = 0.9f),
        tint.copy(alpha = 0.8f),
        tint.copy(alpha = 0.6f),
        tint.copy(alpha = 0.3f),
        tint.copy(alpha = 0.1f),
        tint.copy(alpha = 0.9f),
    )
) {
    IconButton(
        onClick = onClick, modifier = modifier
            .padding(padding)
            .animatedBorder(
                borderColors = if(tint != Color(0xFFE1E1E1)) borderColors else listOf(
                    Color(0xFFFFD700), // Gold
                    Color(0xFFFFE4B5), // Moccasin / warm light
                    Color(0xFFFFE4B5), // Beige / ivory
                    Color(0xFFFFD700), // Floral white / divine glow
                    Color(0xFFEEE8AA), // Pale goldenrod
                    Color(0xFFEEE8AA), // Repeat gold for full cycle
                ),
                backgroundColor = Color.Companion.Transparent,
                shape = RoundedCornerShape(200.dp),
                borderWidth = 3.dp
            )
            .size(size)
    ) {
        Icon(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .size(size - 8.dp),
            tint = tint
        )
    }
}