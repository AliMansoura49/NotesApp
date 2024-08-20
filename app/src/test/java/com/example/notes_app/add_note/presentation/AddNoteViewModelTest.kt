package com.example.notes_app.add_note.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.MainCoroutineRule
import com.example.notes_app.add_note.domain.use_case.SearchImages
import com.example.notes_app.add_note.domain.use_case.UpsertNote
import com.example.notes_app.core.data.repository.FakeImagesRepository
import com.example.notes_app.core.data.repository.FakeNoteRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AddNoteViewModelTest{

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private lateinit var fakeNoteRepository: FakeNoteRepository
    private lateinit var fakeImagesRepository: FakeImagesRepository
    private lateinit var addNoteViewModel: AddNoteViewModel

    @Before
    fun setup(){
        fakeNoteRepository = FakeNoteRepository()
        fakeImagesRepository = FakeImagesRepository()
        val upsertNote = UpsertNote(fakeNoteRepository)
        val searchImage = SearchImages(fakeImagesRepository)
        addNoteViewModel = AddNoteViewModel(upsertNote,searchImage)
    }
    @Test
    fun `upsert note with empty title, return false`() = runTest {
        val isInserted = addNoteViewModel.upsertNote(
            title = "",
            description = "description",
            imageUrl = "image"
        )
        assertThat(isInserted).isFalse()
    }

    @Test
    fun `upsert note with empty description, return false`() = runTest {
        val isInserted = addNoteViewModel.upsertNote(
            title = "title",
            description = "",
            imageUrl = "image"
        )

        assertThat(isInserted).isFalse()
    }

    @Test
    fun `upsert a valid, return true`() = runTest {
        val isInserted = addNoteViewModel.upsertNote(
            title = "title",
            description = "description",
            imageUrl = "image"
        )
        assertThat(isInserted).isTrue()
    }

    @Test
    fun `search image with empty query, image list is empty`() = runTest {

        addNoteViewModel.searchImage("")

        coroutineRule.dispatcher.scheduler.advanceUntilIdle()

        assertThat(addNoteViewModel.addNoteState.value.imageList).isEmpty()

    }

    @Test
    fun `search image with a valid query but with an error, image list is empty`() = runTest {
        fakeImagesRepository.setShouldReturnError(true)

        addNoteViewModel.searchImage("query")

        coroutineRule.dispatcher.scheduler.advanceUntilIdle()

        assertThat(addNoteViewModel.addNoteState.value.imageList).isEmpty()
    }
    @Test
    fun `search image with a valid query, image list is not empty`() = runTest {
        addNoteViewModel.searchImage("query")

        coroutineRule.dispatcher.scheduler.advanceUntilIdle()

        assertThat(addNoteViewModel.addNoteState.value.imageList).isNotEmpty()
    }

}