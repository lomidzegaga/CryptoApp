package com.example.listio.presenter.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.listio.R
import com.example.listio.presenter.composables.CustomButton
import com.example.listio.presenter.composables.CustomText
import com.example.listio.presenter.composables.IconItem
import com.example.listio.utils.CustomColors
import com.example.listio.utils.MainScreenActions
import com.example.listio.utils.Padding
import com.example.listio.utils.clickableWithoutRipple
import com.example.listio.utils.params.ParamsButton
import com.example.listio.utils.params.ParamsIconItem
import com.example.listio.utils.params.ParamsMainScreen
import com.example.listio.utils.params.ParamsText

@Composable
fun MainScreen(
    params: ParamsMainScreen,
    onClick: (MainScreenActions) -> Unit,
) {

    var text by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .systemBarsPadding()
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            singleLine = true,
            placeholder = {
                Text(text = "Search", color = Color(0xFF535353))
            },
            leadingIcon = {
                Box(modifier = Modifier
                    .clip(RoundedCornerShape(50.dp))
                    .background(Color(0xFF2B2B2B))
                    .clickable { }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        modifier = Modifier.padding(14.dp)
                    )
                }
            },
            shape = RoundedCornerShape(30.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFF161616),
                unfocusedContainerColor = Color(0xFF161616),
                focusedTextColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .background(Color(0xFF161616), shape = RoundedCornerShape(20.dp))
        ) {
            CustomText(
                params = ParamsText(
                    text = params.totalAmountText,
                    fontSize = 25.sp,
                    color = Color(0xFFACD577),
                    padding = Padding.All(17.dp)
                )
            )

            IconItem(
                params = ParamsIconItem(
                    coinText = params.randomCoin.name,
                    moneyText = params.randomCoin.price,
                    percentText = params.randomCoin.percentChange24h.toString() + " %",
                    coinChangeText = params.randomCoin.changeFromAth,
                    imageSource = R.drawable.bitcoin,
                    mainRowPadding = Padding.Vertical(horizontal = 20.dp, vertical = 7.dp)
                )
            )

            CustomButton(
                params = ParamsButton(
                    text = "Buy",
                    buttonPadding = Padding.Vertical(vertical = 25.dp, horizontal = 20.dp),
                    textPadding = Padding.All(13.dp)
                )
            ) {
                onClick(MainScreenActions.OnBuyClick)
            }
        }

        AnimatedVisibility(
            visible = params.isCoinDetailLoaded,
            enter = fadeIn()
        ) {
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 10.dp),
                color = Color.Black,
                trackColor = CustomColors.buttonGreenColor
            )
        }

        CustomText(
            params = ParamsText(
                text = "Explore new",
                fontSize = TextUnit.Unspecified,
                padding = Padding.Each(start = 20.dp, top = 17.dp, bottom = 8.dp)
            )
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 13.dp)
                .background(Color(0xFF161616), shape = RoundedCornerShape(20.dp))
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(vertical = 20.dp)
            ) {
                items(params.list) { coin ->
                    IconItem(
                        params = ParamsIconItem(
                            coinText = coin.name,
                            moneyText = coin.price,
                            percentText = coin.percentChange24h.toString() + " %",
                            coinChangeText = coin.changeFromAth,
                            imageSize = 50.dp,
                            imageSource = R.drawable.bitcoin,
                            mainRowPadding = Padding.Vertical(horizontal = 15.dp, vertical = 7.dp)
                        ),
                        modifier = Modifier
                            .clickableWithoutRipple {
                                onClick(MainScreenActions.OnCoinDetailsClick(coinId = coin.id))
                            }
                    )
                }
            }
        }
    }
}