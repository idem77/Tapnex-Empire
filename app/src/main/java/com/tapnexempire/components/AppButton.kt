package com.tapnexempire.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AppButton(

    text: String,

    onClick: () -> Unit,

    modifier: Modifier = Modifier,

    enabled: Boolean = true
) {

    Button(

        onClick = onClick,

        enabled = enabled,

        modifier = modifier
            .fillMaxWidth(0.90f)
            .height(52.dp),

        shape = RoundedCornerShape(18.dp),

        colors = ButtonDefaults.buttonColors(

            containerColor =
                ButtonBackground,

            contentColor =
                ButtonTextColor,

            disabledContainerColor =
                Color.Gray
        )
    ) {

        Text(

            text = text,

            fontSize = 16.sp
        )
    }
}
