package com.example.movietest.ui.description

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.movietest.R
import dagger.hilt.android.AndroidEntryPoint
import com.example.movietest.databinding.FragmentDescriptionBinding
import com.google.gson.Gson


@AndroidEntryPoint
class DescriptionFragment : Fragment() {
    lateinit var binding: FragmentDescriptionBinding

   // lateinit var obj: Results

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let {
            val gson = Gson()
           // obj = gson.fromJson(it?.get("obj").toString(), Results::class.java)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_description, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       // Glide.with(binding.ivPosterdetail).load("https://image.tmdb.org/t/p/w500${obj.backdrop_path}").into(binding.ivPosterdetail)
       // binding.tvTitle.text =obj.original_title
       // binding.tvOverview.text = "Descripción:\n\n${obj.overview}"
       // binding.tvTxt.text = "Fecha: ${obj.release_date}"

    }

}