package com.example.huc_app.ui.studentDocs

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.huc_app.R
import com.example.huc_app.databinding.FragmentStudentDocsBinding
import com.example.huc_app.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StudentDocsFragment : BaseFragment<FragmentStudentDocsBinding>() {

    override fun getLayoutId(): Int = R.layout.fragment_student_docs

    override val viewModel: StudentDocsViewModel by viewModels()

    private lateinit var studentDocsAdapter: StudentDocsAdapter

    override var bottomNavigationViewVisibility = View.GONE


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeUIState()
    }

    private fun observeUIState() {
        lifecycleScope.launch {
            viewModel.studentDocsUIState.collect {
                it.studentDocs.let { studentDocs -> studentDocsAdapter.setItems(studentDocs.documents) }
            }
        }
    }
}

