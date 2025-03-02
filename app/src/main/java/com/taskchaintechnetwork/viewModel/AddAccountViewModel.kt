package com.taskchaintechnetwork.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import com.taskchaintechnetwork.database.AddAccountDatabase
import com.taskchaintechnetwork.model.AddAccount
import com.taskchaintechnetwork.repository.AccountRepository
import kotlinx.coroutines.launch

class AccountViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: AccountRepository
    var allAccounts: LiveData<List<AddAccount>>

    init {
        val database = AddAccountDatabase.getDatabase(application)
        val dao = database.accountDao()
        repository = AccountRepository(dao)
        allAccounts = repository.allAccounts
    }

    fun insert(account: AddAccount) = viewModelScope.launch {
        repository.insert(account)
        allAccounts = repository.allAccounts
    }

    fun delete(account: AddAccount) = viewModelScope.launch {
        repository.delete(account)
    }

    fun update(account: AddAccount)= viewModelScope.launch {
        repository.update(account)
    }
}