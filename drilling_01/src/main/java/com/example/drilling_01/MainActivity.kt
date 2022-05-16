package com.example.drilling_01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.drilling_01.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LemonadeApp()
                }
            }
        }
    }
}

@Composable
fun LemonadeApp() {
    var count by remember { mutableStateOf(1) }
    var taps by remember { mutableStateOf(0) }
    when (count) {
        1 -> TextAndImage(
            textId = R.string.select_lemon,
            imageId = R.drawable.lemon_tree,
            descriptionId = R.string.desc1,
            onImageClick = {
                taps = (2..4).random()
                count++
            },

            )
        2 -> TextAndImage(
            textId = R.string.squeeze_lemon,
            imageId = R.drawable.lemon_squeeze,
            descriptionId = R.string.desc2,
            onImageClick = {
                taps--
                if (taps == 0) {
                    count++
                }
            },

            )
        3 -> TextAndImage(
            textId = R.string.drink,
            imageId = R.drawable.lemon_drink,
            descriptionId = R.string.desc3,
            onImageClick = {
                count++
            },

            )
        4 -> TextAndImage(
            textId = R.string.start_again,
            imageId = R.drawable.lemon_restart,
            descriptionId = R.string.desc4,
            onImageClick = {
                count = 1
            },

            )
    }
}


@Composable
fun TextAndImage(
    textId: Int,
    imageId: Int,
    descriptionId: Int,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = stringResource(id = textId))
        Image(
            painter = painterResource(id = imageId),
            contentDescription = stringResource(id = descriptionId),
            modifier
                .clickable(onClick = onImageClick)
                .border(BorderStroke(width = 2.dp, color = Color(155, 28, 98)))
                .padding(16.dp)

        )

    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LemonadeTheme {
        LemonadeApp()
    }
}