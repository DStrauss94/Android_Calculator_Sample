package com.example.calculator_tutorial

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var result: EditText // being able to assign result as a nullable type temporarily til later on, without Kotlin exception errors.
    private lateinit var newNumber: EditText
    private val displayOperation by lazy(LazyThreadSafetyMode.NONE) { findViewById<TextView>(R.id.operation)} //defining a function that will be called to assign the value to the property

    //Variables to hold the operands and type of calculation
    private var operand1: Double? = null //need to record if its being given a value or not
    private var operand2: Double = 0.0
    private var pendingOperation = "="


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        result = findViewById(R.id.result)
        newNumber = findViewById(R.id.newNumber)

        //*************************************************************************

        //TWO DIFFERENT WAYS TO ASSIGN BUTTON VALUES


        //Data input buttons
        val button0 : Button = findViewById(R.id.button0)
        val button1 : Button = findViewById(R.id.button1)
        val button2 : Button = findViewById(R.id.button2)
        val button3 : Button = findViewById(R.id.button3)
        val button4 : Button = findViewById(R.id.button4)
        val button5 : Button = findViewById(R.id.button5)
        val button6 : Button = findViewById(R.id.button6)
        val button7 : Button = findViewById(R.id.button7)
        val button8 : Button = findViewById(R.id.button8)
        val button9 : Button = findViewById(R.id.button9)
        val buttonDot : Button = findViewById(R.id.buttonDot)

        //Operation Buttons
        val buttonEquals = findViewById<Button>(R.id.buttonEquals)
        val buttonDivide = findViewById<Button>(R.id.buttonDivide)
        val buttonMultiply = findViewById<Button>(R.id.buttonMultiply)
        val buttonMinus= findViewById<Button>(R.id.buttonMinus)
        val buttonPlus = findViewById<Button>(R.id.buttonPlus)

        //***************************************************************************

        //listener holds a reference to a new onClickListener instance
        //https://developer.android.com/reference/android/view/View.OnClickListener
        val listener = View.OnClickListener { v ->
            val b = v as Button // since we are going to set these to buttons, v has to be casted as a button widget
            newNumber.append(b.text)
        }

        button0.setOnClickListener(listener)
        button1.setOnClickListener(listener)
        button2.setOnClickListener(listener)
        button3.setOnClickListener(listener)
        button4.setOnClickListener(listener)
        button5.setOnClickListener(listener)
        button6.setOnClickListener(listener)
        button7.setOnClickListener(listener)
        button8.setOnClickListener(listener)
        button9.setOnClickListener(listener)
        buttonDot.setOnClickListener(listener)

        val opListener = View.OnClickListener { v ->
            val op = (v as Button).text.toString()
            val value = newNumber.text.toString()
            if(value.isNotEmpty()){
                performOperation(value,op)
            }
            pendingOperation = op
            displayOperation.text = pendingOperation
        }

        buttonEquals.setOnClickListener(opListener)
        buttonDivide.setOnClickListener(opListener)
        buttonMultiply.setOnClickListener(opListener)
        buttonMinus.setOnClickListener(opListener)
        buttonPlus.setOnClickListener(opListener)





    }

    private fun performOperation(value: String, operation : String){
        if(operand1 == null){
            operand1 = value.toDouble()
        }
        else{
            operand2 = value.toDouble()
        }
        if(pendingOperation == "="){
            pendingOperation = operation
        }

        when(pendingOperation){
            "=" -> operand1 = operand2
            "/" -> if(operand2 == 0.0){
                operand1 = Double.NaN
            }
                    else{
                            operand1 = operand1!! / operand2
                        }
            "*" -> operand1 = operand1!! * operand2
            "-" -> operand1 = operand1!! - operand2
            "+" -> operand1 = operand1!! + operand2
        }
        result.setText(operand1.toString())
        newNumber.setText("")
    }
}
