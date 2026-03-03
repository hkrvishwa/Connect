package org.connect.chat.data

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.connect.chat.db.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import org.connect.chat.db.Note


class NoteRepository(
    val database: AppDatabase
) {

    fun getNotes(): Flow<List<Note>> {
        return database.noteQueries
            .selectNotes()
            .asFlow()
            .mapToList(Dispatchers.Default)
    }

    fun insertNote(message: String) {
        database.noteQueries.insertNote(message)
    }
}