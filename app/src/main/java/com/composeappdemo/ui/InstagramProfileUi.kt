package com.composeappdemo.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.composeappdemo.R

@Composable
fun ProfileScreen() {
    var selectedTabIndex by remember {
        mutableStateOf(0)
    }
    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(name = "Hovhannisyan Karo")
        Spacer(modifier = Modifier.size(16.dp))
        ProfileSection()
        Spacer(modifier = Modifier.size(16.dp))
        ButtonSection(modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.size(16.dp))
        HighlightSection(highlights = listOf(StoryHighlight(), StoryHighlight(), StoryHighlight(), StoryHighlight(), StoryHighlight()))
        Spacer(modifier = Modifier.height(10.dp))
        PostTabView(tabs = listOf(ImageWithText(), ImageWithText(), ImageWithText(), ImageWithText(), ImageWithText(), ImageWithText()), onTabSelected = { selectedTabIndex = it })
        when (selectedTabIndex) {
            0 -> {
                PostSection(modifier = Modifier.fillMaxWidth(), posts = listOf(painterResource(id = R.drawable.image_funny), painterResource(id = R.drawable.image_funny), painterResource(id = R.drawable.image_funny), painterResource(id = R.drawable.image_funny), painterResource(id = R.drawable.image_funny), painterResource(id = R.drawable.image_funny), painterResource(id = R.drawable.image_funny), painterResource(id = R.drawable.image_funny)))
            }
        }
    }
}

@Composable
fun TopBar(name: String, modifier: Modifier = Modifier) {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceAround, modifier = modifier.fillMaxWidth()) {
        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.Black, modifier = Modifier.size(24.dp))
        Text(text = name, overflow = TextOverflow.Ellipsis, fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Icon(painter = painterResource(id = R.drawable.ic_bell), contentDescription = "Bell", tint = Color.Black, modifier = Modifier.size(24.dp))
        Icon(painter = painterResource(id = R.drawable.ic_menu), contentDescription = "Menu", tint = Color.Black, modifier = Modifier.size(24.dp))

    }
}

@Composable
fun ProfileSection(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)) {
            RoundImage(image = painterResource(id = R.drawable.image_funny), modifier = Modifier
                .size(100.dp)
                .weight(3f))
            Spacer(modifier = Modifier.width(16.dp))
            StatSection(modifier = Modifier.weight(7f))
        }
        ProfileDescription(displayName = "Karo", description = "This is description", url = "www.codeschool.am", followedBy = listOf("A", "B"), otherCount = 10)
    }
}

@Composable
fun RoundImage(image: Painter, modifier: Modifier = Modifier) {
    //matchHeightConstraintsFirst - սկզբից նայում ա height ին, ու ինչ height էլ լինի, կվերագրվի նաև width ին
    Image(painter = image, contentDescription = null, modifier = modifier
        .aspectRatio(1f, matchHeightConstraintsFirst = true)
        .border(width = 1.dp, color = Color.LightGray, shape = CircleShape)
        .padding(3.dp)
        .clip(CircleShape))
}

@Composable
fun StatSection(modifier: Modifier = Modifier) {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceAround, modifier = modifier) {
        ProfileStat(numberText = "0", text = "Posts")
        ProfileStat(numberText = "1B", text = "Followers")
        ProfileStat(numberText = "1", text = "Following")
    }
}

@Composable
fun ProfileStat(numberText: String, text: String, modifier: Modifier = Modifier) {
    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier) {
        Text(text = numberText, fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = text)
    }
}

