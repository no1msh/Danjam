package com.saeongjima.designsystem.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.saeongjima.designsystem.R
import com.saeongjima.designsystem.theme.Black100
import com.saeongjima.designsystem.theme.Black900

@Composable
fun CloseUpIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .clickable(role = Role.Button, onClick = onClick)
            .padding(8.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_close_up_24),
            contentDescription = stringResource(R.string.close_up_Icon_button_description),
            tint = Black100.copy(alpha = 0.6f),
            modifier = Modifier
                .size(32.dp)
                .align(Alignment.Center)
                .background(Black900.copy(alpha = 0.5f), shape = RoundedCornerShape(4.dp)),
        )
    }
}
