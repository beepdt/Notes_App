package com.example.shoppinglist

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.sharp.FavoriteBorder
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesUI(viewModel: NoteViewModel,navController: NavHostController){

    val isDark by remember{ mutableStateOf(false) }
    viewModel.isPinned = false

    //var isClicked by remember { mutableStateOf(false) }

    val bgColor = if(!isDark){
        Color(0xffFCF6F1)
    } else Color(0xff141414)

    val txtColor = if(!isDark){
        Color(0xff111111)
    } else  Color(0xffFCF6F1)

    /*val tileColor = if(!isDark){
        Color(0xFFC7EBB3)
    } else  Color(0xff1E1E1E)*/

    val systemUIController = rememberSystemUiController()

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val scrollState = rememberScrollState()

   // val notes = viewModel.notesData //observe state of shoppingData data class

    LaunchedEffect(Unit){
    systemUIController.setStatusBarColor(
        color = bgColor,
        darkIcons = true
    )}



    Scaffold(

      topBar = {


          TopAppBar(
              colors = topAppBarColors(
                  containerColor = bgColor,
              ),



              title = {
                  Row (
                      Modifier
                          .fillMaxWidth()
                          .padding(end = 8.dp),
                      verticalAlignment = Alignment.CenterVertically,
                      horizontalArrangement = Arrangement.End

                      ){
                      Text(text = "notes.", fontWeight = FontWeight.Bold, color = txtColor)
                  }
                      },

              scrollBehavior = scrollBehavior



              )
      },


        //main body
        content = {
            paddingValues ->
            Column(
                Modifier
                    .background(bgColor)
                    .padding(paddingValues)
                    .fillMaxSize()
                    //.verticalScroll(rememberScrollState())
                ) {
                val noteList = viewModel.getAllNotes.collectAsState(initial = listOf())
                val scope = rememberCoroutineScope()



                LazyColumn(
                    //verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 8.dp)
                        //.nestedScroll(scrollBehavior.nestedScrollConnection),

                    //contentPadding = paddingValues,

                ){
                    val pinnedNotes = noteList.value.filter { it.isPinned }
                    when {
                        pinnedNotes.isNotEmpty() -> {
                            item {
                                Text(
                                    text = "Pinned",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp,
                                    color = txtColor,
                                    modifier = Modifier.padding(horizontal = 8.dp)
                                )
                            }
                            items(pinnedNotes,key={note-> note.id}){note->
                                val dismissState = rememberDismissState(
                                    positionalThreshold = { totalDistance -> totalDistance * 0.4f },
                                    confirmValueChange = { dismissValue ->
                                        when(dismissValue){
                                            DismissValue.DismissedToEnd->{
                                                scope.launch {
                                                    try{
                                                        viewModel.deleteNote(note)
                                                        true
                                                    }catch (e: Exception){
                                                        false
                                                    }
                                                }
                                                true
                                            }
                                            DismissValue.DismissedToStart -> false
                                            DismissValue.Default -> false
                                        }

                                    }
                                )
                                SwipeToDismiss(
                                    state = dismissState,
                                    directions = setOf(DismissDirection.StartToEnd),
                                    background = {

                                        val color = Color(0xff1E1E1E)

                                        Box(modifier = Modifier
                                            .padding(vertical = 8.dp)
                                            .clip(RoundedCornerShape(8.dp))
                                            .fillMaxSize()
                                            .background(color),
                                            contentAlignment = Alignment.CenterStart){
                                            if (dismissState.dismissDirection == DismissDirection.StartToEnd){Icon(imageVector = Delete, contentDescription ="",modifier = Modifier.padding(start = 16.dp),tint = bgColor ) }
                                        }
                                    },
                                    dismissContent = {

                                        NotesItemUI(

                                            id = note.id,
                                            title = note.noteName,
                                            text = note.noteText,
                                            onDelete = {
                                                viewModel.deleteNote(note)
                                            },
                                            navController = navController,
                                            isDark = isDark,
                                            isPinned = note.isPinned,
                                            onEdit = {
                                                viewModel.isPinned = !viewModel.isPinned
                                                viewModel.updateNote(
                                                    NotesData(
                                                        id = note.id,
                                                        noteName = note.noteName,
                                                        noteText = note.noteText,
                                                        isPinned = viewModel.isPinned
                                                    )
                                                )
                                            }
                                        )
                                    }
                                )
                            }
                        }
                    }
                    val unpinnedNotes = noteList.value.filterNot { it.isPinned }
                    if (unpinnedNotes.isNotEmpty()) {
                        item {
                            Text(
                                text = "Others",
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                color = txtColor,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)
                            )
                        }
                        items(unpinnedNotes,key = {note-> note.id}){note->


                            val dismissState = rememberDismissState(
                                positionalThreshold = { totalDistance -> totalDistance * 0.4f },
                                confirmValueChange = { dismissValue ->
                                    when(dismissValue){
                                        DismissValue.DismissedToEnd->{
                                            scope.launch {
                                                try{
                                                    viewModel.deleteNote(note)
                                                    true
                                                }catch (e: Exception){
                                                    false
                                                }
                                            }
                                            true
                                        }
                                        DismissValue.DismissedToStart -> false
                                        DismissValue.Default -> false
                                    }

                                }
                            )
                            SwipeToDismiss(
                                state = dismissState,
                                directions = setOf(DismissDirection.StartToEnd),
                                background = {

                                    val color = Color(0xff1E1E1E)

                                    Box(modifier = Modifier
                                        .padding(vertical = 8.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                        .fillMaxSize()
                                        .background(color),
                                        contentAlignment = Alignment.CenterStart){
                                        if (dismissState.dismissDirection == DismissDirection.StartToEnd){Icon(imageVector = Delete, contentDescription ="",modifier = Modifier.padding(start = 16.dp),tint = bgColor ) }
                                    }
                                },
                                dismissContent = {

                                    NotesItemUI(

                                        id = note.id,
                                        title = note.noteName,
                                        text = note.noteText,
                                        onDelete = {
                                            viewModel.deleteNote(note)
                                        },
                                        navController = navController,
                                        isDark = isDark,
                                        isPinned = note.isPinned,
                                        onEdit = {
                                            viewModel.isPinned = !viewModel.isPinned
                                            viewModel.updateNote(
                                                NotesData(
                                                    id = note.id,
                                                    noteName = note.noteName,
                                                    noteText = note.noteText,
                                                    isPinned = viewModel.isPinned
                                                )
                                            )
                                        }
                                    )
                                }
                            )
                        }
                    }


                }


                //all
                /*Box(modifier = Modifier
                    .padding(horizontal = 8.dp)
                ) {
                    Text(
                        text = "All Notes",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = txtColor,
                    )
                }*/

                 /*LazyColumn(
                    //verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier
                        // .fillMaxSize()
                        .padding(horizontal = 8.dp, vertical = 8.dp)
                        //.nestedScroll(scrollBehavior.nestedScrollConnection),

                    //contentPadding = paddingValues,

                    ) {
                    items(noteList.value,key= {note-> note.id}) { note ->




                        val dismissState = rememberDismissState(
                            positionalThreshold = { totalDistance -> totalDistance * 0.4f },
                            confirmValueChange = { dismissValue ->
                                when(dismissValue){
                                    DismissValue.DismissedToEnd->{
                                        scope.launch {
                                            try{
                                                viewModel.deleteNote(note)
                                                true
                                            }catch (e: Exception){
                                                false
                                            }
                                        }
                                        true
                                    }
                                    DismissValue.DismissedToStart -> false
                                    DismissValue.Default -> false
                                }

                            }
                        )
                        SwipeToDismiss(
                            state = dismissState,
                            directions = setOf(DismissDirection.StartToEnd),
                            background = {

                                val color = Color(0xff1E1E1E)
                                
                                Box(modifier = Modifier
                                    .padding(vertical = 8.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .fillMaxSize()
                                    .background(color),
                                    contentAlignment = Alignment.CenterStart){
                                    if (dismissState.dismissDirection == DismissDirection.StartToEnd){Icon(imageVector = Delete, contentDescription ="",modifier = Modifier.padding(start = 16.dp),tint = bgColor ) }
                                }
                            },
                            dismissContent = {

                                NotesItemUI(

                                    id = note.id,
                                    title = note.noteName,
                                    text = note.noteText,
                                    onDelete = {
                                        viewModel.deleteNote(note)
                                    },
                                    navController = navController,
                                    isDark = isDark,
                                    isPinned = note.isPinned,
                                    onEdit = {
                                        viewModel.isPinned = !viewModel.isPinned
                                        viewModel.updateNote(
                                            NotesData(
                                                id = note.id,
                                                noteName = note.noteName,
                                                noteText = note.noteText,
                                                isPinned = viewModel.isPinned
                                            )
                                        )
                                    }


                                )

                            })
                    }
                }*/
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
            }
        },








    )
    

}






