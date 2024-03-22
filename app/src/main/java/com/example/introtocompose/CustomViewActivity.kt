package com.example.introtocompose

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.introtocompose.components.CustomCard
import com.example.introtocompose.components.CustomCardStyles
import com.example.introtocompose.CustomCardStyles as CustomCardCompose
import com.example.introtocompose.ui.theme.IntroToComposeTheme
import com.example.introtocompose.utils.BorderInt
import com.example.introtocompose.utils.BorderRadiusInt
import com.example.introtocompose.utils.BorderRadiusStyle
import com.example.introtocompose.utils.BorderRadiusValues
import com.example.introtocompose.utils.BorderValues
import com.example.introtocompose.utils.CardCustomTypes
import com.example.introtocompose.utils.ElevationInt

class CustomViewActivity : AppCompatActivity() {

    private var customCardView: CustomCard? = null
    private var customCardCompose: ComposeView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_custom_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        customCardView = findViewById(R.id.customCardView)
        customCardView?.apply {
            customCardStyles(
                CustomCardStyles.Border(
                    bgColor = R.color.yellow,
                    shapeStyle = Pair(
                        BorderRadiusStyle.All,
                        BorderRadiusInt.Round
                    ),
                    borderStyle = Pair(
                        BorderInt.Medium,
                        R.color.purple_500
                    ),
                    context = this@CustomViewActivity
                )
            )
        }
        /*customCardView.apply {
            this?.let {
                style = CardCustomTypes.Border
                bgColor = R.color.light_gray
                deactivateColor = R.color.teal_200

                radiusStyle = BorderRadiusStyle.SharpBottomStart
                radiusValue = BorderRadiusInt.Round

                elevationValue = ElevationInt.Large

                borderColor = R.color.green
                borderSize = BorderInt.Large
            }
        }*/

        customCardCompose = findViewById(R.id.customCardCompose)
        customCardCompose?.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                YourComposable()
            }
        }

    }

    @Composable
    private fun YourComposable() {
        CardCustomSta(
            customCardStyles = CustomCardCompose.Border(
                bgColor = Color.Black,
                shapeStyle = Pair(
                    BorderRadiusStyle.SharpBottomEnd,
                    BorderRadiusValues.Default
                ),
                borderStyle = Pair(BorderValues.XLarge, Color.Magenta)
            ),
        ) {
            Box(
                modifier = Modifier.size(250.dp, 100.dp),
            ) {

            }
        }
    }
}