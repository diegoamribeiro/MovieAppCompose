package br.com.movieapp.framework.util

import java.lang.Exception

sealed class ResourceData<out T> {
    object Loading : ResourceData<Nothing>()
    data class Success<out T>(val data: T) : ResourceData<T>()
    data class Fail(val e: Exception?) : ResourceData<Nothing>()
}