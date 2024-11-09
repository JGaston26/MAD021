package com.example.mad021

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    @SuppressLint("DefaultLocale", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val stopWatch : TextView = findViewById(R.id.stopWatch)
        val playButton : ImageButton = findViewById(R.id.playButtonBlue)
        val stopButton : ImageButton = findViewById(R.id.pausebButtonBlue)
        val restartButton : ImageButton = findViewById(R.id.resetButtonBlue)
        var minutesCount : Int = 0;
        var secondsCount  : Int = 0;
        var milisecondsCount : Int = 0;
        val handler : Handler = Handler(Looper.getMainLooper())
        var minutesRunnable: Runnable = Runnable { }
        var secondsRunnable : Runnable = Runnable { }
        var milisecondsRunnable: Runnable = Runnable { }

        playButton.setOnClickListener{
            milisecondsRunnable = Runnable {
                if(milisecondsCount >= 9){
                    milisecondsCount = 0;
                    secondsCount++;
                }
                milisecondsCount++
                handler.postDelayed(milisecondsRunnable,100)
                stopWatch.text = String.format("%02d:%02d.%d", minutesCount, secondsCount, milisecondsCount)
                print("Miliseconds $milisecondsCount")
            }
            secondsRunnable = Runnable {
                if(secondsCount >= 59){
                    secondsCount = 0
                    minutesCount++;
                }
                secondsCount++
                handler.postDelayed(secondsRunnable,1000)
                stopWatch.text = String.format("%02d:%02d.%d", minutesCount, secondsCount, milisecondsCount)
                print("Seconds $secondsCount")
            }
            minutesRunnable = Runnable {
                minutesCount++
                handler.postDelayed(minutesRunnable,60000)
                stopWatch.text = String.format("%02d:%02d.%d", minutesCount, secondsCount, milisecondsCount)
                print("Minutes $minutesCount")

            }

            handler.postDelayed(milisecondsRunnable,100)
            handler.postDelayed(secondsRunnable,1000)
            handler.postDelayed(minutesRunnable,60000)

        }
        stopButton.setOnClickListener {
            handler.removeCallbacks(milisecondsRunnable)
            handler.removeCallbacks(secondsRunnable)
            handler.removeCallbacks(minutesRunnable)
        }
        restartButton.setOnClickListener {
            handler.removeCallbacks(milisecondsRunnable)
            handler.removeCallbacks(secondsRunnable)
            handler.removeCallbacks(minutesRunnable)
            minutesCount  = 0;
            secondsCount = 0;
            milisecondsCount  = 0;
            stopWatch.text = "00:00.0"

        }
    }

}