package com.davidchura.sistema1228.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.davidchura.sistema1228.R

@Composable
fun HeaderImage(drawableResource: Int, descriptionResource: Int, titleResource: Int) {
    Box (
        contentAlignment = Alignment.BottomEnd,
        modifier = Modifier.height(300.dp)
    ){
        Image(
            painter = painterResource(id = drawableResource),
            modifier = Modifier.height(300.dp),
            contentScale = ContentScale.Crop,
            contentDescription = stringResource(id = descriptionResource)
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Blue.copy(alpha = 0.5f))
        )
        Text(text = stringResource(id = titleResource),
            style = MaterialTheme.typography.headlineLarge,
            color = Color.White,
            modifier = Modifier
                .padding(all = dimensionResource(id = R.dimen.size_2)))
    }
}
