package com.example.introtocompose

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.introtocompose.ui.theme.IntroToComposeTheme

enum class Car(
    val title: String,
    val supportVisual: Int
){
    AUDI("Audi", 0),
    VW("VW", 1),
    BMW("BWM", 2),
    FERRARI("FERRARI", 3),
    LAMBO("LAMBO", 4),
    FIAT("FIAT", 5),
    CHEV("CHEV", 6),
    TOYOTA("TOYOTA", 7),
    HONDA("HONDA", 8),
    HYUNDAI("HYUNDAI", 9),
    JEEP("JEEP", 10),
    RENAULT("RENAULT", 11),
}

fun getAllCars(): List<Car>{
    return listOf(
        Car.AUDI,
        Car.VW,
        Car.BMW,
        Car.FERRARI,
        Car.LAMBO,
        Car.FIAT,
        Car.CHEV,
        Car.TOYOTA,
        Car.HONDA,
        Car.HYUNDAI,
        Car.JEEP,
        Car.RENAULT
    )
}

fun getCar(value: String): Car? {
    val map = Car.entries.associateBy(Car::title)
    return map[value]
}

@Preview
@Composable
fun CustomChip(
    name: String = "Chip",
    isSelected: Boolean = false,
    onSelectionChanged: (String) -> Unit = {},
) {
    IntroToComposeTheme {
        Surface(
            modifier = Modifier.padding(4.dp),
            shadowElevation = 2.dp,
            shape = MaterialTheme.shapes.medium,
            color = if (isSelected) Color.LightGray else MaterialTheme.colorScheme.primary
        ) {
            Row(modifier = Modifier
                .toggleable(
                    value = isSelected,
                    onValueChange = {
                        onSelectionChanged(name)
                    }
                )
            ) {
                Text(
                    modifier = Modifier.widthIn(0.dp, 150.dp),
                    text = name,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.White
                )
            }
        }
    }
}

@Preview
@Composable
fun ChipGroup(
    cars: List<Car> = getAllCars(),
    selectedCar: Car? = null,
    onSelectedChanged: (String) -> Unit = {},
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        cars.forEach { car ->
            CustomChip(
                name = car.title,
                isSelected = selectedCar == car,
                onSelectionChanged = {
                    onSelectedChanged(it)
                },
            )
        }
    }
}

@Preview
@Composable
fun ChipGroupMulti(
    cars: List<Car> = getAllCars(),
    selectedCars: List<Car?> = listOf(),
    onSelectedChanged: (String) -> Unit = {},
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        cars.forEach { car ->
            CustomChip(
                name = car.title,
                isSelected = selectedCars.contains(car),
                onSelectionChanged = {
                    onSelectedChanged(it)
                },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ChipGroupExamplePreview() {

    val selectedCar: MutableState<Car?> = remember {
        mutableStateOf(null)
    }

    val selectsCar: MutableState<List<Car?>> = remember {
        mutableStateOf(listOf())
    }

    IntroToComposeTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ChipGroup(
                cars = getAllCars(),
                selectedCar = selectedCar.value,
                onSelectedChanged = {
                    selectedCar.value = getCar(it)
                }
            )

            ChipGroupMulti(
                cars = getAllCars(),
                selectedCars = selectsCar.value,
                onSelectedChanged = {
                    val oldList: MutableList<Car?> = selectsCar.value.toMutableList()
                    val carFromString = getCar(it)

                    if(oldList.contains(carFromString)){
                        oldList.remove(carFromString)
                    }else{
                        oldList.add(carFromString)
                    }

                    selectsCar.value = oldList
                }
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Preview
@Composable
fun ChipGrid(
    cars: List<Car> = getAllCars(),
    selectedCar: Car? = null,
    onSelectedChanged: (String) -> Unit = {},
) {
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        cars.forEach { car ->
            CustomChip(
                name = car.title,
                isSelected = selectedCar == car,
                onSelectionChanged = {
                    onSelectedChanged(it)
                },
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Preview
@Composable
fun ChipGridMulti(
    cars: List<Car> = getAllCars(),
    selectedCars: List<Car?> = listOf(),
    onSelectedChanged: (String) -> Unit = {},
) {
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        cars.forEach { car ->
            CustomChip(
                name = car.title,
                isSelected = selectedCars.contains(car),
                onSelectionChanged = {
                    onSelectedChanged(it)
                },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ChipGridExamplePreview() {

    val selectedCar: MutableState<Car?> = remember {
        mutableStateOf(null)
    }

    val selectsCar: MutableState<List<Car?>> = remember {
        mutableStateOf(listOf())
    }

    IntroToComposeTheme {
        Column (modifier = Modifier.fillMaxWidth()) {
            ChipGrid(
                cars = getAllCars(),
                selectedCar = selectedCar.value,
                onSelectedChanged = {
                    selectedCar.value = getCar(it)
                }
            )

            ChipGridMulti(
                cars = getAllCars(),
                selectedCars = selectsCar.value,
                onSelectedChanged = {
                    val oldList: MutableList<Car?> = selectsCar.value.toMutableList()
                    val carFromString = getCar(it)

                    if(oldList.contains(carFromString)){
                        oldList.remove(carFromString)
                    }else{
                        oldList.add(carFromString)
                    }

                    selectsCar.value = oldList
                }
            )
        }
    }
}