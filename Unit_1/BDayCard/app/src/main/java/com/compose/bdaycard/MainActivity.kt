package com.compose.bdaycard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose.bdaycard.ui.theme.BDayCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BDayCardTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    BirthDayGreetingWithImage(getString(R.string.happy_birthday), "Heshan")
                }
            }
        }
    }
}

@Composable
fun BirthDayGreetingWithImage(message: String, from: String) {
    val image = painterResource(id = R.drawable.androidparty)
    Box {
        Image(painter = image,
            contentDescription = "This is an Image",
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(), contentScale = ContentScale.Crop)
        BirthDayGreetingWithText(message = message, from = from)
    }
}


@Composable
fun BirthDayGreetingWithText(message: String, from: String) {
    Column {
        Text(text = message,
            fontSize = 36.sp,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(align = Alignment.Start ).padding(16.dp))
        Text(text = from,
            fontSize = 24.sp,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(align = Alignment.End)
                .padding(start = 16.dp, end = 16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun BirthDayGreetingWithTextPreview() {
    BDayCardTheme {
        BirthDayGreetingWithImage ("Happy BirthDay", "Heshan")
    }
}