package com.veylik.news.domain.usecase

import com.veylik.news.domain.entity.Language
import com.veylik.news.domain.repository.SettingsRepository
import javax.inject.Inject

class UpdateLanguageUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository
) {

    suspend operator fun invoke(language: Language) {
        settingsRepository.updateLanguage(language)
    }
}