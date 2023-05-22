package com.composeappdemo.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.composeappdemo.R

@Composable
fun HomeScreen(modifier: Modifier = Modifier
    .background(Color.Blue)
    .fillMaxSize()) {
    Box {
        Column {
            GreetingSection()
            ChipSection(chips = listOf("One", "Two", "Three"))
            CurrentMeditation()
            FeatureSection(feature = listOf(Feature(), Feature(), Feature()))
        }
        BottomMenu(items = listOf(BottomMenuContent(), BottomMenuContent(), BottomMenuContent(), BottomMenuContent(), BottomMenuContent()), modifier = Modifier.align(Alignment.BottomCenter))
    }
}

@Composable
fun GreetingSection(name: String = "Karo") {
    Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically, modifier = Modifier
        .fillMaxWidth()
        .padding(15.dp)) {
        Column(verticalArrangement = Arrangement.Center) {
            Text(text = "Good morning, $name", style = MaterialTheme.typography.h3)
            Text(text = "We wish you have a good day", style = MaterialTheme.typography.body1)
        }
        Icon(painter = painterResource(R.drawable.ic_search), contentDescription = "Search", tint = Color.Red, modifier = Modifier.size(24.dp))
    }
}

@Composable
fun ChipSection(chips: List<String>) {
    var selectedChipIndex by remember {
        mutableStateOf(0)
    }

    LazyRow {
        items(chips.size) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier
                .padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
                .clickable {
                    selectedChipIndex = it
                }
                .clip(RoundedCornerShape(10.dp))
                .background(color = if (selectedChipIndex == it) Color.Black else Color.Green)
                .padding(15.dp)) {
                Text(text = chips[it], color = Color.White)
            }
        }
    }
}

@Composable
fun CurrentMeditation(color: Color = Color.Red) {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
        .padding(16.dp)
        .clip(RoundedCornerShape(10.dp))
        .background(color)
        .padding(horizontal = 16.dp, vertical = 20.dp)
        .fillMaxWidth()) {
        Column(verticalArrangement = Arrangement.Center) {
            Text(text = "Daily Thought", style = MaterialTheme.typography.h3)
            Text(text = "Meditation 3-10 min", style = MaterialTheme.typography.body1, color = Color.White)
        }
        Box(contentAlignment = Alignment.Center, modifier = Modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(Color.Blue)
            .padding(10.dp)) {
            Icon(painter = painterResource(id = R.drawable.ic_play), contentDescription = "Play", tint = Color.White, modifier = Modifier.size(16.dp))
        }
    }
}

@Composable
fun FeatureSection(feature: List<Feature>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = "Featured", style = MaterialTheme.typography.h3, modifier = Modifier.padding(16.dp))
        LazyVerticalGrid(columns = GridCells.Fixed(2), contentPadding = PaddingValues(start = 8.dp, end = 8.dp, bottom = 100.dp), modifier = Modifier.fillMaxHeight()) {
            items(feature.size) {
                FeatureItem(feature = feature[it])
            }
        }
    }
}

