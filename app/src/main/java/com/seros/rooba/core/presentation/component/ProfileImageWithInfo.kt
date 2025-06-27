package com.seros.rooba.core.presentation.component

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material.icons.outlined.Cases
import androidx.compose.material.icons.outlined.LocationCity
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.seros.rooba.R
import com.seros.rooba.core.domain.model.Chip
import com.seros.rooba.core.domain.model.User
import com.seros.rooba.core.utils.calculateDistance


@Composable
fun ProfileImageWithInfo(
    user: User,
    onClickEmoji: () -> Unit,
    onClickReturnArrow: () -> Unit,
    onClickUserX: () -> Unit
) {
    val photoList = user.photos ?: emptyList()
    val pagerState = rememberPagerState { photoList.size }
    val scrollState = rememberScrollState()
    val scrollOffset = scrollState.value.toFloat()
    val scaleFactor = 1f + (scrollOffset.coerceIn(0f, 300f) / 1000f)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(520.dp)
            .graphicsLayer {
                scaleX = scaleFactor
                scaleY = scaleFactor
                translationY = scrollOffset * 0.1f
            }
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            AsyncImage(
                model = photoList[page],
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        Row(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 16.dp)
        ){
            OneChip(
                chip = Chip(
                    name = calculateDistance(),
                    icon = R.drawable.ic_distance
                ),
                background = Color.White,
                iconTint = MaterialTheme.colorScheme.secondary
            )
        }

        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            repeat(photoList.size) { index ->
                val isSelected = pagerState.currentPage == index
                val animatedWidth by animateDpAsState(
                    targetValue = if (isSelected) 24.dp else 8.dp,
                    label = "indicatorWidth"
                )
                Box(
                    modifier = Modifier
                        .width(animatedWidth)
                        .height(6.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(
                            if (isSelected) Color.White else Color.LightGray.copy(alpha = 0.5f)
                        )
                )
            }
        }


        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp, bottom = 48.dp)
            ) {
                Text(
                    text = "${user.name}, ${user.age}",
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.White
                )
                TextWithIcon(
                    text = user.jobTitle,
                    textColor = Color.White,
                    textStyle = MaterialTheme.typography.bodyMedium,
                    icon = Icons.Outlined.Cases,
                    iconTint = Color.White
                )
                user.education?.let {
                    TextWithIcon(
                        text = it,
                        textColor = Color.White,
                        textStyle = MaterialTheme.typography.bodyMedium,
                        icon = Icons.Outlined.Book,
                        iconTint = Color.White
                    )
                }
                TextWithIcon(
                    text = user.location,
                    textColor = Color.White,
                    textStyle = MaterialTheme.typography.bodyMedium,
                    icon = Icons.Outlined.LocationCity,
                    iconTint = Color.White
                )
            }

            Column(
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(bottom = 56.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                RoundActionButton(
                    icon = R.drawable.emoji_2,
                    onClick = onClickEmoji,
                    modifier = Modifier
                        .size(68.dp)
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.Bottom
                ) {
                    RoundActionButton(
                        icon = R.drawable.user_x,
                        onClick = onClickUserX,
                        modifier = Modifier
                            .size(56.dp)
                    )
                    RoundActionButton(
                        icon = R.drawable.arrow_back,
                        onClick = onClickReturnArrow,
                        modifier = Modifier
                            .size(56.dp)
                            .offset(y = 8.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun RoundActionButton(
    icon: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.background)
            .border(2.dp, MaterialTheme.colorScheme.secondary, CircleShape)
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}

