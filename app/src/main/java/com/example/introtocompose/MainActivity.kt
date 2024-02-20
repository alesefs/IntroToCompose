package com.example.introtocompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.introtocompose.ui.theme.IntroToComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IntroToComposeTheme {
                // A surface container using the 'background' color from the theme
                /*Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.primary
                ) {
//                    Greeting("Android")
                }*/
                MyApp()
            }
        }
    }
}

/*@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun ShowAge(age: Int = 12) {
    Text(
        text = age.toString()
    )
}*/

@Composable
fun MyApp() {
    val moneyCounter = remember {
        mutableStateOf(0) //mutableStateOf(0) // = 0
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF546E7A)
    ) {
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "$ ${moneyCounter.value}",
                style = TextStyle(
                    Color.White,
                    fontSize = 35.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            ) //Texto
            Spacer(modifier = Modifier.height(30.dp)) // espaçamento
            CreateCircle(moneyCounter = moneyCounter.value) { newValue -> //Custom Circle
//                moneyCounter.value = it + 1
                moneyCounter.value = newValue
            }

            if (moneyCounter.value > 25) {
                Text(text = "Lots of money!!")
            }
        }
    }
}

@Composable
fun CreateCircle(moneyCounter: Int = 0, updateMoneyCount: (Int) -> Unit) {
    //"by" guarda diretamente o estado
    /*var moneyCounter by remember {
        mutableIntStateOf(0) //mutableStateOf(0) // = 0

    }*/

    //"=" guarda o valor do estado
    /*var moneyCounter = remember {
        mutableIntStateOf(0) //mutableStateOf(0) // = 0
    }*/

    Card(modifier = Modifier
        .padding(3.dp)
        .size(100.dp)
        .clickable {
//            moneyCounter += 1 //by
//            moneyCounter.value += 1 //= (value ou intValue)
            Log.d("ALELOG", "moneyCounter: $moneyCounter")
            updateMoneyCount(moneyCounter + 1)
        },
        shape = CircleShape,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Box (
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Tap $moneyCounter",
                modifier = Modifier
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    IntroToComposeTheme {
        /*Column {
            Greeting("Android")
            ShowAge(24)
        }*/
        MyApp()
    }
}

@Preview
@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    text: String = "teste",
    icon: ImageVector? = Icons.Rounded.Notifications,
    type: String = "",
    enable: Boolean = true,
    isRect: Boolean = false,
    onClick: (() -> Unit)? = null
) {

    val ContentPadding =
        PaddingValues(
            horizontal = 24.dp,
            vertical = 8.dp
        )

    IntroToComposeTheme {
        Button(
            modifier = modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = if (isRect) {
                RoundedCornerShape(0.dp)
            } else {
                RoundedCornerShape(24.dp)
            },
            enabled = enable,
            colors = ButtonDefaults.buttonColors(
                containerColor = if (enable) {
                    Color.Blue
                } else {
                    Color.Green
                }
            ),
            contentPadding = ContentPadding,
            onClick = { onClick?.invoke() }
        ) {

            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .height(16.dp)
                    .wrapContentSize(Alignment.Center, true)
                    .background(Color.Red),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                if (icon != null) {
                    Icon(
                        modifier = modifier.size(16.dp),
                        imageVector = icon,
                        contentDescription = "$icon"
                    )

                    Spacer(modifier = modifier.size(8.dp))
                }

                Text(
                    text = "Tap $text",
                    fontSize = 15.sp,
                    letterSpacing = 0.0045.sp,
                    modifier = modifier
                )
            }

        }
    }
}