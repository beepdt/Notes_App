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
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.shoppinglist.ui.theme.customFont
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch

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
val Bookmark: ImageVector
    get() {
        if (_Bookmark != null) {
            return _Bookmark!!
        }
        _Bookmark = ImageVector.Builder(
            name = "Bookmark",
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
                moveTo(200f, 840f)
                verticalLineToRelative(-640f)
                quadToRelative(0f, -33f, 23.5f, -56.5f)
                reflectiveQuadTo(280f, 120f)
                horizontalLineToRelative(400f)
                quadToRelative(33f, 0f, 56.5f, 23.5f)
                reflectiveQuadTo(760f, 200f)
                verticalLineToRelative(640f)
                lineTo(480f, 720f)
                close()
                moveToRelative(80f, -122f)
                lineToRelative(200f, -86f)
                lineToRelative(200f, 86f)
                verticalLineToRelative(-518f)
                horizontalLineTo(280f)
                close()
                moveToRelative(0f, -518f)
                horizontalLineToRelative(400f)
                close()
            }
        }.build()
        return _Bookmark!!
    }
val BookmarkFilled: ImageVector
    get() {
        if (_BookmarkFilled != null) {
            return _BookmarkFilled!!
        }
        _BookmarkFilled = ImageVector.Builder(
            name = "BookmarkFilled",
            defaultWidth = 15.dp,
            defaultHeight = 15.dp,
            viewportWidth = 15f,
            viewportHeight = 15f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000)),
                fillAlpha = 1.0f,
                stroke = null,
                strokeAlpha = 1.0f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(3.5f, 2f)
                curveTo(3.2239f, 2f, 3f, 2.2239f, 3f, 2.5f)
                verticalLineTo(13.5f)
                curveTo(3f, 13.6818f, 3.0986f, 13.8492f, 3.2576f, 13.9373f)
                curveTo(3.4166f, 14.0254f, 3.6109f, 14.0203f, 3.765f, 13.924f)
                lineTo(7.5f, 11.5896f)
                lineTo(11.235f, 13.924f)
                curveTo(11.3891f, 14.0203f, 11.5834f, 14.0254f, 11.7424f, 13.9373f)
                curveTo(11.9014f, 13.8492f, 12f, 13.6818f, 12f, 13.5f)
                verticalLineTo(2.5f)
                curveTo(12f, 2.2239f, 11.7761f, 2f, 11.5f, 2f)
                horizontalLineTo(3.5f)
                close()
            }
        }.build()
        return _BookmarkFilled!!
    }

private var _BookmarkFilled: ImageVector? = null
private var _Bookmark: ImageVector? = null
private var _Delete: ImageVector? = null

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewNoteScreen(viewModel: NoteViewModel,navController: NavHostController){

    val systemUIController = rememberSystemUiController()
    val statusBarColor = Color(0xffFCF6F1)

   // var noteName by remember{ mutableStateOf("")}
    //var noteText by remember { mutableStateOf("") }

    val scrollBehaviour = TopAppBarDefaults.pinnedScrollBehavior()
    val scrollState = rememberScrollState()
    //val context = LocalContext.current
    val scope = rememberCoroutineScope()
    //val scaffoldState = rememberBottomSheetScaffoldState()
    val snackMes = remember{ mutableStateOf("") }
    val snackbarHostState = remember{SnackbarHostState()}

    viewModel.noteName =""
    viewModel.noteText = ""

    LaunchedEffect(Unit){
    systemUIController.setStatusBarColor(
        color = statusBarColor,
        darkIcons = true
    )}

    Scaffold (
        snackbarHost = { SnackbarHost(snackbarHostState) },

        topBar = {
            TopAppBar(

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = statusBarColor,
                ),

                navigationIcon = {
                    Box(modifier = Modifier
                        .padding(8.dp)
                        .clip(CircleShape)
                        .background(Color(0xff111111))
                    ) {
                        IconButton(onClick = { navController.popBackStack()}) {
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
        //containerColor = statusBarColor,

        content = {
            paddingValues ->
            Column(modifier = Modifier
                .background(statusBarColor)
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(scrollState)
            ) {




                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(top = 16.dp, start = 8.dp, end = 8.dp),
                    value = viewModel.noteName,
                    onValueChange = {
                        viewModel.noteName = it},
                    //singleLine = true,
                    textStyle = TextStyle(fontSize = 32.sp, fontWeight = FontWeight.Bold, fontFamily = customFont),
                    placeholder = { Text(text = "Title", fontWeight = FontWeight.Normal)},
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
                        .padding(8.dp),
                    value = viewModel.noteText,
                    onValueChange = {viewModel.noteText = it},
                    textStyle = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Normal, lineHeight = 24.sp, fontFamily = customFont),
                    placeholder = { Text(text = "Note")},
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

                Spacer(modifier = Modifier.height(80.dp))
            }
        },

        floatingActionButton = {



            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.BottomCenter)
                    .padding(start = 48.dp)
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
                            .width(150.dp)
                            //.weight(0.5f)
                            .clip(RoundedCornerShape(40.dp))
                            .background(Color(0xFFC7EBB3))
                            .clickable {
                                if (viewModel.noteText.isNotEmpty()) {
                                    viewModel.addNote(
                                        NotesData(
                                            noteName = viewModel.noteName.trim(),
                                            noteText = viewModel.noteText.trim()
                                        )
                                    )
                                    viewModel.noteName = ""
                                    viewModel.noteText = ""
                                    scope.launch {

                                        navController.popBackStack()
                                    }
                                } else {
                                    snackMes.value = "Empty Note"
                                }
                                scope.launch { snackbarHostState.showSnackbar(snackMes.value) }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text( text = "Save",
                            color = Color(0xFF111111), // Text color for "Save"
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal)
                    }

                    //reset
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
                                viewModel.noteName = ""
                                viewModel.noteText = ""

                            },
                        contentAlignment = Alignment.Center
                    )
                    {
                        Icon(imageVector = Icons.Outlined.Refresh, contentDescription = "", tint = statusBarColor)
                    }

                    //edit
                }
            }




        },



    )



}



@Composable
@Preview
fun NewNoteScreenPreview(){
    NewNoteScreen(viewModel = NoteViewModel(), navController = rememberNavController())
}


