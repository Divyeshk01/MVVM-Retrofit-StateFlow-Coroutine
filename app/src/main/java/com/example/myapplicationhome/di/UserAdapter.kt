package com.example.myapplicationhome.di

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationhome.R
import com.example.myapplicationhome.data.local.UserEntity
import com.example.myapplicationhome.databinding.ItemBinding
class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private val list = mutableListOf<UserEntity>()

    fun setData(newList: List<UserEntity>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    inner class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name = view.findViewById<TextView>(R.id.tvName)
        val email = view.findViewById<TextView>(R.id.tvEmail)
        val phone = view.findViewById<TextView>(R.id.tvPhone)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = list[position]
        holder.name.text = item.name
        holder.email.text = item.email
        holder.phone.text = item.phone
    }

    override fun getItemCount(): Int = list.size
}