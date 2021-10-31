package com.horse.composetopbarsample

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ananananzhuo.composelib.statusbarColor
import com.google.accompanist.insets.statusBarsHeight

/**
 * author  :mayong
 * function:
 * date    :2021/10/31
 **/


@Composable
fun TopBarView(title: String, callback: () -> Unit) {
    Column {
        Spacer(
            modifier = Modifier
                .statusBarsHeight()
                .fillMaxWidth()
        )
        TopAppBar(title = {
            Text(title)
        }, navigationIcon = {
            IconButton(onClick = {
                callback()
            }) {
                Icon(Icons.Filled.ArrowBack, "")
            }
        })
    }
}


/**
 * 带搜索框的TopBar
 */
@Composable
fun TopBarSearchView(
    title: String,
    textChangeListener: (text: String) -> Unit,
    callback: () -> Unit
) {
    var text by rememberSaveable {
        mutableStateOf("")
    }
    Column {
        Spacer(
            modifier = Modifier
                .statusBarsHeight()
                .fillMaxWidth()
        )
        TopAppBar {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
                    callback()
                }) {
                    Icon(Icons.Filled.ArrowBack, "")
                }
                TextField(
                    value = text,
                    singleLine = true,
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .fillMaxWidth(0.9f)
                        .clip(RoundedCornerShape(5.dp)),
                    colors = TextFieldDefaults.textFieldColors(
                        textColor= statusbarColor,
                        backgroundColor = Color.White,
                        focusedIndicatorColor = Color.White,
                        unfocusedIndicatorColor = Color.White
                    ),
                    label={
                          Text(text = "请输入搜索内容",color= statusbarColor,style = TextStyle(fontSize = 10.sp))
                    },
                    textStyle = TextStyle(fontSize = 14.sp),
                    onValueChange = {
                        text = it
                        textChangeListener(it)
                    })
            }
        }
    }
}