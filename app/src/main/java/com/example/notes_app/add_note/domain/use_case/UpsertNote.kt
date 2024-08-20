package com.example.notes_app.add_note.domain.use_case

import com.example.notes_app.core.domain.model.NoteItem
import com.example.notes_app.core.domain.repository.NoteRepository
import javax.inject.Inject


class UpsertNote @Inject constructor(
    private val noteRepository: NoteRepository
){
    suspend operator fun invoke(
        title: String,
        description: String,
        imageUrl: String
    ): Boolean{
        if(title.isBlank() || description.isBlank()){
            return false
        }
        val note = NoteItem(
            title = title,
            description = description,
            imageUrl = imageUrl,
            dateAdded = System.currentTimeMillis(),
        )
        noteRepository.upsertNote(note)

        return true
    }

}