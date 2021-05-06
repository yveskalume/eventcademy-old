package com.yvkalume.eventcademy.ui.fragment.groupes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import com.yvkalume.eventcademy.R
import com.yvkalume.eventcademy.databinding.FragmentGroupesBinding
import com.yvkalume.eventcademy.groupe

class GroupesFragment : Fragment(R.layout.fragment_groupes) {
    private val binding by viewBinding<FragmentGroupesBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecycler()
    }

    private fun setUpRecycler() {
        binding.rV.withModels {
            for (i in 1..6) {
                groupe {
                    id(i)
                }
            }
        }
    }
}