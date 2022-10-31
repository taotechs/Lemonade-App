package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                    LemonadePrep()
                }
            }
        }
    }
}
// Lemonade app Function
@Composable
fun Lemonade(
    currentText: String, currentImage: Int, onImageClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = currentText,
            fontSize = 20.sp, fontWeight = FontWeight.Bold, modifier=Modifier.padding( bottom = 5.dp)
        )
        Image(
            painter = painterResource(currentImage),
            contentDescription = null,
            modifier = Modifier
                .clickable(onClick = onImageClick)
                .border(
                    width = 3.dp, color = Color.Cyan,
                    shape = RectangleShape,
                )

        )
    }
}

@Composable
fun LemonadePrep() {
    var squeeze by remember { mutableStateOf(0) }
    var status by remember { mutableStateOf(1) }
    when (status) {
        1 -> {
            Lemonade(currentText = stringResource(R.string.TapTree),
                currentImage = R.drawable.lemon_tree,
                onImageClick = {
                    status = 2
                    squeeze = (2..4).random()
                })

        }
        2 -> {
            Lemonade(currentText = stringResource(R.string.KeepTap),
                currentImage = R.drawable.lemon_squeeze,
                onImageClick = {
                    squeeze--
                    if (squeeze == 0) status = 3
                })
        }
        3 -> {
            Lemonade(currentText = stringResource(R.string.TapDrink),
                currentImage = R.drawable.lemon_drink,
                onImageClick = {
                    status = 4
                })
        }
        else -> {
            Lemonade(currentText = stringResource(R.string.TapEmpty),
                currentImage = R.drawable.lemon_restart,
                onImageClick = {
                    status = 1
                })

        }
    }


}

@Preview(showBackground = true)
@Composable
fun LemonadeAppPreview() {
    LemonadeTheme {
        LemonadePrep()
    }
}


