package com.example.shoppinglist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class NoteViewModel(
    private val noteRepository: NoteRepository = Graph.noteRepository
): ViewModel() {

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