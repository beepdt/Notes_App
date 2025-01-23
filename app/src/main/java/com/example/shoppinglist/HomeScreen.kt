package com.example.shoppinglist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.shoppinglist.ui.theme.AppTheme
import com.example.shoppinglist.ui.theme.customFont
import kotlinx.coroutines.launch

public val Light_mode: ImageVector
    get() {
        if (_Light_mode != null) {
            return _Light_mode!!
        }
        _Light_mode = ImageVector.Builder(
            name = "Light_mode",
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
                moveTo(480f, 600f)
                quadToRelative(50f, 0f, 85f, -35f)
                reflectiveQuadToRelative(35f, -85f)
                reflectiveQuadToRelative(-35f, -85f)
                reflectiveQuadToRelative(-85f, -35f)
                reflectiveQuadToRelative(-85f, 35f)
                reflectiveQuadToRelative(-35f, 85f)
                reflectiveQuadToRelative(35f, 85f)
                reflectiveQuadToRelative(85f, 35f)
                moveToRelative(0f, 80f)
                quadToRelative(-83f, 0f, -141.5f, -58.5f)
                reflectiveQuadTo(280f, 480f)
                reflectiveQuadToRelative(58.5f, -141.5f)
                reflectiveQuadTo(480f, 280f)
                reflectiveQuadToRelative(141.5f, 58.5f)
                reflectiveQuadTo(680f, 480f)
                reflectiveQuadToRelative(-58.5f, 141.5f)
                reflectiveQuadTo(480f, 680f)
                moveTo(200f, 520f)
                horizontalLineTo(40f)
                verticalLineToRelative(-80f)
                horizontalLineToRelative(160f)
                close()
                moveToRelative(720f, 0f)
                horizontalLineTo(760f)
                verticalLineToRelative(-80f)
                horizontalLineToRelative(160f)
                close()
                moveTo(440f, 200f)
                verticalLineToRelative(-160f)
                horizontalLineToRelative(80f)
                verticalLineToRelative(160f)
                close()
                moveToRelative(0f, 720f)
                verticalLineToRelative(-160f)
                horizontalLineToRelative(80f)
                verticalLineToRelative(160f)
                close()
                moveTo(256f, 310f)
                lineToRelative(-101f, -97f)
                lineToRelative(57f, -59f)
                lineToRelative(96f, 100f)
                close()
                moveToRelative(492f, 496f)
                lineToRelative(-97f, -101f)
                lineToRelative(53f, -55f)
                lineToRelative(101f, 97f)
                close()
                moveToRelative(-98f, -550f)
                lineToRelative(97f, -101f)
                lineToRelative(59f, 57f)
                lineToRelative(-100f, 96f)
                close()
                moveTo(154f, 748f)
                lineToRelative(101f, -97f)
                lineToRelative(55f, 53f)
                lineToRelative(-97f, 101f)
                close()
                moveToRelative(326f, -268f)
            }
        }.build()
        return _Light_mode!!
    }

private var _Light_mode: ImageVector? = null

public val Dark_mode: ImageVector
    get() {
        if (_Dark_mode != null) {
            return _Dark_mode!!
        }
        _Dark_mode = ImageVector.Builder(
            name = "Dark_mode",
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
                moveTo(480f, 840f)
                quadToRelative(-150f, 0f, -255f, -105f)
                reflectiveQuadTo(120f, 480f)
                reflectiveQuadToRelative(105f, -255f)
                reflectiveQuadToRelative(255f, -105f)
                quadToRelative(14f, 0f, 27.5f, 1f)
                reflectiveQuadToRelative(26.5f, 3f)
                quadToRelative(-41f, 29f, -65.5f, 75.5f)
                reflectiveQuadTo(444f, 300f)
                quadToRelative(0f, 90f, 63f, 153f)
                reflectiveQuadToRelative(153f, 63f)
                quadToRelative(55f, 0f, 101f, -24.5f)
                reflectiveQuadToRelative(75f, -65.5f)
                quadToRelative(2f, 13f, 3f, 26.5f)
                reflectiveQuadToRelative(1f, 27.5f)
                quadToRelative(0f, 150f, -105f, 255f)
                reflectiveQuadTo(480f, 840f)
                moveToRelative(0f, -80f)
                quadToRelative(88f, 0f, 158f, -48.5f)
                reflectiveQuadTo(740f, 585f)
                quadToRelative(-20f, 5f, -40f, 8f)
                reflectiveQuadToRelative(-40f, 3f)
                quadToRelative(-123f, 0f, -209.5f, -86.5f)
                reflectiveQuadTo(364f, 300f)
                quadToRelative(0f, -20f, 3f, -40f)
                reflectiveQuadToRelative(8f, -40f)
                quadToRelative(-78f, 32f, -126.5f, 102f)
                reflectiveQuadTo(200f, 480f)
                quadToRelative(0f, 116f, 82f, 198f)
                reflectiveQuadToRelative(198f, 82f)
                moveToRelative(-10f, -270f)
            }
        }.build()
        return _Dark_mode!!
    }

