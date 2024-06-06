package naves;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlConfig.TransactionMode;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import naves.dto.NaveDTO;

@Sql(scripts = { "/schema.sql",
		"/data.sql" }, config = @SqlConfig(transactionMode = TransactionMode.ISOLATED), executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/delete-data.sql", config = @SqlConfig(transactionMode = TransactionMode.ISOLATED), executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = NavesApplication.class)
class NaveIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	ObjectMapper objectMapper = new ObjectMapper();

	@Test
	void testGetPageBad() throws Exception {
		Integer pageNumber = 0;
		Integer pageSize = 0;
		mockMvc.perform(get("/api/" + pageNumber + "/" + pageSize).header("Authorization", "321654987"))
			.andExpect(status().isBadRequest());
	}

	@Test
	void testGetPageWith3() throws Exception {
		Integer pageNumber = 0;
		Integer pageSize = 5;
		mockMvc.perform(get("/api/" + pageNumber + "/" + pageSize).header("Authorization", "321654987"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.length()").value(3));
	}

	@Test
	void testGetByIdBad() throws Exception {
		mockMvc.perform(get("/api/").header("Authorization", "321654987"))
			.andExpect(status().isBadRequest());
	}
	
	@Test
	void testGetByIdGood() throws Exception {
		mockMvc.perform(get("/api/1").header("Authorization", "321654987"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id").value(1))
			.andExpect(jsonPath("$.nombre").value("x-wing"))
			.andExpect(jsonPath("$.color").value("000000"))
			.andExpect(jsonPath("$.tamano").value("XS"));
	}

	@Test
	void testFilterByNameBad() throws Exception {
		mockMvc.perform(get("/api/filter/").header("Authorization", "321654987"))
			.andExpect(status().isBadRequest());
	}

	@Test
	void testFilterByNameWithOne() throws Exception {
		String name = "wing";

		mockMvc.perform(get("/api/filter/" + name).header("Authorization", "321654987"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.length()").value(1))
			.andExpect(jsonPath("$[0].id").value(1))
			.andExpect(jsonPath("$[0].nombre").value("x-wing"))
			.andExpect(jsonPath("$[0].color").value("000000"))
			.andExpect(jsonPath("$[0].tamano").value("XS"));
	}

	@Test
	void testFilterByNameWithTwo() throws Exception {
		String name = "d";

		mockMvc.perform(get("/api/filter/" + name).header("Authorization", "321654987"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.length()").value(2));
	}

	@Test
	void testCreateBad() throws Exception {
		mockMvc.perform(post("/api/").header("Authorization", "321654987")
			.contentType(MediaType.APPLICATION_JSON)
			.content(""))
			.andExpect(status().isBadRequest());
	}
	
	@Test
	void testCreateGood() throws Exception {
		NaveDTO nave = new NaveDTO();
		nave.setNombre("zzzzz");
		nave.setColor("321654");
		nave.setTamano("321654");
		
		mockMvc.perform(post("/api/").header("Authorization", "321654987")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(nave)))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id").value(4))
			.andExpect(jsonPath("$.nombre").value("zzzzz"))
			.andExpect(jsonPath("$.color").value("321654"))
			.andExpect(jsonPath("$.tamano").value("321654"));
	}

	@Test
	void testModifyByIdBad() throws Exception {
		mockMvc.perform(patch("/api/").header("Authorization", "321654987")
			.contentType(MediaType.APPLICATION_JSON).
			content(""))
			.andExpect(status().isBadRequest());
	}
	
	@Test
	void testModifyByIdGood() throws Exception {
		mockMvc.perform(patch("/api/").header("Authorization", "321654987")
			.contentType(MediaType.APPLICATION_JSON).
			content(""))
			.andExpect(status().isBadRequest());
		
		
		NaveDTO nave = new NaveDTO();
		nave.setId(1l);
		nave.setNombre("zzzzz");
		nave.setColor("321654");
		nave.setTamano("321654");
		
		mockMvc.perform(patch("/api/").header("Authorization", "321654987")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(nave)))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id").value(1))
			.andExpect(jsonPath("$.nombre").value("zzzzz"))
			.andExpect(jsonPath("$.color").value("321654"))
			.andExpect(jsonPath("$.tamano").value("321654"));
	}

	@Test
	void testDeleteByIdBad() throws Exception {
		mockMvc.perform(delete("/api/").header("Authorization", "321654987"))
			.andExpect(status().isBadRequest());
	}
	
	@Test
	void testDeleteByIdGood() throws Exception {
		mockMvc.perform(delete("/api/1").header("Authorization", "321654987"))
			.andExpect(status().isOk());
		
		
		Integer pageNumber = 0;
		Integer pageSize = 5;
		mockMvc.perform(get("/api/" + pageNumber + "/" + pageSize).header("Authorization", "321654987"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.length()").value(2));
	}

}