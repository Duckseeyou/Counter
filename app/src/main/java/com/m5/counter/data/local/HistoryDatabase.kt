package com.m5.counter.data.local


import androidx.room.Database
import androidx.room.RoomDatabase
import com.m5.counter.data.model.LoveModel


@Database(entities = [LoveModel::class], version = 1)

abstract class HistoryDatabase: RoomDatabase() {
    abstract fun historyDao(): HistoryDao
}
