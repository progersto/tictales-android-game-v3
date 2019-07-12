package loveanddiaries.tictales.com.lovediaries.utils

class Result<T> private constructor(val status: ResultStatus, val message: String?, val value: T?) {
    companion object {
        fun <T> loading() = Result<T>(ResultStatus.LOADING, null, null)

        fun <T> success(result: T? = null) = Result<T>(ResultStatus.SUCCESS, null, result)

        fun <T> error(errorMessage: String?) = Result<T>(ResultStatus.ERROR, errorMessage, null)
    }

    enum class ResultStatus {
        LOADING, SUCCESS, ERROR
    }
}