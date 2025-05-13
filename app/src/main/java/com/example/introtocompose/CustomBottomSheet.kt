package com.example.introtocompose

/*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetDefaults
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.contentColorFor
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Stable
class PBBottomSheetPaddings(
    val start: Dp = 16.dp,
    val end: Dp = 16.dp,
    val top: Dp = 16.dp,
    val bottom: Dp = 24.dp,
)

@Composable
fun PBBottomSheet(
    sheetContent: @Composable ColumnScope.() -> Unit,
    sheetState: ModalBottomSheetState,
    modifier: Modifier = Modifier,
    sheetGesturesEnabled: Boolean = true,
    sheetShape: Shape = RoundedCornerShape(
        topStart = PBTheme.borderRadiusValue.large,
        topEnd = PBTheme.borderRadiusValue.large,
        bottomStart = PBTheme.borderRadiusValue.sharp,
        bottomEnd = PBTheme.borderRadiusValue.sharp
    ),
    sheetElevation: Dp = ModalBottomSheetDefaults.Elevation,
    sheetBackgroundColor: Color = MaterialTheme.colors.surface,
    sheetContentColor: Color = contentColorFor(sheetBackgroundColor),
    scrimColor: Color = ModalBottomSheetDefaults.scrimColor,
    paddings: PBBottomSheetPaddings = PBBottomSheetPaddings(),
    screenContent: @Composable () -> Unit
) {
    ModalBottomSheetLayout(
        modifier = modifier,
        sheetState = sheetState,
        sheetContent = {
            Column(
                modifier = Modifier
                    .padding(
                        top = paddings.top,
                        bottom = paddings.bottom,
                        start = paddings.start,
                        end = paddings.end
                    )
            ) {
                Box(
                    modifier = Modifier
                        .size(width = 32.dp, height = 4.dp)
                        .clip(CircleShape)
                        .background(PBTheme.color.GrayCloud50)
                        .align(Alignment.CenterHorizontally)
                )
                Spacer(Modifier.size(size = 16.dp))
                sheetContent.invoke(this)
            }
        },
        sheetElevation = sheetElevation,
        sheetShape = sheetShape,
        sheetBackgroundColor = sheetBackgroundColor,
        sheetContentColor = sheetContentColor,
        scrimColor = scrimColor,
        sheetGesturesEnabled = sheetGesturesEnabled,
        content = screenContent,
    )
}

@Preview(showSystemUi = true)
@Composable
fun PBBottomSheet_Preview() {
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )
    val scope = rememberCoroutineScope()

    PBTheme {
        PBBottomSheet(
            sheetState = sheetState,
            sheetContent = {
                repeat(3) { index ->
                    Row(horizontalArrangement = Arrangement.spacedBy(20.dp),
                        modifier = Modifier
                            .clickable { /* TODO */ }
                            .fillMaxWidth()
                            .padding(vertical = 10.dp)) {
                        Icon(
                            PBImages.Icons.Navigation.JpgLinedSmall, contentDescription = null
                        )
                        Text("Option $index")
                    }
                }

                Button(
                    type = ButtonType.Primary,
                    text = "confirm",
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .padding(
                            top = 8.dp,
                        )
                ) {
                    scope.launch {
                        sheetState.hide()
                    }
                }
            }) {
            Button(
                type = ButtonType.Primary,
                text = "open bottomsheet",
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .padding(top = 8.dp)
                    .padding(horizontal = 16.dp)
            ) {
                scope.launch {
                    sheetState.show()
                }
            }
        }
    }
}
*/