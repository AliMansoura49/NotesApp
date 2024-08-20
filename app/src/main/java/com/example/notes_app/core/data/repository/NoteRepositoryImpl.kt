package com.example.notes_app.core.data.repository


import com.example.notes_app.core.data.local.NotesDatabase
import com.example.notes_app.core.data.mapper.toNoteEntityForDelete
import com.example.notes_app.core.data.mapper.toNoteEntityForInsert
import com.example.notes_app.core.data.mapper.toNoteItem
import com.example.notes_app.core.domain.model.NoteItem
import com.example.notes_app.core.domain.repository.NoteRepository
import javax.inject.Inject


class NoteRepositoryImpl @Inject constructor(
   noteDatabase: NotesDatabase
): NoteRepository {

    private val dao = noteDatabase.noteDao()

    override suspend fun upsertNote(note: NoteItem) {
        dao.upsertNoteEntity(note.toNoteEntityForInsert())
    }

    override suspend fun deleteNote(note: NoteItem) {
        dao.deleteNoteEntity(note.toNoteEntityForDelete())
    }

    override suspend fun getAllNotes(): List<NoteItem> {
        return dao.getAllNoteEntities().map { it.toNoteItem() }
    }

}