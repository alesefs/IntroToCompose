package com.example.introtocompose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.introtocompose.ui.theme.IntroToComposeTheme

@Preview
@Composable
fun CardValueItemPreview() {
    IntroToComposeTheme {
        Box (
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Green)
        ) {
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        //.height(80.dp)
                        .background(Color.Yellow)
                        .clickable { }
                ) {
                    Column {
                        Text(modifier = Modifier
                            .padding(start = 16.dp)
                            .padding(top = 16.dp)
                            .background(Color.Red),
                            text = "TESTES"
                        )
                        Text(modifier = Modifier
                            .padding(start = 16.dp)
                            .padding(vertical = 4.dp)
                            .background(Color.Red),
                            text = "TESTES"
                        )
                        Text(modifier = Modifier
                            .padding(start = 16.dp)
                            .padding(bottom = 16.dp)
                            .background(Color.Red),
                            text = "TESTES"
                        )
                    }
                    /*Box(
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .padding(vertical = 16.dp)
                            .width(100.dp)
                            .height(60.dp)
                            .background(Color.Red)
                    ) {

                    }*/
                }

                Box(
                    modifier = Modifier
                        .background(Color.Blue)
                ) {
                    Column(
                        Modifier.padding(all = 16.dp),
                        horizontalAlignment = Alignment.End
                    ) {
                        Box(
                            modifier = Modifier
                                .padding(bottom = 4.dp)
                                .size(40.dp)
                                .background(Color.Red)
                        ) {

                        }

                        Box(
                            modifier = Modifier
                                .width(100.dp)
                                .height(40.dp)
                                .background(Color.Magenta)
                        ) {

                        }
                    }
                }
            }
        }
    }
}