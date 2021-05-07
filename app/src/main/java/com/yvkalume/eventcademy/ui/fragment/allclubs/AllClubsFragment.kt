package com.yvkalume.eventcademy.ui.fragment.allclubs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.navigation.fragment.findNavController
import com.yvkalume.eventcademy.R
import com.yvkalume.eventcademy.club
import com.yvkalume.eventcademy.databinding.FragmentAllClubsBinding

class AllClubsFragment : Fragment(R.layout.fragment_all_clubs) {
    private val binding by viewBinding<FragmentAllClubsBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecycler()
    }

    private fun setUpRecycler() {
        binding.rV.withModels {
            for (i in 1..6) {
                club {
                    id(i)
                    clickListener { _ ->
                        val direction = AllClubsFragmentDirections.toClubDetailsFragment()
                        findNavController().navigate(direction)
                    }
                }
            }
        }
    }
}