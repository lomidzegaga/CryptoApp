package com.example.listio.presenter.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.listio.R
import com.example.listio.utils.customPadding
import com.example.listio.utils.params.ParamsIconItem
import com.example.listio.utils.params.ParamsText
import com.example.listio.utils.percentTextColor

@Composable
fun IconItem(
    params: ParamsIconItem,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .customPadding(params.mainRowPadding)
    ) {
        AsyncImage(
            model = params.imageSource ?: R.drawable.bitcoin,
            contentDescription = params.contentDescription,
            modifier = Modifier
                .size(params.imageSize)
                .clip(params.clip),
            contentScale = ContentScale.Crop
        )

        Row(
            modifier = Modifier
                .weight(1f)
                .customPadding(params.nestedRowPadding),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(params.spaceBy)
            ) {
                CustomText(
                    params = ParamsText(
                        text = params.coinText
                    )
                )

                CustomText(
                    params = ParamsText(
                        text = params.percentText,
                        color = params.percentText.percentTextColor(),
                        fontSize = TextUnit.Unspecified
                    )
                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(params.spaceBy),
                horizontalAlignment = Alignment.End
            ) {
                CustomText(
                    params = ParamsText(
                        text = params.moneyText, fontSize = 19.sp
                    )
                )
                CustomText(
                    params = ParamsText(
                        text = params.coinChangeText,
                        fontSize = TextUnit.Unspecified,
                        color = params.coinChangeTextColor
                    )
                )
            }
        }
    }
}