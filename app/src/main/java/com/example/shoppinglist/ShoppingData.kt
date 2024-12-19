package com.example.shoppinglist

data class ShoppingData(
    var itemName: String = "",
    var itemQuantity: String = "",
    var id: Int = 0,
    var isEditing: Boolean = false,
    var delete: Boolean = false
)