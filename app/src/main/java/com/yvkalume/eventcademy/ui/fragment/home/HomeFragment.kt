package com.yvkalume.eventcademy.ui.fragment.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.airbnb.epoxy.EpoxyController
import com.airbnb.mvrx.*
import com.google.firebase.auth.FirebaseAuth
import com.yvkalume.data.mapper.EventUiMapper
import com.yvkalume.data.model.EventUiModel
import com.yvkalume.data.model.presentation.HomeData
import com.yvkalume.domain.entity.Event
import com.yvkalume.eventcademy.*
import com.yvkalume.eventcademy.R
import com.yvkalume.eventcademy.databinding.FragmentHomeBinding
import com.yvkalume.eventcademy.util.carousel
import com.yvkalume.eventcademy.util.setImageUrl
import com.yvkalume.eventcademy.util.withModelsFrom
import com.yvkalume.util.Result
import com.yvkalume.util.data
import com.yvkalume.util.succeeded
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home), MavericksView {
    private val binding by viewBinding<FragmentHomeBinding>()
    private val viewModel: HomeViewModel by fragmentViewModel()
    private val auth by lazy {
        FirebaseAuth.getInstance()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        binding.userImage.setImageUrl(auth.currentUser?.photoUrl.toString())
    }

    private fun populateData(data: HomeData) {
        binding.rV.withModels {
            if (data.onlineEvents.isNullOrEmpty() && data.offlineEvents.isNullOrEmpty()) {
                emptyList {
                    id("empty-list")
                }
            }
            getFeaturedEvent(data.featuredEvent)
            offlineEvents(data.offlineEvents)
            onlineEvents(data.onlineEvents)
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
        if (events != null && events.isNotEmpty()) {
            header {
                id("online")
                text("En Ligne")
            }

            carousel {
                id("online-events")
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
        if (events != null && events.isNotEmpty()) {
            header {
                id("presentiel")
                text("En Presentiel")
            }

            carousel {
                id("offline-events")
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

    override fun invalidate() = withState(viewModel) {
        binding.progress.isVisible = it.data is Loading
        when(it.data) {
            is Success -> {
                populateData(it.data.invoke())
            }

            is Fail -> {
                Toast.makeText(requireContext(),"Une erreur s'est produite",Toast.LENGTH_SHORT).show()
            }
        }
    }
}