package com.saeongjima.model.account

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class EnglishContainValidatorTest : BehaviorSpec({
    Given("영어가 포함된 문자열을 가지는 익명객체가") {
        val given = object : EnglishContainValidator {
            override val value: String = "12a45"
        }

        When("영어가 포함되어있는지 검증하면") {
            val actual = given.hasAlphabet()

            Then("성공한다.") {
                actual shouldBe true
            }
        }
    }

    Given("영어가 포함되지 않은 문자열을 가지는 익명객체가") {
        val given = object : EnglishContainValidator {
            override val value: String = "12345"
        }

        When("영어가 포함되어있는지 검증하면") {
            val actual = given.hasAlphabet()

            Then("실패한다.") {
                actual shouldBe false
            }
        }
    }
})
