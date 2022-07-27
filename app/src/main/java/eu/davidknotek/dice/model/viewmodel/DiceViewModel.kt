package eu.davidknotek.dice.model.viewmodel

import android.app.Application
import android.graphics.drawable.Drawable
import android.media.MediaPlayer
import android.net.Uri
import android.os.CountDownTimer
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import eu.davidknotek.dice.R

class DiceViewModel(application: Application): AndroidViewModel(application) {
    private val app = application

    private var diceSide: Int
    private var timer: CountDownTimer? = null
    private var diceRunning = false
    private var mediaPlayer: MediaPlayer? = null

    var typeOfDice = TypeOfDice.SIX
    var rolledNumber = MutableLiveData<Int>()

    init {
        diceSide = getRollSide()
        rolledNumber.value = diceSide
        mediaPlayerSetup()
    }

    fun startRoll() {
        if (!diceRunning) {
            mediaPlayer?.start()
            timer?.cancel()
            diceSide = getRollSide()
            roll()
        }
    }

    fun getRollImage(number: Int): Drawable? {
        return when (typeOfDice) {
            TypeOfDice.SIX -> getSixRollImage(number)
            TypeOfDice.TWELVE -> getTwelveRollImage(number)
            TypeOfDice.TWENTY -> getTwentyRollImage(number)
        }
    }

    private fun roll() {
        timer = object : CountDownTimer(1300, 150) {
            override fun onTick(millisUntilFinished: Long) {
                rolledNumber.value = getRandomNum()
                diceRunning = true
            }

            override fun onFinish() {
                diceRunning = false
            }
        }
        timer?.start()
    }

    private fun getRandomNum(): Int {
        var newRandomNumber: Int

        do {
            newRandomNumber = (1..diceSide).random()
        } while (rolledNumber.value == newRandomNumber)

        return newRandomNumber
    }

    private fun getRollSide(): Int {
        return when (typeOfDice) {
            TypeOfDice.SIX -> 6
            TypeOfDice.TWELVE -> 12
            TypeOfDice.TWENTY -> 20
        }
    }

    private fun getSixRollImage(number: Int): Drawable? {
        return when (number) {
            1 -> AppCompatResources.getDrawable(app.applicationContext, R.drawable.d6_1)
            2 -> AppCompatResources.getDrawable(app.applicationContext, R.drawable.d6_2)
            3 -> AppCompatResources.getDrawable(app.applicationContext, R.drawable.d6_3)
            4 -> AppCompatResources.getDrawable(app.applicationContext, R.drawable.d6_4)
            5 -> AppCompatResources.getDrawable(app.applicationContext, R.drawable.d6_5)
            else -> AppCompatResources.getDrawable(app.applicationContext, R.drawable.d6_6)
        }
    }

    private fun getTwelveRollImage(number: Int): Drawable? {
        return when (number) {
            1 -> AppCompatResources.getDrawable(app.applicationContext, R.drawable.d12_1)
            2 -> AppCompatResources.getDrawable(app.applicationContext, R.drawable.d12_2)
            3 -> AppCompatResources.getDrawable(app.applicationContext, R.drawable.d12_3)
            4 -> AppCompatResources.getDrawable(app.applicationContext, R.drawable.d12_4)
            5 -> AppCompatResources.getDrawable(app.applicationContext, R.drawable.d12_5)
            6 -> AppCompatResources.getDrawable(app.applicationContext, R.drawable.d12_6)
            7 -> AppCompatResources.getDrawable(app.applicationContext, R.drawable.d12_7)
            8 -> AppCompatResources.getDrawable(app.applicationContext, R.drawable.d12_8)
            9 -> AppCompatResources.getDrawable(app.applicationContext, R.drawable.d12_9)
            10 -> AppCompatResources.getDrawable(app.applicationContext, R.drawable.d12_10)
            11 -> AppCompatResources.getDrawable(app.applicationContext, R.drawable.d12_11)
            else -> AppCompatResources.getDrawable(app.applicationContext, R.drawable.d12_12)
        }
    }

    private fun getTwentyRollImage(number: Int): Drawable? {
        return when (number) {
            1 -> AppCompatResources.getDrawable(app.applicationContext, R.drawable.d20_1)
            2 -> AppCompatResources.getDrawable(app.applicationContext, R.drawable.d20_2)
            3 -> AppCompatResources.getDrawable(app.applicationContext, R.drawable.d20_3)
            4 -> AppCompatResources.getDrawable(app.applicationContext, R.drawable.d20_4)
            5 -> AppCompatResources.getDrawable(app.applicationContext, R.drawable.d20_5)
            6 -> AppCompatResources.getDrawable(app.applicationContext, R.drawable.d20_6)
            7 -> AppCompatResources.getDrawable(app.applicationContext, R.drawable.d20_7)
            8 -> AppCompatResources.getDrawable(app.applicationContext, R.drawable.d20_8)
            9 -> AppCompatResources.getDrawable(app.applicationContext, R.drawable.d20_9)
            10 -> AppCompatResources.getDrawable(app.applicationContext, R.drawable.d20_10)
            11 -> AppCompatResources.getDrawable(app.applicationContext, R.drawable.d20_11)
            12 -> AppCompatResources.getDrawable(app.applicationContext, R.drawable.d20_12)
            13 -> AppCompatResources.getDrawable(app.applicationContext, R.drawable.d20_13)
            14 -> AppCompatResources.getDrawable(app.applicationContext, R.drawable.d20_14)
            15 -> AppCompatResources.getDrawable(app.applicationContext, R.drawable.d20_15)
            16 -> AppCompatResources.getDrawable(app.applicationContext, R.drawable.d20_16)
            17 -> AppCompatResources.getDrawable(app.applicationContext, R.drawable.d20_17)
            18 -> AppCompatResources.getDrawable(app.applicationContext, R.drawable.d20_18)
            19 -> AppCompatResources.getDrawable(app.applicationContext, R.drawable.d20_19)
            else -> AppCompatResources.getDrawable(app.applicationContext, R.drawable.d20_20)
        }
    }

    private fun mediaPlayerSetup() {
        try {
            val soundUri = Uri.parse("android.resource://eu.davidknotek.dice/${R.raw.dice}")
            mediaPlayer = MediaPlayer.create(app, soundUri)
            mediaPlayer?.isLooping = false

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}