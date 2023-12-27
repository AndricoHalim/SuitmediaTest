package com.andricohalim.suitmediatest.ui.thirdscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.andricohalim.suitmediatest.adapter.UserAdapter
import com.andricohalim.suitmediatest.data.LoadingStateAdapter
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

        supportActionBar?.title = "Third Screen"


        val layoutManager = LinearLayoutManager(this)
        binding.rvUser.layoutManager = layoutManager

        setupAction()

    }

    private fun setupAction() {
        val adapter = UserAdapter()
        binding.rvUser.adapter = adapter.withLoadStateFooter(
            footer = LoadingStateAdapter {
                adapter.retry()
            }
        )
        thirdScreenViewModel.listUser.observe(this) {
            adapter.submitData(lifecycle, it)
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