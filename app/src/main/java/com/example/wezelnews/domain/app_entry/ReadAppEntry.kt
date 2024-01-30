package com.example.wezelnews.domain.app_entry

import kotlinx.coroutines.flow.Flow

class ReadAppEntry( private val localUserManger: LocalUserManger) {
     operator fun invoke():Flow<Boolean>{
      return  localUserManger.readdata()
    }
}
