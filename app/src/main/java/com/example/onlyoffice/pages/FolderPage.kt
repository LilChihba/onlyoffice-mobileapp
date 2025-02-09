package com.example.onlyoffice.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.onlyoffice.models.DocumentsResponse
import com.example.onlyoffice.objects.URL
import com.example.onlyoffice.viewmodels.DocumentViewModel

@Composable
fun FolderPage(
    folderId: Int,
    navController: NavController,
    documentViewModel: DocumentViewModel = viewModel(),
    onScreenVisible: () -> Unit
) {
    LaunchedEffect(Unit) {
        onScreenVisible()
        documentViewModel.initPortal(URL.link)
        documentViewModel.getFolderById(folderId)
    }

    val documents by documentViewModel.documents.observeAsState(DocumentsResponse(null))
    val currentDocuments = documents
    if (currentDocuments != null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ){
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
            }
            Text(
                text = currentDocuments.response!!.current.title,
                fontSize = 28.sp,
                modifier = Modifier
                    .padding(top = 70.dp, start = 30.dp, end = 30.dp)
            )
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
                            FolderButton(currentDocuments.response.folders[index], navController)
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