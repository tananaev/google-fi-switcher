package com.tananaev.switcher

import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import com.tananaev.switcher.ui.FiSwitcherTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FiSwitcherTheme {
                Surface(color = MaterialTheme.colors.background) {
                    MainScreen()
                }
            }
        }
    }
}

private fun setClipboard(context: Context, text: String) {
    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
    val clip = ClipData.newPlainText("Copied Text", text)
    clipboard.setPrimaryClip(clip)
}

private fun onClick(context: Context, code: String) {
    setClipboard(context, code)
    startActivity(context, Intent(Intent.ACTION_DIAL), null)
    Toast.makeText(context, R.string.toast_info, Toast.LENGTH_LONG).show()
}

@Composable
fun MainScreen() {
    Scaffold {
        Box(modifier = Modifier.padding(it)) {
            Content()
        }
    }
}

@Composable
fun Content() {
    Column(Modifier.padding(8.dp)) {
        Row {
            ImageCard(Modifier.weight(1f), "*#*#34866#*#*", R.drawable.ic_tmobile)
            ImageCard(Modifier.weight(1f), "*#*#34777#*#*", R.drawable.ic_sprint)
            ImageCard(Modifier.weight(1f), "*#*#34872#*#*", R.drawable.ic_uscellular)
        }
        TextCard(Modifier.fillMaxWidth(), "*#*#342886#*#*", R.string.label_auto)
        TextCard(Modifier.fillMaxWidth(), "*#*#346398#*#*", R.string.label_next)
        TextCard(Modifier.fillMaxWidth(), "*#*#34963#*#*", R.string.label_fixme)
        TextCard(Modifier.fillMaxWidth(), "*#*#344636#*#*", R.string.label_net)
        TextCard(Modifier.fillMaxWidth(), "*#*#4636#*#*", R.string.label_info)
        TextCard(Modifier.fillMaxWidth(), "*#*#33284#*#*", R.string.label_debug)
        TextCard(Modifier.fillMaxWidth(), "*#*#794824746#*#*", R.string.label_sim)
    }
}

@Composable
fun ImageCard(modifier: Modifier = Modifier, code: String, @DrawableRes image: Int) {
    val context = LocalContext.current
    Card(modifier.padding(8.dp).clickable(onClick = { onClick(context, code) }), elevation = 4.dp) {
        Image(painter = painterResource(id = image), contentDescription = "")
    }
}

@Composable
fun TextCard(modifier: Modifier = Modifier, code: String, @StringRes text: Int) {
    val context = LocalContext.current
    Card(modifier.padding(8.dp).clickable(onClick = { onClick(context, code) }), elevation = 4.dp) {
        Text(stringResource(id = text), Modifier.padding(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FiSwitcherTheme {
        MainScreen()
    }
}
