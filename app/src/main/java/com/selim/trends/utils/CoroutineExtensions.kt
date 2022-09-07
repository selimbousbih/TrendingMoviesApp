package com.selim.trends.utils

import kotlinx.coroutines.flow.MutableSharedFlow

fun <T> bufferingMutableFlow() = MutableSharedFlow<T>(extraBufferCapacity = 1)