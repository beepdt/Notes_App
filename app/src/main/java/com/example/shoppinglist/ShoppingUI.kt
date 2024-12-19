package com.example.shoppinglist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingUI(viewModel: ShoppingViewmodel){

    var showDialog by remember{ mutableStateOf(false) } //for the alert dialog box
    val items by remember{ derivedStateOf { viewModel.shoppingData }} //observe state of shoppingData data class
    var itemName by remember { mutableStateOf("") }
    var itemQuantity by remember { mutableStateOf("") }



    Scaffold(
      topBar = {


          TopAppBar(
              colors = topAppBarColors(
                  containerColor = Color.DarkGray,
                  titleContentColor = Color.White
              ),
              title = {
                  Row (verticalAlignment = Alignment.CenterVertically){
                      Text(text = "Shopping List")
                  }
          })
      },


        //main body
        content = {
            paddingValues ->
            LazyColumn(
                verticalArrangement = Arrangement.SpaceEvenly,
                contentPadding = paddingValues,
                modifier = Modifier
                    .background(
                        color = Color.White
                    )
                    .fillMaxSize()
            ){
                items(items,key = {it.id}){
                    item ->
                    ShoppingItem(id = item.id, title = item.itemName, quantity = item.itemQuantity)
                }
            }
        },





        //for adding new buttons
        floatingActionButton = { FloatingActionButton(onClick = {

            showDialog = true

        }) {
            Icon(imageVector =
            Icons.Filled.Add, contentDescription = "add new item")
        }}



    )

    if (showDialog){
        AlertDialog(onDismissRequest = { showDialog = false},
            confirmButton = { viewModel.addNewItem(itemName = itemName,itemQuantity = itemQuantity) },
            title = { Text(text = "Add new Item")},
            text = {
                Column {
                    OutlinedTextField(value = itemName, onValueChange = {itemName=it})
                    OutlinedTextField(value = itemQuantity, onValueChange = { itemQuantity = it })
                }
            }
            )
    }
}




@Composable
fun ShoppingItem(
    id: Int,
    title: String,
    quantity: String,
    isEditing: Boolean = false,
    onDelete: Boolean = false
){
    Box(modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth()
        .height(24.dp)){
        Column {
            Row {
                Text(text = title)
                Spacer(modifier = Modifier.weight(1f))
                Icon(imageVector = Icons.Filled.Edit, contentDescription = "edit")
                Icon(imageVector = Icons.Filled.Delete, contentDescription = "delete")
            }
            Text(text = quantity)
        }
    }
}







