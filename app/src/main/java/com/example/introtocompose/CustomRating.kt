package com.example.introtocompose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch

data class RatingModel(
    var rating: Int = 0
)

class RatingDataViewModel : ViewModel() {
    //    var ratingModel: MutableState<Int> = mutableIntStateOf(0)
    var ratingModel: MutableState<RatingModel> = mutableStateOf(RatingModel(rating = 0))

    init {
        viewModelScope.launch {
            updateRating(0)
        }
    }

    fun updateRating(value: Int) {
//        ratingModel.value = value
        ratingModel.value = RatingModel(value)
    }

    fun clearRating() {
//        ratingModel.value = 0
        ratingModel.value = RatingModel(0)
    }
}

@Composable
fun CustomRatingSingleView(
    rating: Int,
    iconActive: Painter,
    iconInactive: Painter,
    activeIconColor: Color,
    viewModel: RatingDataViewModel = RatingDataViewModel(),
    onRatingChanged: ((Int) -> Unit)? = null
) {
    println("viewmodel ${viewModel.ratingModel.value.rating}")
    println("rating $rating")
    val isSelected = viewModel.ratingModel.value.rating >= rating
    val icon = if (isSelected) iconActive else iconInactive
    val iconTintColor = if (isSelected) activeIconColor else Color(0xFFFFFFFF)
    val starSize = 40.dp

    Image(
        painter = icon,
        contentDescription = null,
        colorFilter = ColorFilter.tint(iconTintColor),
        modifier = Modifier
            /*.clickable {
                if (rating == viewModel.ratingModel.value.rating) {
                    viewModel.clearRating()
                    onRatingChanged?.invoke(0)
                } else {
                    viewModel.updateRating(rating)
                    onRatingChanged?.invoke(rating)
                }
            }*/
            .selectable(
                selected = isSelected,
                onClick = {
                    if (rating == viewModel.ratingModel.value.rating) {
                        viewModel.clearRating()
                        onRatingChanged?.invoke(0)
                    } else {
                        viewModel.updateRating(rating)
                        onRatingChanged?.invoke(rating)
                    }
                }
            )
            .size(starSize)
    )
}

@Composable
fun CustomRatingGroup(
    modifier: Modifier = Modifier,
    viewModel: RatingDataViewModel = RatingDataViewModel(),
    minText: String? = null,
    maxText: String? = null,
    iconActive: Painter = rememberVectorPainter(image = Icons.Filled.Star),
    iconInactive: Painter = rememberVectorPainter(image = Icons.Default.Star),
    activeIconColor: Color = Color(0xFFFFC700),
    onRatingChanged: ((Int) -> Unit)? = null
) {
    val starSpacing = 16.dp
    val maxStars = 5

    Box(modifier) {
        Column(
//            modifier = Modifier.width(296.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.selectableGroup(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier
                    .width(starSpacing)
                    .height(10.dp)
                    .background(Color.Red))

                for (index in 1..maxStars) {
                    CustomRatingSingleView(
                        viewModel = viewModel,
                        rating = index,
                        iconActive = iconActive,
                        iconInactive = iconInactive,
                        activeIconColor = activeIconColor,
                        onRatingChanged = onRatingChanged
                    )

                    if (index < maxStars) {
                        Spacer(
                            modifier = Modifier
                                .width(starSpacing)
                                .height(10.dp)
                                .background(Color.Red)
                        )
                    }
                }
                Spacer(modifier = Modifier
                    .width(starSpacing)
                    .height(10.dp)
                    .background(Color.Red))
            }

            if (minText != null && maxText != null) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = minText)
                    Text(text = maxText)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CustomRatingGroupPreview() {
    var rating by remember { mutableIntStateOf(0) }
    val viewModel: RatingDataViewModel = viewModel()

    Column {
        CustomRatingGroup()

        HorizontalDivider()

        CustomRatingGroup()

        HorizontalDivider()

        CustomRatingGroup(
            viewModel = viewModel,
            minText = "min",
            maxText = "max",
            iconActive = rememberVectorPainter(image = Icons.Default.Home),
            iconInactive = rememberVectorPainter(image = Icons.Outlined.Home),
            activeIconColor = Color.Red,
            onRatingChanged = {
                rating = it
            }
        )

        Text("rating par $rating")
    }
}

//---------------

@Composable
fun CustomRating(
    rating: Int,
    modifier: Modifier = Modifier,
    minText: String? = null,
    maxText: String? = null,
    iconActive: Painter = rememberVectorPainter(image = Icons.Filled.Star),
    iconInactive: Painter = rememberVectorPainter(image = Icons.Default.Star),
    activeIconColor: Color = Color(0xFFFFC700),
    onRatingChanged: ((Int) -> Unit)? = null
) {
    val starSpacing = 16.dp
    val starSize = 40.dp
    val maxStars = 5

    Box(modifier) {
        Column(
//            modifier = Modifier.width(296.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.selectableGroup(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier
                    .width(starSpacing)
                    .height(10.dp)
                    .background(Color.Red))
                for (i in 1..maxStars) {
                    val isSelected = i <= rating
                    val icon = if (isSelected) iconActive else iconInactive
                    val iconTintColor = if (isSelected) activeIconColor else Color(0xFFFFFFFF)
                    Image(
                        painter = icon,
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(iconTintColor),
                        modifier = Modifier
                            .selectable(
                                selected = isSelected,
                                onClick = {
                                    if (rating == i) {
                                        onRatingChanged?.let {
                                            onRatingChanged(0)
                                        }
                                    } else {
                                        onRatingChanged?.let {
                                            onRatingChanged(i)
                                        }
                                    }
                                }
                            )
                            .width(starSize)
                            .height(starSize)
                    )

                    if (i < maxStars) {
                        Spacer(
                            modifier = Modifier
                                .width(starSpacing)
                                .height(10.dp)
                                .background(Color.Red)
                        )
                    }
                }
                Spacer(modifier = Modifier
                    .width(starSpacing)
                    .height(10.dp)
                    .background(Color.Red))
            }

            if (minText != null && maxText != null) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = minText)
                    Text(text = maxText)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CustomRatingPreview() {
    var rating by remember { mutableIntStateOf(0) }

    Column {
        CustomRating(
            rating = rating
        )

        HorizontalDivider()

        CustomRating(
            rating = rating,
            onRatingChanged = {
                rating = it
            }
        )

        HorizontalDivider()

        CustomRating(
            rating = rating,
            minText = "min",
            maxText = "max",
            iconActive = rememberVectorPainter(image = Icons.Default.Home),
            iconInactive = rememberVectorPainter(image = Icons.Outlined.Home),
            activeIconColor = Color.Red,
            onRatingChanged = {
                rating = it
            }
        )
    }
}
