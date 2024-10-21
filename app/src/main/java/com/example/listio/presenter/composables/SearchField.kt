package com.example.listio.presenter.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SearchFieldComposable(
    searchText: String,
    onClick: (String) -> Unit
) {

    OutlinedTextField(
        value = searchText,
        onValueChange = onClick,
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
}