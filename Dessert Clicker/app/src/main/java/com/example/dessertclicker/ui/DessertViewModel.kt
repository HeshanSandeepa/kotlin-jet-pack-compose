package com.example.dessertclicker.ui

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import com.example.dessertclicker.R
import com.example.dessertclicker.data.Datasource.dessertList
import com.example.dessertclicker.model.Dessert
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DessertViewModel: ViewModel() {

    // Dessert UI state
    private val _uiState = MutableStateFlow(DesertUIState())
    // Backing property to avoid state updates from other classes
    val uiState: StateFlow<DesertUIState> = _uiState.asStateFlow()
    lateinit var currentDessert: Dessert

    init {
        reset()
    }

    // reset game

    private fun reset() {
        _uiState.value = DesertUIState(revenue = 0, dessertsSold = 0, dessertImageId =R.drawable.bakery_back)
    }

    /**
     * Determine which dessert to show.
     */
    fun determineDessertToShow(
        desserts: List<Dessert>,
        dessertsSold: Int
    ): Dessert {
        currentDessert= desserts.first()
        for (dessert in desserts) {
            if (dessertsSold >= dessert.startProductionAmount) {
                currentDessert = dessert
            } else {
                // The list of desserts is sorted by startProductionAmount. As you sell more desserts,
                // you'll start producing more expensive desserts as determined by startProductionAmount
                // We know to break as soon as we see a dessert who's "startProductionAmount" is greater
                // than the amount sold.
                break
            }
        }

        return currentDessert
    }

    fun onDessertClicked() {

        val currentDessert = determineDessertToShow(dessertList, _uiState.value.dessertsSold)
        val revenue = _uiState.value.revenue + currentDessert.price
        val totalSold =  _uiState.value.dessertsSold + 1


        // Show the next dessert
        val dessertToShow = determineDessertToShow(dessertList, totalSold)
        val currentDessertImageId = dessertToShow.imageId
        //currentDessertPrice = dessertToShow.price

        _uiState.update { currentState-> currentState.copy(
            revenue = revenue, dessertsSold = totalSold, dessertImageId = currentDessertImageId
        ) }
    }

    /**
     * Share desserts sold information using ACTION_SEND intent
     */
     fun shareSoldDessertsInformation(intentContext: Context, dessertsSold: Int, revenue: Int) {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(
                Intent.EXTRA_TEXT,
                intentContext.getString(R.string.share_text, dessertsSold, revenue)
            )
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)

        try {
            ContextCompat.startActivity(intentContext, shareIntent, null)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(
                intentContext,
                intentContext.getString(R.string.sharing_not_available),
                Toast.LENGTH_LONG
            ).show()
        }
    }
}