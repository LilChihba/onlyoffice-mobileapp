package com.example.onlyoffice.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.onlyoffice.models.TrashResponse
import com.example.onlyoffice.objects.URL
import com.example.onlyoffice.viewmodels.TrashViewModel

@Composable
fun TrashPage(
    trashViewModel: TrashViewModel = viewModel(),
    onScreenVisible: () -> Unit
) {
    LaunchedEffect(Unit) {
        onScreenVisible()
        trashViewModel.initPortal(URL.link)
        trashViewModel.getTrash()
    }

    val trash by trashViewModel.trash.observeAsState(TrashResponse(null))
    val currentTrash = trash

    Column(
        modifier = Modifier
            .fillMaxSize()
    ){
        Text(
            text = "Trash",
            fontSize = 28.sp,
            modifier = Modifier
                .padding(top = 70.dp, start = 30.dp, end = 30.dp)
        )
        if(currentTrash?.response != null) {
            LazyColumn(
                modifier = Modifier.padding(top = 18.dp)
            ) {
                if (currentTrash.response.folders.isNotEmpty()) {
                    items(
                        count = currentTrash.response.folders.size,
                        key = {
                            currentTrash.response.folders[it].id
                        },
                        itemContent = { index ->
                            FolderButton(currentTrash.response.folders[index])
                        }
                    )
                }
                if (currentTrash.response.files.isNotEmpty()) {
                    items(
                        count = currentTrash.response.files.size,
                        key = {
                            currentTrash.response.files[it].id
                        },
                        itemContent = { index ->
                            FileButton(currentTrash.response.files[index])
                        }
                    )
                }
            }
        }
    }
}