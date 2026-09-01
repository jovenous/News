package com.veylik.news.domain.entity

data class RefreshConfig(
    val language: Language,
    val interval: Interval,
    val wiiOnly: Boolean
)
