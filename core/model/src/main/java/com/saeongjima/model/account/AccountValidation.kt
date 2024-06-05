package com.saeongjima.model.account

enum class AccountValidation {
    Yet,
    Success,
    FailedByUnsupportedLength,
    FailedByUnsupportedCharacter,
    FailedByNotContainsNumber,
    FailedByNotContainsAlphabet,
    FailedByUnexpectedCondition,
    ;
}
