package com.saeongjima.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saeongjima.designsystem.R
import com.saeongjima.designsystem.theme.Black200
import com.saeongjima.designsystem.theme.Black300
import com.saeongjima.designsystem.theme.Black400
import com.saeongjima.designsystem.theme.Black600
import com.saeongjima.designsystem.theme.Black800

@Composable
fun PhotoSelectGuideBox(onClick: () -> Unit, modifier: Modifier = Modifier, description: String) {
    Row(
        modifier = modifier
            .background(Black300, shape = RoundedCornerShape(4.dp))
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(74.dp)
                .background(Black200, RoundedCornerShape(4.dp))
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_camera_24),
                contentDescription = null,
                tint = Black400,
                modifier = Modifier
                    .size(54.dp)
                    .align(Alignment.Center)
            )
        }
        Column(modifier = Modifier.padding(start = 16.dp)) {
            Text(
                text = description,
                style = MaterialTheme.typography.titleLarge.copy(lineHeight = 24.sp),
                color = Black800,
            )
            Text(
                text = stringResource(R.string.photo_guide_box_sub_description),
                style = MaterialTheme.typography.labelLarge,
                color = Black600,
            )
        }
    }
}
