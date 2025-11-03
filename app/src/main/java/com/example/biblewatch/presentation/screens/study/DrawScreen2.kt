package com.example.biblewatch.presentation.screens.study

import androidx.activity.compose.BackHandler
import androidx.annotation.DrawableRes
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.biblewatch.R

@Composable
fun DrawScreen2(onBack: () -> Unit) {
    BackHandler { onBack() }

    val colorOptions1 = listOf(
        Color(139, 69, 19), // Brown
        Color.Blue,
        Color.Black,
        Color.Magenta,
        Color.Green,
    )

    val colorOptions = remember {
        listOf(
            Color(0xFF540d6e),
            Color(0xFFf694c1),
            Color(0xFFF94144),
            Color(0xFFf9844a),
            Color(0xFFfee440),
            Color(0xFF90be6d),
            Color(0xFF00f5d4),
            Color(0xFF1a8fe3),
        )
    }

    var selectedColor by remember { mutableStateOf(colorOptions[0]) }
    var backgroundColor by remember { mutableStateOf(Color.White) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .clickable(null, null) { backgroundColor = selectedColor }) {
        TopClouds(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .safeContentPadding(),
            selectedColor = selectedColor
        )
        DonkeyAndFire(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .align(Alignment.Center)
                .safeContentPadding(),
            selectedColor = selectedColor
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomEnd)
        ) {
//            Box(modifier = Modifier.fillMaxWidth()) {
//                Icon(
//                    modifier = Modifier
//                        .align(Alignment.CenterEnd)
//                        .safeContentPadding()
//                        .size(30.dp),
//                    imageVector = Icons.AutoMirrored.Filled.Send,
//                    contentDescription = "",
//                    tint = selectedColor,
//                )
//            }
            // Color palette
            Row(
                modifier = Modifier
                    .height(200.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                colorOptions.forEach { color ->
                    val isSelected = selectedColor == color
                    Box(
                        modifier = Modifier
                            .background(color)
                            .animateContentSize()
                            .weight(if (isSelected) 1.3f else 1f)
                            .height(if (isSelected) 200.dp else 180.dp)
                            .clickable(
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() }
                            ) {
                                selectedColor = color
                            }
                    )
                }
            }

        }
        PickerColor(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .safeDrawingPadding()
                .padding(16.dp),
            tint = selectedColor
        )
    }
}

@Composable
fun DonkeyAndFire(modifier: Modifier, selectedColor: Color) {
    Box(modifier = modifier) {
        Column(modifier = Modifier.align(Alignment.CenterStart)) {
            DonkeyIcon(
                selectedColor = selectedColor,
                modifier = Modifier.offset(y = 20.dp, x = 15.dp)
            )
            DonkeyIcon(selectedColor = selectedColor, modifier = Modifier)
            DonkeyIcon(
                selectedColor = selectedColor,
                modifier = Modifier.offset(y = -20.dp, x = 15.dp)
            )
        }
        TintableIcon(
            modifier = Modifier
                .size(80.dp)
                .align(Alignment.Center),
            painterRes = R.drawable.ic_campfire_fill,
            selectedColor = selectedColor,
            defaultColor = fireColor
        )
        Column(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .scale(scaleX = -1f, scaleY = 1f)
        ) {
            DonkeyIcon(
                selectedColor = selectedColor,
                modifier = Modifier.offset(y = 20.dp, x = 15.dp)
            )
            DonkeyIcon(selectedColor = selectedColor, modifier = Modifier)
            DonkeyIcon(
                selectedColor = selectedColor,
                modifier = Modifier.offset(y = (-20).dp, x = 15.dp)
            )
        }
        Column(
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(y = (-100).dp)
        ) {
            TreeIcon(
                modifier = Modifier
                    .size(30.dp),
                selectedColor = selectedColor,
            )
            TreeIcon(
                modifier = Modifier
                    .size(30.dp)
                    .offset(x = 50.dp)
                    .scale(scaleX = -1f, scaleY = 1f),
                selectedColor = selectedColor,
            )
            TreeIcon(
                modifier = Modifier
                    .size(30.dp),
                selectedColor = selectedColor,
            )
        }
        Row(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(bottom = 100.dp)
                .offset(y = -100.dp, x = 15.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            SheepIcon(
                modifier = Modifier,
                size = 65,
                selectedColor = selectedColor,
            )
            TintableIcon(
                modifier = Modifier
                    .padding(vertical = 5.dp, horizontal = 10.dp)
                    .size(25.dp),
                painterRes = R.drawable.ic_campfire_fill,
                selectedColor = selectedColor,
                defaultColor = fireColor.copy(0.6f)
            )
            SheepIcon(
                modifier = Modifier
                    .scale(scaleX = -1f, scaleY = 1f),
                size = 40,
                selectedColor = selectedColor,
            )
        }
    }
}

private val cloudColor = Color(0xFF1a8fe3)
private val donkeyColor = Color.LightGray
private val fireColor = Color(0xFFf9844a)
private val treeColor = Color(139, 69, 19)

@Composable
fun TopClouds(modifier: Modifier, selectedColor: Color) {
    Row(
        modifier = modifier
            .height(200.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        CloudIcon(modifier = Modifier.size(40.dp), selectedColor)
        CloudIcon(
            modifier = Modifier
                .size(55.dp)
                .offset(y = 10.dp), selectedColor
        )
        CloudIcon(
            modifier = Modifier
                .padding(vertical = 40.dp)
                .size(40.dp), selectedColor
        )
        CloudIcon(
            modifier = Modifier
                .size(55.dp)
                .offset(y = 10.dp), selectedColor
        )
        CloudIcon(modifier = Modifier.size(45.dp), selectedColor)
        CloudIcon(
            modifier = Modifier
                .padding(top = 30.dp)
                .size(60.dp), selectedColor
        )
    }
}

@Composable
fun CloudIcon(modifier: Modifier = Modifier, selectedColor: Color) {
    TintableIcon(R.drawable.ic_cloud, modifier, selectedColor, defaultColor = cloudColor)
}

@Composable
fun TreeIcon(modifier: Modifier = Modifier, selectedColor: Color) {
    TintableIcon(R.drawable.ic_tree, modifier, selectedColor, defaultColor = treeColor)
}

@Composable
fun DonkeyIcon(modifier: Modifier = Modifier, size: Int = 80, selectedColor: Color) {
    TintableIcon(
        R.drawable.ic_donkey_fill,
        modifier.size(size.dp),
        selectedColor,
        defaultColor = donkeyColor
    )
}

@Composable
fun SheepIcon(modifier: Modifier = Modifier, size: Int = 80, selectedColor: Color) {
    TintableIcon(
        R.drawable.ic_sheep,
        modifier.size(size.dp),
        selectedColor,
        defaultColor = donkeyColor
    )
}

@Composable
fun TintableIcon(
    @DrawableRes painterRes: Int,
    modifier: Modifier,
    selectedColor: Color,
    defaultColor: Color
) {
    var tint by remember { mutableStateOf(defaultColor) }

    Icon(
        painter = painterResource(painterRes),
        contentDescription = null,
        modifier = modifier.clickable { tint = selectedColor },
        tint = tint
    )
}

@Composable
fun PickerColor(modifier: Modifier = Modifier, tint: Color) {
    Icon(
        modifier = modifier,
        painter = painterResource(R.drawable.ic_water),
        contentDescription = "", tint = tint
    )
}

@Preview(heightDp = 900, widthDp = 380, showSystemUi = true)
@Composable
private fun PreviewTest() {
    DrawScreen2 {}
}