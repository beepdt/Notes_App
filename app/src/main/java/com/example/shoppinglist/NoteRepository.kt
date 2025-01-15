package com.example.shoppinglist

import kotlinx.coroutines.flow.Flow

class NoteRepository (private val noteDao: NoteDao){

    fun getAllNotesDesc(): Flow<List<NotesData>> = noteDao.getAllNotesDesc()

    fun getAllNotesAsc(): Flow<List<NotesData>> = noteDao.getAllNotesAsc()

    fun getRecentNotes(): Flow<List<NotesData>> = noteDao.getRecentNotes()

    suspend fun addANote(note: NotesData){
        noteDao.addANote(note)
    }

    fun getAllNotes(): Flow<List<NotesData>> = noteDao.getAllNotes()

    fun getNoteById(id:Int):Flow<NotesData>{
        return noteDao.getNoteById(id)
    }

    suspend fun updateANote(note: NotesData){
        noteDao.updateNote(note)
    }

    suspend fun deleteANote(note: NotesData){
        noteDao.deleteNote(note)
    }

    //new operations
    fun getAllCategories():Flow<List<Category>> = noteDao.getAllCategories()

    fun getAllNotesWithCategories():Flow<List<NoteWithCategory>> = noteDao.getNotesWithCategories()

    suspend fun addCategory(category: Category){
        noteDao.addCategory(category)
    }

    suspend fun deleteCategory(category: Category){
        noteDao.deleteCategory(category)
    }

    suspend fun updateCategory(noteId: Int,categoryId: Int){
        noteDao.updateNoteCategory(noteId,categoryId)
    }

    suspend fun assignDefaultCategory(categoryId: Int){
        noteDao.assignDefaultCategorytoExistingNotes(categoryId)
    }

    fun getNotesByCategory(categoryId: Int): Flow<List<NotesData>>{
        return noteDao.getNotesByCategory(categoryId)
    }


}