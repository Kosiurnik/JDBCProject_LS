package sda.lukaszs.myjdbcproject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="employee")
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private long id;

    @Getter
    @Setter
    @Column(name = "name", columnDefinition="VARCHAR(50) DEFAULT NULL")
    private String name;

    @Getter
    @Setter
    @Column(name = "salary", columnDefinition="INT(11) DEFAULT NULL")
    private int salary;

    public Employee(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return String.format("%d;%s;%d;", id, name, salary);
    }
}
