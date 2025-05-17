/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.session9;

import jakarta.persistence.*;

import java.io.Serializable;


/**
 *
 * @author QuangCV
 */
@Entity
@Table(name = "destinations")
@NamedQueries({
    @NamedQuery(name = "Destinations.findAll", query = "SELECT d FROM Destinations d"),
    @NamedQuery(name = "Destinations.findById", query = "SELECT d FROM Destinations d WHERE d.id = :id"),
    @NamedQuery(name = "Destinations.findByName", query = "SELECT d FROM Destinations d WHERE d.name = :name"),
    @NamedQuery(name = "Destinations.findByCountry", query = "SELECT d FROM Destinations d WHERE d.country = :country"),
    @NamedQuery(name = "Destinations.findByPrice", query = "SELECT d FROM Destinations d WHERE d.price = :price"),
    @NamedQuery(name = "Destinations.findByAvailableSpots", query = "SELECT d FROM Destinations d WHERE d.availableSpots = :availableSpots")})
public class Destinations implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "country")
    private String country;
    @Lob
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "price")
    private double price;
    @Basic(optional = false)
    @Column(name = "available_spots")
    private int availableSpots;

    public Destinations() {
    }

    public Destinations(Integer id) {
        this.id = id;
    }

    public Destinations(Integer id, String name, String country, double price, int availableSpots) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.price = price;
        this.availableSpots = availableSpots;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAvailableSpots() {
        return availableSpots;
    }

    public void setAvailableSpots(int availableSpots) {
        this.availableSpots = availableSpots;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Destinations)) {
            return false;
        }
        Destinations other = (Destinations) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "vn.aptech.session10.Destinations[ id=" + id + " ]";
    }
    
}
