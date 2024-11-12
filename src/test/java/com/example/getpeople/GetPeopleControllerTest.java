package com.example.getpeople;

import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
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

	GetPeopleController getPeopleController = new GetPeopleController();

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

	@Nested
	class InvalidMethods {
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
	}

	@Nested
	class SortByFirstName {
		@Test
		public void sortAlphabeticallyAscending() throws Exception {
			Person[] people = {
				new Person("Bob", null, null, 0, null, null),
				new Person("Chris", null, null, 0, null, null),
				new Person("Adam", null, null, 0, null, null),
			};

			getPeopleController.sortPeople(people, "firstName", false);

			Person[] expectedOrder = {
				new Person("Adam", null, null, 0, null, null),
				new Person("Bob", null, null, 0, null, null),
				new Person("Chris", null, null, 0, null, null),
			};

			assertArrayEquals(
				Arrays.stream(expectedOrder).map(Person::toString).toArray(),
				Arrays.stream(people).map(Person::toString).toArray(),
				"The people array is not in the expected order."
			);
		}

		@Test
		public void sortAlphabeticallyDescending() throws Exception {
			Person[] people = {
				new Person("Bob", null, null, 0, null, null),
				new Person("Chris", null, null, 0, null, null),
				new Person("Adam", null, null, 0, null, null),
			};
			
			getPeopleController.sortPeople(people, "firstName", true);

			Person[] expectedOrder = {
				new Person("Chris", null, null, 0, null, null),
				new Person("Bob", null, null, 0, null, null),
				new Person("Adam", null, null, 0, null, null),
			};

			assertArrayEquals(
				Arrays.stream(expectedOrder).map(Person::toString).toArray(),
				Arrays.stream(people).map(Person::toString).toArray(),
				"The people array is not in the expected order."
			);
		}
	}


	@Nested
	class SortByScore {
		@Test
		public void sortByScoreAscending() throws Exception {
			Person[] people = {
				new Person("Bob", null, null, 50, null, null),
				new Person("Chris", null, null, 75, null, null),
				new Person("Adam", null, null, 25, null, null),
			};

			getPeopleController.sortPeople(people, "firstName", false);

			Person[] expectedOrder = {
				new Person("Adam", null, null, 25, null, null),
				new Person("Bob", null, null, 50, null, null),
				new Person("Chris", null, null, 100, null, null),
			};

			assertArrayEquals(
				Arrays.stream(expectedOrder).map(Person::toString).toArray(),
				Arrays.stream(people).map(Person::toString).toArray(),
				"The people array is not in the expected order."
			);
		}

		@Test
		public void sortByScoreDescending() throws Exception {
			Person[] people = {
				new Person("Bob", null, null, 50, null, null),
				new Person("Chris", null, null, 75, null, null),
				new Person("Adam", null, null, 25, null, null),
			};

			getPeopleController.sortPeople(people, "firstName", true);

			Person[] expectedOrder = {
				new Person("Chris", null, null, 75, null, null),
				new Person("Bob", null, null, 50, null, null),
				new Person("Adam", null, null, 25, null, null),
			};

			assertArrayEquals(
				Arrays.stream(expectedOrder).map(Person::toString).toArray(),
				Arrays.stream(people).map(Person::toString).toArray(),
				"The people array is not in the expected order."
			);
		}
	}
	


	/*
		Given more time I would have written more unit tests, specifically tests that would cover:
			- When https://demo7586857.mockable.io/people returns an error
			- When https://demo7586857.mockable.io/people returns objects that are missing fields
		I attempted to cover these scenarios, but I had difficulty mocking the GET request with Unirest and was unable to complete the tests in time. 
		Additionally, I think it would be a good idea to test sorting larger arrays
	*/
}
