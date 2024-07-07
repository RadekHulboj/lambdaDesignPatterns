package java.features.records;

public class Record {
    static class MutableClass {
        String address;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }
    public record Home (String address) {

    }
    public record Person(int a, String name, MutableClass mutableClass, Home home) {
        public static int level;
    }
    static public void main (String... args) {
        Person person = new Person(2, "ff", new MutableClass(), new Home("Archy"));
        person.name();
        Person.level = 4;
        person.mutableClass().setAddress("only immutable is pointer/reference to class, which is mutable");
    }
}
