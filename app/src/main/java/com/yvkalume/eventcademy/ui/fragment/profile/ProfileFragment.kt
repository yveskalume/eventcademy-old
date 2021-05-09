package com.yvkalume.eventcademy.ui.fragment.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import com.google.firebase.auth.FirebaseAuth
import com.yvkalume.domain.entity.User
import com.yvkalume.eventcademy.R
import com.yvkalume.eventcademy.databinding.FragmentProfileBinding
import com.yvkalume.eventcademy.profileData

class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private val binding by viewBinding<FragmentProfileBinding>()

    private val firebaseUser by lazy {
        FirebaseAuth.getInstance().currentUser
    }

    private val user by lazy {
        User(uid = firebaseUser.uid, name = firebaseUser.displayName!!,email = firebaseUser.email!!,
                profilUrl = firebaseUser?.photoUrl.toString())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecycler()
    }

    private fun setUpRecycler() {
        binding.rV.withModels {
            profileData {
                id("profile")
                user(user)
            }
        }
    }
}