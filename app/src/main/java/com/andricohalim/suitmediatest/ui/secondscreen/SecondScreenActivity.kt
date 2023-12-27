package com.andricohalim.suitmediatest.ui.secondscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.IntentCompat
import com.andricohalim.suitmediatest.R
import com.andricohalim.suitmediatest.databinding.ActivityFirstScreenBinding
import com.andricohalim.suitmediatest.databinding.ActivitySecondScreenBinding
import com.andricohalim.suitmediatest.response.DataItem
import com.andricohalim.suitmediatest.ui.thirdscreen.ThirdScreenActivity
import com.andricohalim.suitmediatest.utils.loadImage

class SecondScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Second Screen"

        setData()

        binding.btnChooseUser.setOnClickListener {
            val intent = Intent(this, ThirdScreenActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setData() {
        val user = IntentCompat.getParcelableExtra(intent, USER_KEY, DataItem::class.java)
        val firstName = user?.firstName
        val lastName = user?.lastName
        val name = "$firstName $lastName"
        binding.tvSelectedUsername.text = name
    }

    companion object {
        const val USER_KEY = "user_key"
    }
}