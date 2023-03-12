package com.example.programmercalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.programmercalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var convertFrom = ""
        var convertTo = ""

        // convert from
        binding.fromHexa.setOnClickListener {

            convertFrom = "Hex"
        }

        binding.fromDec.setOnClickListener {

            convertFrom = "Dec"
        }

        binding.fromBin.setOnClickListener {

            convertFrom = "Bin"
        }

        binding.fromOct.setOnClickListener {

            convertFrom = "Oct"
        }

        // convert to
        binding.toHex.setOnClickListener {

            convertTo = "Hex"
        }

        binding.toDec.setOnClickListener {

            convertTo = "Dec"
        }

        binding.toOct.setOnClickListener {

            convertTo = "Oct"
        }

        binding.toBin.setOnClickListener {

            convertTo = "Bin"
        }

        binding.convert.setOnClickListener {

            if (inputIsEmpty()) makeAlertToast()
            else if (convertFrom.isEmpty() || convertTo.isEmpty()) Toast.makeText(this, "Select Operation", Toast.LENGTH_SHORT).show()
            else {

                when(convertFrom){

                    "Hex" ->{

                        if (checkIfInputIsValidForHex()){

                            val input = binding.input.text.toString()
                            when(convertTo){

                                "Hex" -> {

                                    binding.Answer.text = convertFromDecToHex(convertFromHexToDec(input)).uppercase()
                                }

                                "Dec" -> {

                                    binding.Answer.text = convertFromDecToDec(convertFromHexToDec(input))
                                }

                                "Oct" -> {

                                    binding.Answer.text = convertFromDecToOct(convertFromHexToDec(input))
                                }

                                "Bin" -> {

                                    binding.Answer.text = convertFromDecToBin(convertFromHexToDec(input))
                                }
                            }

                        } else makeAlertToast()

                    }

                    "Bin" ->{

                        if (checkIfInputIsValidForBin()){

                            val input = binding.input.text.toString()
                            when(convertTo){

                                "Hex" -> {

                                    binding.Answer.text = convertFromDecToHex(convertFromBinToDec(input)).uppercase()
                                }

                                "Dec" -> {

                                    binding.Answer.text = convertFromDecToDec(convertFromBinToDec(input))
                                }

                                "Oct" -> {

                                    binding.Answer.text = convertFromDecToOct(convertFromBinToDec(input))
                                }

                                "Bin" -> {

                                    binding.Answer.text = convertFromDecToBin(convertFromBinToDec(input))
                                }
                            }

                        } else makeAlertToast()

                    }

                    "Dec" ->{

                        if (checkIfInputIsValidForOctAndDec()){

                            val input = binding.input.text.toString()
                            when(convertTo){

                                "Hex" -> {

                                    binding.Answer.text = convertFromDecToHex(convertFromDecToDec(input)).uppercase()
                                }

                                "Dec" -> {

                                    binding.Answer.text = convertFromDecToDec(convertFromDecToDec(input))
                                }

                                "Oct" -> {

                                    binding.Answer.text = convertFromDecToOct(convertFromDecToDec(input))
                                }

                                "Bin" -> {

                                    binding.Answer.text = convertFromDecToBin(convertFromDecToDec(input))
                                }
                            }

                        } else makeAlertToast()

                    }

                    "Oct" ->{

                        if (checkIfInputIsValidForOctAndDec()){

                            val input = binding.input.text.toString()
                            when(convertTo){

                                "Hex" -> {

                                    binding.Answer.text = convertFromDecToHex(convertFromOctToDec(input)).uppercase()
                                }

                                "Dec" -> {

                                    binding.Answer.text = convertFromDecToDec(convertFromOctToDec(input))
                                }

                                "Oct" -> {

                                    binding.Answer.text = convertFromDecToOct(convertFromOctToDec(input))
                                }

                                "Bin" -> {

                                    binding.Answer.text = convertFromDecToBin(convertFromOctToDec(input))
                                }
                            }

                        } else makeAlertToast()
                    }
                }
            }
        }
    }


    private fun makeAlertToast(){

        Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show()
    }

    // checking

    private fun inputIsEmpty() : Boolean{

        val input = binding.input.text.toString()
        if (input == "") return true
        return false
    }

    private fun checkIfInputIsValidForHex() : Boolean{

        val input = binding.input.text.toString()
        if (input[0] == '0' || input.length > 15) return false
        for (i in input){

            if (i in '0'..'9' || i in 'a'..'f' || i in 'A'..'F')
            else return false
        }

        return true
    }

    private fun checkIfInputIsValidForBin() : Boolean{

        val input = binding.input.text.toString()
        if (input.length > 63) return false
        for (i in input){

            if (i != '0' && i != '1') return false
        }

        return true
    }

    private fun checkIfInputIsValidForOctAndDec() : Boolean{

        val input = binding.input.text.toString()
        if (input.length > 18) return false
        if (input[0] == '0') return false
        for (i in input){

            if (i in '0'..'9')
            else return false
        }

        return true
    }


    // convert from * to decimal

    private fun convertFromHexToDec(hex : String) : Long{

        return hex.toLong(16)
    }

    private fun convertFromBinToDec(bin : String) : Long{

        return bin.toLong(2)
    }

    private fun convertFromOctToDec(oct : String) : Long{

        return oct.toLong(8)
    }

    private fun convertFromDecToDec(dec : String) : Long{

        return dec.toLong()
    }


    // convert from decimal to *

    private fun convertFromDecToBin(dec : Long) : String{

        return java.lang.Long.toBinaryString(dec)
    }

    private fun convertFromDecToOct(dec : Long) : String{

        return java.lang.Long.toOctalString(dec)
    }

    private fun convertFromDecToHex(dec : Long) : String{

        return java.lang.Long.toHexString(dec)
    }

    private fun convertFromDecToDec(dec : Long) : String{

        return dec.toString()
    }
}