package com.morenn.morse

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.morenn.morse.utils.MorseUtils

class DecodingFragment: Fragment() {

    private lateinit var mContext: Context

    private lateinit var morseText : TextView
    private lateinit var readableText : TextView
    private lateinit var dotButton : FloatingActionButton
    private lateinit var dashButton : FloatingActionButton
    private lateinit var spaceButton : Button
    private lateinit var clearButton : Button

    private var morseMessage: String = ""
    private var translatableBit: String = ""
    private var savedTranslatedBit: String = ""

    companion object {
        fun newInstance(): DecodingFragment = DecodingFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_decode, container, false)

        morseText = view.findViewById(R.id.morseText)
        readableText = view.findViewById(R.id.readableText)
        dotButton = view.findViewById(R.id.dotButton)
        dashButton = view.findViewById(R.id.dashButton)
        spaceButton = view.findViewById(R.id.spaceButton)
        clearButton = view.findViewById(R.id.clearButton)

        context?.let { _context ->
            mContext = _context
        }

        dotButton.setOnClickListener {
            morseMessage = morseMessage.plus("•")
            translatableBit = translatableBit.plus("•")
            decodeMorse(translatableBit)
            morseText.text = morseMessage
        }

        dashButton.setOnClickListener {
            morseMessage = morseMessage.plus("-")
            translatableBit = translatableBit.plus("-")
            decodeMorse(translatableBit)
            morseText.text = morseMessage
        }

        spaceButton.setOnClickListener {
            morseMessage = morseMessage.plus(" / ")
            decodeMorse(translatableBit)

            translatableBit = ""

            savedTranslatedBit = readableText.text.toString()
            morseText.text = morseMessage
        }

        clearButton.setOnClickListener {
            morseMessage = ""
            translatableBit = ""
            morseText.text = ""
            readableText.text = ""
        }

        return view
    }

    private fun decodeMorse(translatableBit: String) {

        val translatedBit = MorseUtils().decodeTranslatableBit(translatableBit)

        val _translatedMessage = savedTranslatedBit.plus(translatedBit)
        readableText.text = _translatedMessage
    }
}