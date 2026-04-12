package com.example.myapplicationhome.di

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationhome.data.local.UserEntity
import com.example.myapplicationhome.databinding.ItemBinding

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserEntityViewHolder>() {

    private val list = mutableListOf<UserEntity>()

    fun setData(newList: List<UserEntity>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    class UserEntityViewHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserEntityViewHolder {
        val binding = ItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return UserEntityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserEntityViewHolder, position: Int) {
        val item = list[position]
        holder.binding.apply {
            tvName.text = item.name
            tvEmail.text = item.email
        }
    }

    override fun getItemCount(): Int = list.size
}