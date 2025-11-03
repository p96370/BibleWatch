package com.example.biblewatch.presentation.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.biblewatch.R
import com.example.biblewatch.presentation.components.BibleWatchTopBar
import com.example.biblewatch.presentation.components.IconWithBorder
import com.example.biblewatch.presentation.components.animatedBorder
import com.example.biblewatch.presentation.model.Sound
import com.example.biblewatch.presentation.story.CategoryIcon
import com.example.biblewatch.presentation.story.getLabel
import com.example.biblewatch.ui.theme.backgroundColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateToStoryBirth: () -> Unit,
    navigateToStoryGenesis: () -> Unit,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {

    Scaffold(
        topBar = { BibleWatchTopBar() }) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
                .padding(top = 24.dp)
                .background(backgroundColor),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                StoryCard(
                    sectionTitle = "The Creation of the Universe", userCount = 15320, onClick = {
                        viewModel.stopPlayer()
                        navigateToStoryGenesis()
                    }, imageRes = R.drawable.genesis1, categoryIcons = listOf(
                        CategoryIcon.Fire,
                        CategoryIcon.Thunder,
                        CategoryIcon.Waterfall,
                    ), onCategoryClick = {
                        viewModel.playSound(it)
                    })
            }

            item {
                StoryCard(
                    sectionTitle = "The little king was born", userCount = 28774, onClick = {
                        viewModel.stopPlayer()
                        navigateToStoryBirth()
                    }, imageRes = R.drawable.birth_4, categoryIcons = listOf(
                        CategoryIcon.Angel,
                        CategoryIcon.Animals,
                        CategoryIcon.Baby,
                    ), onCategoryClick = {
                        viewModel.playSound(it)
                    })
            }
        }
    }
}

@Composable
fun StoryCard(
    sectionTitle: String,
    userCount: Int?,
    onClick: () -> Unit,
    imageRes: Int,
    categoryIcons: List<CategoryIcon>,
    onCategoryClick: (Sound) -> Unit
) {

    Text(
        sectionTitle,
        style = MaterialTheme.typography.titleLarge,
        color = Color.White,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center
    )

    Spacer(modifier = Modifier.height(16.dp))

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(12.dp))
                .clickable {
                    onClick()
                }) {
            // Replace with Image if you have one
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .animatedBorder(
                        listOf(
                            Color(0xFFFFD700), // Gold
                            Color(0xFFFFE4B5), // Moccasin / warm light
                            Color(0xFFFFE4B5), // Beige / ivory
                            Color(0xFFFFD700), // Floral white / divine glow
                            Color(0xFFEEE8AA), // Pale goldenrod
                            Color(0xFFEEE8AA), // Repeat gold for full cycle
                        ),
                        backgroundColor = Color.Companion.Transparent,
                        shape = RoundedCornerShape(10000.dp),
                        borderWidth = 2.dp
                    )
            )

            userCount?.let { cnt ->
                // Title and optional user count
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 20.dp)
                ) {
                    Icon(
                        Icons.Default.Person,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))

                    Text(text = "%,d".format(cnt), color = Color.White, fontSize = 12.sp)
                }
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.padding(horizontal = 10.dp).fillMaxWidth()
        ) {
            categoryIcons.forEach {
                Box(modifier = Modifier.weight(1f)) {
                    IconWithBorder(
                        painter = painterResource(it.iconRes),
                        size = 42.dp,
                        tint = it.tint,
                        onClick = {
                            onCategoryClick(it.sound)
                        }, modifier = Modifier.align(Alignment.Center).padding(bottom = 20.dp))
                    Box(
                        modifier = Modifier
                            .background(
                                color = it.tint.copy(alpha = 0.2f), shape = RoundedCornerShape(8.dp)
                            )
                            .height(26.dp)
                            .align(Alignment.BottomCenter)
                    ) {
                        BasicText(
                            text = it.getLabel(),
                            maxLines = 1,
                            style = TextStyle.Default.copy(color = it.tint),
                            autoSize = TextAutoSize.StepBased(minFontSize = 1.sp),
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(horizontal = 2.dp)
                                .padding(top = 4.dp)
                        )
                    }
                }
            }
        }
    }
    Spacer(modifier = Modifier.height(32.dp))
}

@Preview
@Composable
fun HomeScreenPreview() {
    Scaffold(
        topBar = { BibleWatchTopBar() }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
                .padding(top = 24.dp)
                .background(backgroundColor),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            StoryCard(
                sectionTitle = "The little king was born",
                userCount = 28774,
                onClick = {},
                imageRes = R.drawable.birth_4,
                categoryIcons = listOf(
                    CategoryIcon.Angel,
                    CategoryIcon.Animals,
                    CategoryIcon.Baby,
                ),
                onCategoryClick = {})
        }
    }
}