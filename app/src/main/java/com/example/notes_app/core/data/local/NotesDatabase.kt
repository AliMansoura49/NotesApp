package com.example.notes_app.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [
        NoteEntity::class
    ],
    version = 1,
)
abstract class NotesDatabase : RoomDatabase(){
    abstract fun noteDao(): NoteDao
    companion object{
        const val DATABASE_NAME = "notes_db"
    }
}