@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NotesItemUI(


    id: Int,
    title: String,
    text: String,
    onDelete: () -> Unit,
    navController: NavHostController,
    isDark: Boolean,
    isPinned: Boolean,
    onEdit:()-> Unit


) {
    val tileColor = if(!isDark){
        Color(0xFFC7EBB3)
    } else  Color(0xff1E1E1E)

    val boxExpanded by remember{ mutableStateOf(false) }



    //var visible by remember{ mutableStateOf(false) }
    //val boxHeight by animateDpAsState(targetValue = if (boxExpanded) Dp.Unspecified else Dp.Unspecified,
       // label = "Box Height Animation" )//dynamic box height

    Box(modifier = Modifier
        .padding(vertical = 8.dp)
        .clip(RoundedCornerShape(8.dp))
        .background(tileColor)
        //.border(width = 0.5.dp, color = Color.LightGray)
        .fillMaxWidth()
        .animateContentSize()
        .wrapContentHeight()
        /*.clickable {
            if (id != -1) {
                navController.navigate("editNote/${id}")
            } //boxExpanded = !boxExpanded


        }*/
        .pointerInput(Unit) {
            detectTapGestures(
                onTap = {
                    if (id != -1) {
                        navController.navigate("editNote/${id}")
                    } //boxExpanded = !boxExpanded
                }
            )

        }


    ){

        var expanded by remember{ mutableStateOf(false) }

        Row(
            Modifier.fillMaxWidth()
        ){


            Column(
                Modifier
                    .weight(0.9f)
                    .padding(top = 32.dp, start = 24.dp, bottom = 32.dp),
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
                    maxLines = if (boxExpanded) Int.MAX_VALUE else 6,
                    overflow = TextOverflow.Ellipsis
                    )
            }


            Column(
                Modifier
                    .weight(0.1f)
                    .padding(top = 36.dp, end = 8.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
                ) {


                Box {
                    /*IconButton(onClick = { onEdit() }, modifier = Modifier.fillMaxSize()) {
                        Icon(imageVector = if (!isPinned) {
                            Bookmark
                        }else{
                             BookmarkFilled
                             }, contentDescription = "")
                    }*/
                    Box(modifier = Modifier.clickable { onEdit() }.wrapContentSize(), contentAlignment = Alignment.Center){
                        if (!isPinned){
                            Icon(imageVector = Bookmark, contentDescription = "", tint =Color(0xff111111) )
                        }else{
                            Icon(imageVector = BookmarkFilled, contentDescription = "",Modifier.size(20.dp),tint =Color(0xff111111))
                        }
                    }
                }
                //used a box because menu rendered on top of icon button
                /*Box {
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
                }8*/
            }

        }

    }

}

@Composable
@Preview
fun NotesUIPreview(){
    NotesUI(viewModel = NoteViewModel(), navController = rememberNavController())
}







