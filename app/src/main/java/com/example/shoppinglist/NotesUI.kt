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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ArrowBack
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
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
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
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.shoppinglist.ui.theme.AppTheme
import com.example.shoppinglist.ui.theme.customFont
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch

public val Sort: ImageVector
    get() {
        if (_Sort != null) {
            return _Sort!!
        }
        _Sort = ImageVector.Builder(
            name = "Sort",
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
                moveTo(120f, 720f)
                verticalLineToRelative(-80f)
                horizontalLineToRelative(240f)
                verticalLineToRelative(80f)
                close()
                moveToRelative(0f, -200f)
                verticalLineToRelative(-80f)
                horizontalLineToRelative(480f)
                verticalLineToRelative(80f)
                close()
                moveToRelative(0f, -200f)
                verticalLineToRelative(-80f)
                horizontalLineToRelative(720f)
                verticalLineToRelative(80f)
                close()
            }
        }.build()
        return _Sort!!
    }

private var _Sort: ImageVector? = null


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesUI(viewModel: NoteViewModel,navController: NavHostController) {

   val isDarkMode by viewModel.isDarkMode.collectAsState(initial = false)
    val scope = rememberCoroutineScope()
    
    
    viewModel.isPinned = false

    //var isClicked by remember { mutableStateOf(false) }



    /*val tileColor = if(!isDark){
        Color(0xFFC7EBB3)
    } else  Color(0xff1E1E1E)*/

    val systemUIController = rememberSystemUiController()

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    rememberScrollState()

    var sortOrder by remember { mutableStateOf(SortOrder.DESCENDING) }
    var sort by remember { mutableStateOf(false) }
    val noteList = when (sortOrder) {
        SortOrder.DESCENDING->viewModel.getAllnotesDesc.collectAsState(initial = listOf())
        SortOrder.ASCENDING->viewModel.getAllnotesAsc.collectAsState(initial = listOf())
    }
    
    var radioButtonDesc by remember{ mutableStateOf(true) }
    var radioButtonAsc by remember{ mutableStateOf(true) }
    if (sortOrder == SortOrder.DESCENDING){
        radioButtonDesc = true
    }
    if (sortOrder == SortOrder.ASCENDING){
        radioButtonAsc = true
    }



   // val notes = viewModel.notesData //observe state of shoppingData data class





    AppTheme(darkTheme = isDarkMode){
        Scaffold(

            topBar = {


                TopAppBar(
                    colors = topAppBarColors(
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
                                text = "all notes.",
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
                                    sort = !sort
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Sort,
                                contentDescription = "",
                                tint = MaterialTheme.colorScheme.background
                            )
                        }

                        DropdownMenu(
                            expanded = sort,
                            onDismissRequest = { sort = false },
                            Modifier.background(MaterialTheme.colorScheme.background)
                        ) {
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = "Descending",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Normal,
                                        fontFamily = customFont,
                                        color = MaterialTheme.colorScheme.secondary
                                    )
                                },
                                trailingIcon = {
                                    Icon(
                                        imageVector = Icons.Rounded.ArrowBack,
                                        modifier = Modifier
                                            .rotate(
                                                270F
                                            )
                                            .size(16.dp),
                                        contentDescription = "",
                                        tint = MaterialTheme.colorScheme.secondary
                                    )
                                },
                                onClick = {
                                    sortOrder = SortOrder.DESCENDING
                                    sort = false
                                }
                            )
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = "Ascending",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Normal,
                                        fontFamily = customFont,
                                        color = MaterialTheme.colorScheme.secondary
                                    )
                                },
                                trailingIcon = {
                                    Icon(
                                        imageVector = Icons.Rounded.ArrowBack,
                                        modifier = Modifier
                                            .rotate(
                                                90F
                                            )
                                            .size(16.dp),
                                        contentDescription = "",
                                        tint = MaterialTheme.colorScheme.secondary
                                    )
                                },
                                onClick = {
                                    sortOrder = SortOrder.ASCENDING
                                    sort = false
                                }
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
                        val pinnedNotes = noteList.value.filter { it.isPinned }
                        when {
                            pinnedNotes.isNotEmpty() -> {
                                item {
                                    Text(
                                        text = "Pinned",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 18.sp,
                                        color = MaterialTheme.colorScheme.secondary,
                                        modifier = Modifier.padding(horizontal = 8.dp)
                                    )
                                }
                                items(pinnedNotes, key = { note -> note.id }) { note ->
                                    val dismissState = rememberDismissState(
                                        positionalThreshold = { totalDistance -> totalDistance * 0.4f },
                                        confirmValueChange = { dismissValue ->
                                            when (dismissValue) {
                                                DismissValue.DismissedToEnd -> {
                                                    scope.launch {
                                                        try {
                                                            viewModel.deleteNote(note)
                                                            true
                                                        } catch (e: Exception) {
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

                                            Box(
                                                modifier = Modifier
                                                    .padding(vertical = 8.dp)
                                                    .clip(RoundedCornerShape(8.dp))
                                                    .fillMaxSize()
                                                    .background(color),
                                                contentAlignment = Alignment.CenterStart
                                            ) {
                                                if (dismissState.dismissDirection == DismissDirection.StartToEnd) {
                                                    Icon(
                                                        imageVector = Delete,
                                                        contentDescription = "",
                                                        modifier = Modifier.padding(start = 16.dp),
                                                        tint = MaterialTheme.colorScheme.background
                                                    )
                                                }
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
                                                showPin = true,
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
                                    color = MaterialTheme.colorScheme.secondary,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)
                                )
                            }
                            items(unpinnedNotes, key = { note -> note.id }) { note ->


                                val dismissState = rememberDismissState(
                                    positionalThreshold = { totalDistance -> totalDistance * 0.4f },
                                    confirmValueChange = { dismissValue ->
                                        when (dismissValue) {
                                            DismissValue.DismissedToEnd -> {
                                                scope.launch {
                                                    try {
                                                        viewModel.deleteNote(note)
                                                        true
                                                    } catch (e: Exception) {
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

                                        Box(
                                            modifier = Modifier
                                                .padding(vertical = 8.dp)
                                                .clip(RoundedCornerShape(8.dp))
                                                .fillMaxSize()
                                                .background(color),
                                            contentAlignment = Alignment.CenterStart
                                        ) {
                                            if (dismissState.dismissDirection == DismissDirection.StartToEnd) {
                                                Icon(
                                                    imageVector = Delete,
                                                    contentDescription = "",
                                                    modifier = Modifier.padding(start = 16.dp),
                                                    tint = MaterialTheme.colorScheme.background
                                                )
                                            }
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
                                            showPin = true,
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
                }
            },


            //for adding new items
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        navController.navigate("new note")
                    },
                    containerColor = MaterialTheme.colorScheme.surface,
                    modifier = Modifier.size(64.dp),
                    shape = CircleShape
                ) {
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.surface)
                            .size(48.dp)
                    )
                    Icon(
                        imageVector = Icons.Rounded.Add,
                        contentDescription = "add new item",
                        tint = MaterialTheme.colorScheme.background,
                        modifier = Modifier.size(24.dp)
                    )
                }
            },
        )
    }
}






@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NotesItemUI(
    id: Int,
    title: String,
    text: String,
    onDelete: () -> Unit,
    navController: NavHostController,
    showPin: Boolean,
    isPinned: Boolean,
    onEdit:()-> Unit


) {


    val boxExpanded by remember{ mutableStateOf(false) }



    //var visible by remember{ mutableStateOf(false) }
    //val boxHeight by animateDpAsState(targetValue = if (boxExpanded) Dp.Unspecified else Dp.Unspecified,
       // label = "Box Height Animation" )//dynamic box height

    Box(modifier = Modifier
        .padding(vertical = 8.dp)
        .clip(RoundedCornerShape(8.dp))
        .background(MaterialTheme.colorScheme.primary)
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
                    color = MaterialTheme.colorScheme.secondary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp,
                    lineHeight = 40.sp)
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = text,
                    color = MaterialTheme.colorScheme.secondary,
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    maxLines = if (boxExpanded) Int.MAX_VALUE else 5,
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

                if (showPin) {
                    Box {
                        Box(modifier = Modifier
                            .clickable { onEdit() }
                            .wrapContentSize(), contentAlignment = Alignment.Center) {
                            if (!isPinned) {
                                Icon(
                                    imageVector = Bookmark,
                                    contentDescription = "",
                                    tint = MaterialTheme.colorScheme.secondary
                                )
                            } else {
                                Icon(
                                    imageVector = BookmarkFilled,
                                    contentDescription = "",
                                    Modifier.size(20.dp),
                                    tint = MaterialTheme.colorScheme.secondary
                                )
                            }
                        }
                    }
                }

            }

        }

    }

}

enum class SortOrder{
    ASCENDING,
    DESCENDING
}









