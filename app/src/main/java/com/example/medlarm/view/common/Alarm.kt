package com.example.medlarm.view.common

import java.util.*

data class Alarm(
    val name: String,
    val dose: String,
    var showOptions: Boolean?,
    var action: String?,
    var startDate: Date?,
    var endDate: Date?
)