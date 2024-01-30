package com.example.wezelnews.presentation.newsnavigator

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.DrawableRes
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wezelnews.R
import com.example.wezelnews.onboarding.Dimens.ExtraSmallPadding
import com.example.wezelnews.onboarding.Dimens.ExtraSmallPadding2
import com.example.wezelnews.onboarding.Dimens.IconSize

@Composable
fun NewsBottomNavigation(
    items: List<BottomNavigationItem>,
    selected: Int,
    onItemClick: (Int) -> Unit
) {
    val isDarkMode = isSystemInDarkTheme()
    val backgroundColor =
        if (isDarkMode) Color.Black else MaterialTheme.colorScheme.surface
   val iconbackgroundcolor=if(isDarkMode) Color.White else Color.Black
    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = backgroundColor,
        tonalElevation = 10.dp
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = index == selected,
                onClick = { onItemClick(index) },
                icon = {
                    Column(horizontalAlignment = CenterHorizontally) {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = null,
                            modifier = Modifier.size(IconSize)
                        )
                        Spacer(modifier = Modifier.height(ExtraSmallPadding2))
                        Text(text = item.text, style = MaterialTheme.typography.labelSmall)
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = colorResource(id = R.color.lightblue),
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = iconbackgroundcolor,
                    unselectedTextColor = colorResource(id = R.color.body),
                    indicatorColor = backgroundColor
                )
            )
        }
    }


}


data class BottomNavigationItem(
    @DrawableRes val icon: Int,
    val text: String
)


@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun NewsNavigationPreview(){
    NewsBottomNavigation(items = listOf(
        BottomNavigationItem(icon = R.drawable.ic_home,"Home"),
        BottomNavigationItem(icon = R.drawable.ic_search,"Search"),
        BottomNavigationItem(icon = R.drawable.ic_bookmark,"Bookmark"))
        , selected =0 , onItemClick = {} )
}