package com.morenn.morse

import android.os.Bundle
import android.text.Editable
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import kotlinx.android.synthetic.main.activity_morse.*

class MorseActivity: AppCompatActivity() {

    private var morseMessage: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_morse)

        setupAlphaNumericField()
        setupMorseField()

        dotButton.setOnClickListener {
            morseMessage = morseMessage.plus("•")
            morseTextField.editText?.text = Editable.Factory.getInstance().newEditable(morseMessage)
            decoding(morseMessage)
        }

        dashButton.setOnClickListener {
            morseMessage = morseMessage.plus("-")
            morseTextField.editText?.text = Editable.Factory.getInstance().newEditable(morseMessage)
            decoding(morseMessage)
        }

        spaceButton.setOnClickListener {
            morseMessage = morseMessage.plus(" ")
            morseTextField.editText?.text = Editable.Factory.getInstance().newEditable(morseMessage)
            alphaNumericTextEditText.setText(alphaNumericTextField.editText?.text.toString().plus(" "))
            decoding(morseMessage)
        }
    }

    private fun setupAlphaNumericField() {
        alphaNumericTextField.editText?.doOnTextChanged { inputText, _, _, _ ->
            var translatedString = ""
            for (character in inputText.toString().toCharArray()) {
                val morseCharacter = MorseUtils().getMorseValue(character)
                translatedString += morseCharacter.plus(" ")
            }

            morseTextEditText.setText(translatedString)
        }

        alphaNumericTextField.setEndIconOnClickListener {
            clearField()
        }
    }

    private fun setupMorseField() {
        morseTextField.editText?.inputType = EditorInfo.TYPE_NULL
        morseTextEditText.isEnabled = false

        morseTextField.setEndIconOnClickListener {
            clearField()
        }
    }

    private fun clearField() {
        morseMessage = ""
        morseTextField.editText?.text?.clear()
        alphaNumericTextField.editText?.text?.clear()
    }

    private fun decoding(inputText: String) {
        var translatedString = ""

        for (morseCharacter in inputText.split(" ")) {
            val decodedBit = MorseUtils().decodeTranslatableBit(morseCharacter)
            translatedString += decodedBit
        }

        alphaNumericTextEditText.setText(translatedString)
    }
}