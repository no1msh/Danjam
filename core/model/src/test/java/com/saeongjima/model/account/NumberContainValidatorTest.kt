package com.saeongjima.model.account

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class NumberContainValidatorTest : BehaviorSpec({
    Given("숫자를 가지는 객체가") {
        val given = object : NumberContainValidator {
            override val value: String = "ab2c"
        }

        When("숫자를 가지고 있는지 검증하면") {
            val actual = given.hasNumber()

            Then("검증에 성공한다.") {
                actual shouldBe true
            }
        }
    }

    Given("숫자를 가지지 않는 객체가") {
        val given = object : NumberContainValidator {
            override val value: String = "abc"
        }

        When("숫자를 가지고 있는지 검증하면") {
            val actual = given.hasNumber()

            Then("검증에 실패한다.") {
                actual shouldBe false
            }
        }
    }
})
