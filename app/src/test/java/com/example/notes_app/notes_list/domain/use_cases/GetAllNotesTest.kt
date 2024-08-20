package com.example.notes_app.notes_list.domain.use_cases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.notes_app.core.data.repository.FakeNoteRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetAllNotesTest{

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var fakeNoteRepository: FakeNoteRepository
    private lateinit var  getAllNotes: GetAllNotes

    @Before
    fun setup(){
        fakeNoteRepository = FakeNoteRepository()
        getAllNotes = GetAllNotes(fakeNoteRepository)

    }

    @Test
    fun `get all notes, sorted by title`()= runTest{

        fakeNoteRepository.shouldHaveFilledList(true)

        val notes = getAllNotes.invoke(true)

        for(i in 0..notes.size-2){
            assertTrue(notes[i].title <= notes[i+1].title)
        }
    }
    @Test
    fun `get all notes, sorted by date added`()= runTest{

        fakeNoteRepository.shouldHaveFilledList(true)
        val notes = getAllNotes.invoke(false)

        for(i in 0..notes.size-2){
            assertTrue(notes[i].dateAdded <= notes[i+1].dateAdded)
        }
    }

    @Test
    fun `get  notes from empty list, empty list`()= runTest{
        val notes = getAllNotes.invoke(true)

        assertTrue(notes.isEmpty())
    }


}