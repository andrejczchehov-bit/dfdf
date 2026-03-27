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

        soundPool = SoundPool.Builder().setMaxStreams(2).build()

        soundPool.setOnLoadCompleteListener { _, _, _ ->
            soundsLoaded = true
        }

        toolbar.setNavigationOnClickListener {
            soundPool.play(soundMenu, 0.3f, 0.3f, 1, 0, 1f)
            drawer.openDrawer(GravityCompat.START)
        }

        findViewById<ImageButton>(R.id.btnLock).setOnClickListener {
            soundPool.play(soundLock, 0.3f, 0.3f, 1, 0, 1f)
            sectionText.visibility = View.GONE
            mainLayout.visibility = View.VISIBLE
            findViewById<Button>(R.id.btn1).setOnClickListener {
                if (soundsLoaded) soundPool.play(soundMenu, 1f, 1f, 1, 0, 1f)
            }

            findViewById<Button>(R.id.btn2).setOnClickListener {
                if (soundsLoaded) soundPool.play(soundMenu, 1f, 1f, 1, 0, 1f)
            }

            findViewById<Button>(R.id.btn3).setOnClickListener {
                if (soundsLoaded) soundPool.play(soundMenu, 1f, 1f, 1, 0, 1f)
            }

            findViewById<Button>(R.id.btn4).setOnClickListener {
                if (soundsLoaded) soundPool.play(soundMenu, 1f, 1f, 1, 0, 1f)
            }

            findViewById<Button>(R.id.btn5).setOnClickListener {
                if (soundsLoaded) soundPool.play(soundMenu, 1f, 1f, 1, 0, 1f)
            }

            findViewById<Button>(R.id.btn6).setOnClickListener {
                if (soundsLoaded) soundPool.play(soundMenu, 1f, 1f, 1, 0, 1f)
            }

            findViewById<Button>(R.id.btn7).setOnClickListener {
                if (soundsLoaded) soundPool.play(soundMenu, 1f, 1f, 1, 0, 1f)
            }

            findViewById<Button>(R.id.btn8).setOnClickListener {
                if (soundsLoaded) soundPool.play(soundMenu, 1f, 1f, 1, 0, 1f)
            }

            findViewById<Button>(R.id.btn9).setOnClickListener {
                if (soundsLoaded) soundPool.play(soundMenu, 1f, 1f, 1, 0, 1f)
            }

            findViewById<Button>(R.id.btnOpen).setOnClickListener {
                if (soundsLoaded) soundPool.play(soundMenu, 1f, 1f, 1, 0, 1f)
            }

            findViewById<Button>(R.id.btnClose).setOnClickListener {
                if (soundsLoaded) soundPool.play(soundMenu, 1f, 1f, 1, 0, 1f)
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

