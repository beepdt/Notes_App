package com.example.shoppinglist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class NoteViewModel(
    private val noteRepository: NoteRepository = Graph.noteRepository
): ViewModel() {

    var noteName by mutableStateOf("")
    var noteText by   mutableStateOf("")
    var isPinned by mutableStateOf(false)

    lateinit var getAllNotes: Flow<List<NotesData>>

    init {
        viewModelScope.launch {
            getAllNotes = noteRepository.getAllNotes()
        }
    }

    fun getNoteById(id: Int): Flow<NotesData>{
        return noteRepository.getNoteById(id = id)
    }

    fun addNote(note: NotesData){
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.addANote(note = note)
        }
    }

    fun updateNote(note: NotesData){
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.updateANote(note = note)
        }
    }

    fun deleteNote(note: NotesData){
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.deleteANote(note = note)
        }
    }

}