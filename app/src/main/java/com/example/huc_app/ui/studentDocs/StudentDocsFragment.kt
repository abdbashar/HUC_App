package com.example.huc_app.ui.studentDocs

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils.loadAnimation
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.huc_app.R
import com.example.huc_app.databinding.FragmentStudentDocsBinding
import com.example.huc_app.ui.base.BaseFragment
import com.example.huc_app.util.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StudentDocsFragment : BaseFragment<FragmentStudentDocsBinding>() {

    override fun getLayoutId(): Int = R.layout.fragment_student_docs

    override val viewModel: StudentDocsViewModel by viewModels()

    override var bottomNavigationViewVisibility = View.GONE


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}

