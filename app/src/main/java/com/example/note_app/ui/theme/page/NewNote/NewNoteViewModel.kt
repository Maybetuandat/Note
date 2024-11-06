package com.example.note_app.ui.theme.page.NewNote

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.note_app.domain.model.Note
import com.example.note_app.domain.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewNoteViewModel @Inject constructor(private val repository: NoteRepository) : ViewModel() {

    var titleText by  mutableStateOf("")
    var bodyText by mutableStateOf("")
    fun saveNewNote()
    {
        viewModelScope.launch {
            val newNote = Note(
                title = titleText,
                content = bodyText)
            repository.insertNote(newNote)
        }

    }
}