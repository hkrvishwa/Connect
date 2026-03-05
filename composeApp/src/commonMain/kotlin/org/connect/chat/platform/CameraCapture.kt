package org.connect.chat.platform

import androidx.compose.runtime.Composable

@Composable
expect fun CameraCapture(
    onImageCaptured: (ByteArray) -> Unit,
    onError: (String) -> Unit
)