package com.amalip.teachers.presentation.course.detail

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.amalip.teachers.R
import com.amalip.teachers.core.extension.failure
import com.amalip.teachers.core.extension.observe
import com.amalip.teachers.core.presentation.BaseFragment
import com.amalip.teachers.core.presentation.BaseViewState
import com.amalip.teachers.databinding.CourseDetailFragmentBinding
import com.amalip.teachers.domain.model.User
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings
import kotlinx.coroutines.DelicateCoroutinesApi

@AndroidEntryPoint
@WithFragmentBindings
@DelicateCoroutinesApi
class CourseDetailFragment : BaseFragment(R.layout.course_detail_fragment) {

    private lateinit var binding: CourseDetailFragmentBinding

    private val courseDetailViewModel by viewModels<CourseDetailViewModel>()
    private val adapter: StudentsAdapter by lazy { StudentsAdapter() }
    private val args: CourseDetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        courseDetailViewModel.apply {
            observe(state, ::onViewStateChanged)
            failure(failure, ::handleFailure)
        }
    }

    override fun onViewStateChanged(state: BaseViewState?) {
        super.onViewStateChanged(state)
        when (state) {
            is CourseViewState.StudentsReceived -> setUpAdapter(state.students)
        }
    }

    private fun setUpAdapter(course: List<User>) {
        binding.emptyView.isVisible = course.isEmpty()

        adapter.addData(course)

        adapter.setListener {

        }

        binding.rcStudents.apply {
            isVisible = course.isNotEmpty()
            adapter = this@CourseDetailFragment.adapter
        }

    }

    override fun setBinding(view: View) {
        binding = CourseDetailFragmentBinding.bind(view)

        binding.lifecycleOwner = this

        binding.apply {
            course = args.course

            swpRefresh.setOnRefreshListener {
                //courseDetailViewModel.getCoursesByUser()
                swpRefresh.isRefreshing = false
            }
        }

        baseActivity.setBottomNavVisibility(View.VISIBLE)
    }

}