private var _Dark_mode: ImageVector? = null


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: NoteViewModel, navController: NavHostController){

    val isDarkMode by viewModel.isDarkMode.collectAsState(initial = isSystemInDarkTheme())
    val scope = rememberCoroutineScope()

    val searchValue = mutableStateOf("")
    val scrollState = rememberScrollState()
    val noteList = viewModel.getRecentNotes.collectAsState(initial = listOf())


        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Scribbl",
                            fontWeight = FontWeight.Bold,
                            fontFamily = customFont,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    },
                    actions = {
                        Switch(
                            modifier = Modifier.padding(horizontal = 8.dp),
                            checked = isDarkMode,
                            onCheckedChange = {
                                scope.launch {
                                    viewModel.toggleTheme()
                                }
                            },
                            thumbContent = {
                                           if (isDarkMode){
                                               Icon(imageVector = Dark_mode, contentDescription = "", tint = MaterialTheme.colorScheme.secondary, modifier = Modifier.size(40.dp))
                                           }else{
                                               Icon(imageVector = Light_mode, contentDescription = "", tint = MaterialTheme.colorScheme.secondary)
                                           }
                            },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = Color.Transparent,
                                checkedTrackColor = MaterialTheme.colorScheme.primary,
                                uncheckedThumbColor = Color.Transparent,
                                uncheckedTrackColor = Color.LightGray,
                                checkedBorderColor = MaterialTheme.colorScheme.background,
                                uncheckedBorderColor = MaterialTheme.colorScheme.background
                            )
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background)

                )
            },

            content = { paddingValues ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .verticalScroll(scrollState)
                        .background(MaterialTheme.colorScheme.background)
                ) {

                    Row(Modifier.padding(horizontal = 8.dp)) {
                        Box(modifier = Modifier
                            .clickable { navController.navigate("allNotes") }
                            .clip(RoundedCornerShape(8.dp))
                            .height(200.dp)
                            .weight(.5f)
                            .background(Color(0xff1B3022)),
                            contentAlignment = Alignment.BottomStart) {
                            Text(
                                text = "Notes",
                                modifier = Modifier.padding(start = 8.dp, bottom = 4.dp),
                                fontWeight = FontWeight.Bold,
                                fontFamily = customFont,
                                fontSize = 30.sp,
                                color = Color(0xffFCF6F1)
                            )
                        }

                        Spacer(modifier = Modifier.width(4.dp))

                        Column(Modifier.weight(0.5f)) {
                            Box(
                                modifier = Modifier
                                    .clickable { navController.navigate("allCategories") }
                                    .clip(RoundedCornerShape(8.dp))
                                    .fillMaxWidth()
                                    .height(100.dp)
                                    .background(Color(0xff47A025)),
                                contentAlignment = Alignment.BottomStart
                            ) {
                                Text(
                                    text = "Categories",
                                    modifier = Modifier.padding(start = 8.dp, bottom = 4.dp),
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = customFont,
                                    fontSize = 20.sp,
                                    color = Color(0xffFCF6F1)
                                )
                            }
                            Spacer(modifier = Modifier.height(4.dp))

                            Row {
                                Box(
                                    Modifier
                                        .clickable { navController.navigate("favScreen") }
                                        .clip(RoundedCornerShape(8.dp))
                                        .weight(0.5f)
                                        .height(96.dp)
                                        .aspectRatio(1f)
                                        .background(Color(0xff2C423F)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = BookmarkFilled,
                                        contentDescription = "",
                                        modifier = Modifier.size(32.dp),
                                        tint = MaterialTheme.colorScheme.background
                                    )
                                }
                                Spacer(modifier = Modifier.width(4.dp))
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(8.dp))
                                        .weight(0.5f)
                                        .height(96.dp)
                                        .aspectRatio(1f)
                                        .background(Color(0xff1B3022)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Delete,
                                        contentDescription = "",
                                        modifier = Modifier.size(32.dp),
                                        tint = Color(0xffFCF6F1)
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
                        fontSize = 24.sp,
                        color = MaterialTheme.colorScheme.secondary
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
                                onEdit = {},
                                showPin = false
                            )
                        }
                    }

                }
            }
        )

}