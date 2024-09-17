package com.example.news.domain.usecases

import com.example.news.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class ReadAppEntry(
    private val localUserManager: LocalUserManager
){
    operator fun invoke(): Flow<Boolean> {
        return localUserManager.readAppEntry()
    }
}