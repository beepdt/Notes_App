package com.example.shoppinglist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDismissState
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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.shoppinglist.ui.theme.AppTheme
import com.example.shoppinglist.ui.theme.customFont
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryNotesScreen(viewModel: NoteViewModel,navController: NavHostController,categoryid: Int?,categoryName: String?) {

    val isDarkMode by viewModel.isDarkMode.collectAsState(initial = false)
    val scope = rememberCoroutineScope()


    viewModel.isPinned = false

    if (categoryid == null){
        navController.popBackStack()
        return
    }
    if (categoryName == null){
        navController.popBackStack()
        return
    }

    val systemUIController = rememberSystemUiController()

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    rememberScrollState()

    var sortOrder by remember { mutableStateOf(SortOrder.DESCENDING) }
    var sort by remember { mutableStateOf(false) }
    val noteList = viewModel.getNotesByCategory(categoryid).collectAsState(initial = listOf())



    // val notes = viewModel.notesData //observe state of shoppingData data class






        Scaffold(

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
                            IconButton(onClick = {navController.popBackStack() }) {
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
                                .padding(end = 8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center

                        ) {
                            Text(
                                text = categoryName,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.secondary
                            )
                        }
                    },

                    scrollBehavior = scrollBehavior,

                    actions = {
                        Box(
                            modifier = Modifier
                                .padding(8.dp)
                                .size(48.dp)
                                //.border(width = 0.5.dp, color =Color(0xff111111))
                                .clip(CircleShape)
                                //.border(width = 0.5.dp, color =Color(0xff111111))
                                .background(MaterialTheme.colorScheme.secondary)
                                .clickable {
                                    navController.navigate("home")
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Home,
                                contentDescription = "",
                                tint = MaterialTheme.colorScheme.background
                            )
                        }
                    }

                )
            },


            //main body
            content = { paddingValues ->
                Column(
                    Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .padding(paddingValues)
                        .fillMaxSize()
                    //.verticalScroll(rememberScrollState())
                ) {





                    LazyColumn(
                        //verticalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier
                            .padding(horizontal = 8.dp, vertical = 8.dp)
                        //.nestedScroll(scrollBehavior.nestedScrollConnection),

                        //contentPadding = paddingValues,

                    ) {
                        items(noteList.value,key = {note-> note.id}){note->
                            NotesItemUI(
                                id = note.id,
                                title = note.noteName,
                                text = note.noteText,
                                onDelete = { /*TODO*/ },
                                navController = navController,
                                showPin = false,
                                isPinned = note.isPinned,
                                onEdit = {}
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
                    containerColor = Color(0xff1B3022),
                    modifier = Modifier.size(64.dp),
                    shape = CircleShape
                ) {
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(Color(0xff1B3022))
                            .size(48.dp)
                    )
                    Icon(
                        imageVector = Icons.Rounded.Add,
                        contentDescription = "add new item",
                        tint = Color(0xFFDDFFD9),
                        modifier = Modifier.size(24.dp)
                    )
                }
            },
        )

}