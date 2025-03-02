package com.taskchaintechnetwork.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "addAccount")
data class AddAccount(
    @PrimaryKey(autoGenerate = true)
    val id: Long?=null,
    val name: String,
    val email: String,
    val password: String
)
