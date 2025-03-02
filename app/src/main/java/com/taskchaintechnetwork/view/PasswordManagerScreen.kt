package com.taskchaintechnetwork.view

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.taskchaintechnetwork.R
import com.taskchaintechnetwork.model.AddAccount
import com.taskchaintechnetwork.utils.AESUtils
import com.taskchaintechnetwork.viewModel.AccountViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordManagerScreen(viewModel: AccountViewModel) {
    val bottomSheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }
    var showEditForm by remember { mutableStateOf(false) }
    var selectedAccount by remember { mutableStateOf<AddAccount?>(null) }
    val accounts by viewModel.allAccounts.observeAsState(initial = emptyList())



    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    selectedAccount = null
                    showEditForm = true
                },
                //containerColor = Color.Green
                containerColor = Color(0xFF3F7DE3)

            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Password",
                    tint = Color.White
                )
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color(0xFFF8F9FA))
        ) {
            Text(
                "Password Manager",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )
            LazyColumn(modifier = Modifier.padding(horizontal = 16.dp)) {
                items(accounts) { account ->
                    PasswordItem(account, onClick = {
                        selectedAccount = account
                        showBottomSheet = true
                    })
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
    }

    // Bottom Sheet for Details & Edit/Delete Options
    if (showBottomSheet && selectedAccount != null) {
        ModalBottomSheet(
            onDismissRequest = { showBottomSheet = false },
            sheetState = bottomSheetState
        ) {
            AccountDetails(viewModel, selectedAccount!!, onEdit = {
                showBottomSheet = false
                showEditForm = true
            }, onDelete = {
                showBottomSheet = false
            })
        }
    }

    // Bottom Sheet for Adding/Editing Account
    if (showEditForm) {
        ModalBottomSheet(
            onDismissRequest = { showEditForm = false },
            sheetState = bottomSheetState
        ) {
            AddAccountForm(viewModel, selectedAccount) {
                showEditForm = false
            }
        }
    }
}

@Composable
fun PasswordItem(account: AddAccount, onClick: () -> Unit) {
    val secretKey = AESUtils.generateSecretKey()
    val iv = ByteArray(16) // Use a fixed/random IV for security

    // Encrypt password
    val encryptedPassword = AESUtils.encrypt(account.password, secretKey, iv)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Text(text = account.name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(
                    text = "*".repeat(encryptedPassword.size),
                    fontSize = 14.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(start = 10.dp)
                )
            }
            // Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit")
            Image(
                painter = painterResource(id = R.drawable.baseline_navigate_next_24),  // Your drawable resource
                contentDescription = "Edit",
                modifier = Modifier
            )
        }
    }
}

@Composable
fun AccountDetails(
    viewModel: AccountViewModel,
    account: AddAccount,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    val secretKey = AESUtils.generateSecretKey()
    val iv = ByteArray(16) // Use a fixed/random IV for security

    // Encrypt password
    val encryptedPassword = AESUtils.encrypt(account.password, secretKey, iv)

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)) {
        Text("Account Details", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.Blue)
        Spacer(modifier = Modifier.height(8.dp))

        Text("Account Type", fontWeight = FontWeight.Bold, fontSize = 14.sp)
        Text(account.name, fontSize = 16.sp, modifier = Modifier.padding(bottom = 8.dp))

        Text("Username/ Email", fontWeight = FontWeight.Bold, fontSize = 14.sp)
        Text(account.email, fontSize = 16.sp, modifier = Modifier.padding(bottom = 8.dp))

        Text("Password", fontWeight = FontWeight.Bold, fontSize = 14.sp)
        Text(
            "*".repeat(encryptedPassword.size),
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    onEdit()
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Edit")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = {
                    viewModel.delete(account)
                    onDelete()
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Delete", color = Color.White)
            }

        }
    }
}

@Composable
fun AddAccountForm(viewModel: AccountViewModel, account: AddAccount?, onSubmit: () -> Unit) {
    val context = LocalContext.current
    var accountName by remember { mutableStateOf(account?.name ?: "") }
    var email by remember { mutableStateOf(account?.email ?: "") }
    var password by remember { mutableStateOf(account?.password ?: "") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = accountName,
            onValueChange = { accountName = it },
            label = { Text("Account Name") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Username/Email") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {

                if (accountName.isNotEmpty() && isValidEmail(email) && isValidPassword(password)) {
                    if (account == null) {
                        viewModel.insert(
                            AddAccount(
                                name = accountName,
                                email = email,
                                password = password
                            )
                        )
                    } else {
                        viewModel.update(
                            AddAccount(
                                id = account.id,
                                name = accountName,
                                email = email,
                                password = password
                            )
                        )
                    }
                    onSubmit()
                } else {
                    Toast.makeText(
                        context,
                        "Invalid email or password! Password must be at least 8 characters long, with uppercase, lowercase, number, and special character.",
                        Toast.LENGTH_LONG
                    ).show()
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black),

            ) {
            Text(if (account == null) "Add New Account" else "Update Account")
        }
    }
}

fun isValidEmail(email: String) = email.contains("@")
fun isValidPassword(password: String): Boolean {
    val passwordPattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#\$%^&+=!]).{8,}\$"
    return password.matches(passwordPattern.toRegex())
}



