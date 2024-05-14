package com.example.fa21_class_demo

data class Response<out T>(
    val status: Status,
    val data: T,
    val message: String?
) {
    enum class Status {
        Loading,
        Success,
        Error,
        Failure
    }

    companion object {
        fun <T> loading(s: Status = Status.Loading, m: String? = "Laoding ..."): Response<T?> {
            return Response(status = s, data = null, message = m)
        }

        fun <T> error(s: Status = Status.Error, data: T, m: String? = "Error"): Response<T?> {
            return Response(status = s, data = null, message = m)
        }

        fun <T> success(s: Status = Status.Success, data: T, m: String? = "Success"): Response<T?> {
            return Response(status = s, data = data, message = m)
        }

        fun <T> failure(s: Status = Status.Failure, data: T, m: String? = "Failure"): Response<T?> {
            return Response(status = s, data = null, message = m)
        }
    }
}


