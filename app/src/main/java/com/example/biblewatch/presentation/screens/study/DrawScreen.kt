package com.example.biblewatch.presentation.screens.study

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.biblewatch.R

@Composable
fun DrawScreen(onBack: () -> Unit) {
    BackHandler { onBack() }

    val colorOptions = listOf(
        Color(139, 69, 19), // Brown
        Color.Blue,
        Color.Black,
        Color.Magenta,
        Color.Green,
    )

    var selectedColor by remember { mutableStateOf(colorOptions[0]) }
    var backgroundColor by remember { mutableStateOf(Color.White) }

    // Replace with your own drawable resources
    val iconResList = listOf(
        R.drawable.bible_icon,
        R.drawable.baby_icon,
        R.drawable.angel_icon,
        R.drawable.book_icon,
        R.drawable.donkey
    )

    val iconTints = remember { mutableStateListOf<Color>().apply { repeat(iconResList.size) { add(Color.Gray) } } }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor.copy(alpha = 0.8f))
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                backgroundColor = selectedColor
            }.padding(bottom = 200.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
            iconResList.forEachIndexed { index, resId ->
                val painter: Painter = painterResource(id = resId)

                Box(
                    modifier = Modifier
                        .size(128.dp)
                        .clip(CircleShape)
                        .background(Color.Transparent)
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) {
                            iconTints[index] = selectedColor
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painter,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize(0.8f).alpha(0.8f),
                        colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(iconTints[index])
                    )
                }
            }

    }
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier= Modifier.align(Alignment.BottomCenter)) {
            // Bottom divider
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(14.dp)
                    .background(Color.Black.copy(alpha = 0.3f))
            )
            // Color palette
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
            ) {
                colorOptions.forEach { color ->
                    val isSelected = selectedColor == color
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .background(color)
                            .border(
                                width = if (isSelected) 4.dp else 0.dp,
                                color = if (isSelected) Color.Black else Color.Transparent
                            )
                            .clickable(
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() }
                            ) {
                                selectedColor = color
                            }
                    )
                }
            }

            // Bottom divider
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(36.dp)
                    .background(Color.Black.copy(alpha = 0.3f))
            )
        }

    }
}

@Preview
@Composable
private fun PreviewTest() {
    DrawScreen {  }
}
