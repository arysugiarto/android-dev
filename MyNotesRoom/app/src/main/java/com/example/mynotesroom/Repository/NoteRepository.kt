package com.example.mynotesroom.Repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.mynotesroom.Database.Note
import com.example.mynotesroom.Database.NoteDao
import com.example.mynotesroom.Database.NoteDatabase
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class NoteRepository(application: Application) {
    private val mNotesDao: NoteDao
    private val executorService: ExecutorService= Executors.newSingleThreadExecutor()

    init {
        val db = NoteDatabase.getDatabase(application)
        mNotesDao = db.noteDao()
    }
    fun getAllNotes(): LiveData<List<Note>> = mNotesDao.getAllnotes()


    fun insert(note:Note){
        executorService.execute{mNotesDao.insert(note)}
    }

    fun delete(note: Note){
        executorService.execute{mNotesDao.delete(note)}
    }

    fun update(note: Note){
        executorService.execute{mNotesDao.update(note)}
    }
}