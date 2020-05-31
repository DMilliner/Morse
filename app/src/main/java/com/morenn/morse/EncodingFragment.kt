package com.morenn.morse

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.morenn.morse.utils.LightUtils
import com.morenn.morse.utils.MorseUtils
import com.morenn.morse.utils.SoundsUtils
import com.morenn.morse.utils.VibrationUtils

private const val RECORD_REQUEST_CODE: Int = 1000

class EncodingFragment : Fragment() {

    private lateinit var mContext: Context

    private lateinit var editText : EditText
    private lateinit var morseResult : TextView
    private lateinit var translateButton : Button
    private lateinit var lightButton : Button
    private lateinit var vibrationButton : Button
    private lateinit var soundButton : Button

    private var _morseMessage: String = ""

    companion object {
        fun newInstance(): EncodingFragment = EncodingFragment()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_encode, container, false)

        editText = view.findViewById(R.id.editText)
        morseResult = view.findViewById(R.id.morseResult)
        translateButton = view.findViewById(R.id.translateButton)
        lightButton = view.findViewById(R.id.lightButton)
        vibrationButton = view.findViewById(R.id.vibrationButton)
        soundButton = view.findViewById(R.id.soundButton)

        context?.let { _context ->
            mContext = _context
        }

        translateButton.setOnClickListener {
            translateListener()
        }

        lightButton.isEnabled = activity?.packageManager!!.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)

        lightButton.setOnClickListener {

            Thread(Runnable {
                translateListener()
            }).start()

            val morseMessage = editText.text.toString()

            val permission = ContextCompat.checkSelfPermission(mContext, Manifest.permission.CAMERA)

            if (permission != PackageManager.PERMISSION_GRANTED) {
                makeRequest()
            } else {
                // keep going
                _morseMessage = morseMessage
                LightUtils().flashlightPerCharacter(morseMessage)
            }
        }

        vibrationButton.setOnClickListener {
            translateListener()

            val vibrator = activity?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

            val morseMessage = editText.text.toString()
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

        soundButton.isEnabled = false
        soundButton.setOnClickListener {
            translateListener()

            val morseMessage = editText.text.toString()

            for (character in morseMessage.toCharArray()) {
                SoundsUtils().getMorseSound(character)
            }
        }

        editText.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    translateListener()
                }

                return true
            }
        })

        return view
    }

    private fun translateListener() {
        val morseMessage = editText.text.toString()

        var translatedString = ""
        for (character in morseMessage.toCharArray()) {
            val morseCharacter = MorseUtils().getMorseValue(character)
            translatedString += morseCharacter.plus(" / ")
        }

        morseResult.text = translatedString
        hideSoftKeyboard()
    }

    ////// Keyboard
    private fun hideSoftKeyboard() {
        if (activity?.currentFocus != null) {
            val inputMethodManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(activity?.currentFocus!!.windowToken, 0)
        }
    }

    private fun showSoftKeyboard(view: View) {
        val inputMethodManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        view.requestFocus()
        inputMethodManager.showSoftInput(view, 0)
    }

    private fun makeRequest() {
        ActivityCompat.requestPermissions(activity!!, arrayOf(Manifest.permission.CAMERA), RECORD_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            RECORD_REQUEST_CODE -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {

                } else {
                    // Keep going
                    LightUtils().flashlightPerCharacter(_morseMessage)
                }
            }
        }
    }
}