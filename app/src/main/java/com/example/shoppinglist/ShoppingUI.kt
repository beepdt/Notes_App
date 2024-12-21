package com.example.shoppinglist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
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
            Column(Modifier
                .background(brush = Brush.linearGradient(
                    colors = listOf(Color(0xff434343), Color(0xff000000)),
                    start = Offset(0f, 0f), // Top-left corner
                    end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
                )
            ),) {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 8.dp),
                    contentPadding = paddingValues,

                    ) {
                    items(items, key = { it.id }) { item ->
                        ShoppingItem(
                            id = item.id,
                            title = item.itemName,
                            quantity = item.itemQuantity
                        )
                    }
                }
            }
        },





        //for adding new buttons
        floatingActionButton = {
                FloatingActionButton(onClick = {
                    showDialog = true

            },
                    containerColor = Color.White,
                    modifier = Modifier.size(width = 64.dp, height = 64.dp)
                ) {
                    Box(

                        Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(64.dp))
                        .background(Color.Black),
                        contentAlignment = Alignment.Center) {
                        Icon(imageVector = Icons.Filled.Add, contentDescription = "add new item",tint = Color.White, modifier = Modifier.size(48.dp))
                    }
            }}



    )

    if (showDialog){
        AlertDialog(onDismissRequest = { showDialog = false},
            confirmButton = {
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    Button(onClick = {
                        viewModel.addNewItem(
                            itemName = itemName,
                            itemQuantity = itemQuantity
                        )
                    }) {
                        Text(text = "Save")
                    }
                    Button(onClick = { showDialog = false}) {
                        Text(text = "Cancel")
                    }
                }
                            },
            title = { Text(text = "Add new Item")},
            text = {
                Column {
                    OutlinedTextField(value = itemName, onValueChange = {itemName=it}, placeholder = { Text(
                        text = "Item Name"
                    )})
                    OutlinedTextField(value = itemQuantity, onValueChange = { itemQuantity = it }, placeholder = {
                        Text(text = "Quantity")
                    })
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
        .clip(RoundedCornerShape(16.dp))
        .background(Color.White)
        .padding(8.dp)
        .fillMaxWidth()
        ){
        Column(Modifier.padding(8.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = title)
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Filled.Edit, contentDescription = "edit")
                }

            }
            Row (verticalAlignment = Alignment.CenterVertically) {
                Text(text = quantity)
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Filled.Delete, contentDescription = "delete")
                }
            }
        }
    }
}







