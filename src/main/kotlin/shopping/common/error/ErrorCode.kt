package shopping.common.error

enum class ErrorCode(
    val code: String,
) {
    VALIDATION_ERROR("C0001"),
    UNKNOWN_ERROR("C0002"),
    LOGIN_FAILED("C0003"),
    TOKEN_EXPIRED("C0004"),
    INVALID_TOKEN("C0005"),
    TOKEN_NOT_FOUND("C0006"),

    PRODUCT_NOT_FOUND("P0000"),
    PRODUCT_NAME_CONTAIN_BAD_WORD("P0001"),

    USER_ALREADY_REGISTERED("U0000"),
    USER_NOT_FOUND("U0001"),
    PASSWORD_MISMATCH("U0002"),
}
