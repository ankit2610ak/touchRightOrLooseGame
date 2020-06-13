package com.example.myapplication

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding
import java.util.concurrent.ThreadLocalRandom

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
class MainActivity : AppCompatActivity() {

    private var selectedNum = -1
    private var randomNum = -1
    private var resultVal = 0
    private var mCountDownTimer: CountDownTimer? = null
    private val mTimeLeftInMillis = START_TIME_IN_MILLIS

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        startTimer()
        clickListener()
    }

    @SuppressLint("SetTextI18n")
    private fun checkResult() {
        if (randomNum == selectedNum) {
            resultVal++
            binding.score.text = " $resultVal"
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
        get() {
            binding.orangeBox.setBackgroundColor(Color.YELLOW)
            binding.blueBox.setBackgroundColor(Color.BLUE)
            binding.redBox.setBackgroundColor(Color.RED)
            binding.greenBox.setBackgroundColor(Color.GREEN)
        }

    private fun setColor() {
        when (randomNum) {
            0 -> {
                binding.orangeBox.setBackgroundColor(Color.parseColor("#dbdbdb"))
            }
            1 -> {
                binding.blueBox.setBackgroundColor(Color.parseColor("#dbdbdb"))
            }
            2 -> {
                binding.redBox.setBackgroundColor(Color.parseColor("#dbdbdb"))
            }
            else -> {
                binding.greenBox.setBackgroundColor(Color.parseColor("#dbdbdb"))
            }
        }
    }

    private val randomNumber: Unit
        get() {
            randomNum = ThreadLocalRandom.current().nextInt(0, 4)
            setColor()
        }

    private fun clickListener() {
        binding.orangeBox.setOnClickListener {
            selectedNum = 0
            checkResult()
        }
        binding.blueBox.setOnClickListener {
            selectedNum = 1
            checkResult()
        }
        binding.redBox.setOnClickListener {
            selectedNum = 2
            checkResult()
        }
        binding.greenBox.setOnClickListener {
            selectedNum = 3
            checkResult()
        }
    }

    companion object {
        private const val START_TIME_IN_MILLIS = Int.MAX_VALUE.toLong()
    }
}