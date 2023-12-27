package com.andricohalim.suitmediatest.ui.firstscreen

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
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
                    Toast.makeText(applicationContext,
                        getString(R.string.name_can_t_be_empty), Toast.LENGTH_SHORT).show()
                }
            }
            btnCheck.setOnClickListener {
                val inputText = edPalindrome.text.toString()

                if (inputText.isNotEmpty()) {
                    val isPalindrome = isPalindrome(inputText)

                    val customDialog = Dialog(this@FirstScreenActivity)
                    customDialog.setContentView(R.layout.customdialog)

                    val messageTextView = customDialog.findViewById<TextView>(R.id.tvDialogMessage)
                    val okButton = customDialog.findViewById<Button>(R.id.btnOK)

                    if (isPalindrome) {
                        messageTextView.text = getString(R.string.yes_it_s_palindrome)
                    } else {
                        messageTextView.text = getString(R.string.no_it_s_not_palindrome)
                    }

                    okButton.setOnClickListener {
                        customDialog.dismiss()
                    }

                    customDialog.show()
                } else {
                    Toast.makeText(this@FirstScreenActivity,
                        getString(R.string.palindrome_cant_be_empty), Toast.LENGTH_SHORT).show()
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