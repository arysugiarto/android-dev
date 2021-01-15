package com.arysugiarto.jetpack_compose_meterials

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BatteryAlert
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arysugiarto.jetpack_compose_meterials.ui.JetpackcomposemeterialsTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackcomposemeterialsTheme {
                LayoutCodelab()
            }
        }
    }
}

@Composable
fun LayoutCodelab(){
    Scaffold(
            topBar = {
                TopAppBar(
                        
                        title = {
                            Text(text = "Layout Codelab")
                        },
                        actions = {
                            IconButton(onClick = { /* doSomething() */ }) {
                                Icon(Icons.Filled.Bookmark)
                            }
                            IconButton(onClick = { /* doSomething() */ }) {
                                Icon(Icons.Filled.Favorite)
                            }
                        }
                )
            }
    ){ innerPadding ->
        BodyContent(Modifier.padding(innerPadding).padding(20.dp))
    }
}

@Composable
fun BodyContent(modifier: Modifier = Modifier){
    Column(modifier = modifier) {
        Text(text = "Hi there!")
        Text(text = "Thanks for going through the Layouts codelab")
    }
}

@Preview (showBackground = true, showSystemUi = true)
@Composable
fun LayoutCodelabPreview(){
    JetpackcomposemeterialsTheme {
        LayoutCodelab()
    }
}