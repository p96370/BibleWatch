package com.example.biblewatch.presentation.screens.bible

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.biblewatch.R
import com.example.biblewatch.presentation.story.StoryPageData
import com.example.biblewatch.presentation.story.StoryScreenContent

@Composable
fun BibleScreen(onBack: () -> Unit) {
    val birthPagesData = listOf(

        StoryPageData(
            title = "The Birth of Jesus",
            imageRes = R.drawable.bible_page,
            interactiveIcons = listOf(
            )
        ),
        StoryPageData(
            title = "", imageRes = R.drawable.bible_page, interactiveIcons = listOf(
            )
        ),
        StoryPageData(
            title = "", imageRes = R.drawable.bible_page, interactiveIcons = listOf(
            )
        ),
        StoryPageData(
            title = "", imageRes = R.drawable.bible_page, interactiveIcons = listOf(
            )
        ),
    )

    Box {
        StoryScreenContent(
            pagesData = birthPagesData,
            onBack = onBack,
            playSound = {},
            playStory = { },
            stopSound = { }) {
            Column(
                modifier = Modifier
                    .padding(top = 80.dp, start = 16.dp, end = 16.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.birth_1),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .height(200.dp)

                        .aspectRatio(9/16f)
                )
                Text(
                    "18 Now the birth of Jesus Christ took place in this way. When his mother Mary had been betrothed to Joseph, before they came together she was found to be with child from the Holy Spirit.\n" + "19 And her husband Joseph, being a just man and unwilling to put her to shame, resolved to divorce her quietly.",
                    textAlign = TextAlign.Center,
                    color = Color.Black,
                    modifier = Modifier.padding(top = 24.dp),
                    fontSize = 18.sp
                )

            }
        }

    }
}

@Preview
@Composable
fun BibleScreenPreview() {
    BibleScreen({})
}
