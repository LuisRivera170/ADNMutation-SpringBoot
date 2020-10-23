package com.springboot.mutation.api;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import com.springboot.mutation.api.service.HumanServiceImpl;

@SpringBootTest
@AutoConfigureMockMvc
class SpringDnaMutationApiApplicationTests {
	
	HumanServiceImpl humanService = new HumanServiceImpl();
	
	@Autowired
	MockMvc mockMvc;
	
	@Test
	void whenMutateDnaThenAccept() {
		String[] dna = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
		assertTrue(humanService.hasMutation(dna)); 
	}
	
	@Test
	void whenNormalDnaThenDontAccept() {
		String[] dna2 = {"ATGCGA", "CAGTGC", "TTATTT", "AGACGG", "GCGTCA", "TCACTG"};
		assertFalse(humanService.hasMutation(dna2)); 
	}

	@Test
	void getAllHumans_withNotToken_thenReturn401() throws Exception {
		mockMvc
			.perform(get("/humans")
					.contentType("application/json"))
			.andExpect(status().isUnauthorized());
	}
	
	@Test
	void verifyDnaWithMutation_thenReturn200() throws Exception {
		String dna = "{\"dna\": [\"ATGCGA\", \"CAGTGC\", \"TTATGT\", \"AGAAGG\", \"CCCCTA\", \"TCACTG\"]}";
		
		mockMvc
		.perform(post("/mutation")
				.contentType("application/json")
				.content(dna))
		.andExpect(status().isOk());
	}
	
	@Test
	void verifyDnaWithoutMutation_thenReturn403() throws Exception {
		String dna = "{\"dna\": [\"ATGCGA\", \"CAGTGC\", \"TTATTT\", \"AGACGG\", \"GCGTCA\", \"TCACTG\"]}";
		
		mockMvc
		.perform(post("/mutation")
				.contentType("application/json")
				.content(dna))
		.andExpect(status().isForbidden());
	}
	
	@Test
	void verifyDnaMutation_withoutDna_thenReturn400() throws Exception {
		String dna = "{}";
		
		mockMvc
		.perform(post("/mutation")
				.contentType("application/json")
				.content(dna))
		.andExpect(status().isBadRequest());
	}
	
	@Test
	void getAllHumans_withToken_thenReturn200() throws Exception {
		MvcResult result = mockMvc.perform(post("/oauth/token")
				.header("Authorization", "Basic bXV0YXRpbmctaHVtYW4tY2xpZW50OmhBUnk3KmJ3bDgxWUNT")
				.header("Content-Type", "application/x-www-form-urlencoded")
				.param("username", "admin")
				.param("password", "123bwl456")
				.param("grant_type", "password")
				).andExpect(status().isOk()).andReturn();
		
		String response = result.getResponse().getContentAsString();
	    JSONObject json = new JSONObject(response);
	    String token = json.getString("access_token");
	    
		mockMvc
			.perform(get("/humans")
					.contentType("application/json")
					.header("Authorization", "Bearer " + token))
			.andExpect(status().isOk());
	}
	
	@Test
	void getStats_withToken_thenReturn200() throws Exception {
		MvcResult result = mockMvc.perform(post("/oauth/token")
				.header("Authorization", "Basic bXV0YXRpbmctaHVtYW4tY2xpZW50OmhBUnk3KmJ3bDgxWUNT")
				.header("Content-Type", "application/x-www-form-urlencoded")
				.param("username", "admin")
				.param("password", "123bwl456")
				.param("grant_type", "password")
				).andExpect(status().isOk()).andReturn();
		
		String response = result.getResponse().getContentAsString();
	    JSONObject json = new JSONObject(response);
	    String token = json.getString("access_token");
	    
		mockMvc
			.perform(get("/stats")
					.contentType("application/json")
					.header("Authorization", "Bearer " + token))
			.andExpect(status().isOk());
	}
	
	@Test 
	void saveHuman_withToken_thenReturn202() throws Exception { 
		MvcResult result = mockMvc.perform(post("/oauth/token")
				.header("Authorization", "Basic bXV0YXRpbmctaHVtYW4tY2xpZW50OmhBUnk3KmJ3bDgxWUNT")
				.header("Content-Type", "application/x-www-form-urlencoded")
				.param("username", "admin")
				.param("password", "123bwl456")
				.param("grant_type", "password")
				).andExpect(status().isOk()).andReturn();
		
		String response = result.getResponse().getContentAsString();
	    JSONObject json = new JSONObject(response);
	    String token = json.getString("access_token");
	    
	    String newHuman = "{\"name\": \"Humano test\"}";
	    
	    mockMvc
		.perform(post("/humans")
				.contentType("application/json")
				.header("Authorization", "Bearer " + token)
				.content(newHuman))
		.andExpect(status().isCreated());
	}
	
	@Test 
	void saveHuman_withToken_withoutName_thenReturn400() throws Exception { 
		MvcResult result = mockMvc.perform(post("/oauth/token")
				.header("Authorization", "Basic bXV0YXRpbmctaHVtYW4tY2xpZW50OmhBUnk3KmJ3bDgxWUNT")
				.header("Content-Type", "application/x-www-form-urlencoded")
				.param("username", "admin")
				.param("password", "123bwl456")
				.param("grant_type", "password")
				).andExpect(status().isOk()).andReturn();
		
		String response = result.getResponse().getContentAsString();
	    JSONObject json = new JSONObject(response);
	    String token = json.getString("access_token");
	    
	    String newHuman = "{}";
	    
	    mockMvc
		.perform(post("/humans")
				.contentType("application/json")
				.header("Authorization", "Bearer " + token)
				.content(newHuman))
		.andExpect(status().isBadRequest());
	}
	
}
