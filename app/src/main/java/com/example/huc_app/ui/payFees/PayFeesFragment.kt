package com.example.huc_app.ui.payFees

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.huc_app.R
import com.example.huc_app.databinding.FragmentPayFeesBinding
import com.example.huc_app.ui.base.BaseFragment
import com.example.huc_app.util.observeEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PayFeesFragment : BaseFragment<FragmentPayFeesBinding>() {

    override fun getLayoutId(): Int = R.layout.fragment_pay_fees

    override val viewModel: PayFeesViewModel by viewModels()

    override var bottomNavigationViewVisibility = View.GONE

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeEvents()
        binding.webView.webViewClient = WebViewClient()
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.settings.setSupportZoom(true)
        binding.webView.loadUrl("")
    }

    private fun observeEvents() {
        viewModel.isArrowBackClicked.observeEvent(viewLifecycleOwner) {
            if (it) findNavController().popBackStack()
        }
    }
}