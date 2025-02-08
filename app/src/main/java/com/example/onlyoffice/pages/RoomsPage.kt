package com.example.onlyoffice.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.onlyoffice.R
import com.example.onlyoffice.models.Folder
import com.example.onlyoffice.models.RoomResponse
import com.example.onlyoffice.objects.URL
import com.example.onlyoffice.ui.theme.Purple40
import com.example.onlyoffice.viewmodels.RoomViewModel

@Composable
fun RoomsPage(
    roomViewModel: RoomViewModel = viewModel(),
    onScreenVisible: () -> Unit
) {
    LaunchedEffect(Unit) {
        onScreenVisible()
        roomViewModel.initPortal(URL.link)
        roomViewModel.getRooms()
    }

    val rooms by roomViewModel.rooms.observeAsState(RoomResponse(null))
    val currentRooms = rooms

    Column(
        modifier = Modifier
            .fillMaxSize()
    ){
        Text(
            text = "Rooms",
            fontSize = 28.sp,
            modifier = Modifier
                .padding(top = 70.dp, start = 30.dp, end = 30.dp)
        )
        if(currentRooms?.response != null) {
            LazyColumn(
                modifier = Modifier.padding(top = 18.dp)
            ) {
                if (currentRooms.response.folders.isNotEmpty()) {
                    items(
                        count = currentRooms.response.folders.size,
                        key = {
                            currentRooms.response.folders[it].id
                        },
                        itemContent = { index ->
                            RoomButton(currentRooms.response.folders[index])
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun RoomButton(
    room: Folder
) {
    Button(
        onClick = {  },
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        shape = ShapeDefaults.Small,
        contentPadding = PaddingValues(0.dp),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column {
            Row(
                modifier = Modifier
                    .padding(start = 30.dp)
            )
            {
                Box(
                    modifier = Modifier
                        .size(35.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.Transparent, CircleShape)
                        .background(Purple40)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_room_service_24),
                        contentDescription = "Room",
                        modifier = Modifier
                            .align(Alignment.Center)
                    )
                }
                Text(
                    text = room.title,
                    color = Color.Gray,
                    modifier = Modifier
                        .padding(top = 8.dp, start = 10.dp)
                )
            }
            HorizontalDivider(
                color = Color.Gray,
                modifier = Modifier
                    .padding(top = 5.dp)
                    .fillMaxWidth()
                    .padding(start = 30.dp, end = 30.dp)
            )
        }
    }
}