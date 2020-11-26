package com.example.mynotesroom.ui.insert

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.mynotesroom.Database.Note
import com.example.mynotesroom.Repository.NoteRepository

class NoteAddUpdateViewModel (application: Application):ViewModel(){
    private val mNotesRepository: NoteRepository = NoteRepository(application)


    fun insert(note: Note){
        mNotesRepository.insert(note)
    }

    fun update(note: Note){
        mNotesRepository.update(note)
    }

    fun delete(note: Note){
        mNotesRepository.delete(note)
    }
}