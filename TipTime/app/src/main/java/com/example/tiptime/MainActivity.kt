package com.example.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.tiptime.databinding.ActivityMainBinding
import org.w3c.dom.Text
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Old way with findViewById()
        /*
        val calculateBtn: Button = findViewById(R.id.calculateButton)
        calculateBtn.setOnClickListener { calculateTips() }
        */

        // New way with binding
        /*
         val myButton: Button = binding.calculateButton
         */
        binding.calculateButton.setOnClickListener { calculateTips() }
    }

    private fun calculateTips() {
        val cost: Double? = binding.costOfServiceEditText.text.toString().toDoubleOrNull()

        if (cost == null) {
            displayTip(0.0)
            return
        }

        val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }

        var tip = tipPercentage * cost

        if (binding.roundUpSwitch.isChecked) {
            tip = kotlin.math.ceil(tip)
        }
        displayTip(tip)
    }

    private fun displayTip(tip: Double) {
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }
}