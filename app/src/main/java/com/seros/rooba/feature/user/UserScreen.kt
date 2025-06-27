package com.seros.rooba.feature.user

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.seros.rooba.R
import com.seros.rooba.core.domain.model.User
import com.seros.rooba.core.presentation.component.OneChip
import com.seros.rooba.core.presentation.component.ProfileImageWithInfo
import com.seros.rooba.core.presentation.component.UserBio
import com.seros.rooba.core.presentation.component.UserChipsSection
import com.seros.rooba.core.presentation.component.UserVisitedCountries


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserScreen(
    navController: NavHostController,
    user: User
) {
    val gradientBrush = Brush.horizontalGradient(
        colors = listOf(
            MaterialTheme.colorScheme.secondary, MaterialTheme.colorScheme.onTertiary
        )
    )
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                ),
                title = {
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(brush = gradientBrush)) {
                                append(stringResource(R.string.app_name))
                            }
                        },
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                actions = {
                    IconButton(onClick = {
                        Toast.makeText(context,"Settings", Toast.LENGTH_SHORT).show()
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.settings),
                            contentDescription = "Settings"
                        )
                    }
                    IconButton(onClick = {
                        Toast.makeText(context,"Notifications", Toast.LENGTH_SHORT).show()
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.notifications),
                            contentDescription = "Notifications"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .background(MaterialTheme.colorScheme.background)
        ) {
            ProfileImageWithInfo(
                user,
                onClickEmoji = {
                    Toast.makeText(context, "Emoji", Toast.LENGTH_SHORT).show()
                },
                onClickReturnArrow = {
                    navController.popBackStack()
                },
                onClickUserX = {
                    Toast.makeText(context, "Return Arrow", Toast.LENGTH_SHORT).show()
                }
            )

            Spacer(Modifier.height(16.dp))

            OneChip(user.partnerStatus)
            UserBio(user.bio)

            UserChipsSection("", user.bioTags)
            UserChipsSection("Lifestyle", user.lifeStyleTags)
            UserChipsSection("I speak", user.languages)
            UserChipsSection("My interests", user.interests)

            UserVisitedCountries(user.countriesVisited)

            Spacer(Modifier.height(24.dp))
        }
    }
}