package com.example.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.nio.file.attribute.AttributeView

class MainActivity : AppCompatActivity() {
    private lateinit var textView: TextView
    var flag: Boolean? = null
    var res: Float? = null
    var cur: Float? = null
    var oper: Char = 'e'
    var operLast: Char = 'e'

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById<TextView>(R.id.per1)
    }

    fun clickNumber(view: View){
        var number =  textView.text.toString()
        if(number == "0" || number == "Error" || (res != null && (res!!.toInt()).toString() == number) || (res != null && (res!!.toFloat()).toString() == number)){
            number = ""
        }
        if (number == "-0") number = "-"
        if (number.length == 8){
            return
        }
        when(view.id){
            R.id.b0 -> number += '0'
            R.id.b1 -> number += '1'
            R.id.b2 -> number += '2'
            R.id.b3 -> number += '3'
            R.id.b4 -> number += '4'
            R.id.b5 -> number += '5'
            R.id.b6 -> number += '6'
            R.id.b7 -> number += '7'
            R.id.b8 -> number += '8'
            R.id.b9 -> number += '9'
            R.id.drob -> {
                if (number == "") {
                    number += '0'
                }
                number += '.'
            }
            R.id.bc -> {
                number = "0"
                res = null
                cur = null
                oper = 'e'
                flag = null
                textView.setText(number)
                return
            }
        }
        textView.setText(number)
        flag = true
    }

    fun abs(view: View){
        val value = textView.text.toString()
        when(value){
            "Error" -> return
            "0" -> textView.setText("-0")
            "-0" -> textView.setText("0")
            else -> {
                if(value.toFloat() == value.toInt().toFloat()) textView.setText((value.toInt() * (-1)).toString())
                else textView.setText((value.toFloat() * (-1)).toString())
            }
        }
    }

    fun proc(view: View){
        if (textView.text.toString() == "Error") return
        val value = textView.text.toString().toFloat()
        when(value){
            0.0f -> return
            else -> {
                textView.setText((value / 100).toString())
            }
        }
    }

    fun deystvie(slag1: Float, slag2: Float, oper: Char): Float{
        when(oper){
            '+' -> return slag1 + slag2
            '-' -> return slag1 - slag2
            '*' -> return slag1 * slag2
            '/' -> {
                if (slag2 == 0.0f){
                    return 0.0f
                }
                return slag1 / slag2
            }
            else -> return slag1
        }
    }

    fun operCh(view: View){
        val str = textView.text.toString()
        var index = -1
        if (oper == '/' && str == "0"){
            textView.setText("Error")
            res = null
            cur = null
            oper = 'e'
            return
        }else if(str == "Error" || flag == null){
            return
        }
        when (view.id) {
            R.id.plus -> {
                if(flag == false) {
                }else if (oper != 'e'){
                    cur = textView.text.toString().toFloat()
                    res = deystvie(res!!, cur!!, oper)
                    if(res == res!!.toInt().toFloat()) textView.setText(res!!.toInt().toString())
                    else textView.setText(res.toString())
                }else {
                    res = textView.text.toString().toFloat()
                }
                oper = '+'
            }
            R.id.minus -> {
                if (flag == false) {
                }else if (oper != 'e'){
                    cur = textView.text.toString().toFloat()
                    res = deystvie(res!!, cur!!, oper)
                    if(res == res!!.toInt().toFloat()) textView.setText(res!!.toInt().toString())
                    else textView.setText(res.toString())
                }else {
                    res = textView.text.toString().toFloat()
                }
                oper = '-'
            }
            R.id.mn -> {
                if(flag == false) {
                }else if (oper != 'e'){
                    cur = textView.text.toString().toFloat()
                    res = deystvie(res!!, cur!!, oper)
                    if(res == res!!.toInt().toFloat()) textView.setText(res!!.toInt().toString())
                    else textView.setText(res.toString())
                }else {
                    res = textView.text.toString().toFloat()
                }
                oper = '*'
            }
            R.id.del -> {
                if(flag == false) {
                }else if (oper != 'e'){
                    cur = textView.text.toString().toFloat()
                    res = deystvie(res!!, cur!!, oper)
                    if(res == res!!.toInt().toFloat()) textView.setText(res!!.toInt().toString())
                    else textView.setText(res.toString())
                }else {
                    res = textView.text.toString().toFloat()
                }
                oper = '/'
            }
            R.id.rav -> {
                if (oper != '=') cur = textView.text.toString().toFloat()
                if (oper == '=') oper = operLast
                    res = deystvie(res!!, cur!!, oper)
                if(res == res!!.toInt().toFloat()){
                    textView.setText(res!!.toInt().toString())
                }else{
                    textView.setText(res.toString())
                }
                operLast = oper
                oper = '='
            }
        }
        flag = false
    }

}