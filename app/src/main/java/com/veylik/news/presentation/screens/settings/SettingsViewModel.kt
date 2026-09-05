package com.veylik.news.presentation.screens.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veylik.news.domain.entity.Interval
import com.veylik.news.domain.entity.Language
import com.veylik.news.domain.usecase.GetSettingsUseCase
import com.veylik.news.domain.usecase.UpdateIntervalUseCase
import com.veylik.news.domain.usecase.UpdateLanguageUseCase
import com.veylik.news.domain.usecase.UpdateNotificationsEnabledUseCase
import com.veylik.news.domain.usecase.UpdateWifiOnlyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    getSettingsUseCase: GetSettingsUseCase,
    private val updateIntervalUseCase: UpdateIntervalUseCase,
    private val updateLanguageUseCase: UpdateLanguageUseCase,
    private val updateNotificationsEnabledUseCase: UpdateNotificationsEnabledUseCase,
    private val updateWifiOnlyUseCase: UpdateWifiOnlyUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<SettingsState>(SettingsState.Initial)
    val state = _state.asStateFlow()

    init {
        getSettingsUseCase()
            .onEach { settings ->
                _state.update {
                    SettingsState.Configuration(
                        language = settings.language,
                        interval = settings.interval,
                        wifiOnly = settings.wiiOnly,
                        notificationsEnabled = settings.notificationsEnabled,
                    )
                }
            }.launchIn(viewModelScope)
    }

    fun processCommand(command: SettingsCommand) {
        viewModelScope.launch {
            when (command) {
                is SettingsCommand.SelectInterval -> {
                    updateIntervalUseCase(command.interval )
                }

                is SettingsCommand.SelectLanguage -> {
                    updateLanguageUseCase(command.language)
                }

                is SettingsCommand.SetNotificationsEnabled -> {
                    updateNotificationsEnabledUseCase(command.enabled)
                }

                is SettingsCommand.SetWifiOnly -> {
                    updateWifiOnlyUseCase(command.wifiOnly)
                }
            }
        }
    }


}

sealed interface SettingsCommand {

    data class SelectLanguage(val language: Language) : SettingsCommand

    data class SelectInterval(val interval: Interval) : SettingsCommand

    data class SetNotificationsEnabled(val enabled: Boolean) : SettingsCommand

    data class SetWifiOnly(val wifiOnly: Boolean) : SettingsCommand
}


sealed interface SettingsState {

    data object Initial : SettingsState

    data class Configuration(
        val language: Language,
        val interval: Interval,
        val wifiOnly: Boolean,
        val notificationsEnabled: Boolean,
        val languages: List<Language> = Language.entries,
        val intervals: List<Interval> = Interval.entries,
    ) : SettingsState
}