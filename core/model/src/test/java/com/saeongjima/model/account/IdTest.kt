package com.saeongjima.model.account

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.ints.shouldBeGreaterThanOrEqual
import io.kotest.matchers.ints.shouldBeLessThan
import io.kotest.matchers.ints.shouldBeLessThanOrEqual
import io.kotest.matchers.shouldBe

class IdTest : BehaviorSpec({
    Given("최소 허용 길이(2) 보다 작은 아이디 객체를 만들고") {
        val id = Id("a")
        val idLength = id.value.length

        When("허용 길이에 부합하는지 확인하면") {
            val actual = id.isKeepRange()

            Then("검증에 실패한다.") {
                actual shouldBe false
                idLength shouldBeLessThan 2
            }
        }
    }

    Given("최대 허용 길이(10) 보다 큰 아이디 객체를 만들고") {
        val id = Id("abcdefghijk")
        val idLength = id.value.length

        When("허용 길이에 부합하는지 확인하면") {
            val actual = id.isKeepRange()

            Then("검증에 실패한다.") {
                actual shouldBe false
                idLength shouldBeGreaterThan 10
            }
        }
    }

    Given("허용 길이에 부합하는 아이디 객체를 만들고") {
        val minId = Id("ab")
        val maxId = Id("abcdefghij")
        val minIdLength = minId.value.length
        val maxIdLength = maxId.value.length

        When("허용 길이에 부합하는지 확인하면") {
            val minActual = minId.isKeepRange()
            val maxActual = maxId.isKeepRange()

            Then("검증에 성공한다.") {
                minActual shouldBe true
                maxActual shouldBe true
                minIdLength shouldBeGreaterThanOrEqual 2
                maxIdLength shouldBeLessThanOrEqual 10
            }
        }
    }
})
