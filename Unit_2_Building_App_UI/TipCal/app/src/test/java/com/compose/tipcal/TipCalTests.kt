package com.compose.tipcal

import org.junit.Test
import java.text.NumberFormat
import org.junit.Assert.assertEquals

internal class TipCalTests {

    @Test
    fun calculate_tip() {
        val amount = 10.00
        val tipPercentage = 20.00
        val expectedTip = NumberFormat.getCurrencyInstance().format(2)
        val actualTip = calculateTip(amount = amount,  tipPercentage, false)
        assertEquals(expectedTip, actualTip)
    }
}