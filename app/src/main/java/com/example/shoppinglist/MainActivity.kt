package com.example.shoppinglist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.shoppinglist.ui.theme.AppTheme
import com.example.shoppinglist.ui.theme.dataStore


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        val noteViewModel: NoteViewModel by viewModels{
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return NoteViewModel(dataStore) as T
                }
            }
        }

        super.onCreate(savedInstanceState)
        setContent {

                // A surface container using the 'background' color from the theme

                  MyApp(noteViewModel)


        }
    }
}




@Composable
fun MyApp(noteViewModel: NoteViewModel) {

    val isDarkMode by noteViewModel.isDarkMode.collectAsState(initial = isSystemInDarkTheme())

    val navController = rememberNavController()

    AppTheme(darkTheme = isDarkMode) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            NavHost(
                navController = navController,
                startDestination = "home"
            ) {
                composable(
                    route = "home",
                    enterTransition = {
                        slideInHorizontally(
                            initialOffsetX = { width -> width / 4 },
                            animationSpec = tween(
                                durationMillis = 200,
                                easing = FastOutLinearInEasing
                            )
                        ) + fadeIn(
                            animationSpec = tween(
                                durationMillis = 200,
                                easing = LinearOutSlowInEasing
                            )
                        )
                    },
                    exitTransition = {
                        slideOutHorizontally(
                            targetOffsetX = { width -> -width / 4 },
                            animationSpec = tween(
                                durationMillis = 200,
                                easing = FastOutLinearInEasing
                            )
                        ) + fadeOut(
                            animationSpec = tween(
                                durationMillis = 200,
                                easing = LinearOutSlowInEasing
                            )
                        )
                    }
                ) {
                    HomeScreen(viewModel = noteViewModel, navController)
                }

                composable(
                    route = "new note",
                    enterTransition = {
                        slideInVertically(
                            initialOffsetY = { height -> height / 6 },
                            animationSpec = tween(
                                durationMillis = 200,
                                easing = FastOutLinearInEasing
                            )
                        ) + fadeIn(
                            animationSpec = tween(
                                durationMillis = 200,
                                easing = LinearOutSlowInEasing
                            )
                        )
                    },
                    exitTransition = {
                        slideOutVertically(
                            targetOffsetY = { height -> height / 6 },
                            animationSpec = tween(
                                durationMillis = 200,
                                easing = FastOutLinearInEasing
                            )
                        ) + fadeOut(
                            animationSpec = tween(
                                durationMillis = 200,
                                easing = LinearOutSlowInEasing
                            )
                        )
                    }
                ) {
                    NewNoteScreen(viewModel = noteViewModel, navController)
                }

                composable(
                    route = "editNote/{noteId}",
                    enterTransition = {
                        slideInVertically(
                            initialOffsetY = { height -> height / 6 },
                            animationSpec = tween(
                                durationMillis = 200,
                                easing = FastOutLinearInEasing
                            )
                        ) + fadeIn(
                            animationSpec = tween(
                                durationMillis = 200,
                                easing = LinearOutSlowInEasing
                            )
                        )
                    },
                    exitTransition = {
                        slideOutVertically(
                            targetOffsetY = { height -> height / 6 },
                            animationSpec = tween(
                                durationMillis = 200,
                                easing = FastOutLinearInEasing
                            )
                        ) + fadeOut(
                            animationSpec = tween(
                                durationMillis = 200,
                                easing = LinearOutSlowInEasing
                            )
                        )
                    }
                ) { backStackEntry ->
                    val noteId = backStackEntry.arguments?.getString("noteId")
                    EditNoteScreen(
                        viewModel = noteViewModel,
                        navController = navController,
                        noteId = noteId
                    )
                }

                composable(
                    route = "allNotes",
                    enterTransition = {
                        slideInHorizontally(
                            initialOffsetX = { width -> width / 4 },
                            animationSpec = tween(
                                durationMillis = 200,
                                easing = FastOutLinearInEasing
                            )
                        ) + fadeIn(
                            animationSpec = tween(
                                durationMillis = 200,
                                easing = LinearOutSlowInEasing
                            )
                        )
                    },
                    exitTransition = {
                        slideOutHorizontally(
                            targetOffsetX = { width -> -width / 4 },
                            animationSpec = tween(
                                durationMillis = 200,
                                easing = FastOutLinearInEasing
                            )
                        ) + fadeOut(
                            animationSpec = tween(
                                durationMillis = 200,
                                easing = LinearOutSlowInEasing
                            )
                        )
                    }
                ) {
                    NotesUI(viewModel = noteViewModel, navController = navController)
                }

                composable(
                    route = "allCategories",
                    enterTransition = {
                        slideInHorizontally(
                            initialOffsetX = { width -> width / 4 },
                            animationSpec = tween(
                                durationMillis = 200,
                                easing = FastOutLinearInEasing
                            )
                        ) + fadeIn(
                            animationSpec = tween(
                                durationMillis = 200,
                                easing = LinearOutSlowInEasing
                            )
                        )
                    },
                    exitTransition = {
                        slideOutHorizontally(
                            targetOffsetX = { width -> -width / 4 },
                            animationSpec = tween(
                                durationMillis = 200,
                                easing = FastOutLinearInEasing
                            )
                        ) + fadeOut(
                            animationSpec = tween(
                                durationMillis = 200,
                                easing = LinearOutSlowInEasing
                            )
                        )
                    }
                ) {
                    Categories(viewModel = noteViewModel, navController = navController)
                }

                composable(
                    route = "favScreen",
                    enterTransition = {
                        slideInHorizontally(
                            initialOffsetX = { width -> width / 4 },
                            animationSpec = tween(
                                durationMillis = 200,
                                easing = FastOutLinearInEasing
                            )
                        ) + fadeIn(
                            animationSpec = tween(
                                durationMillis = 200,
                                easing = LinearOutSlowInEasing
                            )
                        )
                    },
                    exitTransition = {
                        slideOutHorizontally(
                            targetOffsetX = { width -> -width / 4 },
                            animationSpec = tween(
                                durationMillis = 200,
                                easing = FastOutLinearInEasing
                            )
                        ) + fadeOut(
                            animationSpec = tween(
                                durationMillis = 200,
                                easing = LinearOutSlowInEasing
                            )
                        )
                    }
                ) {
                    FavouriteScreen(viewModel = noteViewModel, navController = navController)
                }

                composable(
                    route = "categoryNotes/{categoryid}/{categoryName}",
                    enterTransition = {
                        slideInHorizontally(
                            initialOffsetX = { width -> width / 4 },
                            animationSpec = tween(
                                durationMillis = 200,
                                easing = FastOutLinearInEasing
                            )
                        ) + fadeIn(
                            animationSpec = tween(
                                durationMillis = 200,
                                easing = LinearOutSlowInEasing
                            )
                        )
                    },
                    exitTransition = {
                        slideOutHorizontally(
                            targetOffsetX = { width -> -width / 4 },
                            animationSpec = tween(
                                durationMillis = 200,
                                easing = FastOutLinearInEasing
                            )
                        ) + fadeOut(
                            animationSpec = tween(
                                durationMillis = 200,
                                easing = LinearOutSlowInEasing
                            )
                        )
                    }
                ) { backStackEntry ->
                    val categoryid = backStackEntry.arguments?.getInt("categoryid")
                    val categoryName = backStackEntry.arguments?.getString("categoryName")
                    CategoryNotesScreen(
                        viewModel = noteViewModel,
                        navController = navController,
                        categoryid = categoryid,
                        categoryName = categoryName
                    )
                }
            }
        }
    }
}

