package pojo;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "manufacture")
public class Manufacture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int ID;

    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(128) check(length(name)) >= 3 and length(name) <= 128")
    public String Name;

    @Column(name = "location", nullable = false)
    public String Location;

    @Column(name = "employee", nullable = false)
    public int Employee;

    @OneToMany(mappedBy = "manufacture", fetch = FetchType.LAZY)
    public List<Phone> phones;

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public Manufacture() {}

    public Manufacture(int id) {
        this.ID = id;
    }

    public Manufacture(int id, String location, int employee) {
        this.ID = id;
        this.Location = location;
        this.Employee = employee;
    }

    public Manufacture(int id, String name) {
        this.ID = id;
        this.Name = name;
    }

    public Manufacture(int id, int employee) {
        this.ID = id;
        this.Employee = employee;
    }

    public Manufacture(int ID, String name, String location, int employee) {
        this.ID = ID;
        this.Name = name;
        this.Location = location;
        this.Employee = employee;
    }

    public Manufacture(String name, String location, int employee) {
        this.Name = name;
        this.Location = location;
        this.Employee = employee;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public Integer getEmployee() {
        return Employee;
    }

    public void setEmployee(Integer employee) {
        Employee = employee;
    }

    @Override
    public String toString() {
        return "Manufacture{" +
                "ID='" + ID + '\'' +
                ", Name='" + Name + '\'' +
                ", Location='" + Location + '\'' +
                ", Employee=" + Employee +
                '}';
    }
}
