package com.andricohalim.suitmediatest.ui.secondscreen

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.IntentCompat
import com.andricohalim.suitmediatest.R
import com.andricohalim.suitmediatest.databinding.ActivitySecondScreenBinding
import com.andricohalim.suitmediatest.response.DataItem
import com.andricohalim.suitmediatest.ui.thirdscreen.ThirdScreenActivity

class SecondScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondScreenBinding
    private var selectedUsername: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.back)
        }

        setData()

        binding.btnChooseUser.setOnClickListener {
            val intent = Intent(this, ThirdScreenActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun setData() {
        binding.apply {
            val user = IntentCompat.getParcelableExtra(intent, USER_KEY, DataItem::class.java)
            val username = intent.getStringExtra(USERNAME)

            if (user != null) {
                val firstName = user.firstName
                val lastName = user.lastName
                val name = "$firstName $lastName"
                tvSelectedUsername.text = name
            } else {
                tvSelectedUsername.text = getString(R.string.selected_user_name)
            }

            tvUser.text = username
        }
    }


    override fun onResume() {
        super.onResume()
        selectedUsername = intent.getStringExtra(USERNAME)
        binding.tvUser.text = selectedUsername ?: "User"
    }


    companion object {
        const val USERNAME = "username"
        const val USER_KEY = "user_key"
    }
}