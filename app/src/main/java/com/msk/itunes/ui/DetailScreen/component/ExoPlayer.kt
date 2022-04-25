package com.msk.itunes.Repository.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSource


@Composable
fun ExoPlayer(url:String,modifier: Modifier){
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current


    val Player = remember(context) {
        ExoPlayer.Builder(context).build().apply {
            val datasource = DefaultDataSource.Factory(context)
            val source = ProgressiveMediaSource.Factory(datasource)
                .createMediaSource(MediaItem.fromUri(url))

            addMediaSource(source)
            prepare()
        }
    }




    DisposableEffect(Player) {
        val lifecycleObserver = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_STOP -> Player.pause()
            }
        }

        lifecycleOwner.lifecycle.addObserver(lifecycleObserver)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(lifecycleObserver)
            Player.release()
        }
    }
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        AndroidView(factory = {
            PlayerView(it).apply {
                player = Player
                useController = true
                setShowRewindButton(false)
                setShowPreviousButton(false)
                setShowNextButton(false)
                setShowFastForwardButton(false)
            }
        },modifier=Modifier.align(Alignment.Center))
    }
}

