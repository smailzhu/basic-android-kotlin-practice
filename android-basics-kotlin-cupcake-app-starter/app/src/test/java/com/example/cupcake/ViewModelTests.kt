package com.example.cupcake


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.cupcake.model.OrderViewModel
import junit.framework.TestCase.assertEquals
import org.junit.Rule
import org.junit.Test


class ViewModelTests {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun quantity_twelve_cupcakes() {
        val viewModel = OrderViewModel()
        viewModel.quantity.observeForever {}
        viewModel.setQuantity(12)

        assertEquals(12, viewModel.quantity.value)
    }

    @Test
    fun price_twelve_cupcakes() {
        val viewModel = OrderViewModel()

        /* This is an example of why LiveData should be observed in testing.
         *  The value of price is set by using a Transformation. Essentially, this code takes the
         *  value that we assign to price and transforms it to a currency format so we don't have
         *  to do it manually. However, this code has other implications. When transforming
         *  a LiveData object, the code doesn't get called unless it absolutely has to be,
         *  this saves resources on a mobile device. The code will only be called if we observe
         *  the object for changes. Of course, this is done in our app, but we also need to do
         *  the same for the test.  */
        viewModel.price.observeForever {}

        viewModel.setQuantity(12)

        assertEquals("$27.00", viewModel.price.value)
    }
}