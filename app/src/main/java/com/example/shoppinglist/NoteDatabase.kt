package com.example.shoppinglist

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


@Database(
    entities = [NotesData ::class],
    version = 2,
    exportSchema = false
)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDao():NoteDao
    companion object{
        val MIGRATION_1_2 = object :Migration(1,2){
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE note-table ADD COLUMN is-pinned INTEGER NOT NULL DEFAULT 0 ")
            }
        }
    }

}