package com.example.biblewatch.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.biblewatch.R
import com.example.biblewatch.ui.theme.backgroundColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BibleWatchTopBar(secondTitle: String = "Kids") {
    CenterAlignedTopAppBar(
        title = {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Companion.CenterVertically,
                modifier = Modifier.Companion.height(45.dp).fillMaxWidth().padding(start = 20.dp)
            ) {
                BasicText(
                    text = "Bible",
                    maxLines = 1,
                    style = TextStyle.Companion.Default.copy(color = Color.Companion.White),
                    autoSize = TextAutoSize.Companion.StepBased(),
                )
                Image(
                    painterResource(R.drawable.biblechat_icon),
                    contentDescription = null,
                    modifier = Modifier.Companion.padding(horizontal = 16.dp)
                )
                BasicText(
                    text = secondTitle,
                    maxLines = 1,
                    style = TextStyle.Companion.Default.copy(color = Color.Companion.White),
                    autoSize = TextAutoSize.Companion.StepBased(),
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = backgroundColor,
            titleContentColor = Color.Companion.White,
            navigationIconContentColor = Color.Companion.White
        )
    )
}