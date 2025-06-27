package com.seros.rooba.core.data.repository

import android.graphics.drawable.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.PlayArrow
import com.seros.rooba.R
import com.seros.rooba.core.domain.model.Chip
import com.seros.rooba.core.domain.model.CountryItem
import com.seros.rooba.core.domain.model.User
import com.seros.rooba.core.domain.repository.UserRepository
import kotlinx.coroutines.delay

class UserRepositoryImpl : UserRepository {
    private val users = listOf(
        User(
            id = "1",
            photos = listOf(
                "https://ik.imagekit.io/az3dg9ktt/1644939029_1-fikiwiki-com-p-kartinki-krasivikh-lyudei-1.jpg",
                "https://ik.imagekit.io/az3dg9ktt/portrait-white-man-isolated_53876-40306.avif",
                "https://ik.imagekit.io/az3dg9ktt/young-bearded-man-with-white-t-shirt_273609-7190.avif?updatedAt=1750948983430",
                "https://ik.imagekit.io/az3dg9ktt/come-me-let-me-cuddle-you-portrait-cute-friendly-looking-man-casual-outfit-pulling-arms-towards-smiling-broadly_176420-24921.avif?updatedAt=1750948983449"
            ),
            name = "Amanda",
            age = 22,
            gender = "Female",
            jobTitle = "Product Manager at Apple",
            location = "Chicago, IL, United States",
            education = "UCSF",
            partnerStatus = Chip(R.drawable.hearts, "Long-term partner"),
            bio = "Hey, my name is Amanda. I like traveling and house music Hey, my name is Amanda. I like traveling and house music Hey, my name is Amanda. I like traveling and house music ...",
            bioTags = listOf(
                Chip(R.drawable.catholic, "Catholic"),
                Chip(R.drawable.ruler, "5'5"),
                Chip(R.drawable.gemini, "gemini"),
            ),
            lifeStyleTags = listOf(
                Chip(R.drawable.baby, "I have a children"),
                Chip(R.drawable.car, "Not sure yet"),
                Chip(R.drawable.no_smoking, "Non-smoker"),
                Chip(R.drawable.dragon, "gemini"),
                Chip(R.drawable.wine, "Sometimes"),
            ),
            languages = listOf(
                Chip(R.drawable.spanish, "Spanish"),
                Chip(R.drawable.persian, "Persian"),
                Chip(R.drawable.franch, "Franch"),
            ),
            interests = listOf(
                Chip(R.drawable.headphone, "Music"),
                Chip(R.drawable.movies, "Movies"),
                Chip(R.drawable.horse_racing, "Horse riding"),
                Chip(R.drawable.meditation, "Meditation"),
                Chip(R.drawable.theater, "Theater"),
                Chip(R.drawable.chess, "Chess"),
                Chip(R.drawable.art, "Art"),
                Chip(R.drawable.shopping, "Shopping"),
            ),
            countriesVisited = listOf(
                CountryItem(
                    "Italy",
                    "https://ik.imagekit.io/az3dg9ktt/italy.jpg?updatedAt=1750950042492"
                ),
                CountryItem(
                    "Japan",
                    "https://ik.imagekit.io/az3dg9ktt/japan.jpg?updatedAt=1750950042410"
                ),
                CountryItem(
                    "France",
                    "https://ik.imagekit.io/az3dg9ktt/francejpg.jpg?updatedAt=1750950042143"
                )
            )
        )
    )

    override suspend fun getUsers(): List<User> = users

    override suspend fun getUserById(id: String): User? {
        return users.find { it.id == id }
    }
}
