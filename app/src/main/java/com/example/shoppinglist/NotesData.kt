package com.example.shoppinglist

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "note-table")
data class NotesData(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "note-name")
    var noteName: String = "",
    @ColumnInfo(name = "note-desc")
    var noteText: String = "",

)