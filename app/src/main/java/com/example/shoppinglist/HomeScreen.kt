package com.example.shoppinglist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.shoppinglist.ui.theme.customFont

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: NoteViewModel){

    val searchValue = mutableStateOf("")

    Scaffold (
        topBar = {
                 TopAppBar(
                     title = {
                         Text(text = "Scribbl", fontWeight = FontWeight.Bold, fontFamily = customFont)
                     },
                     actions = {
                         Icon(imageVector = Icons.Rounded.Settings, contentDescription = "")
                     }

                 )
        },

        content = {
            
        }
    )
}