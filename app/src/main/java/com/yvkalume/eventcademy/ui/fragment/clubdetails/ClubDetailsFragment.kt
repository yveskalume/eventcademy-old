package com.yvkalume.eventcademy.ui.fragment.clubdetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import com.yvkalume.eventcademy.*
import com.yvkalume.eventcademy.databinding.FragmentClubDetailsBinding

class ClubDetailsFragment : Fragment(R.layout.fragment_club_details) {
    private val binding by viewBinding<FragmentClubDetailsBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpListener()
    }

    private fun setUpListener() {
        binding.rV.withModels {
            clubDetail {
                id("detail")
            }
            header {
                id("events")
                text("Evenements")
            }
            for (i in 1..8) {
                eventHorizontal {
                    id(i)
                }
            }
        }
    }
}