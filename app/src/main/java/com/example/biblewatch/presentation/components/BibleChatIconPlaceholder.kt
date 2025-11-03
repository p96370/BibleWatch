package com.example.biblewatch.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.biblewatch.R

@Composable
fun BibleChatIconPlaceholder() {
    Box(modifier = Modifier.Companion.fillMaxSize()) {
        Image(
            painterResource(R.drawable.biblechat_icon),
            contentDescription = null,
            modifier = Modifier.Companion.align(
                Alignment.Companion.Center
            )
        )
    }
}