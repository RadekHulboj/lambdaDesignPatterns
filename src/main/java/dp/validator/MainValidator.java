package dp.validator;

import eu.hulboj.model.Person;
/*                                         ANALYZE                                                               */
//        The first validator p1 runs on(p) and validate method on himself, then
//        the second validator p2 runs on(p) and validate method on himself, then ,,, etc
//        ths last validator p3 has to be run his methods on(p) and validate method expicitly by external call
//        This means her is no functional laziness, the functional are running immediately

//        Person sarah = new Person("S", 1);
//        Validator<Person> p1 = Validator.validating(p -> p.getName() != null, "The name should not be null");
//        Validator<Person> p2 = p1.thenValidating(p -> p.getAge() > 0, "The age should be greater than 0");
//        Validator<Person> p3 = p2.thenValidating(p -> p.getAge() > 0, "The age should be greater than 0");
//        Validator.ValidatorSupplier<Person> on = p3.on(sarah);
//        on.validate();

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
