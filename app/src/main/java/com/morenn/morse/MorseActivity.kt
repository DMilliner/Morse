package com.morenn.morse

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.text.Editable
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.morenn.morse.utils.MorseUtils
import com.morenn.morse.utils.VibrationUtils
import kotlinx.android.synthetic.main.activity_morse.*

class MorseActivity: AppCompatActivity() {

    private var morseMessage: String = ""
    private var alphaNumericMessage: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_morse)

        setupAlphaNumericField()
        setupMorseField()

        dotButton.setOnClickListener {
            hideSoftKeyboard()

            morseMessage = morseMessage.plus("•")
            morseTextField.editText?.text = Editable.Factory.getInstance().newEditable(morseMessage)
            decoding(morseMessage)
        }

        dashButton.setOnClickListener {
            hideSoftKeyboard()

            morseMessage = morseMessage.plus("-")
            morseTextField.editText?.text = Editable.Factory.getInstance().newEditable(morseMessage)
            decoding(morseMessage)
        }

        spaceButton.setOnClickListener {
            hideSoftKeyboard()

            morseMessage = morseMessage.plus(" ")
            morseTextField.editText?.text = Editable.Factory.getInstance().newEditable(morseMessage)
            alphaNumericTextEditText.setText(alphaNumericTextField.editText?.text.toString().plus(" "))
            decoding(morseMessage)
        }

        playButton.setOnClickListener {
            hideSoftKeyboard()

            val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

            VibrationUtils.pattern.clear()
            VibrationUtils.pattern.add(0L)
            for (character in morseMessage.toCharArray()) {
                VibrationUtils().getMorseVibration(character)
            }

            val newPattern: LongArray = VibrationUtils.pattern.toLongArray()

            if (Build.VERSION.SDK_INT >= 26) {
                vibrator.vibrate(VibrationEffect.createWaveform(newPattern, -1))
            } else {
                vibrator.vibrate(newPattern ,-1)
            }
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

    private fun hideSoftKeyboard() {
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }
}