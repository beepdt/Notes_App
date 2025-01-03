package com.example.shoppinglist

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewNoteScreen(){


    var noteName by remember{ mutableStateOf("")}
    var noteText by remember { mutableStateOf("") }
    val scrollBehaviour = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold (
        
        topBar = {
            TopAppBar(

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFFFFEE9),
                ),

                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back")
                        
                    }
                    
                },


                title = {
                    Text(text = "add new note", fontWeight = FontWeight.Bold)
                },

                //scrollBehavior = scrollBehaviour
            )
        },

        content = {
            paddingValues ->
            Column(modifier = Modifier
                .padding(paddingValues)
                .background(Color(0xFFFFFEE9))
                .fillMaxSize()) {

                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    value = noteName,
                    onValueChange = {noteName = it},
                    singleLine = true,
                    placeholder = { Text(text = "note title")},
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color.Black,
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        unfocusedTextColor = Color.DarkGray,
                        unfocusedPlaceholderColor = Color.DarkGray
                    )


                )
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    value = noteText,
                    onValueChange = {noteText = it},
                    placeholder = { Text(text = "description")},
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color.Black,
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        unfocusedTextColor = Color.DarkGray,
                        unfocusedPlaceholderColor = Color.DarkGray
                    )
                    )
            }
        }
        
    )



}



@Composable
@Preview
fun NewNoteScreenPreview(){
    NewNoteScreen()
}