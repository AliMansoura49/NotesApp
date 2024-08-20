package com.example.notes_app.core.di

import android.app.Application
import androidx.room.Room
import com.example.notes_app.add_note.domain.use_case.SearchImages
import com.example.notes_app.add_note.domain.use_case.UpsertNote
import com.example.notes_app.core.data.local.NotesDatabase
import com.example.notes_app.core.data.remote.api.ImagesApi
import com.example.notes_app.core.data.repository.FakeAndroidImagesRepository
import com.example.notes_app.core.data.repository.FakeAndroidNoteRepository
import com.example.notes_app.core.data.repository.ImagesRepositoryImpl
import com.example.notes_app.core.domain.repository.ImagesRepository
import com.example.notes_app.core.domain.repository.NoteRepository
import com.example.notes_app.notes_list.domain.use_cases.DeleteNote
import com.example.notes_app.notes_list.domain.use_cases.GetAllNotes
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Provides
    @Singleton
    fun providesNotesDatabase(application: Application) : NotesDatabase {
        return Room.inMemoryDatabaseBuilder(
            application,
            NotesDatabase::class.java,
        ).build()
    }
    @Provides
    @Singleton
    fun providesNotesRepository() : NoteRepository {
        return FakeAndroidNoteRepository()
    }
    @Provides
    @Singleton
    fun providesGetAllNotes(repository: NoteRepository) : GetAllNotes {
        return GetAllNotes(repository)
    }
    @Provides
    @Singleton
    fun providesDeleteNote(repository: NoteRepository) : DeleteNote {
        return DeleteNote(repository)
    }
    @Provides
    @Singleton
    fun providesUpsertNote(repository: NoteRepository) : UpsertNote {
        return UpsertNote(repository)
    }
    @Provides
    @Singleton
    fun providesImagesApi(): ImagesApi {
        return Retrofit.Builder()
            .baseUrl(ImagesApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ImagesApi::class.java)
    }
    @Provides
    @Singleton
    fun providesImagesRepository(): ImagesRepository {
        return FakeAndroidImagesRepository()
    }

    @Provides
    @Singleton
    fun providesSearchImages(repository: ImagesRepository): SearchImages {
        return SearchImages(repository)
    }
}