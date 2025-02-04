package com.example.introtocompose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.introtocompose.ui.theme.IntroToComposeTheme
import kotlinx.coroutines.delay

data class TestDataClass(
    val string1: String,
    val string2: String
)

class TestViewModel: ViewModel(){
    var testVar by mutableStateOf("Hi")
    var dataClass by mutableStateOf<TestDataClass?>(null)

    fun changeText() {
        testVar = "New"
        dataClass = TestDataClass("1", "2")
    }
}

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IntroToComposeTheme {
                setStatusBarColor(color = Color.Green)
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.primary
                ) {
                    Greeting(viewModel = viewModel())
                }
            }
        }
    }
}

@Composable//displayable
fun Greeting(viewModel: TestViewModel = viewModel()) {
    DisplayOnScreen(
        testVar = viewModel.testVar,
        testTitle = viewModel.dataClass?.string1,
        testDescription = viewModel.dataClass?.string2,
        changeText = {
            viewModel.changeText()
        }
    )
}

@Composable//componente final
fun DisplayOnScreen(
    testVar: String,
    testTitle: String?,
    testDescription: String?,
    changeText: () -> Unit
){
    Column {
        Text(testVar)
        if (testTitle != null) {
            Text(testTitle)
        }
        if (testDescription != null) {
            Text(testDescription)
        }
        Button(onClick = changeText){
            Text("Click")
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true, showSystemUi = true)
@Composable//uso
fun GreetingPreview(viewModel: TestViewModel = viewModel()) {
    val counterState by mutableStateOf(TestDataClass("azure", "aws"))

    IntroToComposeTheme {
        LaunchedEffect(null) {
            delay(2000)
            viewModel.dataClass = counterState
        }

        Greeting(viewModel = viewModel())
    }
}