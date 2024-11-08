package com.example.getpeople;

import static org.hamcrest.Matchers.equalTo;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import kong.unirest.core.Config;
import kong.unirest.core.Unirest;

@SpringBootTest
@AutoConfigureMockMvc
public class GetPeopleControllerTest {

	@Autowired
	private MockMvc mvc;

	private static MockedStatic<Unirest> unirestMock;
	private static Config mockedConfig;

    @BeforeAll
    public static void init() {
        // Mock the static Unirest class
        unirestMock = Mockito.mockStatic(Unirest.class);
		mockedConfig = mock(Config.class);
		unirestMock.when(Unirest::config).thenReturn(mockedConfig);
    }

	@AfterAll
    public static void close() {
        // Close the mock after tests
        unirestMock.close();
    }

	// Test that 
	
	@Test
	public void postNotAllowed() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is(405))
				.andExpect(content().string(equalTo("Only GET requests are allowed")));
	}

	@Test
	public void putNotAllowed() throws Exception {
		mvc.perform(MockMvcRequestBuilders.put("/").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is(405))
				.andExpect(content().string(equalTo("Only GET requests are allowed")));
	}

	/*
		Given more time I would have written unit tests to.
		However, 
	*/

}
