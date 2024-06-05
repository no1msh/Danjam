package com.saeongjima.model.account

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.ints.shouldBeLessThan
import io.kotest.matchers.shouldBe

class AccountStringTest : BehaviorSpec({
    Given("값이 빈 값이고") {
        val given = AccountString("")

        When("검증 상태를 가져오면") {
            val actual: AccountValidation = given.getValidateState()

            Then("아직 검증되지 않은 상태이다") {
                actual shouldBe AccountValidation.Yet
            }
        }
    }

    Given("조건에 만족하는 값이고") {
        val given = AccountString("idid1234")

        When("검증 상태를 가져오면") {
            val actual: AccountValidation = given.getValidateState()

            Then("검증에 성공한 상태이다") {
                actual shouldBe AccountValidation.Success
            }
        }
    }

    Given("값의 최소 글자 수(8)보다 적은 글자 수이고") {
        val given = AccountString("abcefgh")
        val givenLength: Int = given.value.length

        When("검증 상태를 가져오면") {
            val actual: AccountValidation = given.getValidateState()

            Then("지원하지 않는 길이로 검증에 실패한 상태이다") {
                givenLength shouldBeLessThan 8
                actual shouldBe AccountValidation.FailedByUnsupportedLength
            }
        }
    }

    Given("값의 최대 글자 수(20)보다 큰 글자 수 이고") {
        val given = AccountString("abcdefghijklmnopqrstu")
        val givenLength: Int = given.value.length

        When("검증 상태를 가져오면") {
            val actual: AccountValidation = given.getValidateState()

            Then("지원하지 않는 길이로 검증에 실패한 상태이다") {
                givenLength shouldBeGreaterThan 20
                actual shouldBe AccountValidation.FailedByUnsupportedLength
            }
        }
    }

    Given("값에 특수문자가 들어가고") {
        val given = AccountString("id123456!")

        When("검증 상태를 가져오면") {
            val actual: AccountValidation = given.getValidateState()

            Then("지원하지 않는 문자로 검증에 실패한 상태이다") {
                actual shouldBe AccountValidation.FailedByUnsupportedCharacter
            }
        }
    }

    Given("값에 이모지가 들어가고") {
        val given = AccountString("id1234567😀")

        When("검증 상태를 가져오면") {
            val actual: AccountValidation = given.getValidateState()

            Then("지원하지 않는 문자로 검증에 실패한 상태이다") {
                actual shouldBe AccountValidation.FailedByUnsupportedCharacter
            }
        }
    }
})
