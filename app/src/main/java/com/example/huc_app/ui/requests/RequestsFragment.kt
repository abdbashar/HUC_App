package com.example.huc_app.ui.requests

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.huc_app.R
import com.example.huc_app.databinding.FragmentRequestsBinding
import com.example.huc_app.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RequestsFragment : BaseFragment<FragmentRequestsBinding>() {

    override fun getLayoutId(): Int = R.layout.fragment_requests

    override val viewModel: RequestsViewModel by viewModels()

    override var bottomNavigationViewVisibility = View.GONE

    private lateinit var requestsAdapter: RequestsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
    }

    private fun setAdapter() {
        requestsAdapter = RequestsAdapter(emptyList(), viewModel)
        binding.recyclerRequests.adapter = requestsAdapter
    }
}
