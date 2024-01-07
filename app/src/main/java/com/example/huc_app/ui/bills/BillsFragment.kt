package com.example.huc_app.ui.bills

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.huc_app.R
import com.example.huc_app.databinding.FragmentBillsBinding
import com.example.huc_app.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BillsFragment : BaseFragment<FragmentBillsBinding>() {

    override fun getLayoutId(): Int = R.layout.fragment_bills

    override val viewModel: BillsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}