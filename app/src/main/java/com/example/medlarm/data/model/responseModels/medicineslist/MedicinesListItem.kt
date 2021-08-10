package com.example.medlarm.data.model.responseModels.medicineslist

data class MedicinesListItem(
    val CategoryId: Int,
    val Id: Int,
    val LookupsValue: Any,
    val Name: String,
    val Price: Double
)