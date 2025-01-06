package com.example.shoppinglist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesUI(viewModel: NotesViewModel,navController: NavHostController){

    val systemUIController = rememberSystemUiController()

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    var showEdit by remember{ mutableStateOf(false) }
    var showDialog by remember{ mutableStateOf(false) } //for the alert dialog box

    var editNoteId by remember{ mutableStateOf(-1) }
    val notes by remember{ derivedStateOf { viewModel.notesData }} //observe state of shoppingData data class
    var noteName by remember { mutableStateOf("") }
    var noteText by remember { mutableStateOf("") }

LaunchedEffect(Unit){
    systemUIController.setStatusBarColor(
        color = Color(0xffFCF6F1),
        darkIcons = true
    )}



    Scaffold(

      topBar = {


          TopAppBar(
              colors = topAppBarColors(
                  containerColor =Color(0xffFCF6F1),
              ),

              title = {
                  Row (
                      Modifier
                          .fillMaxWidth()
                          .padding(end = 8.dp),
                      verticalAlignment = Alignment.CenterVertically,
                      horizontalArrangement = Arrangement.End

                      ){
                      Text(text = "notes.", fontWeight = FontWeight.Bold, color = Color(0xff111111))
                  }
                      },

              scrollBehavior = scrollBehavior



              )
      },


        //main body
        content = {
            paddingValues ->
            Column(Modifier
                .background(
                    Color(0xffFCF6F1)
            ),) {
                LazyColumn(
                    //verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 8.dp, vertical = 8.dp)
                        .nestedScroll(scrollBehavior.nestedScrollConnection),

                    contentPadding = paddingValues,

                    ) {
                    items(notes, key = { it.id }) { item ->
                        NotesItemUI(
                            id = item.id,
                            title = item.noteName,
                            text = item.noteText,
                            onEditing = {
                                id,name,quantity ->
                                editNoteId = id
                                noteName = name
                                noteText = quantity
                                showEdit = true
                            },
                            onDelete = {viewModel.deleteNote(item.id)}
                        )
                    }
                }
            }
        },





        //for adding new items
        floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        navController.navigate("new note")
                              },
                    containerColor = Color(0xff111111),
                    modifier = Modifier.size(64.dp),
                    shape = CircleShape
                ) {
                    Box(modifier = Modifier
                        .clip(CircleShape)
                        .background(Color(0xff323430))
                        .size(48.dp))
                    Icon(imageVector = Icons.Rounded.Add, contentDescription = "add new item",tint = Color(0xffFCF6F1), modifier = Modifier.size(24.dp))
            }}






    )
//adding new item
    if (showDialog){
        AlertDialog(

            onDismissRequest = {
                showDialog = false
                noteName = ""
                noteText = ""
                               },

            confirmButton = {
                Column(modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center) {
                    Button(onClick = {
                        viewModel.addNewNote(
                            noteName = noteName,
                            noteText = noteText
                        )
                        showDialog = false
                        noteName = ""
                        noteText = ""
                    },
                        Modifier.fillMaxWidth(),

                        colors = ButtonDefaults.buttonColors(Color.Black)

                        ) {
                        Text(text = "Save",color = Color(0xFFFFFFE3))
                    }
                    OutlinedButton(
                        onClick = {
                            showDialog = false
                            noteName = ""
                            noteText = ""
                                  },
                        Modifier.fillMaxWidth()) {
                        Text(text = "Cancel",color = Color.Black)
                    }
                }
                            },
            title = { Text(text = "New Note",color = Color.Black)},

            text = {
                Column {
                    OutlinedTextField(
                        value = noteName,
                        onValueChange = {noteName=it},
                        singleLine = true,
                        label = {Text(text = "note title",color = Color.DarkGray)},
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.Black,
                            focusedLabelColor = Color.Black,
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.DarkGray,
                            cursorColor = Color.Black
                        )
                        )
                    OutlinedTextField(
                        value = noteText,
                        onValueChange = { noteText = it },
                        label = {Text(text = "text",color = Color.DarkGray)},
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.Black,
                            focusedLabelColor = Color.Black,
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.DarkGray,
                            cursorColor = Color.Black
                        )
                        )
                }
            },
            containerColor = Color(0xFFFFFFE3),
            tonalElevation = 8.dp,
            modifier = Modifier.shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(24.dp),
                clip = false
            )


            )
    }
    
    //editing item
    if (showEdit){
        AlertDialog(
            onDismissRequest = {
                showEdit = false
                noteName = ""
                noteText = ""
                               },
            confirmButton = { 
                Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center) {
                    Button(
                        onClick = { viewModel.editNote(
                            id = editNoteId,
                            noteName = noteName,
                            noteText = noteText
                    )
                            showEdit = false
                            noteName = ""
                            noteText = ""

                        },

                        modifier = Modifier.fillMaxWidth(),

                        colors = ButtonDefaults.buttonColors(Color.Black)

                    ) {
                        Text(text = "Confirm Edit",color = Color(0xFFFFFFE3))
                    }
                    OutlinedButton(
                        onClick = {
                            showEdit = false
                            noteName = ""
                            noteText = ""
                                  },

                        modifier = Modifier.fillMaxWidth()
                        ) {
                        Text(text = "Cancel",color = Color.Black)
                    }
                }
            },
            title = { Text(text = "Edit Note",color = Color.Black)},
            
            text = {
               Column {
                   OutlinedTextField(
                       value = noteName,
                       onValueChange = { noteName = it },
                       label = { Text(text = "note title",color = Color.DarkGray) },
                       colors = OutlinedTextFieldDefaults.colors(
                           focusedBorderColor = Color.Black,
                           focusedLabelColor = Color.Black,
                           focusedTextColor = Color.Black,
                           unfocusedTextColor = Color.DarkGray,
                           cursorColor = Color.Black
                       )
                   )
                   OutlinedTextField(
                       value = noteText,
                       onValueChange = { noteText = it },
                       label = { Text(text = "text",color = Color.DarkGray) },
                       colors = OutlinedTextFieldDefaults.colors(
                           focusedBorderColor = Color.Black,
                           focusedLabelColor = Color.Black,
                           focusedTextColor = Color.Black,
                           unfocusedTextColor = Color.DarkGray,
                           cursorColor = Color.Black
                       )
                   )
               }
            },

            containerColor = Color(0xFFFFFFE3),
            tonalElevation = 8.dp,
            modifier = Modifier.shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(24.dp),
                clip = false
            )

        )
    }
}






