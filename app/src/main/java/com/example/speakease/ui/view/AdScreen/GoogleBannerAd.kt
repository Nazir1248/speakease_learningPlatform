package com.example.speakease.ui.view.AdScreen

import android.util.Log
import android.widget.FrameLayout
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError

//@Composable
//fun GoogleBannerAd(modifier: Modifier = Modifier) {
//    val context = LocalContext.current
//    AndroidView(
//        factory = { context ->
//            AdView(context).apply {
//                setAdSize(AdSize.BANNER)
//                adUnitId = "ca-app-pub-4910715387270743/2572847549" // Replace with your actual Ad Unit ID
//                loadAd(AdRequest.Builder().build())
//            }
//        },
//        modifier = modifier
//            .fillMaxWidth()
//            .wrapContentHeight()
//    )
//}


//
//@Composable
//fun GoogleBannerAd(modifier: Modifier , adId : String){
//    Column(modifier  = modifier){
//        Spacer(modifier = Modifier.size(24.dp))
//        AndroidView(
//            modifier = Modifier.fillMaxWidth(),
//            factory = { context ->
//                AdView(context).apply{
//                    setAdSize(AdSize.BANNER)
//                    adUnitId = adId
//                    loadAd(AdRequest.Builder().build())
//                }
//            }
//        )
//    }
//}

@Composable
fun GoogleBannerAd(modifier: Modifier, adId: String) {
    Column(modifier = modifier) {
        Spacer(modifier = Modifier.size(24.dp))
        AndroidView(
            modifier = Modifier.fillMaxWidth(),
            factory = { context ->
                AdView(context).apply {
                    setAdSize(AdSize.BANNER)
                    adUnitId = adId
                    adListener = object : AdListener() {
                        override fun onAdFailedToLoad(adError: LoadAdError) {
                            Log.e("AdError", "Ad failed to load: ${adError.message}")
                        }
                    }
                    loadAd(AdRequest.Builder().build())
                }
            }
        )
    }
}