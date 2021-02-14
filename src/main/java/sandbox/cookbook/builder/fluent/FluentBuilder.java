package sandbox.cookbook.builder.fluent;

import java.util.function.Supplier;

public class FluentBuilder {

    static class Person {

        private int age;
        private String name;
        private int sex;


        void setSex(int sex) {
            this.sex = sex;
        }

        void setName(String name) {
            this.name = name;
        }

        void setAge(int age) {
            this.age = age;
        }

        Builder builder(Supplier<Person> personSupplier) {
            return new Builder(personSupplier.get());
        }

        static class Builder {

            Person person;

            private Builder(Person person) {
                this.person = person;
            }

            Step1 setName(String name) {
                return age -> sex -> {
                    person.setName(name);
                    person.setAge(age);
                    person.setSex(sex);
                    return this;

                };
            }

            public Person build() {
                return person;
            }

            private interface Step1 {
                Step2 setAge(Integer i);
            }

            private interface Step2 {
                Builder setSex(Integer i);
            }
        }
    }


    public static void main(String[] args) {
        FluentBuilder.Person person = new Person();
        Person per = person.builder(Person::new)
                .setName("")
                .setAge(1)
                .setSex(2)
                .build();
    }
}
