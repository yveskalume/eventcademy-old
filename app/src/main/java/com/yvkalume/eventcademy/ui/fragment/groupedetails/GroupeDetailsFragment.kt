package com.yvkalume.eventcademy.ui.fragment.groupedetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import com.yvkalume.eventcademy.R
import com.yvkalume.eventcademy.databinding.FragmentGroupeDetailsBinding
import com.yvkalume.eventcademy.eventHorizontal
import com.yvkalume.eventcademy.groupeDetail
import com.yvkalume.eventcademy.header

class GroupeDetailsFragment : Fragment(R.layout.fragment_groupe_details) {
    private val binding by viewBinding<FragmentGroupeDetailsBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpListener()
    }

    private fun setUpListener() {
        binding.rV.withModels {
            groupeDetail {
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