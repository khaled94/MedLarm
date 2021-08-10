package com.example.medlarm.data.model.responseModels.medicinestypelist

data class MedicineTypeListItem(
    val Id: Int,
    val Lookups: Any,
    val Medicines: List<Any>,
    val Name: String,
    val ParentId: Int
)