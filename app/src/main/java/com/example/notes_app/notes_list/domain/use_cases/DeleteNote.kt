package com.example.notes_app.notes_list.domain.use_cases

import com.example.notes_app.core.domain.model.NoteItem
import com.example.notes_app.core.domain.repository.NoteRepository
import javax.inject.Inject

class DeleteNote @Inject constructor(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke(note: NoteItem) {
        noteRepository.deleteNote(note)
    }
}