package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.lemonade.ui.theme.LemonadeTheme

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
                    Lemonade()
                }
            }
        }
    }
}

@Composable
fun Lemonade() {
    LemonSelecting()
   
}

@Composable
fun LemonSelecting(){

    var count by remember{ mutableStateOf(1)}
    var taps by remember{ mutableStateOf(0)}

    when(count){
        1 -> {
            OneLayout(
                textResourceId = R.string.select_lemon,
                imageResourceId = R.drawable.lemon_tree,
                contentDescId = R.string.desc1,
                onImageClick = {
                    taps = (2..4).random()
                    count++
                })


        }
        2 -> {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {
                Text(text = stringResource(id = R.string.squeeze_lemon))
                Image(
                    painter = painterResource(id = R.drawable.lemon_squeeze),
                    contentDescription = stringResource(id = R.string.desc2),
                    modifier = Modifier.clickable(onClick = {
                        if(taps-- == 0) {
                            count++
                        }
                    })
                )
            }

        }
        3 -> {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {
                Text(text = stringResource(id = R.string.drink))
                Image(
                    painter = painterResource(id = R.drawable.lemon_drink),
                    contentDescription = stringResource(id = R.string.desc3),
                    modifier = Modifier.clickable(onClick = { count++ })
                )
            }

        }
        else -> {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {
                Text(text = stringResource(id = R.string.start_again))
                Image(
                    painter = painterResource(id = R.drawable.lemon_restart),
                    contentDescription = stringResource(id = R.string.desc4),
                    modifier = Modifier.clickable(onClick = {
                        taps = 0
                        count = 1 })
                )
            }

            }

    }


}

@Composable

fun OneLayout(
    textResourceId: Int,
    imageResourceId: Int,
    contentDescId: Int,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center)
    {
        Text(text = stringResource(id = textResourceId))

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(id = imageResourceId),
            contentDescription = stringResource(id = contentDescId),
            modifier
                .clickable(
                    onClick = onImageClick
                )
                .border(
                    BorderStroke(2.dp, Color(105, 205, 216)),
                    shape = RoundedCornerShape(4.dp)
                )
                .padding(16.dp)
        )
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LemonadeTheme {
        Lemonade()
    }
}