package com.amalip.teachers.presentation.course.list

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.amalip.teachers.R
import com.amalip.teachers.core.extension.failure
import com.amalip.teachers.core.extension.observe
import com.amalip.teachers.core.presentation.BaseFragment
import com.amalip.teachers.core.presentation.BaseViewState
import com.amalip.teachers.databinding.CoursesFragmentBinding
import com.amalip.teachers.domain.model.Course
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings
import kotlinx.coroutines.DelicateCoroutinesApi

@AndroidEntryPoint
@WithFragmentBindings
@DelicateCoroutinesApi
class CoursesFragment : BaseFragment(R.layout.courses_fragment) {

    private lateinit var binding: CoursesFragmentBinding

    private val coursesViewModel by viewModels<CoursesViewModel>()
    private val adapter: CoursesAdapter by lazy { CoursesAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        coursesViewModel.apply {
            observe(state, ::onViewStateChanged)
            failure(failure, ::handleFailure)
        }
    }

    override fun onResume() {
        super.onResume()
        coursesViewModel.getCoursesByUser()
    }

    override fun onPause() {
        super.onPause()
        binding.svCourses.setQuery("", false)
    }

    override fun onViewStateChanged(state: BaseViewState?) {
        super.onViewStateChanged(state)
        when (state) {
            is CoursesViewState.CoursesReceived -> setUpAdapter(state.courses)
        }
    }

    private fun setUpAdapter(course: List<Course>) {
        binding.emptyView.isVisible = course.isEmpty()

        adapter.addData(course)

        adapter.setListener {
            navController.navigate(
                CoursesFragmentDirections.actionCoursesFragmentToCourseDetailFragment(
                    it
                )
            )
        }

        binding.rcCourses.apply {
            isVisible = course.isNotEmpty()
            adapter = this@CoursesFragment.adapter
        }

    }

    override fun setBinding(view: View) {
        binding = CoursesFragmentBinding.bind(view)

        binding.lifecycleOwner = viewLifecycleOwner

        binding.apply {
            swpRefresh.setOnRefreshListener {
                coursesViewModel.getCoursesByUser()
                swpRefresh.isRefreshing = false
            }

            svCourses.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    coursesViewModel.searchCourse(query ?: "")
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    coursesViewModel.searchCourse(newText ?: "")
                    return true
                }
            })

        }

        baseActivity.setBottomNavVisibility(View.VISIBLE)
    }

}