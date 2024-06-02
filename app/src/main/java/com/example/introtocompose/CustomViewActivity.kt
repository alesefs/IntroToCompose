package com.example.introtocompose

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.load
import coil.request.ImageRequest
import com.example.introtocompose.components.CustomCard
import com.example.introtocompose.components.CustomCardStyles
import com.example.introtocompose.utils.BorderInt
import com.example.introtocompose.utils.BorderRadiusInt
import com.example.introtocompose.utils.BorderRadiusStyle
import com.example.introtocompose.utils.BorderRadiusValues
import com.example.introtocompose.utils.BorderValues
import com.example.introtocompose.CustomCardStyles as CustomCardCompose

class CustomViewActivity : AppCompatActivity() {

    private var customCardView: CustomCard? = null
    private var customCardCompose: ComposeView? = null

    private var imageView: AppCompatImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
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

        imageView = findViewById(R.id.image_viewer)
        imageView?.apply {
            load("file:///android_asset/raw/thunder.svg") {
                decoderFactory { result, options, _ ->
                    SvgDecoder(result.source, options)
                }
            }
            setColorFilter(ContextCompat.getColor(context, R.color.purple_500), android.graphics.PorterDuff.Mode.MULTIPLY)
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
        val context = LocalContext.current
        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(context = context)
                .data("file:///android_asset/raw/thunder.svg")
                .decoderFactory(SvgDecoder.Factory())
                .error(R.drawable.ic_launcher_background)
                .placeholder(R.drawable.ic_launcher_background)
                .build()
        )

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
                Image(
                    painter = painter,
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(Color.Yellow, BlendMode.Multiply)
                )
            }
        }
    }
}