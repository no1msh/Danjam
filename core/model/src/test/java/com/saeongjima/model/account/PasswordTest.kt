package com.saeongjima.model.account

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.ints.shouldBeGreaterThanOrEqual
import io.kotest.matchers.ints.shouldBeLessThan
import io.kotest.matchers.ints.shouldBeLessThanOrEqual
import io.kotest.matchers.shouldBe

class PasswordTest : BehaviorSpec({
    Given("최소 허용 길이(8) 보다 작은 비밀번호 객체를 만들고") {
        val password = Password("abcd123")
        val passwordLength = password.value.length

        When("허용 길이에 부합하는지 확인하면") {
            val actual = password.isKeepRange()

            Then("검증에 실패한다.") {
                actual shouldBe false
                passwordLength shouldBeLessThan 8
            }
        }
    }

    Given("최대 허용 길이(20) 보다 큰 비밀번호 객체를 만들고") {
        val password = Password("zxcvbasdfgqwert123456")
        val passwordLength = password.value.length

        When("허용 길이에 부합하는지 확인하면") {
            val actual = password.isKeepRange()

            Then("검증에 실패한다.") {
                actual shouldBe false
                passwordLength shouldBeGreaterThan 20
            }
        }
    }

    Given("허용 길이에 부합하는 비밀번호 객체를 만들고") {
        val minPassword = Password("abce1234")
        val maxPassword = Password("zxcvbasdfgqwert12345")
        val minPasswordLength = minPassword.value.length
        val maxPasswordLength = maxPassword.value.length

        When("허용 길이에 부합하는지 확인하면") {
            val minActual = minPassword.isKeepRange()
            val maxActual = maxPassword.isKeepRange()

            Then("검증에 성공한다.") {
                minActual shouldBe true
                maxActual shouldBe true
                minPasswordLength shouldBeGreaterThanOrEqual 8
                maxPasswordLength shouldBeLessThanOrEqual 20
            }
        }
    }
})
