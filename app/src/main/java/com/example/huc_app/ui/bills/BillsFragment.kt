package com.example.huc_app.ui.bills

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.huc_app.R
import com.example.huc_app.databinding.FragmentBillsBinding
import com.example.huc_app.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class BillsFragment : BaseFragment<FragmentBillsBinding>() {

    override fun getLayoutId(): Int = R.layout.fragment_bills

    override val viewModel: BillsViewModel by viewModels()

    override var bottomNavigationViewVisibility = View.GONE

    private lateinit var paymentAdapter: PaymentAdapter

    private var originalStatusBarColor: Int = 0


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        originalStatusBarColor = requireActivity().window.statusBarColor
        requireActivity().window.statusBarColor = Color.WHITE
        setAdapter()
        observeUIState()
    }

    override fun onDestroyView() {
        requireActivity().window.statusBarColor = originalStatusBarColor
        super.onDestroyView()
    }

    private fun observeUIState() {
        lifecycleScope.launch {
            viewModel.studentFeesUIState.collect {
                it.studentFees.payments.let { studentPayments -> paymentAdapter.setItems(studentPayments) }
            }
        }
    }

    private fun setAdapter() {
        paymentAdapter = PaymentAdapter(emptyList(), viewModel)
        binding.studentPayments.adapter = paymentAdapter
    }
}