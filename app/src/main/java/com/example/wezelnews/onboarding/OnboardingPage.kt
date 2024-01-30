package com.example.wezelnews.onboarding

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wezelnews.R
import com.example.wezelnews.onboarding.Dimens.MediumPadding1
import com.example.wezelnews.onboarding.Dimens.MediumPadding2
import com.example.wezelnews.ui.theme.WezelNewsTheme

@Composable
fun OnboardingPage(
    modifier: Modifier=Modifier,
    page: Page
){
Column(modifier=modifier) {
Image(modifier= Modifier
    .fillMaxWidth()
    .fillMaxHeight(fraction = 0.5f),
    painter = painterResource(id = page.image),
    contentScale = ContentScale.Crop,
    contentDescription = null)
    val textColor = if (isSystemInDarkTheme()) {
        Color.White // Use white text color for dark theme
    } else {
        Color.Black // Use black text color for light theme
    }
    Spacer(modifier = Modifier.height(MediumPadding1))
        Text(text = page.title, modifier = Modifier.padding(horizontal = MediumPadding2).height(140.dp),
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
            color = textColor
        )
    Text(text = page.description,
        modifier=Modifier.padding(horizontal = MediumPadding2).height(50.dp),
        style = MaterialTheme.typography.bodyMedium,
        color = textColor
    )
}
}
@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun OnboardingPagePreview(){
    WezelNewsTheme {
        OnboardingPage(
        page = pages[0]
        )
    }

}
