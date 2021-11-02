package com.coding.test.model.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

/**
 * @Author zhangke
 * @Date   on 2021/11/1
 */

open class BaseViewModel : ViewModel() {
    /**
     * ui load state
     */
    val isLoading = MutableLiveData<Boolean>()
    val networkError = MutableLiveData<Throwable>()

    fun launch(
        block: suspend () -> Unit,
        error: suspend (Throwable) -> Unit,
        complete: suspend () -> Unit
    ) = viewModelScope.launch {
        try {
            block()
        } catch (e: Throwable) {
            error(e)
        } finally {
            complete()
        }
    }

}