package com.amalip.teachers.presentation.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.amalip.teachers.R
import com.amalip.teachers.core.extension.failure
import com.amalip.teachers.core.extension.observe
import com.amalip.teachers.core.presentation.BaseFragment
import com.amalip.teachers.core.presentation.BaseViewState
import com.amalip.teachers.databinding.ProfileFragmentBinding
import com.amalip.teachers.domain.model.User
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings
import kotlinx.coroutines.DelicateCoroutinesApi

@AndroidEntryPoint
@WithFragmentBindings
@DelicateCoroutinesApi
class ProfileFragment : BaseFragment(R.layout.profile_fragment) {

    private lateinit var binding: ProfileFragmentBinding

    private val profileViewModel by viewModels<ProfileViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        profileViewModel.apply {
            observe(state, ::onViewStateChanged)
            failure(failure, ::handleFailure)
        }
    }

    override fun onViewStateChanged(state: BaseViewState?) {
        super.onViewStateChanged(state)
        when (state) {
            is ProfileViewState.UserFound -> binding.user = state.user
        }
    }

    override fun setBinding(view: View) {
        binding = ProfileFragmentBinding.bind(view)

        binding.lifecycleOwner = this

        binding.apply {
            user = User()
        }

        baseActivity.setBottomNavVisibility(View.VISIBLE)
    }

}