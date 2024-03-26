package com.example.introtocompose

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.introtocompose.ui.theme.IntroToComposeTheme
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SnackBarCustom(
    message: String,
    actionLabel: String? = null,
    duration: SnackbarDuration = SnackbarDuration.Short
) {
    val snackState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    scope.launch {
        snackState.showSnackbar(
            message = message,
            actionLabel = actionLabel,
            duration = duration
        )
    }

    Box(modifier = Modifier.fillMaxSize(), Alignment.BottomCenter) {
        SnackbarHost(hostState = snackState) {
            Snackbar(
                snackbarData = it,
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

@Preview
@Composable
fun SnackBarPreview() {
    IntroToComposeTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {

            var isClicked = remember {
                mutableStateOf(false)
            }

            Box(contentAlignment = Alignment.Center) {
                Button(
                    onClick = {
                        isClicked.value = !isClicked.value
                    },
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    Text(text = "Show Snackbar")
                }
            }

            if (isClicked.value) {
                SnackBarCustom(
                    message = "Hi i am snackbar",
                    actionLabel = "Hide",
                    duration = SnackbarDuration.Indefinite,
                )
            } else {
                Box {}
            }
        }
    }
}
