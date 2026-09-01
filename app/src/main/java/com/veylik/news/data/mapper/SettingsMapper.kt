package com.veylik.news.data.mapper

import com.veylik.news.domain.entity.RefreshConfig
import com.veylik.news.domain.entity.Settings

fun Settings.toRefreshConfig(): RefreshConfig {
    return RefreshConfig(language, interval, wiiOnly)
}