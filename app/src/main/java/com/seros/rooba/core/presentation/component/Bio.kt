package com.seros.rooba.core.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp

@Composable
fun UserBio(bio: String?) {
    var expanded by remember { mutableStateOf(false) }

    if (!bio.isNullOrEmpty()) {
        val showToggle = bio.length > 100
        val textToShow = remember(bio, expanded) {
            if (expanded || !showToggle) bio
            else bio.take(100).trimEnd() + "..."
        }

        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            Text(
                text = "About me",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground
            )

            val annotatedText = buildAnnotatedString {
                append(textToShow)
                if (showToggle) {
                    append(" ")
                    pushStringAnnotation(tag = "TOGGLE", annotation = "toggle")
                    withStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colorScheme.onBackground,
                            fontWeight = FontWeight.Medium
                        )
                    ) {
                        append(if (expanded) "Скрыть" else "Показать больше")
                    }
                    pop()
                }
            }

            ClickableText(
                text = annotatedText,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.outlineVariant
                ),
                onClick = { offset ->
                    annotatedText.getStringAnnotations("TOGGLE", offset, offset)
                        .firstOrNull()?.let {
                            expanded = !expanded
                        }
                }
            )
        }
    }
}

