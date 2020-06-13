package com.example.myapplication

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.util.concurrent.ThreadLocalRandom

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
class MainActivity : AppCompatActivity() {
    var score: TextView? = null
    var orangeBox: TextView? = null
    var blueBox: TextView? = null
    var redBox: TextView? = null
    var greenBox: TextView? = null
    var selectedNum = -1
    var randomNum = -1
    var resultVal = 0
    private var mCountDownTimer: CountDownTimer? = null
    private val mTimeLeftInMillis = START_TIME_IN_MILLIS
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findviews()
        startTimer()
        clickListener()
    }

    @SuppressLint("SetTextI18n")
    private fun checkResult() {
        if (randomNum == selectedNum) {
            resultVal++
            score!!.text = " $resultVal"
        }
        selectedNum = -1
        resetTimer()
    }

    private fun startTimer() {
        mCountDownTimer = object : CountDownTimer(mTimeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                originalState
                randomNumber
            }

            override fun onFinish() {}
        }.start()
    }

    private fun resetTimer() {
        mCountDownTimer!!.cancel()
        startTimer()
    }

    private val originalState: Unit
        private get() {
            orangeBox!!.setBackgroundColor(Color.YELLOW)
            blueBox!!.setBackgroundColor(Color.BLUE)
            redBox!!.setBackgroundColor(Color.RED)
            greenBox!!.setBackgroundColor(Color.GREEN)
        }

    private fun setColor() {
        if (randomNum == 0) {
            orangeBox!!.setBackgroundColor(Color.parseColor("#dbdbdb"))
        } else if (randomNum == 1) {
            blueBox!!.setBackgroundColor(Color.parseColor("#dbdbdb"))
        } else if (randomNum == 2) {
            redBox!!.setBackgroundColor(Color.parseColor("#dbdbdb"))
        } else {
            greenBox!!.setBackgroundColor(Color.parseColor("#dbdbdb"))
        }
    }

    private val randomNumber: Unit
        private get() {
            randomNum = ThreadLocalRandom.current().nextInt(0, 4)
            setColor()
        }

    private fun clickListener() {
        orangeBox!!.setOnClickListener {
            selectedNum = 0
            checkResult()
        }
        blueBox!!.setOnClickListener {
            selectedNum = 1
            checkResult()
        }
        redBox!!.setOnClickListener {
            selectedNum = 2
            checkResult()
        }
        greenBox!!.setOnClickListener {
            selectedNum = 3
            checkResult()
        }
    }

    private fun findviews() {
        score = findViewById(R.id.score)
        orangeBox = findViewById(R.id.orangeBox)
        blueBox = findViewById(R.id.blueBox)
        redBox = findViewById(R.id.redBox)
        greenBox = findViewById(R.id.greenBox)
    }

    companion object {
        private const val START_TIME_IN_MILLIS = Int.MAX_VALUE.toLong()
    }
}