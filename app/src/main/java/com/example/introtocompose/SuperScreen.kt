package com.example.introtocompose

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun SuperScreen() {
    Scaffold(
        topBar = {
            /*TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Blue,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White,
                    actionIconContentColor = Color.White
                ),
                title = { Text(text = "I'm a topBar") },
                navigationIcon = {
                    IconButton(
                        modifier = Modifier.wrapContentSize(),
                        onClick = { *//**//* }
                    ) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                    }
                },
                actions = {
                    Row {
                        IconButton(
                            modifier = Modifier.wrapContentSize(),
                            onClick = { *//**//* }
                        ) {
                            Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null)
                        }

                        IconButton(
                            modifier = Modifier.wrapContentSize(),
                            onClick = { *//**//* }
                        ) {
                            Icon(imageVector = Icons.Default.Settings, contentDescription = null)
                        }
                    }
                }
            )*/
            TopBarConstraintLayout(
                type = TopBarType.Text("I'm a TopBar I'm a TopBar I'm a TopBar").apply {
                    leftBox = Boxes(0, true)
                    centerBox = Boxes(0, true)
                    rightBox = Boxes(0, true)
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color.Blue,
                contentColor = Color.White,
            ) {
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    IconButton(
                        modifier = Modifier.wrapContentSize(),
                        onClick = { /**/ }
                    ) {
                        Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null)
                    }

                    IconButton(
                        modifier = Modifier.wrapContentSize(),
                        onClick = { /**/ }
                    ) {
                        Icon(imageVector = Icons.Default.Settings, contentDescription = null)
                    }

                    IconButton(
                        modifier = Modifier.wrapContentSize(),
                        onClick = { /**/ }
                    ) {
                        Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null)
                    }

                    IconButton(
                        modifier = Modifier.wrapContentSize(),
                        onClick = { /**/ }
                    ) {
                        Icon(imageVector = Icons.Default.Settings, contentDescription = null)
                    }
                }
            }
        }
    ) {
        Surface {
            Box (
                modifier = Modifier.fillMaxSize().background(Color.White).padding(it)
            ) {
                Text(text = "I'm a topBar")
            }
        }
    }
}