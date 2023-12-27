package com.andricohalim.suitmediatest.adapter

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.andricohalim.suitmediatest.databinding.UserItemBinding
import com.andricohalim.suitmediatest.response.DataItem
import com.andricohalim.suitmediatest.ui.secondscreen.SecondScreenActivity
import com.andricohalim.suitmediatest.utils.loadImage

class UserAdapter :
    PagingDataAdapter<DataItem, UserAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = getItem(position)
        if (user != null) {
            holder.bind(user)
            holder.itemView.setOnClickListener {
//                onUserItemClickListener.onUserItemClick(user)
            }
        }
    }

    class ViewHolder(private val binding: UserItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: DataItem) {
            binding.apply {
                loadImage(root.context, user.avatar, ivUser)
                tvFirstName.text = user.firstName
                tvLastName.text = user.lastName
            }

        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataItem>() {
            override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}