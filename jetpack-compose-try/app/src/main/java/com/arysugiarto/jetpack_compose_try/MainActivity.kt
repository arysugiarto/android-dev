package com.arysugiarto.jetpack_compose_try

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setContent {
            Greeting()
        }
    }
}

@Composable
fun Greeting() {
    val image = imageResource(R.drawable.header)
    MaterialTheme {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            val imageModifier = Modifier
                .preferredHeight(220.dp) //heigth
                .fillMaxWidth() //full widt or match parrent on xml
                .clip(shape = RoundedCornerShape(10.dp)) //rounded


            Image(image, modifier = imageModifier,
                contentScale = ContentScale.Crop)
            Spacer(Modifier.preferredHeight(20.dp)) //spasi dengan text

            Text( "A day wandering through the sandhills " +
                    "in Shark Fin Cove, and a few of the " +
                    "sights I saw", style = typography.h6,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis) //menambahkan titik2
            Text("Davenport, California", style = typography.body1)
            Text("December 2018", style = typography.body1)
        }
    }


}

@Preview (showBackground = true)
@Composable
fun Previewgreatings(){
    Greeting()
}