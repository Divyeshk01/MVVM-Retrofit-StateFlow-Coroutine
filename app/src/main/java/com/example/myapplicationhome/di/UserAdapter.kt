package com.example.myapplicationhome.di

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationhome.R
import com.example.myapplicationhome.data.bean.User
import com.example.myapplicationhome.databinding.ItemBinding
class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private val list = mutableListOf<User>()

    fun setData(newList: List<User>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    class UserViewHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = list[position]
        holder.binding.apply {
            tvName.text = item.name
            tvEmail.text = item.email
            tvPhone.text = item.phone
        }
    }

    override fun getItemCount(): Int = list.size
}