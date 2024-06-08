package com.saeongjima.designsystem.component

import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ScrollView
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.saeongjima.designsystem.R

@Composable
fun ScrollableView(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    val context = LocalContext.current
    AndroidView(
        modifier = modifier.padding(16.dp),
        factory = {
            val scrollView = ScrollView(it)
            val layout = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            scrollView.layoutParams = layout
            scrollView.isVerticalFadingEdgeEnabled = true
            scrollView.isScrollbarFadingEnabled = false
            scrollView.isVerticalScrollBarEnabled = true
            scrollView.scrollBarSize = 12
            scrollView.scrollBarStyle = View.SCROLLBARS_OUTSIDE_INSET
            scrollView.isSmoothScrollingEnabled = true
            scrollView.verticalScrollbarThumbDrawable = AppCompatResources.getDrawable(
                context,
                R.drawable.scroll_bar,
            )
            scrollView.addView(
                ComposeView(it).apply {
                    setContent {
                        content()
                    }
                },
            )
            val linearLayout = LinearLayout(it)
            linearLayout.orientation = LinearLayout.VERTICAL
            linearLayout.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            linearLayout.addView(scrollView)
            linearLayout
        },
    )
}
