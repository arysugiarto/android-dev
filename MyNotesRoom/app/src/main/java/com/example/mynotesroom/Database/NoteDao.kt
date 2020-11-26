package com.example.mynotesroom.Database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao{
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(note: Note)

    @Update
    fun update(note: Note)

    @Delete
    fun delete(note: Note)

    @Query("SELECT * from note ORDER BY id ASC")
    fun getAllnotes():LiveData<List<Note>>
}