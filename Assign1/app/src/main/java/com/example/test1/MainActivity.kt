package com.example.test1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Cyan
import androidx.compose.ui.tooling.preview.Preview
import com.example.test1.ui.theme.Test1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Test1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
                    color = Cyan
                ) {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    var myName = "Zhiyan Hu"
    Text(
        text = "Hi, my name is $myName.",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Test1Theme {
        Greeting()
    }
}