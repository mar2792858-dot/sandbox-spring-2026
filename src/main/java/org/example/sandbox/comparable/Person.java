package org.example.sandbox.comparable;

public class Person implements Comparable<Person> {

    private String firstName;
    private String lastName;
    private int age;

    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Person {");
        sb.append("firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", age=").append(age);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int compareTo(Person that) {

        // First compare by age
        if (this.age > that.age) {
            return 1;
        } else if (this.age < that.age) {
            return -1;
        } else {

            // If ages are equal, compare by last name
            if(this.lastName.compareTo(that.lastName) > 0) {
                return 1;
            } else if (this.lastName.compareTo(that.lastName) < 0) {
                return -1;
            } else {

                // If last names are also equal, compare by first name
                if(this.firstName.compareTo(that.firstName) > 0) {
                    return 1;
                } else if (this.firstName.compareTo(that.firstName) < 0) {
                    return -1;
                } else {
                    return 0;
                }
            }

        }
    }
}