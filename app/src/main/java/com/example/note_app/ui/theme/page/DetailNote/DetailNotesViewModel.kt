package com.example.note_app.ui.theme.page.DetailNote

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.note_app.domain.model.Note
import com.example.note_app.domain.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailNotesViewModel @Inject constructor(private val repository: NoteRepository) :
    ViewModel() {

    private val _note = MutableStateFlow<Note?>(null)
    val note: StateFlow<Note?> get() = _note

    fun getNoteById(id: Int) {
        viewModelScope.launch {
            _note.value = repository.getNoteById(id)
        }
    }

    fun updateNoteTitle(newTitle: String) {
        _note.value?.let {
            _note.value = it.copy(title = newTitle)
        }
    }

    fun updateNoteContent(newContent: String) {
        _note.value?.let {
            _note.value = it.copy(content = newContent)
        }
    }

    fun saveNote() {
        val currentNote = _note.value
        if (currentNote != null) {
            // Gọi repository để cập nhật note vào kho dữ liệu (database)
            viewModelScope.launch {
                repository.updateNote(currentNote)
            }
        } else {
            // Thông báo lỗi khi note là null
            // Bạn có thể thêm một phương thức để thông báo lỗi hoặc log ra
            Log.e("DetailNotesViewModel", "Note is null, cannot save.")
        }
    }


}