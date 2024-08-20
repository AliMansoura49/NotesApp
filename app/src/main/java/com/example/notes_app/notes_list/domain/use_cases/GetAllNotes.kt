package com.example.notes_app.notes_list.domain.use_cases

import com.example.notes_app.core.domain.model.NoteItem
import com.example.notes_app.core.domain.repository.NoteRepository
import javax.inject.Inject

class GetAllNotes @Inject constructor(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(isOrderedByTitle: Boolean):List<NoteItem> {
        return if (isOrderedByTitle){
            repository.getAllNotes().sortedBy { it.title.lowercase() }
        }else{
            repository.getAllNotes().sortedBy { it.dateAdded }
        }
    }
}