package com.example.myapplicationhome.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Entity")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Int=0,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "email") val email: String?,
    @ColumnInfo(name = "phone") val phone: String?
)