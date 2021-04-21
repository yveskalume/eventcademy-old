package com.yvkalume.eventcademy.util.groupie

import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import com.yvkalume.domain.entity.User
import com.yvkalume.eventcademy.R
import com.yvkalume.eventcademy.databinding.ItemAttendeeBinding

class AttendeeItem(private val user: User) : BindableItem<ItemAttendeeBinding>() {
    override fun bind(viewBinding: ItemAttendeeBinding, position: Int) {
        viewBinding.user = user
    }

    override fun getLayout() = R.layout.item_attendee

    override fun initializeViewBinding(view: View) = ItemAttendeeBinding.bind(view)
}