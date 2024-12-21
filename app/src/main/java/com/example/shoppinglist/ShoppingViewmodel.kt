package com.example.shoppinglist

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel

class ShoppingViewmodel: ViewModel(){

    private val _shoppingData = mutableStateListOf<ShoppingData>()

    private var _id = 0

    val shoppingData: SnapshotStateList<ShoppingData> get() = _shoppingData

    fun addNewItem(itemName: String, itemQuantity:String){
        _shoppingData.add(ShoppingData(id = _id++, itemName = itemName, itemQuantity = itemQuantity))
    }

    fun editItem(id:Int,itemName: String,itemQuantity: String){
        val index = _shoppingData.indexOfFirst { it.id == id }
        if(index != -1){
            _shoppingData[index] = _shoppingData[index].copy(itemName = itemName, itemQuantity = itemQuantity)
        }
    }





}