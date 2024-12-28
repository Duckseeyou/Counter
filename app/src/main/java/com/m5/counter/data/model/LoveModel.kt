package com.m5.counter.data.model
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "history_table")
data class LoveModel(
    @SerializedName("fname")
    @ColumnInfo(name = "first_name") val firstName: String,
    @SerializedName("sname")
    @ColumnInfo(name = "second_name") val secondName: String,
    val percentage: String,
    val result: String
) : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
