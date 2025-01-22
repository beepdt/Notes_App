package com.example.shoppinglist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.shoppinglist.ui.theme.AppTheme
import com.example.shoppinglist.ui.theme.dataStore
import com.google.accompanist.systemuicontroller.rememberSystemUiController


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
            AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                  MyApp(noteViewModel)
                }
            }
        }
    }
}

@Composable
fun MyApp(noteViewModel: NoteViewModel){

    val systemUIController = rememberSystemUiController()
    val bgColor = MaterialTheme.colorScheme.background
    LaunchedEffect(bgColor){
        systemUIController.setStatusBarColor(
            color = Color.Red,
            darkIcons = true
        )}


   // val noteViewModel : NoteViewModel = viewModel()
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"

    ){
        composable("home", enterTransition = { fadeIn(animationSpec = tween(250)) } ){ HomeScreen(viewModel = noteViewModel,navController) }
        composable("new note",enterTransition = { fadeIn(animationSpec = tween(250)) }){ NewNoteScreen(viewModel = noteViewModel, navController)}
        composable("editNote/{noteId}"){
            backStackEntry -> val noteId = backStackEntry.arguments?.getString("noteId")
            EditNoteScreen(viewModel = noteViewModel, navController = navController,noteId)
        }
        composable("allNotes"){ NotesUI(viewModel = noteViewModel, navController = navController) }
    }

}