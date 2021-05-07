package com.yvkalume.eventcademy.ui.fragment.allclubs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.airbnb.mvrx.*
import com.yvkalume.data.model.ClubUiModel
import com.yvkalume.eventcademy.R
import com.yvkalume.eventcademy.club
import com.yvkalume.eventcademy.databinding.FragmentAllClubsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllClubsFragment : Fragment(R.layout.fragment_all_clubs), MavericksView {
    private val binding by viewBinding<FragmentAllClubsBinding>()
    private val viewModel: AllClubsViewModel by fragmentViewModel()

    override fun invalidate() = withState(viewModel) {
        binding.progress.isVisible = it.clubs is Loading

        when(it.clubs) {
            is Success -> {
                populateData(it.clubs.invoke())
            }

            is Fail -> {
                Toast.makeText(requireContext(),"Une erreur s'est produite", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun populateData(clubs: List<ClubUiModel>) {
        binding.rV.withModels {
            for (club in clubs) {
                club {
                    id(club.uid)
                    clickListener { _ ->
                        val direction = AllClubsFragmentDirections.toClubDetailsFragment(club = club)
                        findNavController().navigate(direction)
                    }
                }
            }
        }
    }
}