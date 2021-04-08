package com.yvkalume.eventcademy.ui.fragment.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.navigation.fragment.findNavController
import com.yvkalume.eventcademy.EventBindingModel_
import com.yvkalume.eventcademy.R
import com.yvkalume.eventcademy.databinding.FragmentHomeBinding
import com.yvkalume.eventcademy.featuredEvent
import com.yvkalume.eventcademy.header
import com.yvkalume.eventcademy.util.carousel
import com.yvkalume.eventcademy.util.withModelsFrom
import dagger.hilt.EntryPoint

@EntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private val binding by viewBinding<FragmentHomeBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecycler()
    }

    private fun setUpRecycler() {
        binding.rV.withModels {
            featuredEvent {
                id("featured")
                clickListener { _ ->
                    val directions = HomeFragmentDirections.toEventFragment()
                    findNavController().navigate(directions)
                }
            }

            header {
                id("online")
                text("En Ligne")
            }

            carousel {
                id("carousel")
                withModelsFrom(listOf(1,2,3,4,5)) {
                    EventBindingModel_()
                        .id(it)
                        .clickListener { _ ->
                            val directions = HomeFragmentDirections.toEventFragment()
                            findNavController().navigate(directions)
                        }
                }
            }

            header {
                id("presentiel")
                text("En Presentiel")
            }

            carousel {
                id("carousel")
                withModelsFrom(listOf(1,2,3,4,5)) {
                    EventBindingModel_()
                        .id(it)
                }
            }
        }
    }
}