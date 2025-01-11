package com.example.shoppinglist

import kotlinx.coroutines.flow.Flow

class NoteRepository (private val noteDao: NoteDao){

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


}