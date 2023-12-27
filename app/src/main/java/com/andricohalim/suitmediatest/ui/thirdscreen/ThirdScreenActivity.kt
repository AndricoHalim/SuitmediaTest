package com.andricohalim.suitmediatest.ui.thirdscreen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.andricohalim.suitmediatest.R
import com.andricohalim.suitmediatest.adapter.UserAdapter
import com.andricohalim.suitmediatest.data.LoadingStateAdapter
import com.andricohalim.suitmediatest.databinding.ActivityThirdScreenBinding
import com.andricohalim.suitmediatest.ui.secondscreen.SecondScreenActivity
import com.andricohalim.suitmediatest.utils.ViewModelFactory

class ThirdScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdScreenBinding
    private val thirdScreenViewModel by viewModels<ThirdScreenViewModel> {
        ViewModelFactory.getInstance(this)
    }
    private lateinit var adapter: UserAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.back)
        }

        binding.tvError.isVisible = true

        adapter = UserAdapter()

        val layoutManager = LinearLayoutManager(this)
        binding.rvUser.layoutManager = layoutManager

        setupAction()

        binding.swiperefresh.setOnRefreshListener {
            adapter.refresh()
            Toast.makeText(this, "User Refresh", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupAction() {
        binding.apply {
            binding.rvUser.adapter = adapter.withLoadStateFooter(
                footer = LoadingStateAdapter {
                    adapter.retry()
                }
            )
            adapter.addLoadStateListener { loadState ->
                if (loadState.append.endOfPaginationReached) {
                    if (adapter.itemCount < 1) {
                        tvError.isVisible = true
                        rvUser.isVisible = false
                    } else {
                        tvError.isVisible = false
                        rvUser.isVisible = true
                    }
                }
            }
            thirdScreenViewModel.listUser.observe(this@ThirdScreenActivity) {
                Log.d("MyApp", "Observing data: $it")
                adapter.submitData(lifecycle, it)
                swiperefresh.isRefreshing = false
                tvError.isVisible = false
            }
        }
    }
}