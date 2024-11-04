package br.com.edu.observability.infra.rest.v1

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc
class PokeDeskControllerTest {

    @Autowired
    lateinit var mvc: MockMvc

    @Test
    @DisplayName("Should return a Charmander")
    fun `should return a Charmander`() {
        mvc.perform(get("/v1/pokemon/charmander")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("charmander"))
            .andExpect(jsonPath("$.weight").value(85))
            .andExpect(jsonPath("$.height").value(6))
    }


}