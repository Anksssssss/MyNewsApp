package com.example.mynewsapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynewsapp.Utils.Resource
import com.example.mynewsapp.models.NewsResponse
import com.example.mynewsapp.repositories.NewsRespository
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(
    val newsRepository : NewsRespository
    ):ViewModel() {

        val breakingNews:MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
        val breakingNewsPage =  1

    val searchNews:MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    val searchNewsPage =  1

    init {
        getbreakingNews("in")
    }

    fun getbreakingNews(countryCode:String )=viewModelScope.launch {
        breakingNews.postValue(Resource.Loading())
        val response = newsRepository.getBreakingNews(countryCode,breakingNewsPage)
        breakingNews.postValue(handleBreakingNewsResposnse(response))
    }

    fun searchNews(searchQuery:String) = viewModelScope.launch {
        searchNews.postValue(Resource.Loading())
        val response = newsRepository.searchNews(searchQuery,searchNewsPage)
        searchNews.postValue(handleSearchNewsResposnse(response))
    }

    private fun handleBreakingNewsResposnse(response :Response<NewsResponse>) : Resource<NewsResponse>{
        if(response.isSuccessful){
            response.body()?.let{resultResponse->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleSearchNewsResposnse(response :Response<NewsResponse>) : Resource<NewsResponse>{
        if(response.isSuccessful){
            response.body()?.let{resultResponse->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}