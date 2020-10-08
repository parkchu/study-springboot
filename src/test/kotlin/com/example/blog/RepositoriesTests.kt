package com.example.blog

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.data.repository.findByIdOrNull

@DataJpaTest
class RepositoriesTests @Autowired constructor(
        val entityManager: TestEntityManager,
        val userRepository: UserRepository,
        val articleRepository: ArticleRepository){

    @Test
    fun `When findByIdOrNull then return Article`() {
        val joohan = User("parkjoopika", "park", "joohan")
        entityManager.persist(joohan)
        val article = Article("피카츄는 왜 귀여울까?", "ㅇㅁㅇ!!", "카츄!!!!", joohan)
        entityManager.persist(article)
        entityManager.flush()
        val found = articleRepository.findByIdOrNull(article.id!!)
        assertThat(found).isEqualTo(article)
        assertThat(found!!.title).isEqualTo("피카츄는 왜 귀여울까?")
    }

    @Test
    fun `When findByLogin then return User`() {
        val joohan = User("parkjoopika", "park", "joohan")
        entityManager.persist(joohan)
        entityManager.flush()
        val user = userRepository.findByLogin(joohan.login)
        assertThat(user).isEqualTo(joohan)
        assertThat(user!!.login).isEqualTo("parkjoopika")
    }
}
