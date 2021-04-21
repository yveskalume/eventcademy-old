package com.yvkalume.eventcademy.ui.fragment.event

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.airbnb.mvrx.*
import com.google.firebase.auth.FirebaseAuth
import com.yvkalume.domain.entity.User
import com.yvkalume.eventcademy.R
import com.yvkalume.eventcademy.databinding.FragmentEventBinding
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

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
    }

    private fun checkIfIsAttending() {
        viewModel.checkIfUserIsAttending(currentUser.uid,args.event.uid)
        viewModel.onAsync(
                asyncProp = EventViewState::isAttending,
                onSuccess = {
                    setAttendeeBtn(it ?: false)
                    Toast.makeText(requireContext(),it.toString(),Toast.LENGTH_SHORT).show()
                },
                onFail = {
                    Timber.e("Erreur")
                }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpNavigation()
        checkIfIsAttending()
        binding.event = args.event
    }

    private fun setUpNavigation() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setAttendeeBtn(isAttendee: Boolean) {
        when(isAttendee) {
            true -> {
                binding.attendBtn.text = getString(R.string.txt_you_are_going)
                binding.attendBtn.apply {
                    setBackgroundColor(resources.getColor(R.color.gray))
                    strokeWidth = 2
                    elevation = 0F
                    setTextColor(Color.GRAY)
                }
            }
            else -> {
                binding.attendBtn.text = getString(R.string.txt_going)
                binding.attendBtn.apply {
                    setBackgroundColor(resources.getColor(R.color.blue700))
                    setTextColor(resources.getColor(R.color.white))
                    setUpListener()
                }
            }
        }
    }

    private fun setUpListener() {
        binding.attendBtn.setOnClickListener {
            viewModel.attend(user,args.event.uid)
            Toast.makeText(requireContext(),"Vous y allez",Toast.LENGTH_SHORT).show()
        }
    }

    private fun populateData(users: List<User>) {
        binding.attendeesPictures = users.map {
            it.profilUrl
        }.shuffled()
    }

    override fun invalidate() = withState(viewModel) {
        when(it.attendees) {
            is Loading -> {

            }

            is Success -> {
                populateData(it.attendees.invoke())
            }

            is Fail -> {
                Timber.e("Erreur")
            }
        }
    }
}