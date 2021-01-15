package com.arysugiarto.jepack_compose_codelabs1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arysugiarto.jepack_compose_codelabs1.ui.Jepackcomposecodelabs1Theme
import com.arysugiarto.jepack_compose_codelabs1.ui.typography

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyScreenContent()
        }
    }
}

@Composable
fun MyApp(content : @Composable () -> Unit){
    Jepackcomposecodelabs1Theme {
        Surface(color = MaterialTheme.colors.background) {
            Greeting("Android")
            content()
        }
    }
}
@Composable
fun Greeting(name: String) {
        Text(text = "Hello $name!", modifier = Modifier.padding(24.dp),
        style = typography.h6)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
  MyApp{
      Greeting("Android")
  }
}

@Composable
fun MyScreenContent(){
    Column{
        Greeting("Android")
        Divider(color = Color.Black)
        Greeting("there")
    }
}