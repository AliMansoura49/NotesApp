package com.example.notes_app.core.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.notes_app.add_note.presentation.AddNoteScreen
import com.example.notes_app.core.presentation.ui.theme.NotesAppTheme
import com.example.notes_app.notes_list.presentation.NoteListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesAppTheme {
                Navigation()
            }
            }
        }
    }
    @Composable
    fun Navigation(modifier: Modifier = Modifier) {
        val navController = rememberNavController()

        NavHost(
            modifier = modifier,
            navController = navController,
            startDestination = "main_screen"
        ) {
            composable("main_screen"){
                NoteListScreen(
                    onNavigateToAddNote = {
                        navController.navigate("add_note_screen")
                    }
                )
            }
            composable("add_note_screen"){
                AddNoteScreen(onSave = {
                    navController.navigateUp()
                })
            }
    }



}
