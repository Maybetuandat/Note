package com.example.note_app.data.repository

import com.example.note_app.data.local.Dao.NoteDao
import com.example.note_app.data.mapper.asExternalModel
import com.example.note_app.data.mapper.toEntity
import com.example.note_app.domain.model.Note
import com.example.note_app.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NoteRepositoryImpl (private val dao : NoteDao):NoteRepository{
    override fun getAllNotes(): Flow<List<Note>> {
        return dao.getALlNotes()
            .map { notes ->
                notes.map {
                    it.asExternalModel()
                }
            }
    }

    override suspend fun getNoteById(id: Int): Note? {
        return dao.getNoteById(id)?.asExternalModel()
    }

    override suspend fun insertNote(note: Note) {
        dao.insertNote(note.toEntity())
    }

    override suspend fun deleteNote(note: Note) {
        dao.deleteNote(note.toEntity())
    }

    override suspend fun updateNote(note: Note) {
        dao.updateNote(note.toEntity())
    }

}