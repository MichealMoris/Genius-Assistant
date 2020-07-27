package com.genius.geniusassistant;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DAO {

    @Query("SELECT * FROM sentences")
    List<Sentences> getAll();

    @Insert
    void insert(Sentences sentences);

    @Delete
    void delete(Sentences sentences);

    @Update
    void update(Sentences sentences);

}
