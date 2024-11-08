package com.example.getpeople;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kong.unirest.core.HttpResponse;
import kong.unirest.core.Unirest;

@RestController
public class GetPeopleController {

	/**
	 * Handles a GET request
	 * @param sortBy: Property that the Person objects should be sorted by
	 * @param descending: Whether the Person objects should be returned in ascending or descending order
	 * @return A list of Person objects in a requested order
	 */
	@GetMapping("/")
	public ResponseEntity<Object> getPeople(@RequestParam(name="sortBy", required = false) String sortBy, @RequestParam(name="descending", required = false) boolean descending) {
		System.out.println("Received a GET request");
		Unirest.config().verifySsl(false); // I have added the certificate to my path, but it still says I am missing the certificate. Given more time I would have investigated this issue further
		HttpResponse<Person[]> response = Unirest.get("https://demo7586857.mockable.io/people").asObject(Person[].class);
		// Given more time I would have separated the get request into a separate function and handled additional error scenarios. 
		// The nested If blocks are not ideal
		if (response.getStatus() == 200){
			Person[] people = response.getBody();

			if (sortBy!= null){
				if(sortBy.toLowerCase().equals("firstname")){
					System.out.println("Sorting objects by FirstName");
					Arrays.sort(people, Comparator.comparing(Person::getFirstName));
				}else if(sortBy.toLowerCase().equals("score")){
					System.out.println("Sorting objects by Score");
					Arrays.sort(people, Comparator.comparingInt(Person::getScore));
				}
		
				if (descending){
					System.out.println("Reversing sort order");
					Collections.reverse(Arrays.asList(people));
				}
			}
			
			return ResponseEntity.ok(Arrays.asList(people));
		} 

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Could not get people from the demo endpoint");
	}


	/**
	 * Handles any request method other than GET
	 * @return 405 Method not allowed error
	 */
	@RequestMapping("/")
    public ResponseEntity<String> handleInvalidMethods() {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body("Only GET requests are allowed");
    }
}
