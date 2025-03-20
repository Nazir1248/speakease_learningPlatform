package com.example.speakease.ui.view.Exercise.A1Exercise

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource

@Composable
fun ZoomableImage(
    imageResId: Int,
    modifier: Modifier = Modifier,
    maxScale: Float = 3f,
    minScale: Float = 1f
) {
    var scale by remember { mutableStateOf(1f) }
    var offset by remember { mutableStateOf(Offset.Zero) }

    Box(
        modifier = modifier
            .fillMaxSize()
            // Detect double-tap first (separate handler)
            .pointerInput(Unit) {
                detectTapGestures(
                    onDoubleTap = {
                        scale = if (scale == minScale) maxScale else minScale
                        offset = Offset.Zero // Reset position
                    }
                )
            }
            // Detect pinch-to-zoom and pan separately
            .pointerInput(Unit) {
                detectTransformGestures { _, pan, zoom, _ ->
                    scale = (scale * zoom).coerceIn(minScale, maxScale)

                    // Prevent image from moving when zoomed out
                    if (scale > minScale) {
                        offset = Offset(
                            (offset.x + pan.x).coerceIn(-((scale - 1) * size.width / 2), ((scale - 1) * size.width / 2)),
                            (offset.y + pan.y).coerceIn(-((scale - 1) * size.height / 2), ((scale - 1) * size.height / 2))
                        )
                    } else {
                        offset = Offset.Zero
                    }
                }
            }
    ) {
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = "Zoomable Image",
            modifier = Modifier
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale,
                    translationX = offset.x,
                    translationY = offset.y
                )
                .fillMaxSize()
        )
    }
}
