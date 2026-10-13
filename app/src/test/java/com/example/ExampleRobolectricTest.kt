package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.UtilityRepository
import com.example.location.LocationPoint
import com.example.location.LocationRepository
import com.example.location.LocationUtils
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class ExampleRobolectricTest {

    @Test
    fun `read string from context`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val appName = context.getString(R.string.app_name)
        assertEquals("Pak Utility Hub", appName)
    }

    @Test
    fun `verify distance calculation and nearest city finder`() {
        // Lahore coordinates
        val (city, distKm) = LocationUtils.findNearestPakCity(31.5204, 74.3587)
        assertEquals("Lahore", city.name)
        assertTrue(distKm < 1.0)
    }

    @Test
    fun `verify zakat calculation Nisab compliance`() {
        val zakat = UtilityRepository.calculateZakat(
            goldGrams = 100.0,
            silverGrams = 0.0,
            cashPkr = 0.0
        )
        assertTrue(zakat.isNisabReached)
        assertTrue(zakat.zakatPayablePkr > 0)
    }

    @Test
    fun `verify FBR progressive tax calculation`() {
        // 250,000 monthly = 3,000,000 annual
        val breakdown = UtilityRepository.calculateIncomeTax(3000000.0)
        assertEquals(3000000.0, breakdown.annualGross, 0.01)
        assertTrue(breakdown.annualTax > 0)
        assertTrue(breakdown.monthlyTax > 0)
    }

    @Test
    fun `verify location tracking state updates`() {
        val testPoint = LocationPoint(
            latitude = 31.5204,
            longitude = 74.3587,
            speedKmh = 60f
        )
        LocationRepository.onNewLocationFix(testPoint)
        assertEquals(testPoint, LocationRepository.currentLocation.value)
    }
}
