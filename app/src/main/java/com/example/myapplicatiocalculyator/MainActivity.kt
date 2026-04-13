package com.example.myapplicatiocalculyator
import androidx.activity.OnBackPressedCallback
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

    // 🔊 звуки 1–11
    private lateinit var tones: IntArray

    override fun onCreate(savedInstanceState: Bundle?) {

        onBackPressedDispatcher.addCallback(this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {

                    val mainLayout = findViewById<View>(R.id.mainLayout)
                    val sectionText = findViewById<TextView>(R.id.sectionText)

                    if (mainLayout.visibility == View.GONE) {
                        sectionText.visibility = View.GONE
                        mainLayout.visibility = View.VISIBLE
                    } else {
                        finish()
                    }
                }
            }
        )

        soundPool = SoundPool.Builder().setMaxStreams(5).build()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val drawer = findViewById<DrawerLayout>(R.id.drawerLayout)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        val mainLayout = findViewById<View>(R.id.mainLayout)
        val sectionText = findViewById<TextView>(R.id.sectionText)



        supportActionBar?.title = ""

        soundPool = SoundPool.Builder().setMaxStreams(12).build()

        // 🔊 загружаем меню и замок
        soundMenu = soundPool.load(this, R.raw.tone_menu, 1)
        soundLock = soundPool.load(this, R.raw.tone_lock, 1)

        // 🔊 загружаем tone_1 … tone_11
        tones = intArrayOf(
            soundPool.load(this, R.raw.tone_1, 1),
            soundPool.load(this, R.raw.tone_2, 1),
            soundPool.load(this, R.raw.tone_3, 1),
            soundPool.load(this, R.raw.tone_4, 1),
            soundPool.load(this, R.raw.tone_5, 1),
            soundPool.load(this, R.raw.tone_6, 1),
            soundPool.load(this, R.raw.tone_7, 1),
            soundPool.load(this, R.raw.tone_8, 1),
            soundPool.load(this, R.raw.tone_9, 1),
            soundPool.load(this, R.raw.tone_10, 1), // OPEN
            soundPool.load(this, R.raw.tone_11, 1)  // CLOSE
        )

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

        // 🔊 кнопки 1–9
        val digitButtons = listOf(
            R.id.btn1, R.id.btn2, R.id.btn3,
            R.id.btn4, R.id.btn5, R.id.btn6,
            R.id.btn7, R.id.btn8, R.id.btn9
        )

        for (i in digitButtons.indices) {
            findViewById<Button>(digitButtons[i]).setOnClickListener {
                if (soundsLoaded) {
                    soundPool.play(tones[i], 1f, 1f, 1, 0, 1f)
                }
            }
        }

        // 🔊 OPEN (tone_10)
        findViewById<Button>(R.id.btnOpen).setOnClickListener {
            if (soundsLoaded) {
                soundPool.play(tones[9], 1f, 1f, 1, 0, 1f)
            }
        }

        // 🔊 CLOSE (tone_11)
        findViewById<Button>(R.id.btnClose).setOnClickListener {
            if (soundsLoaded) {
                soundPool.play(tones[10], 1f, 1f, 1, 0, 1f)
            }
        }

        findViewById<NavigationView>(R.id.navigationView)
            .setNavigationItemSelectedListener {
                drawer.closeDrawers()

                when (it.itemId) {
                    R.id.p1 -> sectionText.text = getString(R.string.lock_default_text)
                    R.id.p2 -> sectionText.text = ""
                    R.id.p3 -> sectionText.text = ""
                    R.id.p4 -> sectionText.text = ""
                    R.id.p5 -> sectionText.text = ""
                    else -> sectionText.text = ""
                }

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

