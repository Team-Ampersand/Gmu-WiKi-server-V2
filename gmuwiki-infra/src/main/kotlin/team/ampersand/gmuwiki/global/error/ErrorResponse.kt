package team.ampersand.gmuwiki.global.error

import org.springframework.dao.DataIntegrityViolationException
import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError
import team.ampersand.gmuwiki.common.error.ErrorProperty

data class ErrorResponse(
    val status: Int,
    val message: String
) {
    companion object {
        fun of(errorProperty: ErrorProperty) =
            ErrorResponse(
                errorProperty.status(),
                errorProperty.message()
            )

        fun of(e: BindingResult): ValidationErrorResponse {
            val errorMap = HashMap<String, String?>()

            for(error: FieldError in e.fieldErrors) {
                errorMap[error.field] = error.defaultMessage
            }

            return ValidationErrorResponse(
                status = GlobalErrorCode.BAD_REQUEST.status(),
                fieldError = errorMap
            )
        }

        fun of(e: DataIntegrityViolationException) = DataErrorResponse(
            status = GlobalErrorCode.BAD_REQUEST.status(),
            message = e.message.toString()
        )
    }
}

data class ValidationErrorResponse(
    val status: Int,
    val fieldError: Map<String, String?>
)

data class DataErrorResponse(
    val status: Int,
    val message: String
)