package com.veylik.news.domain.usecase

import com.veylik.news.domain.repository.SettingsRepository
import javax.inject.Inject

class GetSettingsUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository
) {

    operator fun invoke() = settingsRepository.getSettings()
}