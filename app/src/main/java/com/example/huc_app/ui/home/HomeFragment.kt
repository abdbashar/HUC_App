package com.example.huc_app.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.huc_app.R
import com.example.huc_app.databinding.FragmentHomeBinding
import com.example.huc_app.ui.base.BaseFragment
import com.example.huc_app.util.observeEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override fun getLayoutId(): Int = R.layout.fragment_home

    override val viewModel: HomeViewModel by viewModels()
    private lateinit var newsAdapter: NewsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        observeUIState()
        onEvent()
    }

    private fun observeUIState() {
        lifecycleScope.launch {
            viewModel.newsUIState.collect { uiState ->
                uiState.news.let { universityNews -> newsAdapter.setItems(universityNews) }
            }
        }
    }

    private fun setAdapter() {
        newsAdapter = NewsAdapter(emptyList(), viewModel)
        binding.universityNews.adapter = newsAdapter
    }

    private fun onEvent() {
        viewModel.newsDetails.observeEvent(viewLifecycleOwner) {
            val action = HomeFragmentDirections.actionHomeFragmentToNewsDetailsFragment(
                it.imagePath,
                it.title,
                it.details
            )
            findNavController().navigate(action)
        }
    }

}