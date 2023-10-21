package com.example.bmi

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val edtWeight:EditText=findViewById(R.id.edtWeight)
        val edtHeight:EditText=findViewById(R.id.edtHeight)
        val btnCalculate:Button=findViewById(R.id.btnCalculate)
        btnCalculate.setOnClickListener {
          val wgInput=edtWeight.text.toString()
          val hgInput=edtHeight.text.toString()
            if (validateInput(wgInput,hgInput)) {
                val weight=wgInput.toFloat()
                val height=hgInput.toFloat()
                val htInMeters = height / 100
                val bmi = weight / (htInMeters * htInMeters)
                //get result with only two decimial digits
                val bmi2Digits = String.format("%.2f", bmi).toFloat()
                displayBmi(bmi2Digits)
            }
        }
    }
    private fun validateInput(weight:String?,height:String?):Boolean{
        return when{
            weight.isNullOrEmpty()->{
                Toast.makeText(this,"Empty Input",Toast.LENGTH_SHORT).show()
                return false
            }
            height.isNullOrEmpty()->{
                Toast.makeText(this,"Empty Input",Toast.LENGTH_SHORT).show()
                return false
            }
            else->{
                return true
            }
        }

    }
    private fun displayBmi(bmi:Float){
        val txtBmi=findViewById<TextView>(R.id.txtBmi)
        val txtResult=findViewById<TextView>(R.id.txtResult)
        val txtInfo=findViewById<TextView>(R.id.txtInfo)
        txtInfo.text="(Normal range is 18.5-24.9)"
        var result=""
        var color=0
        when {
            bmi<18.5 ->{
                result="Underweight"
                color=R.color.skyBlue
            }
            bmi in 18.5..24.9->{
                result="Normal"
                color=R.color.green
            }
            bmi in 25.0..29.9->{
                result="Overweight"
                color=R.color.orange
            }
            bmi >=30.0->{
                result="Obese"
                color=R.color.red
            }
        }
        txtBmi.text=bmi.toString()
        txtResult.text=result
        txtResult.setTextColor(ContextCompat.getColor(this,color))
    }
}