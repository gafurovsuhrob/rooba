package com.seros.rooba.core.domain.model


data class User(
    val id: String,
    val photos: List<String>? = emptyList(),
    val name: String,
    val age: Int,
    val gender: String,
    val jobTitle: String,
    val location: String,
    val education: String? = "",
    val partnerStatus: Chip? = null,
    val bio: String? = "",
    val bioTags: List<Chip>? = emptyList(),
    val lifeStyleTags: List<Chip>? = emptyList(),
    val languages: List<Chip>? = emptyList(),
    val interests: List<Chip>? = emptyList(),
    val countriesVisited: List<CountryItem>? = emptyList()
)
