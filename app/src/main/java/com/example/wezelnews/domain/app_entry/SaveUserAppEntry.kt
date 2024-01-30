package com.example.wezelnews.domain.app_entry

class SaveUserAppEntry(
    private val localUserManger: LocalUserManger
) {
    suspend operator fun invoke(){
         localUserManger.saveEntryApp()
    }
}