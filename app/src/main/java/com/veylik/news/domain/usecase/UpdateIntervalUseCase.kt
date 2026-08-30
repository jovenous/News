package com.veylik.news.domain.usecase

import com.veylik.news.domain.entity.Interval
import com.veylik.news.domain.repository.SettingsRepository
import javax.inject.Inject

class UpdateIntervalUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository
) {

    suspend operator fun invoke(interval: Interval) {
        settingsRepository.updateInterval(interval.minutes)
    }
}