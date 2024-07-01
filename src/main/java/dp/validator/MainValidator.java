package dp.validator;

import dp.validator.paper.IValidator;
import eu.hulboj.model.Person;

import static dp.validator.paper.IValidator.init;

public class MainValidator {
	public static void main(String[] args) {

		Person sarah = new Person(null, -1);

//		Validator.<Person>create()
//				.match(MainValidator::getPersonPredicate, "The name should not be null")
//				.match(p -> p.getAge() > 0, "The age should be greater than 0")
//				.match(p -> p.getAge() < 150, "Allowed max age")
//				.on(sarah)
//				.validate();
//
//		System.out.println("Sarah : " + sarah);

		IValidator.<Person>init()
				.match(MainValidator::getPersonPredicate, "The name should not be null")
				.match(p -> p.getAge() > 0, "The age should be greater than 0")
				.on(sarah);

		System.out.println("Sarah : " + sarah);

	}
	private static <P extends Person> Boolean getPersonPredicate(P p) {
		return p.getName() != null;
	}
}
