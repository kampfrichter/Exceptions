public class User {
    private String lastName;
    private String firstName;
    private String middleName;
    private String gender;
    private String birthDate;
    private String phoneNumber;

    public User(String lastName, String firstName, String middleName, String gender, String birthDate, String phoneNumber) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "<" + String.join("><", lastName, firstName, middleName, birthDate, phoneNumber, gender) + ">";
    }
}
