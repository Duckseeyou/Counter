package com.m5.counter.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.m5.counter.data.model.LoveModel

@Dao
interface HistoryDao {
    @Query("SELECT * FROM history_table")
    fun getAll(): LiveData<List<LoveModel>>

    @Insert
    fun insert(historyEntity: LoveModel)

    @Query("DELETE FROM history_table")
    fun deleteAll()

    @Query("DELETE FROM history_table WHERE id = :id")
    fun deleteById(id: Int)

    @Update
    fun update(historyEntity: LoveModel)

}