package com.morenn.morse.utils

import android.media.AudioManager
import android.media.ToneGenerator
import android.os.Handler

class SoundsUtils {

    fun getMorseSound(character: Char) {
        when (character) {
            'a', 'A' -> {
//                "•-"
                shortSound()
                synchronized(shortSound()) {
                    longSound()
                }
            }
            'b', 'B' -> {
//                "-•••"
                longSound()
                shortSound()
                shortSound()
                shortSound()

            }
            'c', 'C' -> {
//                "-•-•"
                longSound()
                shortSound()
                longSound()
                shortSound()

            }
            'd', 'D' -> {
//                "-••"
                longSound()
                shortSound()
                shortSound()
            }
            'e', 'E' -> {
//                "•"
                shortSound()
            }
            'f', 'F' -> {
//                "••-•"
                shortSound()
                shortSound()
                longSound()
                shortSound()
            }
            'g', 'G' -> {
//                "--•"
                longSound()
                longSound()
                shortSound()
            }
            'h', 'H' -> {
//                "••••"
                shortSound()
                shortSound()
                shortSound()
                shortSound()
            }
            'i', 'I' -> {
//                "••"
                shortSound()
                shortSound()
            }
            'j', 'J' -> {
//                "•---"
                shortSound()
                longSound()
                longSound()
                longSound()
            }
            'k', 'K' -> {
//                "-•-"
                longSound()
                shortSound()
                longSound()
            }
            'l', 'L' -> {
//                "•-••"
                shortSound()
                longSound()
                shortSound()
                shortSound()
            }
            'm', 'M' -> {
//                "--"
                longSound()
                longSound()
            }
            'n', 'N' -> {
//                "-•"
                longSound()
                shortSound()
            }
            'o', 'O' -> {
//                "---"
                longSound()
                longSound()
                longSound()
            }
            'p', 'P' -> {
//                "•--•"
                shortSound()
                longSound()
                longSound()
                shortSound()
            }
            'q', 'Q' -> {
//                "--•-"
                longSound()
                longSound()
                shortSound()
                longSound()
            }
            'r', 'R' -> {
//                "•-•"
                shortSound()
                longSound()
                shortSound()
            }
            's', 'S' -> {
//                "•••"
                shortSound()
                shortSound()
                shortSound()
            }
            't', 'T' -> {
//                "-"
                longSound()
            }
            'u', 'U' -> {
//                "••-"
                shortSound()
                shortSound()
                longSound()
            }
            'v', 'V' -> {
//                "•••-"
                shortSound()
                shortSound()
                shortSound()
                longSound()
            }
            'w', 'W' -> {
//                "•--"
                shortSound()
                longSound()
                longSound()

            }
            'x', 'X' -> {
//                "-••-"
                longSound()
                shortSound()
                shortSound()
                longSound()
            }
            'y', 'Y' -> {
//                "-•--"
                longSound()
                shortSound()
                longSound()
                longSound()

            }
            'z', 'Z' -> {
//                "--••"
                longSound()
                longSound()
                shortSound()
                shortSound()
            }

            '1' -> {
//                "•----"
                shortSound()
                longSound()
                longSound()
                longSound()
                longSound()
            }
            '2' -> {
//                "••---"
                shortSound()
                shortSound()
                longSound()
                longSound()
                longSound()
            }
            '3' -> {
//                "•••--"
                shortSound()
                shortSound()
                shortSound()
                longSound()
                longSound()
            }
            '4' -> {
//                "••••-"
                shortSound()
                shortSound()
                shortSound()
                shortSound()
                longSound()
            }
            '5' -> {
//                "•••••"
                shortSound()
                shortSound()
                shortSound()
                shortSound()
                shortSound()
            }
            '6' -> {
//                "-••••"
                longSound()
                shortSound()
                shortSound()
                shortSound()
                shortSound()
            }
            '7' -> {
//                "--•••"
                longSound()
                longSound()
                shortSound()
                shortSound()
                shortSound()
            }
            '8' -> {
//                "---••"
                longSound()
                longSound()
                longSound()
                shortSound()
                shortSound()
            }
            '9' -> {
//                "----•"
                longSound()
                longSound()
                longSound()
                longSound()
                shortSound()
            }
            '0' -> {
//                "-----"
                longSound()
                longSound()
                longSound()
                longSound()
                longSound()
            }

            else -> {
//                ""
            }
        }
    }

    private fun shortSound() {
        val handler = Handler()
        handler.postDelayed( {
            val toneG = ToneGenerator(AudioManager.STREAM_ALARM, 100)
            toneG.startTone(ToneGenerator.TONE_CDMA_ABBR_INTERCEPT, 250)
        }, 500)
    }

    private fun longSound() {

        val handler = Handler()
        handler.postDelayed( {
            val toneG = ToneGenerator(AudioManager.STREAM_ALARM, 100)
            toneG.startTone(ToneGenerator.TONE_CDMA_ABBR_ALERT, 400)
        }, 500)
    }
}