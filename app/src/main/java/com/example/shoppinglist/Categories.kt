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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.shoppinglist.ui.theme.AppTheme
import com.example.shoppinglist.ui.theme.customFont
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Categories(viewModel: NoteViewModel,navController: NavHostController){


    val isDarkMode by viewModel.isDarkMode.collectAsState(initial = false)
    //val colors = listOf(Color(0xFFC7EBB3),Color(0xFFa2e494),Color(0xFFe7efda),Color(0xFFcadbb7))
    viewModel.categoryName = ""
    rememberSystemUiController()
    val categoryList = viewModel.getAllCategories.collectAsState(initial = listOf())
    var isEnabled by remember{ mutableStateOf(false) }

    if (isEnabled){
        AlertDialog(
            shape = RoundedCornerShape(8.dp),
            containerColor = MaterialTheme.colorScheme.primary,
            onDismissRequest = { isEnabled = false
                               viewModel.categoryName = ""},
            confirmButton = {
                Button(
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
                    onClick = {
                    viewModel.addCategory(
                        Category(categoryName = viewModel.categoryName)
                    )
                    viewModel.categoryName = ""
                    isEnabled = false
                }
                ) {
                Text(text = "Add Category",color = MaterialTheme.colorScheme.background)
            } },
            text = {
                OutlinedTextField(
                    value = viewModel.categoryName,
                    onValueChange ={viewModel.categoryName = it},
                    placeholder = { Text(text = "Category")},
                    singleLine = true,
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
            }
            )
    }





        Scaffold(

            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.background),

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
                                    contentDescription = "",
                                    tint = MaterialTheme.colorScheme.background
                                )
                            }
                        }
                    },

                    title = {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                //.padding(end = 8.dp)
                                .padding(vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.End
                        ) {
                            //Text(text = "All Categories ")
                            Box(contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .padding(vertical = 8.dp)
                                    .height(40.dp)
                                    .wrapContentWidth()
                                    .clip(RoundedCornerShape(24.dp))
                                    .background(MaterialTheme.colorScheme.secondary)
                                    .clickable { isEnabled = !isEnabled }
                            ) {
                                Row(
                                    modifier = Modifier.padding(start = 8.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        text = "category",
                                        fontFamily = customFont,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 12.sp,
                                        color = MaterialTheme.colorScheme.background
                                    )
                                    Box(
                                        contentAlignment = Alignment.Center,
                                        modifier = Modifier
                                            .padding(4.dp)
                                            .clip(CircleShape)
                                            .size(30.dp)
                                            .background(Color(0xff47A025))
                                    ) {
                                        Icon(
                                            imageVector = Icons.Rounded.Add,
                                            contentDescription = "",
                                            tint = MaterialTheme.colorScheme.background
                                        )
                                    }
                                }
                            }
                        }
                    }

                )
            },


            content = { paddingValues ->

                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .background(MaterialTheme.colorScheme.background)
                ) {

                    LazyVerticalGrid(
                        columns = GridCells.Adaptive(minSize = 160.dp),
                        Modifier.padding(horizontal = 8.dp, vertical = 8.dp)
                    ) {
                        items(categoryList.value,key = {category-> category.categoryId}) { category ->
                            CategoryItem(
                                navController = navController,
                                id = category.categoryId,
                                text = category.categoryName,
                                onDelete = {
                                    viewModel.deleteCategory(category)
                                }
                            )

                        }
                    }
                }
            }

        )

}

@Composable
fun CategoryItem(
    navController: NavHostController,
    id:Int,
    text: String,
    onDelete: ()-> Unit
){

    Column(
        modifier = Modifier
            .clickable { navController.navigate("categoryNotes/${id}/${text}") }
            .padding(horizontal = 4.dp, vertical = 4.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.primary)
            //.border(width = 0.5.dp, color = Color.LightGray)
            .fillMaxWidth()
            //.animateContentSize()
            //.defaultMinSize(minHeight = 140.dp)
            .wrapContentHeight(),
        verticalArrangement = Arrangement.Bottom
    ) {
        IconButton(onClick = { onDelete() },Modifier.align(Alignment.End)) {
            Icon(imageVector = Delete, contentDescription = "", tint = MaterialTheme.colorScheme.secondary)
        }
        Spacer(modifier = Modifier.height(100.dp))
        Text(
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            text = text,
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            lineHeight = 40.sp,
            modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 4.dp),
            color = MaterialTheme.colorScheme.secondary
        )
    }
    
}