package eu.hulboj.model;

import java.util.Objects;

public class Person implements IPerson {

	private String name;
	private int age;
	private Heart heart;

	public Person() { }

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setAll(String name, int age) {
		setName(name);
		setAge(age);
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	@Override
	public void setAge(int age) {
		this.age = age;
	}

	public Heart getHeart() {
		return heart;
	}

	public void setHeart(Heart heart) {
		this.heart = heart;
	}

	@Override
	public String toString() {
		if(Objects.nonNull(heart) ) {
			return "Person [name=" + name + ", age=" + age + ", health=" + heart.getHealth() + "]";
		}
		return "Person [name=" + name + ", age=" + age + "]";
	}
}
