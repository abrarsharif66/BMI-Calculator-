package com.example.bmi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val weightText = findViewById<TextView>(R.id.etweight)
        val heightText = findViewById<TextView>(R.id.etHeight)
        val calcButton = findViewById<Button>(R.id.btnCalculate)
        calcButton.setOnClickListener {
            val weight = weightText.text.toString()
            val height = heightText.text.toString()
            if(validateInput(weight,height)) { // if fields are left empty this will return toast message
                val bmi = weight.toFloat() / ((height.toFloat() / 100) * (height.toFloat() / 100))
                val bmi2Dights = String.format("%2f",bmi).toFloat() //reduces bmi decimals to upto 2 points
                displayResult(bmi2Dights)
            }
        }
    }
    private fun validateInput(weight:String?, height:String?):Boolean{
        return when{
            weight.isNullOrEmpty() ->{
                Toast.makeText(this, "Weight is Empty", Toast.LENGTH_LONG).show()
                return false
            }
            height.isNullOrEmpty() ->{
                Toast.makeText(this, "height is Empty", Toast.LENGTH_LONG).show()
                return false
        }
            else ->{
                return true
            }
        }

    }
    private fun displayResult(bmi:Float){
        val resultIndex =findViewById<TextView>(R.id.tvIndex)
        val resultDescription = findViewById<TextView>(R.id.tvResult)
        val info = findViewById<TextView>(R.id.tvInfo)
        resultIndex.text = bmi.toString()
        info.text = "Normal range is 18.9-24.9"

        var resultText = ""
        var color = 0


        when{
            bmi<18.50 ->{
                resultText = "Underweight"
                color = R.color.under_weight
            }
            bmi in 18.50..24.99->{
                resultText = "Healthy"
                color = R.color.normal
            }
            bmi in 25.00..29.99 ->{
                resultText="Overweight!"
                color = R.color.over_weight

            }
            bmi>29.99 ->{
                resultText="FatAss #gtfg"
                color = R.color.obese
            }
        }
        resultDescription.setTextColor(ContextCompat.getColor(this, color))
        resultDescription.text = resultText
    }
}