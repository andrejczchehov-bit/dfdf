package com.example.myapplicatiocalculyator

import android.media.SoundPool
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private var soundsLoaded = false
    private lateinit var soundPool: SoundPool
    private var soundMenu = 0
    private var soundLock = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val drawer = findViewById<DrawerLayout>(R.id.drawerLayout)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        val mainLayout = findViewById<View>(R.id.mainLayout)
        val sectionText = findViewById<TextView>(R.id.sectionText)

        setSupportActionBar(toolbar)

        // 🔊 создаём SoundPool
        soundPool = SoundPool.Builder().setMaxStreams(5).build()

        // 🔊 загружаем звуки
        soundMenu = soundPool.load(this, R.raw.tone_menu, 1)
        soundLock = soundPool.load(this, R.raw.tone_lock, 1)

        // 🔊 проверка загрузки
        soundPool.setOnLoadCompleteListener { _, _, _ ->
            soundsLoaded = true
        }

        // 🔊 меню
        toolbar.setNavigationOnClickListener {
            if (soundsLoaded) {
                soundPool.play(soundMenu, 0.3f, 0.3f, 1, 0, 1f)
            }
            drawer.openDrawer(GravityCompat.START)
        }

        // 🔊 замок
        findViewById<ImageButton>(R.id.btnLock).setOnClickListener {
            if (soundsLoaded) {
                soundPool.play(soundLock, 0.3f, 0.3f, 1, 0, 1f)
            }
            sectionText.visibility = View.GONE
            mainLayout.visibility = View.VISIBLE
        }

        // 🔊 КНОПКИ (1–9, open, close)
        val buttons = listOf(
            R.id.btn1, R.id.btn2, R.id.btn3,
            R.id.btn4, R.id.btn5, R.id.btn6,
            R.id.btn7, R.id.btn8, R.id.btn9,
            R.id.btnOpen, R.id.btnClose
        )

        for (id in buttons) {
            findViewById<Button>(id).setOnClickListener {
                if (soundsLoaded) {
                    soundPool.play(soundMenu, 1f, 1f, 1, 0, 1f)
                }
            }
        }

        findViewById<NavigationView>(R.id.navigationView)
            .setNavigationItemSelectedListener {
                drawer.closeDrawers()
                sectionText.text = getString(R.string.entered_section, it.title)

                sectionText.visibility = View.VISIBLE
                mainLayout.visibility = View.GONE
                true
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        soundPool.release()
    }
}

