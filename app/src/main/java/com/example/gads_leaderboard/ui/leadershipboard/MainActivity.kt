package com.example.gads_leaderboard.ui.leadershipboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.viewpager.widget.ViewPager
import com.example.gads_leaderboard.R
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //View pager setup
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter

        //Tabs setup
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)

        val submitButton: Button = findViewById(R.id.submitButton)
        submitButton.setOnClickListener {
          //  val intent = Intent(this, SubmitActivity::class.java)
            startActivity(intent)
        }
    }
}