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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val Delete: ImageVector
    get() {
        if (_Delete != null) {
            return _Delete!!
        }
        _Delete = ImageVector.Builder(
            name = "Delete",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 960f,
            viewportHeight = 960f
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                fillAlpha = 1.0f,
                stroke = null,
                strokeAlpha = 1.0f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(280f, 840f)
                quadToRelative(-33f, 0f, -56.5f, -23.5f)
                reflectiveQuadTo(200f, 760f)
                verticalLineToRelative(-520f)
                horizontalLineToRelative(-40f)
                verticalLineToRelative(-80f)
                horizontalLineToRelative(200f)
                verticalLineToRelative(-40f)
                horizontalLineToRelative(240f)
                verticalLineToRelative(40f)
                horizontalLineToRelative(200f)
                verticalLineToRelative(80f)
                horizontalLineToRelative(-40f)
                verticalLineToRelative(520f)
                quadToRelative(0f, 33f, -23.5f, 56.5f)
                reflectiveQuadTo(680f, 840f)
                close()
                moveToRelative(400f, -600f)
                horizontalLineTo(280f)
                verticalLineToRelative(520f)
                horizontalLineToRelative(400f)
                close()
                moveTo(360f, 680f)
                horizontalLineToRelative(80f)
                verticalLineToRelative(-360f)
                horizontalLineToRelative(-80f)
                close()
                moveToRelative(160f, 0f)
                horizontalLineToRelative(80f)
                verticalLineToRelative(-360f)
                horizontalLineToRelative(-80f)
                close()
                moveTo(280f, 240f)
                verticalLineToRelative(520f)
                close()
            }
        }.build()
        return _Delete!!
    }

private var _Delete: ImageVector? = null

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewNoteScreen(viewModel: NotesViewModel){


    var noteName by remember{ mutableStateOf("")}
    var noteText by remember { mutableStateOf("") }
    val scrollBehaviour = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold (
        
        topBar = {
            TopAppBar(

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xffFCF6F1),
                ),

                navigationIcon = {
                    Box(modifier = Modifier.clip(CircleShape).background(Color(0xff111111))) {
                        IconButton(onClick = { /*TODO*/ }) {
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
                            text = "new note",
                            fontWeight = FontWeight.Bold,
                            color = Color(0xff111111),
                            fontSize = 16.sp
                        )
                    }
                },



                scrollBehavior = scrollBehaviour
            )
        },

        content = {
            paddingValues ->
            Column(modifier = Modifier
                .padding(paddingValues)
                .background(Color(0xffFCF6F1))
                .fillMaxSize()) {



                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .padding(top = 16.dp, start = 8.dp, end = 8.dp),
                    value = noteName,
                    onValueChange = {noteName = it},
                    singleLine = true,
                    textStyle = TextStyle(fontSize = 30.sp),
                    placeholder = { Text(text = "note title")},
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color(0xff111111),
                        focusedContainerColor = Color.Transparent,
                        focusedPlaceholderColor = Color.Black,
                        focusedIndicatorColor = Color.Black,
                        unfocusedContainerColor = Color.Transparent,
                        unfocusedTextColor = Color.DarkGray,
                        unfocusedPlaceholderColor = Color.DarkGray,
                        unfocusedIndicatorColor = Color.LightGray,
                        cursorColor = Color.DarkGray
                    )


                )
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    value = noteText,
                    onValueChange = {noteText = it},
                    placeholder = { Text(text = "description")},
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
            }
        },


        bottomBar = {
            BottomAppBar (
                containerColor = Color(0xffFCF6F1),
                content = {

                    Row(
                        modifier = Modifier
                            .padding(8.dp)
                            .clickable {
                                viewModel.addNewNote(
                                    noteName = noteName,
                                    noteText = noteText
                                )
                            }
                            .clip(RoundedCornerShape(16.dp))
                            .weight(0.7f)
                            .background(Color(0xffFFD5F8))
                            .fillMaxSize(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Text(text = "Save", textAlign = TextAlign.Center, color = Color(0xff111111), fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    }


                    Row(
                        modifier = Modifier
                            .clickable {
                                noteName = ""
                                noteText = ""
                            }
                            .padding(8.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color(0xff111111))
                            .weight(0.2f)
                            .fillMaxSize(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ){

                            Icon(imageVector = Delete, contentDescription = "", tint = Color(0xffFCF6F1))
                        
                    }

                }
            )
        }
        
    )



}



@Composable
@Preview
fun NewNoteScreenPreview(){
    NewNoteScreen(viewModel = NotesViewModel())
}


