package com.example.biblewatch

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.SizeMode
import androidx.glance.appwidget.action.actionStartActivity
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Alignment.Companion.CenterHorizontally
import androidx.glance.layout.Box
import androidx.glance.layout.ContentScale
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.layout.width
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextAlign.Companion.Center
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider

class BibleWatchWidget : GlanceAppWidget() {
    override val sizeMode: SizeMode = SizeMode.Responsive(setOf(DpSize(250.dp, 250.dp)))


    override suspend fun provideGlance(
        context: Context,
        id: GlanceId
    ) {
        provideContent { MyContent() }
    }
    @SuppressLint("RestrictedApi")
    @Composable
    private fun MyContent() {
        val intent = Intent(Intent.ACTION_VIEW, "biblewatch://story/genesis".toUri()).apply {
            // Ensure the intent resolves correctly
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        Box(modifier = GlanceModifier.fillMaxSize()) {
            Image(
                provider = ImageProvider(R.drawable.genesis1),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = GlanceModifier
                    .fillMaxSize()
                    .clickable(onClick = actionStartActivity(intent))
            )
            Box(
                modifier = GlanceModifier
                    .fillMaxSize()
                    .background(ColorProvider(Color(0x30000000))) // 50% black overlay, adjust alpha as needed
            ) {

            }
            Row(modifier = GlanceModifier.fillMaxWidth().padding(top = 16.dp), horizontalAlignment = CenterHorizontally, verticalAlignment = Alignment.CenterVertically) {
                Image(
                    provider = ImageProvider(R.drawable.bible_widget_icon), // your birth image resource
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = GlanceModifier
                        .size(30.dp)

                )
                Spacer(modifier = GlanceModifier.width(8.dp))
                Text(
                    "The story of the world",
                    style = TextStyle(
                        textAlign = Center,
                        color = ColorProvider(Color.White),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Medium
                    )
                )
            }

//            Text("Genesis 1:26-27", modifier = GlanceModifier.fillMaxWidth().padding(top = 300.dp), style = TextStyle(textAlign = Center, color = ColorProvider(Color.White), fontSize = 18.sp, fontWeight = FontWeight.Bold))

        }
    }
}

class BirthWatchWidget : GlanceAppWidget() {
    override val sizeMode: SizeMode = SizeMode.Responsive(setOf(DpSize(250.dp, 250.dp)))

    override suspend fun provideGlance(
        context: Context,
        id: GlanceId
    ) {
        provideContent { BirthContent() }
    }

    @SuppressLint("RestrictedApi")
    @Composable
    private fun BirthContent() {
        val intent = Intent(Intent.ACTION_VIEW, "biblewatch://story/birth".toUri()).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        Box(modifier = GlanceModifier.fillMaxSize()) {
            Image(
                provider = ImageProvider(R.drawable.birth_4), // your birth image resource
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = GlanceModifier
                    .fillMaxSize()
                    .clickable(onClick = actionStartActivity(intent))
            )
            // Semi-transparent overlay for better text contrast
            Box(
                modifier = GlanceModifier
                    .fillMaxSize()
                    .background(ColorProvider(Color(0x30000000))) // 50% black overlay
            ) {

            }


            Row(modifier = GlanceModifier.fillMaxWidth().padding(top = 38.dp), horizontalAlignment = CenterHorizontally, verticalAlignment = Alignment.CenterVertically) {
                Image(
                    provider = ImageProvider(R.drawable.bible_widget_icon), // your birth image resource
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = GlanceModifier
                        .size(30.dp)

                )
                Spacer(modifier = GlanceModifier.width(8.dp))
                Text(
                    "Birth of Jesus",
                    style = TextStyle(
                        textAlign = Center,
                        color = ColorProvider(Color.White),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Medium
                    )
                )
            }
//            Text(
//                "Luke 2:7",
//                modifier = GlanceModifier.fillMaxWidth().padding(top = 300.dp),
//                style = TextStyle(
//                    textAlign = Center,
//                    color = ColorProvider(Color.White),
//                    fontSize = 22.sp,
//                    fontWeight = FontWeight.Bold
//                )
//            )

        }
    }
}

class GenesisWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = BibleWatchWidget()
}

class BirthWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = BirthWatchWidget()
}
