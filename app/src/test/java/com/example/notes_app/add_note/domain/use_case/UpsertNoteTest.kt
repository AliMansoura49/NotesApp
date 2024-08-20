package com.example.notes_app.add_note.domain.use_case

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.MainCoroutineRule
import com.example.notes_app.core.data.repository.FakeNoteRepository
import com.example.notes_app.core.domain.model.NoteItem
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UpsertNoteTest{

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineDispatcher = MainCoroutineRule()

    private lateinit var  fakeRepository : FakeNoteRepository
    private lateinit var upsertNote: UpsertNote

    @Before
    fun setup(){
        fakeRepository = FakeNoteRepository()
        upsertNote = UpsertNote(fakeRepository)
    }

    @Test
    fun `upsert note with an empty title returns false`() = runTest{
        val isInserted = upsertNote(
            title = "",
            description = "test description",
            imageUrl = "test image url"
        )
        assertThat(isInserted).isFalse()
    }
    @Test
    fun `upsert note with an empty description returns false`() = runTest{
        val isInserted = upsertNote(
            title = "test title",
            description = "",
            imageUrl = "test image url"
        )
        assertThat(isInserted).isFalse()
    }
    @Test
    fun `upsert a valid note,returns true`() = runTest{
        val isInserted = upsertNote(
            title = "test title",
            description = "test description",
            imageUrl = "test image url"
        )
        assertThat(isInserted).isTrue()
    }

}