package com.yvkalume.eventcademy.ui.fragment.event

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.list.customListAdapter
import com.airbnb.mvrx.*
import com.google.firebase.auth.FirebaseAuth
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.yvkalume.domain.entity.User
import com.yvkalume.eventcademy.R
import com.yvkalume.eventcademy.databinding.FragmentEventBinding
import com.yvkalume.eventcademy.util.addToGoogleAgendaIntent
import com.yvkalume.eventcademy.util.groupie.AttendeeItem
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

    private val attendeeAdapter = GroupAdapter<GroupieViewHolder>()

    private val user = User(currentUser.uid,currentUser.displayName!!,currentUser.email!!,currentUser.photoUrl?.toString() ?: "", null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getAttendees(args.event.uid)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpNavigation()
        checkIfIsAttending()
        binding.event = args.event
        binding.participants.setOnClickListener {
            showAttendeesDialog()
        }
    }

    private fun checkIfIsAttending() {
        viewModel.checkIfUserIsAttending(currentUser.uid,args.event.uid)
        viewModel.onAsync(
                asyncProp = EventViewState::isAttending,
                deliveryMode = UniqueOnly("isAttending"),
                onSuccess = {
                    setAttendeeBtn(it ?: false)
                },
                onFail = {
                    Toast.makeText(requireContext(),"Une erreur s'est produite",Toast.LENGTH_SHORT).show()
                    Timber.e("Erreur")
                }
        )
    }

    private fun setUpNavigation() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setAttendeeBtn(isAttendee: Boolean) {
        when(isAttendee) {
            true -> {
                binding.attendBtn.text = getString(R.string.txt_add_to_agenda)
                binding.attendBtn.apply {
                    setBackgroundColor(resources.getColor(R.color.gray))
                    strokeWidth = 2
                    elevation = 0F
                    setTextColor(Color.GRAY)
                    addToAgenda()
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

    private fun addToAgenda() {
        binding.attendBtn.setOnClickListener {
            startActivity(args.event.addToGoogleAgendaIntent())
        }
    }

    private fun setUpListener() {
        binding.attendBtn.setOnClickListener {
            viewModel.attend(user,args.event.uid)
            viewModel.checkIfUserIsAttending(currentUser.uid,args.event.uid)
        }
    }

    private fun populateData(users: List<User>) {
        binding.attendeesPictures = users.map {
            it.profilUrl
        }.shuffled()

        val items = users.map {
            AttendeeItem(it)
        }
        attendeeAdapter.updateAsync(items)
    }

    private fun showAttendeesDialog() {
        if (attendeeAdapter.itemCount > 0) {
            MaterialDialog(requireContext()).show {
                title(text = "Participants")
                customListAdapter(attendeeAdapter)
            }
        }
    }

    override fun invalidate() = withState(viewModel) {
        binding.progress.isVisible = it.isAttending is Loading

        when(it.attendees) {

            is Success -> {
                populateData(it.attendees.invoke())
            }

            is Fail -> {
                Toast.makeText(requireContext(),"Une erreur s'est produite",Toast.LENGTH_SHORT).show()
                Timber.e("Erreur")
            }
        }
    }
}