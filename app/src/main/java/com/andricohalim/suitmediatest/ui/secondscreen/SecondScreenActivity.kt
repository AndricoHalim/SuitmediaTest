package com.andricohalim.suitmediatest.ui.secondscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.andricohalim.suitmediatest.R
import com.andricohalim.suitmediatest.databinding.ActivityFirstScreenBinding
import com.andricohalim.suitmediatest.databinding.ActivitySecondScreenBinding
import com.andricohalim.suitmediatest.ui.thirdscreen.ThirdScreenActivity

class SecondScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Second Screen"

        binding.btnChooseUser.setOnClickListener {
            val intent = Intent(this, ThirdScreenActivity::class.java)
            startActivity(intent)
        }
    }
}