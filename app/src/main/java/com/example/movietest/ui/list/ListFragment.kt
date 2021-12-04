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
import com.example.movietest.R
import com.example.movietest.data.database.dbmodel.MovieTest
import com.example.movietest.databinding.FragmentListBinding
import com.example.movietest.domain.model.Results
import com.example.movietest.utils.Constants.API_KEY
import com.example.movietest.utils.Nav
import com.example.movietest.utils.OnClickList
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ListFragment : Fragment(), OnClickList {
    lateinit var binding: FragmentListBinding

    private val viewModel: ListViewModel by viewModels()
    lateinit var listAdapter: ListAdapter
    @Inject
    lateinit var navigation: Nav


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listAdapter = ListAdapter(this)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)
        binding.viewModel = viewModel
        binding.rvResult.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvResult.adapter = listAdapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onResume() {
        super.onResume()
        if(validateNetwork()){
            viewModel.deleteData()
            viewModel.onCreateCharacter(API_KEY)
            getCharacter()
        }else{
            viewModel.getData()
            getData()
        }
    }

    private fun getCharacter(){
        viewModel.getDataList.observe(viewLifecycleOwner, Observer {
                listAdapter.addData(it)
                saveData(it)
                binding.pdLoad.hide()
        })
    }
    private fun saveData(list: List<Results>){
        list.map {
            val gsTest = MovieTest(it.original_title,it.overview, it.poster_path,it.release_date, it.backdrop_path)
            viewModel.saveData(gsTest)
        }
    }

    private fun getData(){
        viewModel.getDataList.observe(viewLifecycleOwner, Observer {
            listAdapter.addData(it)
        })
        binding.pdLoad.hide()
    }
    fun validateNetwork(): Boolean{
        val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
        return isConnected
    }

    override fun goToFragment(result: Any, view: View) {
        val bundle = bundleOf("obj" to result.toString())
        navigation.gotoFragment(view,R.id.action_listFragment_to_descriptionFragment,bundle)

    }


}