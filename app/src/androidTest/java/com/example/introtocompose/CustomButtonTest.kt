package com.example.introtocompose

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertHeightIsEqualTo
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.test.filters.LargeTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

@LargeTest
class CustomButtonTest {
    @get: Rule
    val rule = createComposeRule()

    val icon = Icons.Rounded.Notifications
    val image = R.drawable.baseline_electric_car_24

    val text = "custom button text"
    var clicked = true

    @Test
    fun check_custom_button_double_click_enable() {
        setUpCustomButton(enable = true)
        testRuleset(Color.Blue, Color.White)
    }

    @Test
    fun check_custom_button_double_click_disable() {
        setUpCustomButton(enable = false)
        testRuleset(Color.Gray, Color.LightGray)
    }

    private fun setUpCustomButton(enable: Boolean) {
        rule.setContent {
            CustomButtonDoubleClick(
                text = text,
                image = image,
                enable = enable,
            )
        }
    }

    private fun testRuleset(
        color: Color,
        textColor: Color
    ) {
        rule.onNodeWithTag("TAG_BUTTON_TEST", true)
            .assertHeightIsEqualTo(48.dp)
            .assertBackgroundColor(color)

        rule.onNodeWithTag("TAG_STRING_TEST", true)
            .assertIsDisplayed()
            .assertTextAlign(TextAlign.Center)
            .assertTextColor(textColor)

        rule.onNodeWithTag("TAG_IMAGE_TEST", true)
            .assertIsDisplayed()

        rule.onRoot().performClick()
        assertTrue(clicked)
    }

    @Test
    fun check_custom_button_constraint_layout_enable() {
        setUpCustomButtonConstraintLayout(enable = true)
        testRulesetConstraint(Color.Blue, Color.White)
    }

    @Test
    fun check_custom_button_double_constraint_layout_disable() {
        setUpCustomButtonConstraintLayout(enable = false)
        testRulesetConstraint(Color.Gray, Color.LightGray)
    }

    private fun setUpCustomButtonConstraintLayout(enable: Boolean) {
        rule.setContent {
            CustomButtonConstraintLayout(
                text = text,
                icon = icon,
                enable = enable,
            )
        }
    }

    private fun testRulesetConstraint(
        color: Color,
        textColor: Color
    ) {
        rule.onNodeWithTag("TAG_BUTTON_TEST", true)
            .assertHeightIsEqualTo(48.dp)
            .assertBackgroundColor(color)

        rule.onNodeWithTag("TAG_TEXT_TEST", true)
            .assertIsDisplayed()
            .assertTextAlign(TextAlign.Start)
            .assertTextColor(textColor)

        rule.onNodeWithTag("TAG_ICON_TEST", true)
            .assertIsDisplayed()

        rule.onRoot().performClick()
        assertTrue(clicked)
    }
}

private fun SemanticsNodeInteraction.assertBackgroundColor(color: Color) {
    val captureColor = captureToImage().colorSpace.name
    assertEquals(color.colorSpace.name, captureColor)
}

private fun SemanticsNodeInteraction.assertTextAlign(
    align: TextAlign
) = assert(textAlignment(align))

fun textAlignment(align: TextAlign): SemanticsMatcher = SemanticsMatcher(
    "${SemanticsProperties.Text.name} textAlign $align"
) {
    val result = mutableListOf<TextLayoutResult>()

    it.config.getOrNull(SemanticsActions.GetTextLayoutResult)
        ?.action
        ?.invoke(result)

    return@SemanticsMatcher if (result.isEmpty()) {
        false
    } else {
        result.first().layoutInput.style.textAlign == align
    }
}

fun SemanticsNodeInteraction.assertTextColor(
    color: Color
): SemanticsNodeInteraction = assert(isOfColor(color))

private fun isOfColor(color: Color): SemanticsMatcher = SemanticsMatcher(
    "${SemanticsProperties.Text.name} textColor '$color'"
) {
    val textLayoutResults = mutableListOf<TextLayoutResult>()
    it.config.getOrNull(SemanticsActions.GetTextLayoutResult)
        ?.action
        ?.invoke(textLayoutResults)
    return@SemanticsMatcher if (textLayoutResults.isEmpty()) {
        false
    } else {
        textLayoutResults.first().layoutInput.style.color == color
    }
}

