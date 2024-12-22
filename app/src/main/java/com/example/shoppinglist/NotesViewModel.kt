package com.example.shoppinglist

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel

class NotesViewModel: ViewModel(){

    private val _notesData = mutableStateListOf<NotesData>()

    private var _id = 0

    val notesData: SnapshotStateList<NotesData> get() = _notesData

    fun addNewNote(noteName: String, noteText:String){
        _notesData.add(NotesData(id = _id++, noteName = noteName, noteText = noteText))
    }

    fun editNote(id:Int, noteName: String, noteText: String){
        val index = _notesData.indexOfFirst { it.id == id }
        if(index != -1){
            _notesData[index] = _notesData[index].copy(noteName = noteName, noteText = noteText)
        }
    }

    fun deleteNote(id: Int) {
        val index = _notesData.indexOfFirst { it.id == id }
        if (index != -1) {
            _notesData.removeAt(index)
        }
    }





}