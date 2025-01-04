package com.example.shoppinglist


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
fun EditNoteScreen(){


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
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back", tint = Color.Black)

                    }

                },


                title = {
                    Text(text = "edit note", fontWeight = FontWeight.Bold, color = Color.Black)
                },

                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Rounded.Delete, contentDescription = "",tint = Color.Black)
                    }
                },

                scrollBehavior = scrollBehaviour
            )
        },

        content = {
                paddingValues ->
            Column(modifier = Modifier
                .padding(paddingValues)
                .background(Color(0xFFFFFEE9))
                .fillMaxSize()) {



                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, start = 8.dp,end = 8.dp),
                    value = noteName,
                    onValueChange = {noteName = it},
                    singleLine = true,
                    placeholder = { Text(text = "note title")},
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color.Black,
                        focusedContainerColor = Color.Transparent,
                        focusedPlaceholderColor = Color.Black,
                        focusedIndicatorColor = Color.Black,
                        unfocusedContainerColor = Color.Transparent,
                        unfocusedTextColor = Color.DarkGray,
                        unfocusedPlaceholderColor = Color.DarkGray,
                        unfocusedIndicatorColor = Color.LightGray,
                        cursorColor = Color.DarkGray
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
                        focusedPlaceholderColor = Color.Black,
                        focusedIndicatorColor = Color.Transparent,
                        cursorColor = Color.DarkGray,
                        unfocusedContainerColor = Color.Transparent,
                        unfocusedTextColor = Color.DarkGray,
                        unfocusedPlaceholderColor = Color.DarkGray,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
            }
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = { /*TODO*/ },
                shape = CircleShape,
                modifier = Modifier.size(64.dp),
                containerColor = Color.DarkGray,

                ) {
                Icon(imageVector = Icons.Rounded.Check, contentDescription = "",tint = Color.White, modifier = Modifier.size(32.dp))
            }
        }

    )



}



@Composable
@Preview
fun EditNoteScreenPreview(){
    EditNoteScreen()
}