package com.taskchaintechnetwork

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.taskchaintechnetwork.ui.theme.TaskChaintechNetworkTheme
import com.taskchaintechnetwork.view.PasswordManagerScreen
import com.taskchaintechnetwork.viewModel.AccountViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val viewModel: AccountViewModel = ViewModelProvider(this)[AccountViewModel::class.java]
        setContent {
            TaskChaintechNetworkTheme {
                PasswordManagerScreen(viewModel)
            }
        }
    }
}
