package com.taskchaintechnetwork.repository

import androidx.lifecycle.LiveData
import com.taskchaintechnetwork.dao.AddAccountDao
import com.taskchaintechnetwork.model.AddAccount

class AccountRepository(private val accountDao: AddAccountDao) {

    val allAccounts: LiveData<List<AddAccount>> = accountDao.getAccount()

    suspend fun insert(account: AddAccount):Long {
       return accountDao.insertAccount(account)
    }

    suspend fun delete(account: AddAccount) {
        accountDao.deleteAccount(account)
    }

    suspend fun update(account: AddAccount){
        accountDao.updateAccount(account)
    }

}
