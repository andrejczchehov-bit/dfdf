package com.example.myapplicatiocalculyator

import android.media.SoundPool
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var soundPool: SoundPool
    private val sounds = HashMap<Int, Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        soundPool = SoundPool.Builder()
            .setMaxStreams(11)
            .build()

        sounds[R.id.btn1] = soundPool.load(this, R.raw.tone_1, 1)
        sounds[R.id.btn2] = soundPool.load(this, R.raw.tone_2, 1)
        sounds[R.id.btn3] = soundPool.load(this, R.raw.tone_3, 1)
        sounds[R.id.btn4] = soundPool.load(this, R.raw.tone_4, 1)
        sounds[R.id.btn5] = soundPool.load(this, R.raw.tone_5, 1)
        sounds[R.id.btn6] = soundPool.load(this, R.raw.tone_6, 1)
        sounds[R.id.btn7] = soundPool.load(this, R.raw.tone_7, 1)
        sounds[R.id.btn8] = soundPool.load(this, R.raw.tone_8, 1)
        sounds[R.id.btn9] = soundPool.load(this, R.raw.tone_9, 1)

        sounds[R.id.btnOpen] = soundPool.load(this, R.raw.tone_10, 1)
        sounds[R.id.btnClose] = soundPool.load(this, R.raw.tone_11, 1)

        sounds.forEach { (buttonId, soundId) ->
            findViewById<Button>(buttonId).setOnClickListener {
                soundPool.play(soundId, 1f, 1f, 1, 0, 1f)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        soundPool.release()
    }
}
//app fur mute people

