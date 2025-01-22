package com.example.shoppinglist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.shoppinglist.ui.theme.AppTheme
import com.example.shoppinglist.ui.theme.customFont

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: NoteViewModel, navController: NavHostController){

    val isDarkMode by viewModel.isDarkMode.collectAsState(initial = false)

    val searchValue = mutableStateOf("")
    val scrollState = rememberScrollState()
    val noteList = viewModel.getRecentNotes.collectAsState(initial = listOf())

    AppTheme(darkTheme = isDarkMode) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Scribbl",
                            fontWeight = FontWeight.Bold,
                            fontFamily = customFont
                        )
                    },
                    actions = {
                        Icon(imageVector = Icons.Rounded.Settings, contentDescription = "")
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xffFCF6F1))

                )
            },

            content = { paddingValues ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .verticalScroll(scrollState)
                        .background(Color(0xffFCF6F1))
                ) {

                    Row(Modifier.padding(horizontal = 8.dp)) {
                        Box(modifier = Modifier
                            .clickable { navController.navigate("home") }
                            .clip(RoundedCornerShape(8.dp))
                            .height(200.dp)
                            .weight(.5f)
                            .background(Color(0xFF667558)),
                            contentAlignment = Alignment.BottomStart) {
                            Text(
                                text = "Notes",
                                modifier = Modifier.padding(start = 8.dp, bottom = 4.dp),
                                fontWeight = FontWeight.Bold,
                                fontFamily = customFont,
                                fontSize = 30.sp
                            )
                        }

                        Spacer(modifier = Modifier.width(4.dp))

                        Column(Modifier.weight(0.5f)) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .fillMaxWidth()
                                    .height(100.dp)
                                    .background(Color(0xFFcadbb7)),
                                contentAlignment = Alignment.BottomStart
                            ) {
                                Text(
                                    text = "Categories",
                                    modifier = Modifier.padding(start = 8.dp, bottom = 4.dp),
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = customFont,
                                    fontSize = 20.sp
                                )
                            }
                            Spacer(modifier = Modifier.height(4.dp))

                            Row {
                                Box(
                                    Modifier
                                        .clip(RoundedCornerShape(8.dp))
                                        .weight(0.5f)
                                        .aspectRatio(1f)
                                        .background(Color.Yellow),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Rounded.Favorite,
                                        contentDescription = "",
                                        modifier = Modifier.size(32.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.width(4.dp))
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(8.dp))
                                        .weight(0.5f)
                                        .aspectRatio(1f)
                                        .background(Color.Red),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Delete,
                                        contentDescription = "",
                                        modifier = Modifier.size(32.dp)
                                    )
                                }
                            }
                        }
                    }
                    Text(
                        text = "Recently Added",
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .padding(top = 16.dp)
                            .padding(bottom = 8.dp),
                        fontWeight = FontWeight.Bold,
                        fontFamily = customFont,
                        fontSize = 24.sp
                    )

                    Column(Modifier.padding(horizontal = 8.dp)) {
                        noteList.value.forEach { notes ->
                            NotesItemUI(
                                id = notes.id,
                                title = notes.noteName,
                                text = notes.noteText,
                                onDelete = { /*TODO*/ },
                                navController = navController,
                                isPinned = notes.isPinned,
                                onEdit = {}
                            )
                        }
                    }

                }
            }
        )
    }
}