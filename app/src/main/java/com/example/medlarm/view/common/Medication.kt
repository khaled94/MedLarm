package com.example.medlarm.view.common

data class Medication(
        val id: Int,
        val name: String,
        val image: Int,
        var isChecked: Boolean,
)
