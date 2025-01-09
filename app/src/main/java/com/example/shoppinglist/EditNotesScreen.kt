package com.example.shoppinglist


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditNoteScreen(viewModel: NotesViewModel,navController:NavHostController,noteId: String?){

    val systemUIController = rememberSystemUiController()
    val statusBarColor = Color(0xffFCF6F1)

    var ifEdit by remember{ mutableStateOf(true) }


    val editNoteId = noteId?.toInt()
    val notes = viewModel.notesData
    var noteName by remember{ mutableStateOf("")}
    var noteText by remember { mutableStateOf("") }
    
    LaunchedEffect(editNoteId){
        if (editNoteId != null) {
            val editNote = notes.find { it.id == editNoteId }
            if (editNote != null){
            noteName = editNote.noteName
            noteText = editNote.noteText
            }
        }else{
            navController.popBackStack()
        }
    }
    
    val scrollBehaviour = TopAppBarDefaults.pinnedScrollBehavior()
    val scrollState = rememberScrollState()
    

    LaunchedEffect(Unit){
        systemUIController.setStatusBarColor(
            color = statusBarColor,
            darkIcons = true
        )}

    Scaffold (

        topBar = {
            TopAppBar(

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = statusBarColor,
                ),

                navigationIcon = {
                    Box(modifier = Modifier
                        .padding(8.dp)
                        .clip(CircleShape)
                        .background(Color(0xff111111))) {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.Rounded.ArrowBack,
                                contentDescription = "back",
                                tint = Color(0xffFCF6F1)
                            )

                        }
                    }

                },


                title = {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.Absolute.Right) {

                        Text(
                            text = "edit",
                            fontWeight = FontWeight.Bold,
                            color = Color(0xff111111),
                            fontSize = 16.sp
                        )
                    }
                },



                scrollBehavior = scrollBehaviour
            )
        },

        containerColor = statusBarColor,

        content = {
                paddingValues ->
            Column(modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(scrollState)

            ) {



                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(top = 16.dp, start = 8.dp, end = 8.dp),
                    value = noteName,
                    onValueChange = {noteName = it},
                    readOnly = ifEdit,
                   // singleLine = true,
                    textStyle = TextStyle(fontSize = 30.sp, fontWeight = FontWeight.Bold),
                    placeholder = { Text(text = "Title")},
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color(0xff111111),
                        focusedContainerColor = Color.Transparent,
                        focusedPlaceholderColor = Color.Black,
                        focusedIndicatorColor = Color.Black,
                        unfocusedContainerColor = Color.Transparent,
                        unfocusedTextColor = Color.DarkGray,
                        unfocusedPlaceholderColor = Color.DarkGray,
                        unfocusedIndicatorColor = Color.DarkGray,
                        cursorColor = Color.DarkGray
                    )


                )
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(horizontal = 8.dp)
                        .padding(top = 8.dp),
                    value = noteText,
                    onValueChange = { noteText = it },
                    readOnly = ifEdit,
                    textStyle = TextStyle(fontSize = 16.sp),
                    placeholder = { Text(text = "Note") },
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color(0xff111111),
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

                Spacer(modifier = Modifier
                    .height(80.dp))

            }
        },

        floatingActionButton = {

            Row(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(horizontal = 72.dp)
                    .height(64.dp)
                    .clip(RoundedCornerShape(48.dp))
                    .background(Color(0xFF111111)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Row {

                    //save
                    Box(
                        modifier = Modifier
                            .padding(6.dp)
                            .fillMaxHeight()
                            .width(100.dp)
                            //.weight(0.5f)
                            .clip(RoundedCornerShape(40.dp))
                            .background(Color(0xFFC7EBB3))
                            .clickable {
                                if (editNoteId != null) {
                                    viewModel.editNote(
                                        id = editNoteId,
                                        noteName = noteName,
                                        noteText = noteText
                                    )
                                }

                                noteName = ""
                                noteText = ""
                                navController.navigate("home")
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text( text = "Save",
                            color = Color(0xFF111111), // Text color for "Save"
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal)
                    }

                    //delete
                    Box(
                        modifier = Modifier
                            .padding(vertical = 6.dp)
                            .padding(end = 8.dp)
                            .aspectRatio(1f)
                            .clip(
                                CircleShape
                            )
                            .background(Color(0xff323430))
                            .clickable {
                                if (editNoteId != null) {
                                    viewModel.deleteNote(editNoteId)
                                    navController.popBackStack()
                                }

                            },
                        contentAlignment = Alignment.Center
                    )
                    {
                        Icon(imageVector = Delete, contentDescription = "", tint = statusBarColor)
                    }

                    //edit
                    Box(
                        modifier = Modifier
                            .padding(vertical = 6.dp)
                            .padding(end = 6.dp)
                            .aspectRatio(1f)
                            .clip(
                                CircleShape
                            )
                            .background(Color(0xff323430))
                            .clickable {
                                       ifEdit = !ifEdit
                            },
                        contentAlignment = Alignment.Center
                    )
                    {
                        Icon(imageVector = Icons.Outlined.Edit, contentDescription = "", tint = statusBarColor)
                    }
                }
            }



        },




    )



}



@Composable
@Preview
fun EditNoteScreenPreview(){
    EditNoteScreen(viewModel = NotesViewModel(), navController = rememberNavController(), noteId = "0")
}