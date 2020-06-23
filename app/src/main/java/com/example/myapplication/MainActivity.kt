package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding
import java.util.*

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
class MainActivity : AppCompatActivity() {

    private var selectedNum = -1
    private var randomNum = -1
    private var resultVal = 0
    private var mCountDownTimer: CountDownTimer? = null
    private val mTimeLeftInMillis = START_TIME_IN_MILLIS
    private val TAG: String = "MainActivity"
    private lateinit var binding: ActivityMainBinding
    private var hasStarted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        randomNumberAndResetColor
        clickListener()
    }

    @SuppressLint("SetTextI18n")
    private fun checkResult() {
        if(!hasStarted){
            startTimer()
            hasStarted = true
        }
        if (randomNum == selectedNum) {
            resultVal++
            binding.score.text = " $resultVal"

        } else {
            Log.d(TAG, "wrong touch")
            openResultPage()
        }
        selectedNum = -1
        resetTimer()


    }

    override fun onDestroy() {
        super.onDestroy()
        hasStarted = false
        mCountDownTimer?.cancel()
        Log.d(TAG, "on Destroy called")
    }

    private fun openResultPage() {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("result", resultVal)
        finish()
        startActivity(intent)

    }

    private fun startTimer() {
        mCountDownTimer = object : CountDownTimer(mTimeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                originalState
                randomNumberAndResetColor
                Log.d(TAG, "tick$resultVal")
            }

            override fun onFinish() {
                Log.d(TAG, "timer finish")
                openResultPage()

            }
        }.start()
    }

    private fun resetTimer() {
        Log.d(TAG, "reset timer")
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

    private val randomNumberAndResetColor: Unit
        get() {
            getRandomNumber()
            setColor()
        }

    private fun getRandomNumber() {

        val randomNumTenp = Random().nextInt(4)
        if (randomNumTenp == randomNum) {
            getRandomNumber()
        } else {
            randomNum = randomNumTenp
            Log.d(TAG, "Random Num: $randomNum")

        }
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
        private const val START_TIME_IN_MILLIS = 1000.toLong()
    }
}