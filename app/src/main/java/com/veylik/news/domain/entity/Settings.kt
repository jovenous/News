package com.veylik.news.domain.entity

data class Settings(
    val language: Language,
    val interval: Interval,
    val notificationsEnabled: Boolean,
    val wiiOnly: Boolean

)

enum class Language {

    ENGLISH, RUSSIAN, FLENCH, GERMAN
}

enum class Interval(val minutes: Int) {

    MIN_15(15),
    MIN_30(30),
    HOUR_1(60),
    HOUR_2(120),
    HOUR_4(240),
    HOUR_8(480),
    HOUR_24(1440)
}