package com.example.introtocompose

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.introtocompose.components.CustomCard
import com.example.introtocompose.components.CustomCardStyles
import com.example.introtocompose.utils.BorderInt
import com.example.introtocompose.utils.BorderRadiusInt
import com.example.introtocompose.utils.BorderRadiusStyle
import com.example.introtocompose.utils.CardCustomTypes
import com.example.introtocompose.utils.ElevationInt

class CustomViewActivity : AppCompatActivity() {

    private var cardbase: CustomCard? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_custom_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        cardbase = findViewById(R.id.cardbase)
        cardbase?.apply {
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
        /*cardbase.apply {
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
    }
}