package com.example.onlyoffice.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.onlyoffice.models.MyProfileResponse
import com.example.onlyoffice.objects.URL
import com.example.onlyoffice.viewmodels.MyProfileViewModel
import com.example.onlyoffice.viewmodels.TokenViewModel

@Composable
fun ProfilePage(
    tokenViewModel: TokenViewModel,
    navController: NavController,
    profileViewModel: MyProfileViewModel = viewModel(),
    onScreenVisible: () -> Unit
) {
    profileViewModel.initPortal()
    profileViewModel.getProfile()
    val profile by profileViewModel.profile.observeAsState(MyProfileResponse(null))

    LaunchedEffect(Unit) {
        onScreenVisible()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 30.dp, end = 30.dp)
    ){
        Text(
            text = "Profile",
            fontSize = 28.sp,
            modifier = Modifier
                .padding(top = 70.dp)
        )
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column {
                AsyncImage(
                    model = "${URL.link} + ${profile?.response?.avatar}",
                    contentDescription = "Example Image",
                    modifier = Modifier
                        .padding(top = 40.dp)
                        .size(120.dp)
                        .clip(CircleShape)
                )
                Text(
                    text = "${profile?.response?.userName}",
                    fontSize = 22.sp,
                    modifier = Modifier
                        .padding(top = 15.dp)
                )
            }
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "E-mail",
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(top = 15.dp)
            )
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "${profile?.response?.email}",
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(top = 5.dp)
            )
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Button(
                onClick = {
                    tokenViewModel.logout()
                    navController.navigate("authPage")
                },
                modifier = Modifier
                    .padding(top = 25.dp)
                    .width(200.dp)
            ) {
                Text(
                    text = "Logout"
                )
            }
        }
    }
}