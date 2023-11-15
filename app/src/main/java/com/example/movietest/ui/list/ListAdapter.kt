package com.example.movietest.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movietest.R
import com.example.movietest.databinding.ListItemBinding
import com.example.movietest.domain.model.movie.Results
import com.example.movietest.utils.OnClickList
import com.google.gson.Gson

class ListAdapter(val onClickList: OnClickList) : RecyclerView.Adapter<ListAdapter.ViewHolder>(){

    private val items: MutableList<Results> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context), R.layout.list_item, parent, false
        )
        return ViewHolder(view)
    }

    fun addData(list: List<Results>){
        this.items.clear()
        this.items.addAll(list)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder as ViewHolder).onBind(getItem(position))
    }

    private fun getItem(position: Int):Results{
        return items[position] as Results
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(val dataBinding: ViewDataBinding): RecyclerView.ViewHolder(dataBinding.root) {
        fun onBind(item: Results){
            val listItem = dataBinding as ListItemBinding
            Glide.with(listItem.ivLogo).load("https://image.tmdb.org/t/p/w500${item.poster_path}").into(listItem.ivLogo)

            itemView.setOnClickListener {
                val gson = Gson()
                val str = gson.toJson(item)
                onClickList.goToFragment(str ,it)
            }
        }
    }
}