@Composable
fun FeatureItem(feature: Feature) {
    BoxWithConstraints(modifier = Modifier
        .padding(8.dp)
        .aspectRatio(1f)
        .clip(RoundedCornerShape(10.dp))
        .background(Color.Gray)) {
        val width = constraints.maxWidth
        val height = constraints.maxHeight

        //Medium colored path
        val mediumColoredPoint1 = Offset(0f, height * 0.3f)
        val mediumColoredPoint2 = Offset(width * 0.1f, height * 0.35f)
        val mediumColoredPoint3 = Offset(width * 0.4f, height * 0.5f)
        val mediumColoredPoint4 = Offset(width * 0.7f, height * 0.7f)
        val mediumColoredPoint5 = Offset(width * 1.4f, -height.toFloat())

        val mediumColoredPath = Path().apply {
            moveTo(mediumColoredPoint1.x, mediumColoredPoint1.y)
            standardQuadFromTo(mediumColoredPoint1, mediumColoredPoint2)
            standardQuadFromTo(mediumColoredPoint2, mediumColoredPoint3)
            standardQuadFromTo(mediumColoredPoint3, mediumColoredPoint4)
            standardQuadFromTo(mediumColoredPoint4, mediumColoredPoint5)
            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()
        }

        //Light Colored path
        val lightPoint1 = Offset(0f, height * 0.35f)
        val lightPoint2 = Offset(width * 0.1f, height * 0.4f)
        val lightPoint3 = Offset(width * 0.3f, height * 0.35f)
        val lightPoint4 = Offset(width * 0.65f, height.toFloat())
        val lightPoint5 = Offset(width * 1.4f, -height.toFloat() / 3f)

        val lightColoredPath = Path().apply {
            moveTo(lightPoint1.x, lightPoint1.y)
            standardQuadFromTo(lightPoint1, lightPoint2)
            standardQuadFromTo(lightPoint2, lightPoint3)
            standardQuadFromTo(lightPoint3, lightPoint4)
            standardQuadFromTo(lightPoint4, lightPoint5)
            lineTo(width.toFloat() * 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()
        }

        Canvas(modifier = Modifier.fillMaxSize()) {
            drawPath(mediumColoredPath, color = feature.mediumColor)
            drawPath(lightColoredPath, color = feature.lightColor)
        }

        Box(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)) {
            Text(text = feature.title, style = MaterialTheme.typography.h2, lineHeight = 26.sp, modifier = Modifier.align(Alignment.TopStart))
            Icon(painter = painterResource(id = feature.iconId), contentDescription = feature.title, tint = Color.White, modifier = Modifier.align(Alignment.BottomStart))
            Text(text = "Start", color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold, modifier = Modifier
                .clickable {
                    //Handle the click
                }
                .align(Alignment.BottomEnd)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.Blue)
                .padding(vertical = 8.dp, horizontal = 16.dp))

        }
    }
}

@Composable
fun BottomMenu(items: List<BottomMenuContent>, modifier: Modifier = Modifier, activeHighlightColor: Color = Color.Blue, activeTextColor: Color = Color.White, inactiveTextColor: Color = Color.DarkGray, initialSelectedItemIndex: Int = 0) {
    var selectedItemIndex by remember {
        mutableStateOf(initialSelectedItemIndex)
    }

    Row(horizontalArrangement = Arrangement.SpaceAround, verticalAlignment = Alignment.CenterVertically, modifier = modifier
        .fillMaxWidth()
        .background(Color.Magenta)
        .padding(16.dp)) {
        items.forEachIndexed { index, item ->
            BottomMenuItem(item, index == selectedItemIndex, activeHighlightColor, activeTextColor, inactiveTextColor) {
                selectedItemIndex = index
            }
        }
    }
}

@Composable
fun BottomMenuItem(item: BottomMenuContent, isSelected: Boolean = false, activeHighlightColor: Color = Color.Blue, activeTextColor: Color = Color.White, inactiveTextColor: Color = Color.DarkGray, onItemClick: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center, modifier = Modifier.clickable { onItemClick() }) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(color = if (isSelected) activeHighlightColor else Color.Transparent)
            .padding(10.dp)) {
            Icon(painter = painterResource(id = item.iconId), contentDescription = item.title, tint = if (isSelected) activeTextColor else inactiveTextColor, modifier = Modifier.size(20.dp))
        }
        Text(text = item.title, color = if (isSelected) activeTextColor else inactiveTextColor)
    }
}

data class BottomMenuContent(val title: String = "Menu", @DrawableRes val iconId: Int = R.drawable.ic_search)

data class Feature(val title: String = "Title", @DrawableRes val iconId: Int = R.drawable.ic_search, val lightColor: Color = Color.Yellow, val mediumColor: Color = Color.Gray, val darkColor: Color = Color.DarkGray)

fun Path.standardQuadFromTo(from: Offset, to: Offset) {
    quadraticBezierTo(from.x, from.y, kotlin.math.abs(from.x + to.x) / 2f, kotlin.math.abs(from.y + to.y) / 2f)
}