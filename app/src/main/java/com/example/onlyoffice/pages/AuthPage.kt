package com.example.onlyoffice.pages

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults.OutlinedTextFieldDecorationBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.onlyoffice.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthPage(onScreenVisible: () -> Unit) {
    LaunchedEffect(Unit) {
        onScreenVisible()
    }

    val textPortal = remember { mutableStateOf("") }
    val textEmail = remember { mutableStateOf("") }
    val textPassword = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 30.dp, end = 30.dp)
    ){
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 100.dp)
        ){
            Text(
                text = "Connect to ONLYOFFICE",
                fontSize = 30.sp,
            )
        }

        BasicTextField(
            value = textPortal.value,
            onValueChange = { newText -> textPortal.value = newText },
            singleLine = true,
            decorationBox = { innerTextField ->
                OutlinedTextFieldDecorationBox(
                    value = textPortal.value,
                    innerTextField = innerTextField,
                    enabled = true,
                    singleLine = false,
                    visualTransformation = VisualTransformation.None,
                    interactionSource = remember { MutableInteractionSource() },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_edit_location_24),
                            contentDescription = "portal",
                        )
                    },
                    placeholder = {
                        Text(
                            text = "Portal"
                        )
                    }
                )
            },
            modifier = Modifier
                .padding(top = 70.dp)
                .fillMaxWidth()
                .height(50.dp)
        )

        BasicTextField(
            value = textEmail.value,
            onValueChange = { newText -> textEmail.value = newText },
            singleLine = true,
            decorationBox = { innerTextField ->
                OutlinedTextFieldDecorationBox(
                    value = textEmail.value,
                    innerTextField = innerTextField,
                    enabled = true,
                    singleLine = false,
                    visualTransformation = VisualTransformation.None,
                    interactionSource = remember { MutableInteractionSource() },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_email_24),
                            contentDescription = "email",
                        )
                    },
                    placeholder = {
                        Text(
                            text = "E-mail"
                        )
                    }
                )
            },
            modifier = Modifier
                .padding(top = 15.dp)
                .fillMaxWidth()
                .height(50.dp)
        )

        BasicTextField(
            value = textPassword.value,
            onValueChange = { newText -> textPassword.value = newText },
            singleLine = true,
            decorationBox = { innerTextField ->
                OutlinedTextFieldDecorationBox(
                    value = textPassword.value,
                    innerTextField = innerTextField,
                    enabled = true,
                    singleLine = false,
                    visualTransformation = VisualTransformation.None,
                    interactionSource = remember { MutableInteractionSource() },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_key_24),
                            contentDescription = "password",
                        )
                    },
                    placeholder = {
                        Text(
                            text = "Password"
                        )
                    }
                )
            },
            modifier = Modifier
                .padding(top = 15.dp, bottom = 30.dp)
                .fillMaxWidth()
                .height(50.dp)
        )

        Button(
            onClick = {  },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Login"
            )
        }
    }
}