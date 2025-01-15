package com.example.shoppinglist

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Relation


@Entity(tableName = "note-categories")
data class Category(
    @PrimaryKey(autoGenerate = true)
    var categoryId: Int = 0,
    @ColumnInfo(name = "category-name")
    var categoryName:String=""
)


@Entity(
    tableName = "note-table",
    foreignKeys = [
        ForeignKey(
            entity = Category::class,
            parentColumns = ["categoryId"],
            childColumns = ["category-Id"],
            onDelete = ForeignKey.SET_NULL
        )
    ]
)
data class NotesData(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "note-name")
    var noteName: String = "",
    @ColumnInfo(name = "note-desc")
    var noteText: String = "",
    @ColumnInfo(name = "is-pinned", defaultValue = "false")
    var isPinned: Boolean = false,
    @ColumnInfo(name = "category-Id")
    var categoryId: Int? = null,
    @ColumnInfo(name = "created")
    var dateCreated: Long = System.currentTimeMillis()
)

data class NoteWithCategory(
    @Embedded val note: NotesData,
    @Relation(
        parentColumn = "category-Id",
        entityColumn = "categoryId"
    )
    val category: Category?
)

