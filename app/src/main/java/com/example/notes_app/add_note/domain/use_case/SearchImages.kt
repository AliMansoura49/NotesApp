package com.example.notes_app.add_note.domain.use_case


import com.example.notes_app.core.domain.model.Images
import com.example.notes_app.core.domain.repository.ImagesRepository
import com.example.notes_app.add_note.presentation.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchImages @Inject constructor(
    private val imageRepository: ImagesRepository
) {

    suspend operator fun invoke(
        query: String
    ): Flow<Resource<Images>> {
        return flow {

            emit(Resource.Loading(true))

            if(query.isBlank()){
                emit(Resource.Success(Images(emptyList())))
                emit(Resource.Loading(false))
                return@flow
            }

            val images = try {
                imageRepository.searchImages(query)
            }catch (e: Exception){
                e.printStackTrace()
                emit(Resource.Error())
                emit(Resource.Loading(false))
                return@flow
            }
            images?.let {
                emit(Resource.Success(it))
                emit(Resource.Loading(false))
                return@flow
            }
            emit(Resource.Error())
            emit(Resource.Loading(false))
        }
    }

}