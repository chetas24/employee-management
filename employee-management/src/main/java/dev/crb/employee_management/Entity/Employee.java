package dev.crb.employee_management.Entity;


import jakarta.persistence.*;


/*
The @Entity annotation is used to mark a class as a JPA entity.
This means that the class is mapped to a table in a database.
When the JPA provider (such as Hibernate) scans your code,
it will recognize this class as a representation of a database table.
*/
@Entity
@Table(name = "employees")
public class Employee {

    /*
    The @Id annotation is used to specify the primary key of an entity.
    The primary key is a unique identifier for each entity instance.
    In the Employee class, the id field is marked with @Id to indicate that it is the primary key of the entity.
     */
    /*
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    specifies that the primary key should be generated automatically by the database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email_id")
    private String email;
    @Column(name = "position")
    private String position;
    @Column(name = "salary")
    private double salary;

    
    public Employee()
    {

    }

    public Employee(Long id, String firstName, String lastName, String email, String position, double salary) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.position = position;
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
