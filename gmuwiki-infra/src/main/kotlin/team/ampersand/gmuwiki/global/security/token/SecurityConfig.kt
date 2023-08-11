package team.ampersand.gmuwiki.global.security.token

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import team.ampersand.gmuwiki.global.filter.FilterConfig
import team.ampersand.gmuwiki.global.security.handler.CustomAccessDenied
import team.ampersand.gmuwiki.global.security.handler.CustomAuthenticationEntryPoint

@Configuration
class SecurityConfig(
    private val jwtParser: JwtParser,
    private val objectMapper: ObjectMapper,
    private val customAuthenticationEntryPoint: CustomAuthenticationEntryPoint,
    private val customAccessDenied: CustomAccessDenied
) {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf().disable()
            .cors().and()

        http
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        http
            .authorizeRequests()
            .requestMatchers(HttpMethod.GET, "/").permitAll()
            .requestMatchers(HttpMethod.POST, "/auth/signup").permitAll()
            .requestMatchers(HttpMethod.POST,"/auth/token").permitAll()
            .anyRequest().denyAll()

        http.apply(FilterConfig(jwtParser, objectMapper))

        http.exceptionHandling()
            .authenticationEntryPoint(customAuthenticationEntryPoint)
            .accessDeniedHandler(customAccessDenied)

        return http.build()
    }
}