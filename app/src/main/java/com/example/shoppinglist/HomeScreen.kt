package com.example.shoppinglist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.shoppinglist.ui.theme.customFont

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: NoteViewModel, navController: NavHostController){

    val searchValue = mutableStateOf("")
    val scrollState = rememberScrollState()
    val noteList = viewModel.getRecentNotes.collectAsState(initial = listOf())

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
           paddingValues ->
           Column(
               modifier = Modifier
                   .fillMaxSize()
                   .verticalScroll(scrollState)
                   .padding(paddingValues)
                   .padding(horizontal = 8.dp)
           ) {

               Row (modifier = Modifier.fillMaxWidth().height(320.dp)){
                   Column(modifier = Modifier.weight(0.5f)) {
                       Box(modifier = Modifier
                           .background(Color.Cyan)
                           .fillMaxSize())
                   }
                   Column {
                       Box(modifier = Modifier
                           .weight(0.5f)
                           .fillMaxWidth().background(Color.Green))
                   }
                   Box(modifier = Modifier.fillMaxWidth().background(Color.Green))
               }

              noteList.value.forEach {
                  note->
                  NotesItemUI(
                      id = note.id,
                      title = note.noteName,
                      text = note.noteText,
                      onDelete = { /*TODO*/ },
                      navController = navController,
                      isDark = false,
                      isPinned = note.isPinned
                  ) {

                  }
              }
           }
       }
    )
}