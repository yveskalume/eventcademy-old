package com.yvkalume.eventcademy.ui.fragment.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.navigation.fragment.findNavController
import com.airbnb.epoxy.EpoxyController
import com.airbnb.mvrx.*
import com.yvkalume.data.mapper.EventUiMapper
import com.yvkalume.data.model.EventUiModel
import com.yvkalume.data.model.presentation.HomeData
import com.yvkalume.domain.entity.Event
import com.yvkalume.eventcademy.EventBindingModel_
import com.yvkalume.eventcademy.R
import com.yvkalume.eventcademy.databinding.FragmentHomeBinding
import com.yvkalume.eventcademy.featuredEvent
import com.yvkalume.eventcademy.header
import com.yvkalume.eventcademy.util.carousel
import com.yvkalume.eventcademy.util.withModelsFrom
import com.yvkalume.util.Result
import com.yvkalume.util.data
import com.yvkalume.util.succeeded
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home), MavericksView {
    private val binding by viewBinding<FragmentHomeBinding>()
    private val viewModel: HomeViewModel by fragmentViewModel()
    private val eventUiMapper by lazy { EventUiMapper() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun populateData(data: HomeData) {
        binding.rV.withModels {
            getFeaturedEvent(data.featuredEvent)
            onlineEvents(data.onlineEvents)
            offlineEvents(data.offlineEvents)
        }
    }

    private fun EpoxyController.getFeaturedEvent(event: EventUiModel?) {
        if (event != null) {
            featuredEvent {
                id("featured")
                event(event)
                clickListener { _ ->
                    val directions = HomeFragmentDirections.toEventFragment(event)
                    findNavController().navigate(directions)
                }
            }
        }
    }

    private fun EpoxyController.onlineEvents(events: List<EventUiModel>?) {
        if (events != null) {
            header {
                id("online")
                text("En Ligne")
            }

            carousel {
                id("carousel")
                withModelsFrom(events) {
                    EventBindingModel_()
                            .id(it.uid)
                            .event(it)
                            .clickListener { _ ->
                                val directions = HomeFragmentDirections.toEventFragment(it)
                                findNavController().navigate(directions)
                            }
                }
            }
        }
    }

    private fun EpoxyController.offlineEvents(events: List<EventUiModel>?) {
        if (events != null) {
            header {
                id("presentiel")
                text("En Presentiel")
            }

            carousel {
                id("carousel")
                withModelsFrom(events) {
                    EventBindingModel_()
                            .id(it.uid)
                            .event(it)
                }
            }
        }
    }

    override fun invalidate() = withState(viewModel) {
        when(it.data) {
            is Loading -> {

            }
            is Success -> {
                populateData(it.data.invoke())
            }

            is Fail -> {

            }
        }
    }
}