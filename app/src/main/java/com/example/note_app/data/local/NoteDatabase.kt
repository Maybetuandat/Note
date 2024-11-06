package com.example.note_app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.note_app.data.local.Dao.NoteDao
import com.example.note_app.data.local.Entity.NoteEntity

@Database(
    entities = [NoteEntity::class],
    version = 1

)
abstract class NoteDatabase :RoomDatabase(){
    abstract val dao:NoteDao
    companion object{
        const val name = "note_db"
    }
}