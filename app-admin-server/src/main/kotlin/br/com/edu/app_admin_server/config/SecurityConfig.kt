package br.com.edu.app_admin_server.config


import de.codecentric.boot.admin.server.config.AdminServerProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler
import org.springframework.security.web.csrf.CookieCsrfTokenRepository
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

@EnableWebSecurity
@Configuration(proxyBeanMethods = false)
class SecurityConfig(private val adminServer: AdminServerProperties) {

    @Bean
    @Throws(Exception::class)
    protected fun filterChain(http: HttpSecurity): SecurityFilterChain {

        val successHandler: SavedRequestAwareAuthenticationSuccessHandler =
            SavedRequestAwareAuthenticationSuccessHandler()
        successHandler.setTargetUrlParameter("redirectTo")
        successHandler.setDefaultTargetUrl(adminServer.path("/"))

        http.authorizeHttpRequests { authorizeRequests ->
            authorizeRequests
                .requestMatchers(AntPathRequestMatcher(adminServer.path("/assets/**")))
                .permitAll()
                .requestMatchers(AntPathRequestMatcher(adminServer.path("/login")))
                .permitAll()
                .anyRequest()
                .authenticated()
        }
            .formLogin { formLogin ->
                formLogin.loginPage(adminServer.path("/login"))
                    .successHandler(successHandler)
            }
            .logout { logout -> logout.logoutUrl(adminServer.path("/logout")) }
            .httpBasic(Customizer.withDefaults())
            .csrf { csrf ->
                csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                    .ignoringRequestMatchers(
                        AntPathRequestMatcher(
                            adminServer.path("/instances"),
                            HttpMethod.POST.toString()
                        ),
                        AntPathRequestMatcher(
                            adminServer.path("/instances/*"),
                            HttpMethod.DELETE.toString()
                        ),
                        AntPathRequestMatcher(adminServer.path("/actuator/**"))
                    )
            }

        return http.build()
    }
}