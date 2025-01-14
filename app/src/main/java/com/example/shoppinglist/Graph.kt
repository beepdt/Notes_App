package com.example.shoppinglist

import android.content.Context
import androidx.room.Room

object Graph {

    lateinit var database: NoteDatabase

    val noteRepository by lazy {
        NoteRepository(noteDao = database.noteDao())
    }

    @Synchronized
    fun provide(context: Context){
        database = Room.databaseBuilder(
            context.applicationContext,
            NoteDatabase::class.java,
            "noteList.db").addMigrations(NoteDatabase.MIGRATION_1_2,NoteDatabase.MIGRATION_2_3).build()
    }
}