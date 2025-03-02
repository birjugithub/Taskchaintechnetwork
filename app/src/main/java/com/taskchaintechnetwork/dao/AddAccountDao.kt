package com.taskchaintechnetwork.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.taskchaintechnetwork.model.AddAccount

@Dao
interface AddAccountDao {

    @Insert
    suspend fun insertAccount(addAccount: AddAccount):Long

    @Update
    suspend fun updateAccount(dAccount: AddAccount)

    @Delete
    suspend fun deleteAccount(dAccount: AddAccount)

    @Query("SELECT * FROM addAccount")
    fun getAccount(): LiveData<List<AddAccount>>

}