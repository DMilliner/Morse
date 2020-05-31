package com.morenn.morse.utils

import android.hardware.Camera


class LightUtils {
    fun flashlightPerCharacter(morseMessage: String) {
        for (character in morseMessage.toCharArray()) {
            getMorseLighting(character)
        }
    }

    private fun getMorseLighting(character: Char) {
        when (character) {
            'a', 'A' -> {
//                "•-"
                shortFlash()
                longFlash()
            }
            'b', 'B' -> {
//                "-•••"
                longFlash()
                shortFlash()
                shortFlash()
                shortFlash()
            }
            'c', 'C' -> {
//                "-•-•"
                longFlash()
                shortFlash()
                longFlash()
                shortFlash()
            }
            'd', 'D' -> {
//                "-••"
                longFlash()
                shortFlash()
                shortFlash()
            }
            'e', 'E' -> {
//                "•"
                shortFlash()
            }
            'f', 'F' -> {
//                "••-•"
                shortFlash()
                shortFlash()
                longFlash()
                shortFlash()
            }
            'g', 'G' -> {
//                "--•"
                longFlash()
                longFlash()
                shortFlash()
            }
            'h', 'H' -> {
//                "••••"
                shortFlash()
                shortFlash()
                shortFlash()
                shortFlash()
            }
            'i', 'I' -> {
//                "••"
                shortFlash()
                shortFlash()
            }
            'j', 'J' -> {
//                "•---"
                shortFlash()
                longFlash()
                longFlash()
                longFlash()
            }
            'k', 'K' -> {
//                "-•-"
                longFlash()
                shortFlash()
                longFlash()
            }
            'l', 'L' -> {
//                "•-••"
                shortFlash()
                longFlash()
                shortFlash()
                shortFlash()
            }
            'm', 'M' -> {
//                "--"
                longFlash()
                longFlash()
            }
            'n', 'N' -> {
//                "-•"
                longFlash()
                shortFlash()
            }
            'o', 'O' -> {
//                "---"
                longFlash()
                longFlash()
                longFlash()
            }
            'p', 'P' -> {
//                "•--•"
                shortFlash()
                longFlash()
                longFlash()
                shortFlash()
            }
            'q', 'Q' -> {
//                "--•-"
                longFlash()
                longFlash()
                shortFlash()
                longFlash()
            }
            'r', 'R' -> {
//                "•-•"
                shortFlash()
                longFlash()
                shortFlash()
            }
            's', 'S' -> {
//                "•••"
                shortFlash()
                shortFlash()
                shortFlash()
            }
            't', 'T' -> {
//                "-"
                longFlash()
            }
            'u', 'U' -> {
//                "••-"
                shortFlash()
                shortFlash()
                longFlash()
            }
            'v', 'V' -> {
//                "•••-"
                shortFlash()
                shortFlash()
                shortFlash()
                longFlash()
            }
            'w', 'W' -> {
//                "•--"
                shortFlash()
                longFlash()
                longFlash()
            }
            'x', 'X' -> {
//                "-••-"
                longFlash()
                shortFlash()
                shortFlash()
                longFlash()
            }
            'y', 'Y' -> {
//                "-•--"
                longFlash()
                shortFlash()
                longFlash()
                longFlash()
            }
            'z', 'Z' -> {
//                "--••"
                longFlash()
                longFlash()
                shortFlash()
                shortFlash()
            }

            '1' -> {
//                "•----"
                shortFlash()
                longFlash()
                longFlash()
                longFlash()
                longFlash()
            }
            '2' -> {
//                "••---"
                shortFlash()
                shortFlash()
                longFlash()
                longFlash()
                longFlash()
            }
            '3' -> {
//                "•••--"
                shortFlash()
                shortFlash()
                shortFlash()
                longFlash()
                longFlash()
            }
            '4' -> {
//                "••••-"
                shortFlash()
                shortFlash()
                shortFlash()
                shortFlash()
                longFlash()
            }
            '5' -> {
//                "•••••"
                shortFlash()
                shortFlash()
                shortFlash()
                shortFlash()
                shortFlash()
            }
            '6' -> {
//                "-••••"
                longFlash()
                shortFlash()
                shortFlash()
                shortFlash()
                shortFlash()
            }
            '7' -> {
//                "--•••"
                longFlash()
                longFlash()
                shortFlash()
                shortFlash()
                shortFlash()
            }
            '8' -> {
//                "---••"
                longFlash()
                longFlash()
                longFlash()
                shortFlash()
                shortFlash()
            }
            '9' -> {
//                "----•"
                longFlash()
                longFlash()
                longFlash()
                longFlash()
                shortFlash()
            }
            '0' -> {
//                "-----"
                longFlash()
                longFlash()
                longFlash()
                longFlash()
                longFlash()
            }

            else -> {
//                ""
            }
        }
    }

    private fun longFlash() {

        val camera = Camera.open()
        val parameters = camera.parameters

        val myString = "00001"
        val blinkDelay: Long = 250
        for (i in 0 until myString.length) {
            if (myString[i] == '0') {
                parameters.flashMode = Camera.Parameters.FLASH_MODE_TORCH
                camera.parameters = parameters
                camera.startPreview()
            } else {
                parameters.flashMode = Camera.Parameters.FLASH_MODE_OFF
                camera.parameters = parameters
                camera.stopPreview()
                camera.release()
            }
            try {
                Thread.sleep(blinkDelay)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }

    private fun shortFlash() {

        val camera = Camera.open()
        val parameters = camera.parameters

        val myString = "01"
        val blinkDelay: Long = 250
        for (i in 0 until myString.length) {
            if (myString[i] == '0') {
                parameters.flashMode = Camera.Parameters.FLASH_MODE_TORCH
                camera.parameters = parameters
                camera.startPreview()
            } else {
                parameters.flashMode = Camera.Parameters.FLASH_MODE_OFF
                camera.parameters = parameters
                camera.stopPreview()
                camera.release()
            }
            try {
                Thread.sleep(blinkDelay)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }
}