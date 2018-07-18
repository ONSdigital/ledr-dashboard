package uk.gov.ons.lerp.poc.endpoint;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import uk.gov.ons.lerp.poc.endpoint.AuthController;

@RunWith(SpringRunner.class)
//@WebMvcTest(AuthController.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private JdbcTemplate jdbcTemplate;
	
	@Test
	public void login_basic() throws Exception {
		
		//call GET "/login" application/json
		RequestBuilder request = MockMvcRequestBuilders
				.get("/login")
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(request).andReturn();
		
		//verify "login"
		assertTrue(result.getResponse().getContentAsString().contains("login"));
	}
}
