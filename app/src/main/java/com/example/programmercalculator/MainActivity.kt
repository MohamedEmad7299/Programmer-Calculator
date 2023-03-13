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

            if (binding.input.text.isEmpty()) makeAlertToast("Invalid Input")
            else if (convertFrom.isEmpty() || convertTo.isEmpty()) makeAlertToast("Select Operation")
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

                        } else makeAlertToast("Invalid Input")

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

                        } else makeAlertToast("Invalid Input")

                    }

                    "Dec" ->{

                        if (checkIfInputIsValidForDec()){

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

                        } else makeAlertToast("Invalid Input")

                    }

                    "Oct" ->{

                        if (checkIfInputIsValidForOct()){

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

                        } else makeAlertToast("Invalid Input")
                    }
                }
            }
        }
    }

    private fun makeAlertToast(message : String){

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    // Checking
    private fun checkIfInputIsValidForHex() : Boolean{

        val input = binding.input.text.toString()
        if ((input[0] == '0' && input.length > 1) || input.length > 15) return false
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

    private fun checkIfInputIsValidForDec() : Boolean{

        val input = binding.input.text.toString()
        if (input.length > 18) return false
        if (input[0] == '0' && input.length > 1) return false
        for (i in input){

            if (i in '0'..'9')
            else return false
        }

        return true
    }

    private fun checkIfInputIsValidForOct() : Boolean{

        val input = binding.input.text.toString()
        if (input.length > 18) return false
        if (input[0] == '0' && input.length > 1) return false
        for (i in input){

            if (i in '0'..'7')
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