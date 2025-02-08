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
import com.example.onlyoffice.models.DocumentsResponse
import com.example.onlyoffice.models.File
import com.example.onlyoffice.models.Folder
import com.example.onlyoffice.objects.URL
import com.example.onlyoffice.ui.theme.Purple40
import com.example.onlyoffice.viewmodels.DocumentViewModel

@Composable
fun DocumentsPage(
    documentViewModel: DocumentViewModel = viewModel(),
    onScreenVisible: () -> Unit
) {
    LaunchedEffect(Unit) {
        onScreenVisible()
        documentViewModel.initPortal(URL.link)
        documentViewModel.getDocuments()
    }

    val documents by documentViewModel.documents.observeAsState(DocumentsResponse(null))
    val currentDocuments = documents
    Column(
        modifier = Modifier
            .fillMaxSize()
    ){
        Text(
            text = "Documents",
            fontSize = 28.sp,
            modifier = Modifier
                .padding(top = 70.dp, start = 30.dp, end = 30.dp)
        )
        if(currentDocuments?.response != null) {
            LazyColumn(
                modifier = Modifier.padding(top = 18.dp)
            ) {
                if(currentDocuments.response.folders.isNotEmpty()) {
                    items(
                        count = currentDocuments.response.folders.size,
                        key ={
                            currentDocuments.response.folders[it].id
                        },
                        itemContent = { index ->
                            FolderButton(currentDocuments.response.folders[index])
                        }
                    )
                }
                if(currentDocuments.response.files.isNotEmpty()) {
                    items(
                        count = currentDocuments.response.files.size,
                        key ={
                            currentDocuments.response.files[it].id
                        },
                        itemContent = { index ->
                            FileButton(currentDocuments.response.files[index])
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun FolderButton(
    folder: Folder
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
                        painter = painterResource(R.drawable.baseline_folder_24),
                        contentDescription = "Folder",
                        modifier = Modifier
                            .align(Alignment.Center)
                    )
                }
                Text(
                    text = folder.title,
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

@Composable
fun FileButton(
    file: File
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
                        painter = painterResource(R.drawable.baseline_insert_drive_file_24),
                        contentDescription = "File",
                        modifier = Modifier
                            .align(Alignment.Center)
                    )
                }
                Text(
                    text = file.title,
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