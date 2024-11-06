package com.example.note_app.data.mapper

import com.example.note_app.data.local.Entity.NoteEntity
import com.example.note_app.domain.model.Note

fun NoteEntity.asExternalModel(): Note = Note(
    id, title, content
)

fun Note.toEntity(): NoteEntity {
    return NoteEntity(
        id = this.id ?:0,
        title = this.title,
        content = this.content
    )
}