package com.example.notes_app.notes_list.domain.use_cases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.notes_app.core.data.repository.FakeNoteRepository
import com.example.notes_app.core.domain.model.NoteItem
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DeleteNoteTest{

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var repository: FakeNoteRepository
    private lateinit var deleteNote: DeleteNote

    @Before
    fun setup(){
        repository = FakeNoteRepository()
        deleteNote = DeleteNote(repository)

        repository.shouldHaveFilledList(true)
    }

    @Test
    fun `delete note, note deleted`() = runTest {

        val note =  NoteItem("d title 1", "desc 1", "url1", 1)

        deleteNote(note)
        val notes = repository.getAllNotes()
        assertThat(notes.contains(note)).isFalse()
    }



}