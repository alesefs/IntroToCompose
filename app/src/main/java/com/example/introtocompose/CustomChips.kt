package com.example.introtocompose

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.introtocompose.ui.theme.IntroToComposeTheme

data class CustomChipModel(
    var title: String,
    var supportVisual: ChipSupportVisualData? = null,
)

@Composable
fun CustomChipsList(
    options: List<CustomChipModel>,
    type: ChipType,
    size: ChipSize,
    modifier: Modifier = Modifier,
    selectedOption: CustomChipModel? = null,
    enabled: Boolean = true,
    selectionType: ChipSelectionType,
    onSelectionChanged: (String) -> Unit
) {
    Box(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            options.forEach { item ->
                CustomChip(
                    title = item.title,
                    supportVisual = if (item.supportVisual != null) {
                        ChipSupportVisualData(
                            supportVisual = item.supportVisual!!.supportVisual,
                            supportVisualType = item.supportVisual!!.supportVisualType
                        )
                    } else {
                        null
                    },
                    type = type,
                    size = size,
                    isEnabled = enabled,
                    isSelected = selectedOption == item,
                    selectionType = selectionType,
                    onSelectionChanged = {
                        onSelectionChanged(it)
                    },
                )
            }
        }
    }
}

@Composable
fun CustomChipsListMulti(
    options: List<CustomChipModel>,
    type: ChipType,
    size: ChipSize,
    modifier: Modifier = Modifier,
    selectedOptions: List<CustomChipModel?> = listOf(),
    enabled: Boolean = true,
    selectionType: ChipSelectionType,
    onSelectionChanged: (String) -> Unit
) {
    Box(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            options.forEach { item ->
                CustomChip(
                    title = item.title,
                    supportVisual = if (item.supportVisual != null) {
                        ChipSupportVisualData(
                            supportVisual = item.supportVisual!!.supportVisual,
                            supportVisualType = item.supportVisual!!.supportVisualType
                        )
                    } else {
                        null
                    },
                    type = type,
                    size = size,
                    isEnabled = enabled,
                    isSelected = selectedOptions.contains(item),
                    selectionType = selectionType,
                    onSelectionChanged = {
                        onSelectionChanged(it)
                    },
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CustomChipsListExamplePreview() {

    val selectedChipOption: MutableState<CustomChipModel?> = remember {
        mutableStateOf(null)
    }

    val selectedMultiOption: MutableState<List<CustomChipModel?>> = remember {
        mutableStateOf(listOf())
    }

    IntroToComposeTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            CustomChipsList(
                options = getAllChips(),
                selectedOption = selectedChipOption.value,
                type = ChipType.Neutral,
                size = ChipSize.Medium,
                selectionType = ChipSelectionType.Dropdown,
                onSelectionChanged = {
                    selectedChipOption.value = getChip(it)
                }
            )

            HorizontalDivider()

            CustomChipsListMulti(
                options = getAllChips(),
                selectedOptions = selectedMultiOption.value,
                type = ChipType.Neutral,
                size = ChipSize.Medium,
                selectionType = ChipSelectionType.Dropdown,
                onSelectionChanged = {
                    val oldList: MutableList<CustomChipModel?> = selectedMultiOption.value.toMutableList()
                    val optionFromString = getChip(it)

                    if (oldList.contains(optionFromString)){
                        oldList.remove(optionFromString)
                    } else {
                        oldList.add(optionFromString)
                    }

                    selectedMultiOption.value = oldList
                }
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PBChipGrid(
    options: List<CustomChipModel>,
    type: ChipType,
    size: ChipSize,
    modifier: Modifier = Modifier,
    selectedOption: CustomChipModel? = null,
    enabled: Boolean = true,
    selectionType: ChipSelectionType,
    onSelectionChanged: (String) -> Unit
) {
    Box(modifier = modifier) {
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            options.forEach { item ->
                CustomChip(
                    title = item.title,
                    supportVisual = if (item.supportVisual != null) {
                        ChipSupportVisualData(
                            supportVisual = item.supportVisual!!.supportVisual,
                            supportVisualType = item.supportVisual!!.supportVisualType
                        )
                    } else {
                        null
                    },
                    type = type,
                    size = size,
                    isEnabled = enabled,
                    isSelected = selectedOption == item,
                    selectionType = selectionType,
                    onSelectionChanged = {
                        onSelectionChanged(it)
                    },
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PBChipGridMulti(
    options: List<CustomChipModel>,
    type: ChipType,
    size: ChipSize,
    modifier: Modifier = Modifier,
    selectedOptions: List<CustomChipModel?> = listOf(),
    enabled: Boolean = true,
    selectionType: ChipSelectionType,
    onSelectionChanged: (String) -> Unit
) {
    Box(modifier = modifier) {
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            options.forEach { item ->
                CustomChip(
                    title = item.title,
                    supportVisual = if (item.supportVisual != null) {
                        ChipSupportVisualData(
                            supportVisual = item.supportVisual!!.supportVisual,
                            supportVisualType = item.supportVisual!!.supportVisualType
                        )
                    } else {
                        null
                    },
                    type = type,
                    size = size,
                    isEnabled = enabled,
                    isSelected = selectedOptions.contains(item),
                    selectionType = selectionType,
                    onSelectionChanged = {
                        onSelectionChanged(it)
                    },
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PBChipGridExamplePreview() {

    val selectedChipOption: MutableState<CustomChipModel?> = remember {
        mutableStateOf(null)
    }

    val selectedMultiOption: MutableState<List<CustomChipModel?>> = remember {
        mutableStateOf(listOf())
    }

    IntroToComposeTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            PBChipGrid(
                options = getAllChips(),
                selectedOption = selectedChipOption.value,
                type = ChipType.Neutral,
                size = ChipSize.Medium,
                selectionType = ChipSelectionType.Dropdown,
                onSelectionChanged = {
                    selectedChipOption.value = getChip(it)
                }
            )

            Divider()

            PBChipGridMulti(
                options = getAllChips(),
                selectedOptions = selectedMultiOption.value,
                type = ChipType.Neutral,
                size = ChipSize.Medium,
                selectionType = ChipSelectionType.Dropdown,
                onSelectionChanged = {
                    val oldList: MutableList<CustomChipModel?> = selectedMultiOption.value.toMutableList()
                    val optionFromString = getChip(it)

                    if (oldList.contains(optionFromString)){
                        oldList.remove(optionFromString)
                    } else {
                        oldList.add(optionFromString)
                    }

                    selectedMultiOption.value = oldList
                }
            )
        }
    }
}

private fun getChip(value: String): CustomChipModel? {
    val map = getAllChips().associateBy(CustomChipModel::title)
    return map[value]
}

private fun getAllChips(): List<CustomChipModel>{
    return listOf(
        CustomChipModel(
            title = "chip1",
            supportVisual = ChipSupportVisualData(
                R.drawable.baseline_electric_car_24,
                ChipSupportVisualType.Icon
            ),
        ),
        CustomChipModel(
            title = "chip2",
            supportVisual = ChipSupportVisualData(
                R.drawable.baseline_electric_car_24,
                ChipSupportVisualType.Icon
            ),
        ),
        CustomChipModel(
            title = "chip3",
            supportVisual = ChipSupportVisualData(
                R.drawable.baseline_electric_car_24,
                ChipSupportVisualType.Icon
            ),
        ),
        CustomChipModel(
            title = "chip4",
            supportVisual = ChipSupportVisualData(
                R.drawable.baseline_electric_car_24,
                ChipSupportVisualType.Icon
            ),
        ),
        CustomChipModel(
            title = "chip5",
            supportVisual = ChipSupportVisualData(
                R.drawable.baseline_electric_car_24,
                ChipSupportVisualType.Icon
            ),
        ),
        CustomChipModel(
            title = "chip6",
            supportVisual = ChipSupportVisualData(
                R.drawable.baseline_electric_car_24,
                ChipSupportVisualType.Icon
            ),
        )
    )
}