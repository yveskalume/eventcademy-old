package com.yvkalume.eventcademy.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.show(context: Context) {
    val intent = Intent(context,this::class.java)
    startActivity(intent)
}