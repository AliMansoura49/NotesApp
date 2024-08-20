package com.example.notes_app.core.domain.repository

import com.example.notes_app.core.domain.model.NoteItem


interface NoteRepository {

    suspend fun upsertNote(note: NoteItem)

    suspend fun deleteNote(note: NoteItem)

    suspend fun getAllNotes(): List<NoteItem>

}