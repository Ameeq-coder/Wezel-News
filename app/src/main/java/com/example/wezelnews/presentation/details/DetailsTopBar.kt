package com.example.wezelnews.presentation.details

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.wezelnews.R
import com.example.wezelnews.ui.theme.WezelNewsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsTopBar(
    onBrowsingClick: () -> Unit,
    onShareClick: () -> Unit,
    onBookmarkClick: () -> Unit,
    onBackClick: () -> Unit
) {
    val isDarkMode = isSystemInDarkTheme()
    val backgroundColor =
        if (isDarkMode) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.surface
    val iconColor =
        if (isDarkMode) Color.White else colorResource(id = R.color.body) // Use your light mode color resource
    Box(modifier = Modifier.background(backgroundColor)) {


        TopAppBar(
            title = { },
            modifier = Modifier.fillMaxWidth(),
            colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = Color.Transparent,
                actionIconContentColor = colorResource(id = R.color.body),
                navigationIconContentColor = colorResource(id = R.color.body)
            ),
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back_arrow),
                        contentDescription = null,
                        tint = iconColor
                    )
                }
            },
            actions = {
                IconButton(onClick = onBookmarkClick) {
                    Icon(painter = painterResource(id = R.drawable.ic_bookmark), contentDescription = null, tint = iconColor)
                }
                IconButton(onClick = onShareClick) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        tint = iconColor

                    )
                }
                IconButton(onClick = onBrowsingClick) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_network),
                        contentDescription = null,
                        tint = iconColor

                    )
                }
            }


        )

    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun DetailsTopBarPreview() {
    WezelNewsTheme {
        DetailsTopBar(
            onBrowsingClick = { /*TODO*/ },
            onShareClick = { /*TODO*/ },
            onBookmarkClick = { /*TODO*/ }) {

        }
    }
}