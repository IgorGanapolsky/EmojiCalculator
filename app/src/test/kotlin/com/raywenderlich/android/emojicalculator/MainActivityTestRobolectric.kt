package com.raywenderlich.android.emojicalculator

import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.raywenderlich.android.emojicalculator.ScreenRobotRobolectric.Companion.withRobot
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTestRobolectric {
    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java, false, true)


    @Test
    fun onLaunchCheckAmountInputIsDisplayed() {
        withRobot(CalculatorScreenRobot::class.java)
                .checkIsDisplayed(R.id.inputAmount)
    }

    @Test
    fun onLaunchOkayButtonIsDisplayed() {
        withRobot(CalculatorScreenRobot::class.java)
                .checkViewHasText(R.id.buttonOkay, R.string.okay)
    }

    @Test
    fun whenOkayButtonIsPressedAndAmountIsEmptyTipIsEmpty() {
        withRobot(CalculatorScreenRobot::class.java)
                .clickOkOnView(R.id.buttonOkay)
                .checkViewHasText(R.id.textTip, "")
    }

    @Test
    fun whenOkayButtonIsPressedAndRoundSwitchIsSelectedAmountIsCorrect() {
        withRobot(CalculatorScreenRobot::class.java)
                .clickOkOnView(R.id.switchRound)
                .inputCheckAmountAndSelectOkayButton("11")
                .verifyTipIsCorrect("2.00")
    }

    class CalculatorScreenRobot : ScreenRobotRobolectric<CalculatorScreenRobot>() {
        fun verifyTipIsCorrect(tip: String): CalculatorScreenRobot {
            return checkViewHasText(R.id.textTip, tip)
        }

        fun inputCheckAmountAndSelectOkayButton(input: String): CalculatorScreenRobot {
            return enterTextIntoView(R.id.inputAmount, input)
                    .clickOkOnView(R.id.buttonOkay)
        }

    }

}
