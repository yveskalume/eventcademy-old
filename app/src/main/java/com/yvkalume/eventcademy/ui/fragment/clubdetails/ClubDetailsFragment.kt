package com.yvkalume.eventcademy.ui.fragment.clubdetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.airbnb.mvrx.*
import com.yvkalume.data.model.EventUiModel
import com.yvkalume.eventcademy.*
import com.yvkalume.eventcademy.R
import com.yvkalume.eventcademy.databinding.FragmentClubDetailsBinding
import com.yvkalume.eventcademy.util.setImageUrl
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class ClubDetailsFragment : Fragment(R.layout.fragment_club_details) , MavericksView{
    private val binding by viewBinding<FragmentClubDetailsBinding>()
    private val viewModel: ClubDetailsViewModel by fragmentViewModel()
    private val args by navArgs<ClubDetailsFragmentArgs>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpListener()
        binding.club = args.club
    }

    private fun setUpListener() {
        binding.backBtn.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getClubEvents(args.club.uid)
    }

    private fun populateData(events: List<EventUiModel>) {
        binding.rV.withModels {
            if (!events.isNullOrEmpty()) {
                header {
                    id("events")
                    text("Evenements")
                }
                for (event in events) {
                    eventHorizontal {
                        id(event.uid)
                        event(event)
                        clickListener { _ ->
                            val direction = ClubDetailsFragmentDirections.toEventFragment(event)
                            findNavController().navigate(direction)
                        }
                    }
                }
            }
        }
    }

    override fun invalidate() = withState(viewModel) {
        binding.progress.isVisible = it.events is Loading
        when(it.events) {
            is Success -> {
                populateData(it.events.invoke())
                Timber.d("${it.events.invoke()}")
            }

            is Fail -> {
                Toast.makeText(requireContext(),"Une erreur s'est produite", Toast.LENGTH_SHORT).show()
            }
        }
    }
}