package com.temp.webshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebshopApplication.class, args);

		/**
		 *
		 * OBS! Enda ändringen är att jag har bytt namn från "model" till "entity" i både auth och webshop-paketen.
		 * Detta kan givetvis ändras på om ni vill ha det på ett annat sätt.
		 *
		 *
		 *
		 *
		 * auth-package
		 * I denna så har jag lagt till alla paket med tomma klasser som UnknownCoder videon använder.
		 *
		 *
		 *
		 *
		 * webshop-package:
		 * I denna så har jag lagt till alla paket med tomma klasser som vi pratat om.Detta kan ju så klart komma att
		 * ändras vid behov och vidare reflektion.
		 *
		 * Jag har väntat med "User" klassen. Jag tänkte att vi kan diskutera om vi måste använda samma User klass
		 * i både auth och webshop-paketen eller om vi ska ha olika klasser?
		 *
		 * Vi får komma fram till vilka endpoints vi ska ha och vilka klasser som behövs för att kunna hantera dessa
		 * innan vi lägger in klasser i controller-package
		 */
	}
}
