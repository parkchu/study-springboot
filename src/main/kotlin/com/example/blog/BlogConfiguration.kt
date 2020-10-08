package com.example.blog

import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BlogConfiguration {

    @Bean
    fun databaseInitializer(userRepository: UserRepository,
                            articleRepository: ArticleRepository) = ApplicationRunner {
        val parkchu = userRepository.save(User("parkchu", "park", "joohan"))
        articleRepository.save(Article(
                title = "pikachu is so cute!!",
                headline = "쿄쿄쿄쿅",
                content = "피카츄가 귀여운건 어쩌고 저쩌고 블라블라~~",
                author = parkchu
        ))
        articleRepository.save(Article(
                title = "pikachu",
                headline = "삐까!",
                content = "피카피카피카피카피카피카피카 (대충 피카츄만 이해할수있는 문장)",
                author = parkchu
        ))
    }
}
