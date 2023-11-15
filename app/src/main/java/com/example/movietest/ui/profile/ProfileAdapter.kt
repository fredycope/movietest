package com.example.movietest.ui.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movietest.R
import com.example.movietest.databinding.ProfileItemBinding
import com.example.movietest.domain.model.person.Profiles


class ProfileAdapter : RecyclerView.Adapter<ProfileAdapter.ViewHolder>() {
    private val items: MutableList<Profiles> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileAdapter.ViewHolder {
        val view = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context), R.layout.profile_item, parent, false
        )
        return ViewHolder(view)
    }

    fun addData(list: List<Profiles>){
        this.items.clear()
        this.items.addAll(list)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ProfileAdapter.ViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
    private fun getItem(position: Int): Profiles {
        return items[position]
    }
    override fun getItemCount(): Int {
        return items.size
    }
    inner class ViewHolder(val dataBinding: ViewDataBinding): RecyclerView.ViewHolder(dataBinding.root) {
        fun onBind(item: Profiles){
            val listItem = dataBinding as ProfileItemBinding
            Glide.with(listItem.ivProfile).load("https://image.tmdb.org/t/p/w500${item.file_path}").into(listItem.ivProfile)
        }
    }
}