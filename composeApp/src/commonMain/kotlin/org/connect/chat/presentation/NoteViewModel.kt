package org.connect.chat.presentation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import org.connect.chat.data.NoteRepository
import org.connect.chat.db.Note


class NoteViewModel(
    private val repository: NoteRepository
) {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    val notes: StateFlow<List<Note>> =
        repository.getNotes()
            .stateIn(
                scope,
                SharingStarted.WhileSubscribed(5000),
                emptyList()
            )

    fun addNote(message: String) {
        if (message.isBlank()) return
        repository.insertNote(message)
    }

    fun clear() {
        scope.cancel()
    }
}