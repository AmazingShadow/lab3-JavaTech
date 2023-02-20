package pojo;

import dao.ManufactureDAO;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "phone")
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int ID;

    @Column(name = "name",  nullable = false, columnDefinition = "VARCHAR(128) check(length(name)) >= 3 and length(name) <= 128")
    public String Name;

    @Column(name = "price", nullable = false)
    public double Price;

    @Column(name = "color", nullable = false)
    public String Color;

    @Column(name = "country", nullable = false)
    public String Country;

    @Column(name = "quantity", nullable = false)
    public int Quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manufacture_id")
    public Manufacture manufacture;

    public Phone(int ID, String name, double price, String color, String country, int quantity) {
        this.ID = ID;
        this.Name = name;
        this.Price = price;
        this.Color = color;
        this.Country = country;
        this.Quantity = quantity;
    }

    public Phone(int ID, String name, double price, String color, String country, int quantity, Manufacture manufacture) {
        this.ID = ID;
        this.Name = name;
        this.Price = price;
        this.Color = color;
        this.Country = country;
        this.Quantity = quantity;
        this.manufacture = manufacture;
    }


    public Phone(String name, double price, String color, String country, int quantity) {
        this.Name = name;
        this.Price = price;
        this.Color = color;
        this.Country = country;
        this.Quantity = quantity;
    }
    public Phone() {

    }

    public Phone(int id, String name) {
        this.ID = id;
        this.Name = name;
    }

    public int getID() {
        return ID;
    }

    public Manufacture getManufacture() {
        return manufacture;
    }

    public void setManufacture(Manufacture manufacture) {
        this.manufacture = manufacture;
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

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public Integer getQuantity() {
        return Quantity;
    }

    public void setQuantity(Integer quantity) {
        Quantity = quantity;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "ID=" + ID +
                ", Name='" + Name + '\'' +
                ", Price=" + Price +
                ", Color='" + Color + '\'' +
                ", Country='" + Country + '\'' +
                ", Quantity=" + Quantity +
                ", " + manufacture +
                '}';
    }
}
