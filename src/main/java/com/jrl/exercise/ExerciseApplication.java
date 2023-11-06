package com.jrl.exercise;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExerciseApplication {

	public static void main(String[] args) {

		VinService vinService = new VinService();
		VinController vinController = new VinController(vinService);
		vinController.createVin("047195869-2");
		vinController.createVin("047195869-6"); // invalid

		System.out.println(vinController.getVinRepository().getBody());
		System.out.println(vinController.getVinValidation("047195869-2")); // true
		System.out.println(vinController.getVinValidation("047195869-6")); // false

//		SpringApplication.run(ExerciseApplication.class, args);

	}
}
