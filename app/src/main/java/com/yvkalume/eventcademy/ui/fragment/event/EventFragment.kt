package com.yvkalume.eventcademy.ui.fragment.event

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.airbnb.mvrx.*
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

    private val user = User(currentUser.uid,currentUser.displayName!!,currentUser.email!!,currentUser.photoUrl?.toString() ?: "", null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getAttendees(args.event.uid)
        checkIfIsAttending()
    }

    private fun checkIfIsAttending() {
        viewModel.checkIfUserIsAttending(currentUser.uid,args.event.uid)
        viewModel.onAsync(
                asyncProp = EventViewState::isAttending,
                onSuccess = {
                    setAttendeeBtn(it)
                }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpNavigation()
        binding.event = args.event
    }

    private fun setUpNavigation() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    // FIXME: 20/04/21 checkuserattendingcase 
    private fun setAttendeeBtn(isAttendee: Boolean) {
        when {
            isAttendee -> {
                binding.attendBtn.run {
                    setBackgroundColor(Color.GRAY)
                    setTextColor(resources.getColor(R.color.blue700))
                    text = getString(R.string.txt_you_are_going)
                }
            }
            else -> {
                binding.attendBtn.run {
                    setBackgroundColor(resources.getColor(R.color.blue700))
                    setTextColor(resources.getColor(R.color.white))
                    text = getString(R.string.txt_going)
                    setUpListner()
                }
            }
        }
    }

    private fun setUpListner() {
        binding.attendBtn.setOnClickListener {
            viewModel.attend(user,args.event.uid)
        }
    }

    override fun invalidate() = withState(viewModel) {
        when(it.attendees) {
            is Loading -> {

            }

            is Success -> {
                setAttendeeBtn(it.attendees.invoke().contains(user))
            }

            is Fail -> {

            }
        }
    }
}