@Composable
fun ProfileDescription(displayName: String, description: String, url: String, followedBy: List<String>, otherCount: Int) {
    val letterSpacing = 0.5.sp
    val lineHeight = 20.sp
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(20.dp)) {
        Text(text = displayName, fontWeight = FontWeight.Bold, letterSpacing = letterSpacing, lineHeight = lineHeight)
        Text(text = description, letterSpacing = letterSpacing, lineHeight = lineHeight)
        Text(text = url, letterSpacing = letterSpacing, lineHeight = lineHeight, color = Color(0xFF3D3D91))
        if (followedBy.isNotEmpty()) {
            Text(text = buildAnnotatedString {
                val boldStyle = SpanStyle(color = Color.Black, fontWeight = FontWeight.Bold)
                append("Followed by ")
                followedBy.forEachIndexed { index, name ->
                    pushStyle(boldStyle)
                    append(name)
                    pop()
                    if (index < followedBy.size - 1) append(", ")
                }
                if (otherCount > 2) {
                    append(" and ")
                    pushStyle(boldStyle)
                    append("$otherCount others")
                }
            }, letterSpacing = letterSpacing, lineHeight = lineHeight)
        }
    }
}

@Composable
fun ButtonSection(modifier: Modifier = Modifier) {
    val minWidth = 95.dp
    val height = 30.dp
    Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = modifier) {
        ActionButton(text = "Following", icon = Icons.Default.KeyboardArrowDown, modifier = Modifier
            .defaultMinSize(minWidth = minWidth)
            .height(height))

        ActionButton(text = "Message", modifier = Modifier
            .defaultMinSize(minWidth = minWidth)
            .height(height))

        ActionButton(text = "Email", modifier = Modifier
            .defaultMinSize(minWidth = minWidth)
            .height(height))

        ActionButton(icon = Icons.Default.KeyboardArrowDown, modifier = Modifier
            .size(height)
            .defaultMinSize(minWidth = minWidth)
            .height(height))
    }
}

@Composable
fun ActionButton(modifier: Modifier = Modifier, text: String? = null, icon: ImageVector? = null) {
    Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically, modifier = modifier
        .border(width = 1.dp, color = Color.LightGray, shape = RoundedCornerShape(5.dp))
        .padding(6.dp)) {
        if (text != null) {
            Text(text = text, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
        }
        if (icon != null) {
            Icon(imageVector = icon, contentDescription = null, tint = Color.Black)
        }
    }
}

@Composable
fun HighlightSection(modifier: Modifier = Modifier, highlights: List<StoryHighlight>) {
    LazyRow(modifier = modifier) {
        items(highlights.size) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center, modifier = Modifier.padding(end = 15.dp)) {
                RoundImage(image = painterResource(id = highlights[it].imageResId), modifier = Modifier.size(70.dp))
                Text(text = highlights[it].text, overflow = TextOverflow.Ellipsis, textAlign = TextAlign.Center)
            }
        }
    }
}

@Composable
fun PostTabView(modifier: Modifier = Modifier, tabs: List<ImageWithText>, onTabSelected: (selectedIndex: Int) -> Unit) {
    var selectedTabIndex by remember {
        mutableStateOf(0)
    }
    val inactiveColor = Color(0xFF777777)
    TabRow(selectedTabIndex = selectedTabIndex, contentColor = Color.Black, modifier = modifier) {
        tabs.forEachIndexed { index, imageWithText ->
            Tab(selected = selectedTabIndex == index, selectedContentColor = Color.Black, unselectedContentColor = inactiveColor, onClick = {
                selectedTabIndex = index
                onTabSelected(index)
            }) {
                Icon(painter = painterResource(id = imageWithText.imageResId), contentDescription = imageWithText.text, tint = if (selectedTabIndex == index) Color.Black else inactiveColor, modifier = Modifier
                    .padding(10.dp)
                    .size(12.dp))
            }
        }
    }
}

@Composable
fun PostSection(posts: List<Painter>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(columns = GridCells.Fixed(3), modifier = modifier.scale(1.01f)) {
        items(posts.size) {
            Image(painter = posts[it], contentDescription = null, contentScale = ContentScale.Crop, modifier = modifier
                .aspectRatio(1f)
                .border(width = 1.dp, color = Color.White))
        }
    }
}

data class StoryHighlight(@DrawableRes val imageResId: Int = R.drawable.image_funny, val text: String = "Title")
data class ImageWithText(@DrawableRes val imageResId: Int = R.drawable.ic_search, val text: String = "Text")