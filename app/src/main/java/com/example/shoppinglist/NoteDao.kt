package com.example.shoppinglist

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
abstract class NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun addANote(noteEntity: NotesData)

    //loads all wishes from the wish table
    @Query("Select * from `note-table`")
    abstract fun getAllNotes(): Flow<List<NotesData>>

    @Update
    abstract suspend fun updateNote(noteEntity: NotesData)

    @Delete
    abstract suspend fun deleteNote(noteEntity: NotesData)

    @Query("Select * from `note-table` where id= :id")
    abstract fun getNoteById(id: Int): Flow<NotesData>
}