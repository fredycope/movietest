package com.example.movietest.ui.list

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movietest.R
import com.example.movietest.data.database.dbmodel.MovieTest
import com.example.movietest.databinding.FragmentListBinding
import com.example.movietest.domain.model.movie.Results
import com.example.movietest.utils.Constants.API_KEY
import com.example.movietest.utils.Nav
import com.example.movietest.utils.OnClickList
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ListFragment : Fragment(), OnClickList {
    lateinit var binding: FragmentListBinding

    private val listViewModel: ListViewModel by viewModels()
    lateinit var popularMoviesAdapter: ListAdapter
    lateinit var topRatedMoviesAdapter: ListAdapter
    lateinit var upcomingMoviesAdapter: ListAdapter
    @Inject
    lateinit var navigation: Nav


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        popularMoviesAdapter = ListAdapter(this)
        topRatedMoviesAdapter = ListAdapter(this)
        upcomingMoviesAdapter = ListAdapter(this)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)
        binding.viewModel = listViewModel
        binding.rvPopularMovies.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvPopularMovies.adapter = popularMoviesAdapter

        binding.rvTopRatedMovies.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvTopRatedMovies.adapter = topRatedMoviesAdapter

        binding.rvUpcomingMovies.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvUpcomingMovies.adapter = upcomingMoviesAdapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onResume() {
        super.onResume()
        if(validateNetwork()){
            listViewModel.deleteData()

            listViewModel.getPopularMovies()
            listViewModel.getTopRatedMovies()
            listViewModel.getUpcomingMovies()
            getPopularMovies()
            getTopRatedMovies()
            getUpcomingMovies()
        }else{
            listViewModel.getData( 1)
            listViewModel.getData( 2)
            listViewModel.getData( 3)
             getData()
        }
    }

    private fun getPopularMovies(){
        listViewModel.getPopularMovies.observe(viewLifecycleOwner, Observer {
            popularMoviesAdapter.addData(it)
            listViewModel.saveData(it, 1)
                binding.pdLoad.hide()
        })
    }

    private fun getTopRatedMovies(){
        listViewModel.getTopRatedMovies.observe(viewLifecycleOwner, Observer {
            topRatedMoviesAdapter.addData(it)
            listViewModel.saveData(it,2)
            binding.pdLoad.hide()
        })
    }

    private fun getUpcomingMovies(){
        listViewModel.getUpcomingMovies.observe(viewLifecycleOwner, Observer {
            upcomingMoviesAdapter.addData(it)
            listViewModel.saveData(it,3)
            binding.pdLoad.hide()
        })
    }


    private fun getData(){
        listViewModel.getPopularMoviesDB.observe(viewLifecycleOwner, Observer {
            popularMoviesAdapter.addData(it)
        })

        listViewModel.getTopRatedMoviesDB.observe(viewLifecycleOwner, Observer {
            topRatedMoviesAdapter.addData(it)
        })

        listViewModel.getUpcomingMoviesDB.observe(viewLifecycleOwner, Observer {
            upcomingMoviesAdapter.addData(it)
        })
        binding.pdLoad.hide()
    }

    private fun validateNetwork(): Boolean{
        val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
        return isConnected
    }

    override fun goToFragment(result: Any, view: View) {
        val bundle = bundleOf("obj" to result.toString())
    }


}