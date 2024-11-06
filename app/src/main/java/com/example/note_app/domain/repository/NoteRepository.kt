package com.example.note_app.domain.repository

import com.example.note_app.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun getAllNotes():Flow<List<Note>>
    suspend fun  getNoteById(id : Int):Note?
    suspend fun insertNote(note : Note)
    suspend fun deleteNote(note : Note)
    suspend fun updateNote(note : Note)
}