package com.yvkalume.eventcademy.ui.fragment.event

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.airbnb.mvrx.MavericksView
import com.airbnb.mvrx.fragmentViewModel
import com.google.firebase.auth.FirebaseAuth
import com.yvkalume.domain.entity.User
import com.yvkalume.eventcademy.R
import com.yvkalume.eventcademy.databinding.FragmentEventBinding
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EventFragment : Fragment(R.layout.fragment_event), MavericksView {
    private val binding by viewBinding<FragmentEventBinding>()
    private val viewModel: EventFragmentViewModel by fragmentViewModel()
    private val args by navArgs<EventFragmentArgs>()
    private val currentUser by lazy {
        FirebaseAuth.getInstance().currentUser
    }

    private val user = User(currentUser.uid,currentUser.displayName,currentUser.email,currentUser.photoUrl.toString(), null)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpNavigation()
        binding.event = args.event
        setUpListner()
    }

    private fun setUpListner() {
        binding.attendBtn.setOnClickListener {
            viewModel.attend(user,args.event.uid)
        }
    }

    private fun setUpNavigation() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun invalidate() {

    }
}