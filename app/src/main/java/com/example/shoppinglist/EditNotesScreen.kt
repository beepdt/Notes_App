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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.shoppinglist.ui.theme.AppTheme
import com.example.shoppinglist.ui.theme.customFont
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditNoteScreen(viewModel: NoteViewModel,navController:NavHostController,noteId: String?){

    val isDarkMode by viewModel.isDarkMode.collectAsState(initial = false)

    val systemUIController = rememberSystemUiController()

    var ifEdit by remember{ mutableStateOf(true) }
    val snackMes = remember{ mutableStateOf("") }
    val snackbarHostState = remember{ SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val editNoteId = noteId?.toInt()
    if (editNoteId == null){
        navController.popBackStack()
        return
    }


    val notes = viewModel.getNoteById(editNoteId).collectAsState(initial = NotesData(0, "", ""))
    viewModel.noteName = notes.value.noteName
    viewModel.noteText = notes.value.noteText
    //var noteName by remember{ mutableStateOf("")}
    //var noteText by remember { mutableStateOf("") }
    

    
    val scrollBehaviour = TopAppBarDefaults.pinnedScrollBehavior()
    val scrollState = rememberScrollState()
    



    AppTheme(darkTheme = isDarkMode) {
        Scaffold(
            snackbarHost = { SnackbarHost(snackbarHostState) },

            topBar = {
                TopAppBar(

                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.background,
                    ),


                    navigationIcon = {
                        Box(
                            modifier = Modifier
                                .padding(8.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.secondary)
                        ) {
                            IconButton(onClick = { navController.popBackStack() }) {
                                Icon(
                                    imageVector = Icons.Rounded.ArrowBack,
                                    contentDescription = "back",
                                    tint = MaterialTheme.colorScheme.background
                                )

                            }
                        }

                    },


                    title = {
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            horizontalArrangement = Arrangement.End
                        ) {

                            Text(
                                text = "edit",
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.secondary,
                                fontSize = 16.sp
                            )
                        }
                    },


                    scrollBehavior = scrollBehaviour
                )
            },

            containerColor = MaterialTheme.colorScheme.background,

            content = { paddingValues ->
                Column(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize()
                        .verticalScroll(scrollState)

                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
                        val timeStamp = notes.value.dateCreated
                        val createdAt = dateFormat.format(Date(timeStamp))
                        Text(
                            text = "Created on $createdAt",
                            Modifier.padding(top = 16.dp, start = 22.dp),
                            fontWeight = FontWeight.Normal,
                            fontFamily = customFont,
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }



                    TextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(top = 16.dp, start = 8.dp, end = 8.dp),
                        value = viewModel.noteName,
                        onValueChange = { viewModel.noteName = it },
                        readOnly = ifEdit,
                        // singleLine = true,
                        textStyle = TextStyle(
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = customFont
                        ),
                        placeholder = { Text(text = "Title") },
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = MaterialTheme.colorScheme.secondary,
                            focusedContainerColor = Color.Transparent,
                            focusedPlaceholderColor = MaterialTheme.colorScheme.secondary,
                            focusedIndicatorColor = MaterialTheme.colorScheme.secondary,
                            unfocusedContainerColor = Color.Transparent,
                            unfocusedTextColor = MaterialTheme.colorScheme.secondary,
                            unfocusedPlaceholderColor = MaterialTheme.colorScheme.secondary,
                            unfocusedIndicatorColor = MaterialTheme.colorScheme.secondary,
                            cursorColor = Color.Transparent
                        )


                    )
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(horizontal = 8.dp)
                            .padding(top = 8.dp),
                        value = viewModel.noteText,
                        onValueChange = { viewModel.noteText = it },
                        readOnly = ifEdit,
                        textStyle = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                            lineHeight = 24.sp,
                            fontFamily = customFont
                        ),
                        placeholder = { Text(text = "Note") },
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = MaterialTheme.colorScheme.secondary,
                            focusedContainerColor = Color.Transparent,
                            focusedPlaceholderColor = MaterialTheme.colorScheme.secondary,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            unfocusedTextColor = MaterialTheme.colorScheme.secondary,
                            unfocusedPlaceholderColor = MaterialTheme.colorScheme.secondary,
                            unfocusedIndicatorColor = Color.Transparent,
                            cursorColor = MaterialTheme.colorScheme.secondary
                        )
                    )

                    Spacer(
                        modifier = Modifier
                            .height(80.dp)
                    )

                }
            },

            floatingActionButton = {

                Row(
                    Modifier
                        .padding(8.dp)
                        .height(64.dp)
                        .clip(RoundedCornerShape(48.dp))
                        .background(MaterialTheme.colorScheme.onSecondary)

                ) {
                    ExtendedFloatingActionButton(
                        containerColor = Color(0xFFC7EBB3),
                        modifier = Modifier
                            .padding(6.dp)
                            .width(120.dp),
                        shape = RoundedCornerShape(40.dp),
                        onClick = {

                            viewModel.updateNote(
                                NotesData(
                                    editNoteId,
                                    noteName = viewModel.noteName,
                                    noteText = viewModel.noteText
                                )
                            )
                            snackMes.value = "Update Saved"
                            scope.launch { snackbarHostState.showSnackbar(snackMes.value) }

                        }) {
                        Text(
                            text = "Save",
                            color = Color(0xFF111111), // Text color for "Save"
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal
                        )
                    }
                    FloatingActionButton(
                        containerColor = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.padding(end = 6.dp, top = 6.dp, bottom = 6.dp),
                        shape = CircleShape,
                        onClick = { ifEdit = !ifEdit }) {
                        Icon(
                            imageVector = Icons.Outlined.Edit,
                            contentDescription = "",
                            tint = Color(0xffFCF6F1)
                        )
                    }
                }
            },
            floatingActionButtonPosition = FabPosition.Center
        )
    }



}




