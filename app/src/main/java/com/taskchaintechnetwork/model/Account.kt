package com.taskchaintechnetwork.model


// Account Data Class
data class Account(
    val name: String,
    val email: String,
    val password: String
)



//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun PasswordManagerScreen(viewModel: AccountViewModel) {
//    val bottomSheetState = rememberModalBottomSheetState()
//    var showAddAccountSheet by remember { mutableStateOf(false) }
//    var showDetailsSheet by remember { mutableStateOf(false) }
//    var selectedAccount by remember { mutableStateOf<AddAccount?>(null) }
//    val accounts by viewModel.allAccounts.observeAsState(initial = emptyList())
//
//    Scaffold(
//        floatingActionButton = {
//            FloatingActionButton(
//                onClick = { showAddAccountSheet = true },
//                containerColor = Color.Red
//            ) {
//                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Password", tint = Color.White)
//            }
//        }
//    ) { padding ->
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(padding)
//                .background(Color(0xFFF8F9FA))
//        ) {
//            Text(
//                text = "Password Manager",
//                fontSize = 20.sp,
//                fontWeight = FontWeight.Bold,
//                modifier = Modifier.padding(16.dp)
//            )
//
//            LazyColumn(
//                modifier = Modifier.padding(horizontal = 16.dp)
//            ) {
//                items(accounts) { account ->
//                    PasswordItem(account) {
//                        selectedAccount = account
//                        showDetailsSheet = true
//                    }
//                    Spacer(modifier = Modifier.height(12.dp))
//                }
//            }
//        }
//    }
//
//    // Add New Account Bottom Sheet
//    if (showAddAccountSheet) {
//        ModalBottomSheet(onDismissRequest = { showAddAccountSheet = false },
//            sheetState = bottomSheetState) {
//            AddAccountForm(viewModel) {
//                showAddAccountSheet = false
//            }
//        }
//    }
//
//    // Account Details Bottom Sheet
//    if (showDetailsSheet && selectedAccount != null) {
//        ModalBottomSheet(
//            onDismissRequest = { showDetailsSheet = false },
//            sheetState = bottomSheetState) {
//            AccountDetails(viewModel,selectedAccount!!) {
//                showDetailsSheet = false
//            }
//        }
//    }
//}
//
//// Password Item Clickable Row
//@Composable
//fun PasswordItem(account: AddAccount, onClick: () -> Unit) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .clickable { onClick() },
//        shape = RoundedCornerShape(16.dp),
//        colors = CardDefaults.cardColors(containerColor = Color.White),
//        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
//    ) {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp),
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//            Column {
//                Text(text = account.name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
//                Text(text = account.password, fontSize = 14.sp, color = Color.Gray)
//            }
//            Icon(imageVector = Icons.Default.Edit, contentDescription = "Go")
//        }
//    }
//}
//
//// Account Details Bottom Sheet
//@Composable
//fun AccountDetails(viewModel: AccountViewModel,account: AddAccount, onClose: () -> Unit) {
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(16.dp)
//    ) {
//        Text(text = "Account Details", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.Blue)
//        Spacer(modifier = Modifier.height(8.dp))
//
//        Text(text = "Account Type", fontWeight = FontWeight.Bold, fontSize = 14.sp)
//        Text(text = account.name, fontSize = 16.sp, modifier = Modifier.padding(bottom = 8.dp))
//
//        Text(text = "Username/ Email", fontWeight = FontWeight.Bold, fontSize = 14.sp)
//        Text(text = account.email, fontSize = 16.sp, modifier = Modifier.padding(bottom = 8.dp))
//
//        Text(text = "Password", fontWeight = FontWeight.Bold, fontSize = 14.sp)
//        Row(
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Text(text = account.password, fontSize = 16.sp)
//            Spacer(modifier = Modifier.width(8.dp))
//            // Icon(imageVector = Icons.Default.MoreVert, contentDescription = "Show Password")
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//            Button(
//                onClick = {
//
////                   viewModel.update(account)
////                    onClose()
//
//
//                },
//                colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
//                modifier = Modifier.weight(1f)) {
//                Text(text = "Edit")
//            }
//            Spacer(modifier = Modifier.width(8.dp))
//            Button(
//                onClick = {
//                    viewModel.delete(account)
//                    onClose()
//                },
//                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
//                modifier = Modifier.weight(1f)
//            ) {
//                Text(text = "Delete", color = Color.White)
//            }
//        }
//    }
//}
//
//// Add Account Form Bottom Sheet
//@Composable
//fun AddAccountForm(
//    viewModel: AccountViewModel,onSubmit: () -> Unit) {
//    val accounts by viewModel.allAccounts.observeAsState(initial = emptyList())
//    val context= LocalContext.current
//    var accountName by remember { mutableStateOf("") }
//    var email by remember { mutableStateOf("") }
//    var password by remember { mutableStateOf("") }
//
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(16.dp)) {
//
//        OutlinedTextField(
//            value = accountName,
//            onValueChange = { accountName = it },
//            placeholder = { Text("Account Name", color = Color.Gray) },
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(52.dp)
//                .background(Color.White),
//            shape = RoundedCornerShape(8.dp),
//            colors = OutlinedTextFieldDefaults.colors(
//                focusedBorderColor = Color.LightGray,
//                unfocusedBorderColor = Color.LightGray,
//                cursorColor = Color.Black
//            ),
//            singleLine = true
//        )
//
//        Spacer(modifier = Modifier.height(8.dp))
//
//        OutlinedTextField(
//            value = email,
//            onValueChange = { email = it },
//            placeholder = { Text("Username/ Email", color = Color.Gray) },
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(52.dp)
//                .background(Color.White),
//            shape = RoundedCornerShape(8.dp),
//            colors = OutlinedTextFieldDefaults.colors(
//                focusedBorderColor = Color.LightGray,
//                unfocusedBorderColor = Color.LightGray,
//                cursorColor = Color.Black
//            ),
//            singleLine = true
//        )
//
//        Spacer(modifier = Modifier.height(8.dp))
//
//        OutlinedTextField(
//            value = password,
//            onValueChange = { password = it },
//            placeholder = { Text("Password", color = Color.Gray) },
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(52.dp)
//                .background(Color.White),
//            shape = RoundedCornerShape(8.dp),
//            colors = OutlinedTextFieldDefaults.colors(
//                focusedBorderColor = Color.LightGray,
//                unfocusedBorderColor = Color.LightGray,
//                cursorColor = Color.Black
//            ),
//            singleLine = true
//        )
//        Spacer(modifier = Modifier.height(16.dp))
//
////        Button(
////            onClick = onSubmit,
////            modifier = Modifier.fillMaxWidth(),
////            colors = ButtonDefaults.buttonColors(containerColor = Color.Black)) {
////            Text(text = "Add New Account", color = Color.White)
//
//        Button(
//            onClick = {
//                if (accountName.isNotEmpty() && isValidEmail(email) && isValidPassword(password)) {
//                    viewModel.insert(AddAccount(name = accountName, email = email, password = password))
//
//                } else {
//                    Toast.makeText(context, "Invalid email or password must be at least 8 characters", Toast.LENGTH_SHORT).show()
//                }
//            },
//            modifier = Modifier.fillMaxWidth(),
//            colors = ButtonDefaults.buttonColors(containerColor = Color.Black)) {
//            Text(text = "Add New Account", color = Color.White)
//        }
//    }
//}
//
//fun isValidEmail(email: String): Boolean {
//    val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()
//    return email.matches(emailRegex)
//}
//
//
//fun isValidPassword(password: String): Boolean {
//    return password.length > 7
//
//}
