package com.example.notes_app.core.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteEntity(
    val title: String,
    val description: String,
    val imageUrl: String,

    val dateAdded: Long,

    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
)