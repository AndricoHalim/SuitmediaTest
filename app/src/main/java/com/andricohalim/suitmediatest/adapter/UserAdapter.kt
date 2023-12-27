package com.andricohalim.suitmediatest.adapter

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.RecyclerView
import com.andricohalim.suitmediatest.databinding.UserItemBinding
import com.andricohalim.suitmediatest.response.DataItem
import com.andricohalim.suitmediatest.ui.secondscreen.SecondScreenActivity
import com.andricohalim.suitmediatest.utils.loadImage

class UserAdapter(private val listUser: List<DataItem>) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            UserItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false))
    }

    class ViewHolder(private var binding: UserItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun binding(user: DataItem) {
            binding.apply {
                loadImage(root.context, user.avatar, ivUser)
                tvFirstName.text = user.firstName
                tvLastName.text = user.lastName
                tvEmail.text = user.email

                root.setOnClickListener {
//                    val detailIntent = Intent(binding.root.context, SecondScreenActivity::class.java)
//                    detailIntent.putExtra(SecondScreenActivity.DETAIL_INFO, games)
//                    itemView.context.startActivity(detailIntent, ActivityOptionsCompat.makeSceneTransitionAnimation(itemView.context as Activity).toBundle())
                }
            }

        }
    }

    override fun getItemCount(): Int {
        Log.d("GamesAdapter", "ItemCount: ${listUser.size}")
        return listUser.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding(listUser[position])
    }
}