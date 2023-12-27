package com.andricohalim.suitmediatest.ui.firstscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import com.andricohalim.suitmediatest.R
import com.andricohalim.suitmediatest.databinding.ActivityFirstScreenBinding
import com.andricohalim.suitmediatest.ui.secondscreen.SecondScreenActivity
import com.andricohalim.suitmediatest.ui.secondscreen.SecondScreenActivity.Companion.USERNAME

class FirstScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFirstScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.apply {
            btnNext.setOnClickListener {
                val edName = edName.text.toString()

                if (edName.isNotEmpty()) {
                    val intent = Intent(this@FirstScreenActivity, SecondScreenActivity::class.java)
                    intent.putExtra(USERNAME, edName)
                    startActivity(intent)
                } else {
                    Toast.makeText(applicationContext, "Please enter a name", Toast.LENGTH_SHORT).show()
                }
            }
            btnCheck.setOnClickListener {
                val inputText = edPalindrome.text.toString()
                val isPalindrome = isPalindrome(inputText)
                if (isPalindrome) {
                    AlertDialog.Builder(this@FirstScreenActivity).apply {
                        setTitle(getString(R.string.is_palindrome))
                        setMessage(getString(R.string.yes_it_s_palindrome))
                        setPositiveButton(getString(R.string.ok)) { it, _ ->
                            it.dismiss()
                        }
                    }.create().show()
                } else{
                    AlertDialog.Builder(this@FirstScreenActivity).apply {
                        setTitle(getString(R.string.no_palindrome))
                        setMessage(getString(R.string.no_it_s_not_palindrome))
                        setPositiveButton(getString(R.string.ok)) { it, _ ->
                            it.dismiss()
                        }
                    }.create().show()
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