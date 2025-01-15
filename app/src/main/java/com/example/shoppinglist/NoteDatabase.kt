package com.example.shoppinglist

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


@Database(
    entities = [NotesData ::class,Category::class],
    version = 3,
    exportSchema = false
)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDao():NoteDao
    /*companion object{
        val MIGRATION_1_2 = object :Migration(1,2){
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE note-table ADD COLUMN is-pinned INTEGER NOT NULL DEFAULT 0 ")
            }
        }

        val MIGRATION_2_3 = object :Migration(2,3){
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("""
                    CREATE TABLE IF NOT EXISTS `note-categories` (
                        `categoryId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        `category-name` TEXT NOT NULL DEFAULT ''
                    )
                """)

                db.execSQL("ALTER TABLE `note-table` ADD COLUMN `category-Id` INTEGER DEFAULT NULL")
            }
        }

        /*@Volatile
        private var INSTANCE:NoteDatabase? = null

        fun getDatabase(context: Context): NoteDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    "note_database"
                )
                    .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
                    .build()
                INSTANCE = instance
                instance
            }
        }*/
    }*/
}