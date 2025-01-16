package com.example.shoppinglist

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shoppinglist.ui.theme.customFont
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch
import java.util.concurrent.Flow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Categories(viewModel: NoteViewModel){


    val colors = listOf(Color(0xFFC7EBB3),Color(0xFFa2e494),Color(0xFFe7efda),Color(0xFFcadbb7))
    viewModel.categoryName = ""
    val systemUIController = rememberSystemUiController()
    val categoryList = viewModel.getAllCategories.collectAsState(initial = listOf())
    var isEnabled by remember{ mutableStateOf(false) }

    if (isEnabled){
        AlertDialog(
            onDismissRequest = { isEnabled = false
                               viewModel.categoryName = ""},
            confirmButton = {
                Button(onClick = {
                    viewModel.addCategory(
                        Category(categoryName = viewModel.categoryName)
                    )
                    viewModel.categoryName = ""
                }
                ) {
                Text(text = "Add Category")
            } },
            text = {
                OutlinedTextField(value = viewModel.categoryName, onValueChange ={viewModel.categoryName = it})
            }
            )
    }

    LaunchedEffect(Unit){
        systemUIController.setStatusBarColor(
            color = Color(0xffFCF6F1),
            darkIcons = true
        )}



    Scaffold (

        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(Color(0xffFCF6F1)),

                navigationIcon = {
                    Box(modifier = Modifier
                        .padding(8.dp)
                        .clip(CircleShape)
                        .background(Color(0xff111111))
                    ){
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = "", tint = Color.White)
                        }
                    }
                },

                title = {
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        //.padding(end = 8.dp)
                        .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ){
                        //Text(text = "All Categories ")
                        Box(contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .padding(vertical = 8.dp)
                                .height(40.dp)
                                .wrapContentWidth()
                                .clip(RoundedCornerShape(24.dp))
                                .background(Color(0xff111111))
                                .clickable { isEnabled = !isEnabled }
                        ) {
                            Row(modifier = Modifier.padding(start = 8.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ){
                                Text(text = "category", fontFamily = customFont, fontWeight = FontWeight.Bold, fontSize = 12.sp, color = Color.White)
                                Box(contentAlignment = Alignment.Center,
                                   modifier = Modifier
                                       .padding(4.dp)
                                       .clip(CircleShape)
                                       .size(30.dp)
                                       .background(Color(0xff323430))
                                ){ Icon(imageVector = Icons.Rounded.Add, contentDescription = "", tint = Color(0xffFCF6F1)) }
                            }
                        }
                    }
                }
            
            )
        },




        content = {
            paddingValues ->

            Column(
                Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(Color(0xffFCF6F1))
            ) {

                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 160.dp),
                    Modifier.padding(horizontal = 8.dp, vertical = 8.dp)
                ){
                    items(categoryList.value){category->
                        CategoryItem(
                            id = category.categoryId,
                            text = category.categoryName,
                            color = colors.random()
                        )

                    }
                }
            }
        }

    )
}

@Composable
fun CategoryItem(
    id:Int,
    text: String,
    color: Color
){

    Column(
        modifier = Modifier
            .padding(horizontal = 4.dp, vertical = 4.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(color)
            //.border(width = 0.5.dp, color = Color.LightGray)
            .fillMaxWidth()
            //.animateContentSize()
            //.defaultMinSize(minHeight = 140.dp)
            .wrapContentHeight(),
        verticalArrangement = Arrangement.Bottom
    ) {
        IconButton(onClick = { /*TODO*/ },Modifier.align(Alignment.End)) {
            Icon(imageVector = Icons.Rounded.MoreVert, contentDescription = "")
        }
        Spacer(modifier = Modifier.height(100.dp))
        Text(
            text = text,
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            lineHeight = 40.sp,
            modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 4.dp)
        )
    }
    
}