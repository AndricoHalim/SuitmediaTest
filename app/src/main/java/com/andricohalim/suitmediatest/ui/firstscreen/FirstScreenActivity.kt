package com.andricohalim.suitmediatest.ui.firstscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.andricohalim.suitmediatest.R
import com.andricohalim.suitmediatest.databinding.ActivityFirstScreenBinding
import com.andricohalim.suitmediatest.ui.secondscreen.SecondScreenActivity

class FirstScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFirstScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.apply {
            btnNext.setOnClickListener {
                val intent = Intent(this@FirstScreenActivity, SecondScreenActivity::class.java)
                startActivity(intent)
            }
            btnCheck.setOnClickListener {
                val inputText = edPalindrome.text.toString()
                val isPalindrome = isPalindrome(inputText)
                if (isPalindrome) {
                    Toast.makeText(applicationContext, "isPalindrome", Toast.LENGTH_SHORT).show()
                } else{
                    Toast.makeText(applicationContext, "not Palindrome", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun isPalindrome(str: String): Boolean {
        var start = 0
        var end = str.length - 1
        while (start < end) {
            while (start < end && !str[start].isLetterOrDigit()) {
                start++
            }
            while (start < end && !str[end].isLetterOrDigit()) {
                end--
            }
            if (str[start] != str[end]) {
                return false
            }
            start++
            end--
        }
        return true
    }
}