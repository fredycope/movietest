package com.example.movietest.ui.profile

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.movietest.R
import com.example.movietest.data.database.dbmodel.Profile
import com.example.movietest.databinding.FragmentProfileBinding
import com.example.movietest.ui.list.ListAdapter
import com.example.movietest.ui.list.ListViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    lateinit var profileAdapter: ProfileAdapter

    private val profileViewModel: ProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        profileAdapter = ProfileAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        binding.rvProfile.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvProfile.adapter = profileAdapter
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        if(validateNetwork()) {
            profileViewModel.deleteData()
            profileViewModel.getPopularPerson()
            getPopularPerson()
            getPersonImages()
        } else {
            profileViewModel.getData()
            getProfile()
        }
    }

    private fun validateNetwork(): Boolean{
        val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
        return isConnected
    }

    private fun getPopularPerson(){
        profileViewModel.getPopularPerson.observe(viewLifecycleOwner) {
            Glide.with(binding.ivProfile).load("https://image.tmdb.org/t/p/w500${it[1].profile_path}").into(binding.ivProfile)
            binding.tvName.text = "${getString(R.string.name)} ${it[1].name}"
            binding.tvOriginalName.text = "${getString(R.string.original_name)} ${it[1].original_name}"
            binding.tvOverview.text = "${getString(R.string.overview)} ${it[1].known_for[0].overview}"

            val profile = Profile(image_profile =it[1].profile_path,
                name = it[1].name,
                original_name = it[1].original_name,
                overview = it[1].known_for[0].overview,
                images_updates = it[1].profile_path
            )
            profileViewModel.saveProfile(profile)

            profileViewModel.getPersonImages(it[1].id.toLong())
        }
    }

    private fun getPersonImages(){
        profileViewModel.getPersonImage.observe(viewLifecycleOwner) {
            profileAdapter.addData(it)
        }
    }

    private fun getProfile() {
        profileViewModel.getProfile.observe(viewLifecycleOwner) {
            Glide.with(binding.ivProfile).load("https://image.tmdb.org/t/p/w500${it.image_profile}").into(binding.ivProfile)
            binding.tvName.text = "${getString(R.string.name)} ${it.name}"
            binding.tvOriginalName.text = "${getString(R.string.original_name)} ${it.original_name}"
            binding.tvOverview.text = "${getString(R.string.overview)} ${it.overview}"
        }
    }

}