package com.example.demosearch;

import com.example.demosearch.secusearch_api.SecuSearchEngine;
import com.example.demosearch.secusearch_api.TestResourceUtils;
import com.secugen.secusearch.api.SSException;
import com.secugen.secusearch.api.SecuSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.io.IOException;

@SpringBootApplication
public class DemoSearchApplication {
	@Autowired
	private TestResourceUtils testResourceUtils;

	public static void main(String[] args) {
		SpringApplication.run(DemoSearchApplication.class, args);
	}

	@PostConstruct
	public void testRun() {
		final SecuSearch secuSearch = SecuSearch.getInstance();

		// You can call SecuSearch.getVersion() before or after the initialization.
		System.out.println("API version: " + secuSearch.getVersion());
		System.out.println();

		try {
			SecuSearchEngine test = new SecuSearchEngine(secuSearch, testResourceUtils);

			test.initializeEngine();

			test.registerFP();
			test.getFPCount();
			test.getTemplate();
			test.getIDList();

			test.searchFP();
			test.identifyFP();

			test.removeFP();
			test.getFPCount();

			test.registerFPBatch();
			test.getFPCount();
			test.searchFP();
			test.identifyFP();
			test.saveFPDB();

			test.removeFPBatch();
			test.getFPCount();

			test.loadFPDB();
			test.getFPCount();

			test.searchFP();
			test.identifyFP();

			test.clearFPDB();
			test.getFPCount();

//			test.extractTemplate();

			test.terminateEngine();
		} catch (IOException e) {
			System.out.println("A file operation has failed - " + e);
		} catch (SSException e) {
			System.out.println("A SecuSearch operation has failed - " + e);
		}
	}

}
