package com.samkt.statemanagement

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.samkt.statemanagement.ui.theme.StateManagementTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StateManagementTheme {
                val viewModel: LoginScreenViewModel = viewModel()
                val state = viewModel.loginScreenState.collectAsState().value

                LoginScreen(
                    loginScreenState = state,
                    onEvent = viewModel::onEvent
                )
            }
        }
    }
}

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    loginScreenState: LoginScreenState,
    onEvent: (LoginScreenEvents) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = "Welcome Happy To see you.Login to your account",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold
            ),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(modifier = Modifier.fillMaxWidth(),
            value = loginScreenState.email,
            onValueChange = { value ->
                onEvent(LoginScreenEvents.OnEmailChange(value))
            },
            label = {
                Text(text = "Email")
            })
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = loginScreenState.password,
            onValueChange = { value ->
                onEvent(LoginScreenEvents.OnPasswordChange(value))
            },
            visualTransformation = if (loginScreenState.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            label = {
                Text(text = "Password")
            },
            trailingIcon = {
                IconButton(onClick = {
                    onEvent(LoginScreenEvents.OnEyeClicked)
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_eye),
                        contentDescription = null
                    )
                }
            }
        )
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                onEvent(LoginScreenEvents.OnLoginClicked)
            },
            shape = RoundedCornerShape(8.dp),
            content = {
                Text(text = "Login")
            }
        )
    }
}


@Preview(showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    StateManagementTheme {
        LoginScreen(
            loginScreenState = LoginScreenState(),
            onEvent = {}
        )
    }
}