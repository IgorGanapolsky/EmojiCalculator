/*
 * Copyright (c) 2019 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.raywenderlich.android.emojicalculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Main Screen
 */
class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    buttonBad.setOnClickListener { calculateTip(BAD_TIP_PERCENTAGE) }
    buttonOkay.setOnClickListener { calculateTip(OKAY_TIP_PERCENTAGE) }
    buttonGreat.setOnClickListener { calculateTip(GREAT_TIP_PERCENTAGE) }
  }

  private fun calculateTip(percentage: Double) {
    inputAmount.text?.toString()?.let { bill ->
      if (bill.isNotEmpty()) {
        val billTotal = bill.toDouble()
        var tip = billTotal * percentage
        if (switchRound.isChecked) {
          val additionalTip = Math.ceil(tip + billTotal) - (tip + billTotal)
          tip += additionalTip
        }
        showResult(tip, tip + billTotal, tip / billTotal * 100)
      }
    }
  }

  private fun showResult(tip: Double, total: Double, percentage: Double) {
    textTip.text = String.format("%.2f", tip)
    textTotal.text = String.format("%.2f", total)
    textPercent.text = String.format("%.2f", percentage) + "%"
  }

  companion object {
    const val BAD_TIP_PERCENTAGE = 0.15
    const val OKAY_TIP_PERCENTAGE = 0.18
    const val GREAT_TIP_PERCENTAGE = 0.20
  }
}
