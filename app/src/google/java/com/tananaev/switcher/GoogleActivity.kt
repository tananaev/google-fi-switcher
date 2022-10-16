package com.tananaev.switcher

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import com.tananaev.switcher.ui.FiSwitcherTheme

class GoogleActivity : AppCompatActivity() {
    private lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseAnalytics = Firebase.analytics
        MobileAds.initialize(this) {}
        setContent {
            FiSwitcherTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Box {
                        MainScreen()
                        AdvertView(modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter))
                    }
                }
            }
        }
    }
}

@Composable
fun AdvertView(modifier: Modifier = Modifier) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            AdView(context).apply {
                setAdSize(AdSize.BANNER)
                //adUnitId = "ca-app-pub-3940256099942544/6300978111" // test
                adUnitId = "ca-app-pub-9061647223840223/8013136321"
                loadAd(AdRequest.Builder().build())
            }
        }
    )
}
