package dp.validator;

import eu.hulboj.model.Person;

public class MainValidator {

	public static void main(String[] args) {

		Person sarah = new Person("Sarah", -1);

		Validator.<Person>validating(p -> p.getName() != null, "The name should not be null")
				.thenValidating(p -> p.getAge() > 0, "The age should be greater than 0")
				.thenValidating(p -> p.getAge() < 150, "Allowed max age")
				.on(sarah)
				.validate();

		System.out.println("Sarah : " + sarah);
	}
}
