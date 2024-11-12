package com.example.listio.presenter.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.presentation.util.boldBlack
import com.example.core.presentation.util.lightGray
import com.example.core.presentation.util.lightGreen
import com.example.core.presentation.util.mediumGray
import com.example.core.presentation.util.white

@Composable
fun SearchFieldComposable(
    searchText: String,
    isListLoading: Boolean,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {

    OutlinedTextField(
        value = searchText,
        onValueChange = onClick,
        singleLine = true,
        placeholder = {
            Text(text = "Search", color = lightGray)
        },
        leadingIcon = {
            Box(modifier = Modifier
                .clip(RoundedCornerShape(50.dp))
                .background(mediumGray)
                .clickable { }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    modifier = Modifier.padding(14.dp)
                )
            }
        },
        trailingIcon = {
            if (isListLoading) {
                CircularProgressIndicator(
                    trackColor = lightGreen,
                    strokeWidth = 3.dp,
                    modifier = Modifier.size(20.dp)
                )
            }
        },
        shape = RoundedCornerShape(30.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = boldBlack,
            unfocusedContainerColor = boldBlack,
            focusedTextColor = white,
            unfocusedTextColor = white,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
    )
}

@Preview
@Composable
fun SearchFieldComposablePreview() {
    SearchFieldComposable(
        searchText = "",
        isListLoading = false,
        onClick = { }
    )
}