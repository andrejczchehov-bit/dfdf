package com.example.yourpackage

import android.media.SoundPool
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

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
        soundMenu = soundPool.load(this, R.raw.tone_menu, 1)
        soundLock = soundPool.load(this, R.raw.tone_lock, 1)

        toolbar.setNavigationOnClickListener {
            soundPool.play(soundMenu, 0.3f, 0.3f, 1, 0, 1f)
            drawer.openDrawer(GravityCompat.START)
        }

        findViewById<ImageButton>(R.id.btnLock).setOnClickListener {
            soundPool.play(soundLock, 0.3f, 0.3f, 1, 0, 1f)
            sectionText.visibility = View.GONE
            mainLayout.visibility = View.VISIBLE
        }

        findViewById<NavigationView>(R.id.navigationView)
            .setNavigationItemSelectedListener {
                drawer.closeDrawers()
                sectionText.text = "Вы вошли в ${it.title}"
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

