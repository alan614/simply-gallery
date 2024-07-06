package com.teleserv.gallery

import android.os.Bundle
import android.widget.Gallery
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.teleserv.gallery.ui.theme.GalleryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GalleryTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    /*Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )*/
                    GalleryApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GalleryTheme {
        //Greeting("Android")
        GalleryApp()
    }
}

@Composable
fun GalleryApp(modifier: Modifier = Modifier) {
    Gallery(
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize(align = Alignment.Center)
    )
}

@Composable
fun Gallery(modifier: Modifier) {
    var imageKey by rememberSaveable {
        mutableIntStateOf(0)
    }

    val imageId: Int = when (imageKey) {
        0 -> R.drawable.puppy
        1 -> R.drawable.cats
        2 -> R.drawable.jellyfish
        else -> R.drawable.shrine
    }

    val imageDescription: String = when(imageKey) {
        0 -> stringResource(R.string.pupper)
        1 -> stringResource(R.string.car)
        2 -> stringResource(R.string.jellyfish)
        else -> stringResource(R.string.shrine)
    }
    
    Column(
        modifier = modifier
            .statusBarsPadding()
            .padding(horizontal = 40.dp)
            .safeDrawingPadding()
            .fillMaxWidth()
            .fillMaxHeight(),
    ) {
        Row(
            modifier = Modifier
                .weight(1.0f)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            //horizontalArrangement = Arrangement.Center,
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(painter = painterResource(id = imageId), contentDescription = imageDescription, modifier = Modifier.weight(1.0f))
                Text(text = imageDescription, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth().weight(0.1f))
            }
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier
                .fillMaxWidth()
                //.padding(bottom = 40.dp)
        ) {
            NavigateButton(
                text = "Previous",
                isEnabled = imageKey > 0
            ) {
                val newImageKey = imageKey - 1
                imageKey = if (newImageKey < 0) {
                    0
                } else {
                    newImageKey
                }
            }
            NavigateButton(
                text = "Next",
                isEnabled = imageKey < 3
            ) {
                val newImageKey = imageKey + 1
                imageKey = if (newImageKey > 3) {
                    3
                } else {
                    newImageKey
                }
            }
        }
    }
}

@Composable
fun NavigateButton(text: String, modifier: Modifier = Modifier, isEnabled: Boolean = true, onClickLambda: () -> Unit) {
    Button(
        enabled = isEnabled,
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        modifier = modifier
            //.padding(horizontal = 8.dp)
            .width(96.dp),
        contentPadding = PaddingValues(horizontal = 1.dp),
        onClick = onClickLambda
    ) {
        Text(text = text, modifier = Modifier.padding(horizontal = 16.dp))
    }
}