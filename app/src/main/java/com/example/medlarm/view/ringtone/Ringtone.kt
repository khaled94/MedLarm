package com.example.medlarm.view.ringtone


data class Ringtone(

    val name: String,
    val ring: Int,
    var isPlaying: Boolean = false,
    var isSelected: Boolean = false,
    var action: String = ""
)