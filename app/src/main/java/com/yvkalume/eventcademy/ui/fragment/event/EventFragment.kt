package com.yvkalume.eventcademy.ui.fragment.event

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.navigation.fragment.findNavController
import com.yvkalume.eventcademy.R
import com.yvkalume.eventcademy.databinding.FragmentEventBinding
import dagger.hilt.EntryPoint

@EntryPoint
class EventFragment : Fragment(R.layout.fragment_event) {
    private val binding by viewBinding<FragmentEventBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpNavigation()
    }

    private fun setUpNavigation() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }
}