package com.horse.composetopbarsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.ananananzhuo.composelib.statusbarColor
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsHeight
import com.google.accompanist.insets.statusBarsHeight
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.horse.composetopbarsample.ui.theme.ComposeTopBarSampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTopBarSampleTheme {
                // A surface container using the 'background' color from the theme
                WindowCompat.setDecorFitsSystemWindows(window, false)
                rememberSystemUiController().run {
                    setStatusBarColor(statusbarColor, false)
                    setSystemBarsColor(statusbarColor, false)
                    setNavigationBarColor(statusbarColor, false)
                }
                ProvideWindowInsets {
                    Surface(color = MaterialTheme.colors.background) {
                        Greeting("Android")
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {

    Scaffold(topBar = {
        Column {
            Spacer(//???????????????Spacer?????????????????????????????????
                modifier = Modifier
                    .statusBarsHeight()
                    .fillMaxWidth()
            )
            TopAppBar(
                backgroundColor = statusbarColor,
                title = {
                    Text("??????", style = TextStyle(color = Color.White))
                }, navigationIcon = {
                    IconButton(onClick = {

                    }) {
                        Icon(Icons.Filled.ArrowBack, "", tint = Color.White)
                    }
                })
        }
    },
        bottomBar = {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .background(statusbarColor),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "??????")
                    Text(text = "?????????")
                    Text(text = "?????????")
                    Text(text = "??????")

                }
                Spacer(modifier = Modifier.navigationBarsHeight())//BottomBar?????????????????????
            }
        }
    ) {padding->
        LazyColumn(Modifier.padding(bottom = padding.calculateBottomPadding())) {//bottom = padding.calculateBottomPadding()?????????????????????????????????????????????Jul???????????????????????????????????????
            items(30) { index ->
                Box(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(Color.Green),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = index.toString())
                }
            }
        }

    }

}

@Preview(showBackground = true)
@Composable
fun TopBarPreview1() {
    ComposeTopBarSampleTheme {
        TopBarSearchView(title = "??????", textChangeListener = {}) {

        }
    }
}