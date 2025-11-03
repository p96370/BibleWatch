package com.example.biblewatch.presentation.screens.study

import androidx.activity.compose.BackHandler
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.biblewatch.presentation.story.CategoryIcon
import com.example.biblewatch.presentation.story.VideoPlayerFillHeight
import kotlinx.coroutines.delay

@Composable
fun AnimalsGameScreen(onBack: () -> Unit, viewModel: AnimalsGameScreenViewModel = hiltViewModel()) {
    var selectedItemIndex by remember { mutableStateOf<Int?>(null) }
    BackHandler {
        selectedItemIndex = null
        onBack()
    }
    val categoryIcons = remember {
        listOf(
            CategoryIcon.Donkey,
            CategoryIcon.Lamb,
            CategoryIcon.Horse,
            CategoryIcon.Fire,
            CategoryIcon.Animals,
            CategoryIcon.Angel,
            CategoryIcon.Pray,
            CategoryIcon.Baby,
            CategoryIcon.Thunder,
            CategoryIcon.Water,
            CategoryIcon.Waterfall,
            CategoryIcon.AdamEve
        )
    }

    var visible: Int? by remember { mutableStateOf(null) }

    // Trigger visibility when selection changes
    LaunchedEffect(selectedItemIndex) {
        visible = null // trigger fade out
        if (selectedItemIndex != null) {
            // delay a bit before showing new video
            // optional: skip this if you want instant switch
            delay(150)
            visible = selectedItemIndex // trigger fade in
            delay(7850)
            viewModel.stopPlayer()
        }
    }


    Box {
//        AnimatedVisibility(
//            visible = visible != null,
//            enter = scaleIn(initialScale = 0.95f, animationSpec = tween(400)),
//            exit = scaleOut(targetScale = 0.95f, animationSpec = tween(300))
//        ) {
            visible?.let { index ->
                categoryIcons[index].let {
                    if (it.videoName.isEmpty()) {
                        Box(modifier = Modifier.fillMaxSize().background(color = it.tint.copy(alpha = 0.8f))) {
                        }
                    } else {
                        VideoPlayerFillHeight(categoryIcons[index].videoName, modifier = Modifier.alpha(0.4f))
                    }
                }

            }
//        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(3), // 3 columns
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxSize().padding(top = 100.dp)
        ) {
            itemsIndexed(categoryIcons) { index, icon ->
                val isSelected = selectedItemIndex == index
                val animatedBackgroundColor by animateColorAsState(
                    targetValue = if (isSelected) Color.Yellow.copy(alpha = 0.3f) else Color.White.copy(
                        alpha = 0.3f
                    ),
                    animationSpec = tween(durationMillis = 300), label = "highlightColor"
                )

                    Card(
                        modifier = Modifier
                            .aspectRatio(1f)
                            .clickable {
                                viewModel.playSound(icon.sound)
                                selectedItemIndex =
                                    if (isSelected) null else index // Deselect if already selected
                            },
                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors(containerColor = animatedBackgroundColor)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize(), // Apply icon-specific padding
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(id = icon.iconRes),
                                modifier = Modifier.size(64.dp), // Apply icon-specific size
                                tint = if (icon == CategoryIcon.Baby || icon == CategoryIcon.Lamb) Color.Black else icon.tint,
                                contentDescription = null,
                            )
                        }
                    }
            }
        }


    }
}