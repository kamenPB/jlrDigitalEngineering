package com.jrl.exercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExerciseApplication {

	public static void main(String[] args) {

		VinService vinService = new VinService();
		VinController vinController = new VinController(vinService);
		vinController.createVin("047195869-2");
		vinController.createVin("a047195869-2b");
		vinController.createVin("047195869-6"); // invalid
		vinController.createVin("a047195869-6b"); // invalid

		System.out.println(vinController.getVinRepository().getBody());
		System.out.println(vinController.getVinValidation("047195869-2")); // true
		System.out.println(vinController.getVinValidation("a047195869-2b")); // true
		System.out.println(vinController.getVinValidation("047195869-6")); // false
		System.out.println(vinController.getVinValidation("a047195869-6b")); // false

//		SpringApplication.run(ExerciseApplication.class, args);

	}
}
