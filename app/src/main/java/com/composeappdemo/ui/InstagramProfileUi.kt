package com.composeappdemo.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.composeappdemo.R


@Composable
fun ProfileScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(name = "Hovhannisyan Karo")
        Spacer(modifier = Modifier.size(16.dp))
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

        }
    }
}

@Composable
fun RoundImage(image: Painter, modifier: Modifier = Modifier) {
    //matchHeightConstraintsFirst - սկզբից նայում ա height ին, ու ինչ height էլ լինի, կվերագրվի նաև width ին
    Image(painter = image, contentDescription = null, modifier = modifier.aspectRatio(1f, matchHeightConstraintsFirst = true))
}