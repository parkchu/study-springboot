package com.example.blog

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IntegrationTests(@Autowired val restTemplate: TestRestTemplate) {

    @BeforeAll
    fun setup() {
        println(">> 테스트 시작")
    }

    @Test
    fun `Assert blog page title, content and status code`() {
        println(">> 블로그 주제와 내용 그리고 상태 코드 확인")
        val entity = restTemplate.getForEntity<String>("/")
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(entity.body).contains("<h1>여긴 피카츄에 대해 끄적이는 공간 입니다.</h1>", "pikachu")
    }

    @Test
    fun `Assert article page title, content and status code`() {
        println(">> 기사 주제와 내용 그리고 상태 코드 확인")
        val title = "pikachu"
        val entity = restTemplate.getForEntity<String>("/article/${title.toSlug()}")
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(entity.body).contains(title, "삐까!", "피카피카피카피카피카피카피카 (대충 피카츄만 이해할수있는 문장)")
    }

    @AfterAll
    fun teardown() {
        println(">> 테스트 끝")
    }

}
