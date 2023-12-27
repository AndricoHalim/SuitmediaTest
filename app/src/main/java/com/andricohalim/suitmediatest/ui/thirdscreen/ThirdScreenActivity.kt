package com.andricohalim.suitmediatest.ui.thirdscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.andricohalim.suitmediatest.adapter.UserAdapter
import com.andricohalim.suitmediatest.utils.Result
import com.andricohalim.suitmediatest.databinding.ActivityThirdScreenBinding
import com.andricohalim.suitmediatest.response.DataItem
import com.andricohalim.suitmediatest.utils.ViewModelFactory

class ThirdScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdScreenBinding
    private val thirdScreenViewModel by viewModels<ThirdScreenViewModel> {
        ViewModelFactory.getInstance(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        binding.rvUser.layoutManager = layoutManager

//        val dividerItemDecoration = DividerItemDecoration(
//            binding.rvUser.context,
//            layoutManager.orientation
//        )
//        binding.rvUser.addItemDecoration(dividerItemDecoration)


        thirdScreenViewModel.listUser.observe(this) { result ->
            when (result) {
                is Result.Loading -> {
                    showLoading(true)
                }

                is Result.Success -> {
                    showLoading(false)
                    setupAction(result.data.data)
                }

                is Result.Error -> {
                    showLoading(false)
                    binding.tvError.text = result.error
                    binding.tvError.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setupAction(story: List<DataItem>) {
        binding.apply {
            if (story.isNotEmpty()) {
                val adapter = UserAdapter(story)
                binding.rvUser.adapter = adapter
            } else {
                rvUser.adapter = null
                binding.tvError.visibility = View.VISIBLE
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.apply {
            if (isLoading) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        }
    }
}