@Composable
fun NotesItemUI(
    id: Int,
    title: String,
    text: String,
    onEditing: (Int,String,String) -> Unit,
    onDelete: () -> Unit
) {

    var boxExpanded by remember{ mutableStateOf(false) }
    //val boxHeight by animateDpAsState(targetValue = if (boxExpanded) Dp.Unspecified else Dp.Unspecified,
       // label = "Box Height Animation" )//dynamic box height

    Box(modifier = Modifier
        .padding(vertical = 8.dp)
        .clip(RoundedCornerShape(8.dp))
        .background(Color(0xFFC7EBB3))
        //.border(width = 0.5.dp, color = Color.LightGray)
        .fillMaxWidth()
        .wrapContentHeight()
        .clickable { boxExpanded = !boxExpanded }
    ){

        var expanded by remember{ mutableStateOf(false) }

        Row(
            Modifier.fillMaxWidth()
        ){

            Column(
                Modifier
                    .weight(0.9f)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Top,

            )

            {
                Text(text =if (title=="")"Untitled" else title,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp,
                    lineHeight = 40.sp)
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = text,
                    color = Color.Black,
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    maxLines = if (boxExpanded) Int.MAX_VALUE else 3,
                    overflow = TextOverflow.Ellipsis
                    )
            }

            Column(
                Modifier
                    .weight(0.1f)
                    .padding(8.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
                ) {
                //used a box because menu rendered on top of icon button
                Box {
                    IconButton(onClick = { expanded = !expanded }) {
                        Icon(
                            imageVector = Icons.Rounded.MoreVert,
                            contentDescription = "edit and delete options",
                            tint = Color.Black
                        )
                    }

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier.background(Color(0xFFFFFFE3))
                        )
                    {
                        //editing
                        DropdownMenuItem(
                            text = {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    //modifier = Modifier.background(Color(0xFFFFFFE3))
                                ) {

                                    Text(text = "Edit",color = Color.Black)

                                    Spacer(modifier = Modifier.weight(1f))

                                    Icon(
                                        imageVector = Icons.Outlined.Edit,
                                        contentDescription = "edit",
                                        tint = Color.Black
                                    )

                                }
                                     },
                            onClick = {
                                onEditing(id,title,text)
                                expanded = false
                            })

                        DropdownMenuItem(
                            text = {

                                Row (
                                    verticalAlignment = Alignment.CenterVertically,
                                    //modifier = Modifier.background(Color(0xFFFFFFE3))
                                    ){

                                    Text(text = "Delete", color = Color.Black)

                                    Spacer(modifier = Modifier.weight(1f))

                                    Icon(
                                        imageVector = Icons.Outlined.Delete,
                                        contentDescription = "delete",
                                        tint = Color.Black
                                    )

                                }
                            },
                            onClick = {
                                onDelete()
                                expanded = false })
                    }
                }
            }

        }

    }

}

@Composable
@Preview
fun NotesUIPreview(){
    NotesUI(viewModel = NotesViewModel(), navController = rememberNavController())
}







