package com.example.aiapp.item

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.aiapp.R

@Composable
fun PickPhotoButton(onImageSelected: (Uri) -> Unit,isIconMicrophon: Boolean) {

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { onImageSelected(it) }
    }

    if (!isIconMicrophon){
        IconButton(onClick = {
            launcher.launch("image/*")
        }) {
            Icon(
                painter = painterResource(R.drawable.plus_large_svgrepo_com),
                contentDescription = "photo",
                tint = Color.DarkGray,
                modifier = Modifier
                    .padding(start = 5.dp)
                    .fillMaxSize()

            )
        }
    }



}
