package com.example.mynewsapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mynewsapp.R
import com.example.mynewsapp.Utils.Resource
import com.example.mynewsapp.adapter.NewsAdapter
import com.example.mynewsapp.databinding.FragmentBreakingNewsBinding
import com.example.mynewsapp.ui.NewsActivity
import com.example.mynewsapp.ui.NewsViewModel

class BreakingNewsFragment:Fragment(R.layout.fragment_breaking_news) {

    private var _binding: FragmentBreakingNewsBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBreakingNewsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter

    val TAG = "BreakingNewsFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel
        setupRecyclerView()

        viewModel.breakingNews.observe(viewLifecycleOwner, Observer { response ->
        when(response){
            is Resource.Success->{
                hideProgressBar()
                response.data?.let{newsResponse ->
                    newsAdapter.setData(newsResponse.articles)
                }
            }
            is Resource.Error->{
                hideProgressBar()
                response.message?.let{message->
                    Log.e(TAG,"An error occured: $message")
                }
            }
            is Resource.Loading->{
                showPrograssBar()
            }
        }


        })
    }

    private fun hideProgressBar(){
        binding.paginationProgressBar.visibility = View.INVISIBLE
    }

    private fun showPrograssBar(){
        binding.paginationProgressBar.visibility = View.VISIBLE
    }


    private fun setupRecyclerView(){
        newsAdapter= NewsAdapter()
        binding.rvBreakingNews.apply {
            adapter=newsAdapter
            layoutManager=LinearLayoutManager(activity)
        }
    }
}