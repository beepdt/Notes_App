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
    var categoryName by mutableStateOf("")

    var selectedCategoryId by mutableStateOf<Int?>(null)

    lateinit var getAllNotes: Flow<List<NotesData>>
    lateinit var getAllCategories: Flow<List<Category>>
    lateinit var getAllnotesWithCategory: Flow<List<NoteWithCategory>>
    lateinit var getAllnotesDesc: Flow<List<NotesData>>
    lateinit var getAllnotesAsc:Flow<List<NotesData>>
    lateinit var getRecentNotes: Flow<List<NotesData>>

    init {
        viewModelScope.launch {
            getAllNotes = noteRepository.getAllNotes()
            getAllCategories = noteRepository.getAllCategories()
            getAllnotesWithCategory = noteRepository.getAllNotesWithCategories()
            getAllnotesDesc = noteRepository.getAllNotesDesc()
            getAllnotesAsc = noteRepository.getAllNotesAsc()
            getRecentNotes = noteRepository.getRecentNotes()
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

    fun addCategory(category: Category){
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.addCategory(category)
        }
    }

    fun deleteCategory(category: Category) {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.deleteCategory(category)
        }
    }

    fun updateNoteCategory(noteId: Int, categoryId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.updateCategory(noteId, categoryId)
        }
    }

    fun assignDefaultCategory(categoryId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.assignDefaultCategory(categoryId)
        }
    }

    fun getNotesByCategory(categoryId: Int): Flow<List<NotesData>> {
        return noteRepository.getNotesByCategory(categoryId)
    }

}