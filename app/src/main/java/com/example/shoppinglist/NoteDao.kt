package com.example.shoppinglist

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
abstract class NoteDao {

    //insert new note
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun addANote(noteEntity: NotesData)

    //loads all notes from the note table
    @Query("Select * from `note-table`")
    abstract fun getAllNotes(): Flow<List<NotesData>>

    //update existing note
    @Update
    abstract suspend fun updateNote(noteEntity: NotesData)

    //delete  a note
    @Delete
    abstract suspend fun deleteNote(noteEntity: NotesData)

    //get a note by id
    @Query("Select * from `note-table` where id= :id")
    abstract fun getNoteById(id: Int): Flow<NotesData>


    //category operations
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun addCategory(category: Category) //new category

    @Query("Select * FROM `note-categories`")
    abstract fun getAllCategories(): Flow<List<Category>> //get all categories

    @Delete
    abstract suspend fun deleteCategory(category: Category)

    //getting notes with their categories
    @Transaction
    @Query("Select * FROM `note-table`")
    abstract fun getNotesWithCategories():Flow<List<NoteWithCategory>>

    @Query("UPDATE `note-table` SET `category-Id` = :categoryId WHERE `category-Id` IS NULL")
    abstract suspend fun assignDefaultCategorytoExistingNotes(categoryId: Int)

    @Query("Select * FROM `note-table` WHERE `category-Id` = :categoryId")
    abstract fun getNotesByCategory(categoryId: Int): Flow<List<NotesData>>

    @Query("Update `note-table` SET `category-Id` =:categoryId WHERE id = :noteId")
    abstract suspend fun updateNoteCategory(noteId: Int,categoryId: Int)
}