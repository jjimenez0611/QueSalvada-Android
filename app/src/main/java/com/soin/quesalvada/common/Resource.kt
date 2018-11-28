package com.soin.quesalvada.common
import com.soin.quesalvada.common.Status.SUCCESS
import com.soin.quesalvada.common.Status.ERROR
import com.soin.quesalvada.common.Status.LOADING

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
</T> */
data class Resource<out T>(val status: Status, val data: T?, val message: String?, val code: Int) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(SUCCESS, data, null,0)
        }

        fun <T> error(code: Int,msg: String, data: T?): Resource<T> {
            return Resource(ERROR, data, msg, code)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(LOADING, data, null,0)
        }

        fun <T> loading(): Resource<T> {
            return Resource(LOADING, null, null,0)
        }
    }
}