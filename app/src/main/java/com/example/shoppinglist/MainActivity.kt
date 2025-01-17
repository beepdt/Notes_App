package com.example.shoppinglist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.shoppinglist.ui.theme.ShoppingListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShoppingListTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                  MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp(){
    val notesViewModel : NotesViewModel = viewModel()
    val noteViewModel : NoteViewModel = viewModel()
    NotesUI(viewModel = noteViewModel, navController = rememberNavController())
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"

    ){
        composable("home", enterTransition = { fadeIn(animationSpec = tween(250)) } ){ NotesUI(viewModel = noteViewModel,navController) }
        composable("new note",enterTransition = { fadeIn(animationSpec = tween(250)) }){ NewNoteScreen(viewModel = noteViewModel, navController)}
        composable("editNote/{noteId}"){
            backStackEntry -> val noteId = backStackEntry.arguments?.getString("noteId")
            EditNoteScreen(viewModel = noteViewModel, navController = navController,noteId)
        }
        composable("homeScreen"){ HomeScreen(viewModel = , navController = )}
    }

}