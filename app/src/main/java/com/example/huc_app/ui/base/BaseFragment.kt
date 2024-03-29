package com.example.huc_app.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.huc_app.BR
import com.example.huc_app.ui.main.MainActivity

abstract class BaseFragment<VDB: ViewDataBinding> : Fragment() {

    @LayoutRes
    abstract fun getLayoutId(): Int
    abstract val viewModel: ViewModel

    private var _binding: VDB? = null
    val binding: VDB get() = _binding!!
    protected open var bottomNavigationViewVisibility = View.VISIBLE

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        setBottomNavigationVisibility()
        binding.run {
            lifecycleOwner = viewLifecycleOwner
            setVariable(BR.viewModel, this@BaseFragment.viewModel)
        }
        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun setBottomNavigationVisibility() {
        if (activity is MainActivity) {
            (activity as MainActivity).setBottomNavigationVisibility(bottomNavigationViewVisibility)
        }
    }
}