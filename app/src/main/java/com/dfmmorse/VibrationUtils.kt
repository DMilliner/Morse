package com.dfmmorse


class VibrationUtils {

    companion object {
        val pattern: ArrayList<Long> = arrayListOf()
    }


    ////// Vibration
    fun getMorseVibration(character: Char) {
        when (character) {
            'a', 'A' -> {
//                "•-"
                shortVibration()
                longVibration()
            }
            'b', 'B' -> {
//                "-•••"
                longVibration()
                shortVibration()
                shortVibration()
            }
            'c', 'C' -> {
//                "-•-•"
                longVibration()
                shortVibration()
                longVibration()
                shortVibration()
            }
            'd', 'D' -> {
//                "-••"
                longVibration()
                shortVibration()
                shortVibration()
            }
            'e', 'E' -> {
//                "•"
                shortVibration()
            }
            'f', 'F' -> {
//                "••-•"
                shortVibration()
                shortVibration()
                longVibration()
                shortVibration()
            }
            'g', 'G' -> {
//                "--•"
                longVibration()
                longVibration()
                shortVibration()
            }
            'h', 'H' -> {
//                "••••"
                shortVibration()
                shortVibration()
                shortVibration()
                shortVibration()
            }
            'i', 'I' -> {
//                "••"
                shortVibration()
                shortVibration()
            }
            'j', 'J' -> {
//                "•---"
                shortVibration()
                longVibration()
                longVibration()
                longVibration()
            }
            'k', 'K' -> {
//                "-•-"
                longVibration()
                shortVibration()
                longVibration()
            }
            'l', 'L' -> {
//                "•-••"
                shortVibration()
                longVibration()
                shortVibration()
                shortVibration()
            }
            'm', 'M' -> {
//                "--"
                longVibration()
                longVibration()
            }
            'n', 'N' -> {
//                "-•"
                longVibration()
                shortVibration()
            }
            'o', 'O' -> {
//                "---"
                longVibration()
                longVibration()
                longVibration()
            }
            'p', 'P' -> {
//                "•--•"
                shortVibration()
                longVibration()
                longVibration()
                shortVibration()
            }
            'q', 'Q' -> {
//                "--•-"
                longVibration()
                longVibration()
                shortVibration()
                longVibration()
            }
            'r', 'R' -> {
//                "•-•"
                shortVibration()
                longVibration()
                shortVibration()
            }
            's', 'S' -> {
//                "•••"
                shortVibration()
                shortVibration()
                shortVibration()
            }
            't', 'T' -> {
//                "-"
                longVibration()
            }
            'u', 'U' -> {
//                "••-"
                shortVibration()
                shortVibration()
                longVibration()
            }
            'v', 'V' -> {
//                "•••-"
                shortVibration()
                shortVibration()
                shortVibration()
                longVibration()
            }
            'w', 'W' -> {
//                "•--"
                shortVibration()
                longVibration()
                longVibration()
            }
            'x', 'X' -> {
//                "-••-"
                longVibration()
                shortVibration()
                shortVibration()
                longVibration()
            }
            'y', 'Y' -> {
//                "-•--"
                longVibration()
                shortVibration()
                longVibration()
                longVibration()
            }
            'z', 'Z' -> {
//                "--••"
                longVibration()
                longVibration()
                shortVibration()
                shortVibration()
            }

            '1' -> {
//                "•----"
                shortVibration()
                longVibration()
                longVibration()
                longVibration()
                longVibration()
            }
            '2' -> {
//                "••---"
                shortVibration()
                shortVibration()
                longVibration()
                longVibration()
                longVibration()
            }
            '3' -> {
//                "•••--"
                shortVibration()
                shortVibration()
                shortVibration()
                longVibration()
                longVibration()
            }
            '4' -> {
//                "••••-"
                shortVibration()
                shortVibration()
                shortVibration()
                shortVibration()
                longVibration()
            }
            '5' -> {
//                "•••••"
                shortVibration()
                shortVibration()
                shortVibration()
                shortVibration()
                shortVibration()
            }
            '6' -> {
//                "-••••"
                longVibration()
                shortVibration()
                shortVibration()
                shortVibration()
                shortVibration()
            }
            '7' -> {
//                "--•••"
                longVibration()
                longVibration()
                shortVibration()
                shortVibration()
                shortVibration()
            }
            '8' -> {
//                "---••"
                longVibration()
                longVibration()
                longVibration()
                shortVibration()
                shortVibration()
            }
            '9' -> {
//                "----•"
                longVibration()
                longVibration()
                longVibration()
                longVibration()
                shortVibration()
            }
            '0' -> {
//                "-----"
                longVibration()
                longVibration()
                longVibration()
                longVibration()
                longVibration()
            }

            else -> {
//                ""
            }
        }
    }

    private fun longVibration() {
        pattern.add(1000)
        pattern.add(250)
    }

    private fun shortVibration() {
        pattern.add(500)
        pattern.add(250)
    }
/////////////////////////////////////////////////////////////////////
}