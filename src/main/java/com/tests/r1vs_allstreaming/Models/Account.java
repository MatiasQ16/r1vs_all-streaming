package com.tests.r1vs_allstreaming.Models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @NotEmpty
    private String password;

    @NotEmpty
    @Email
    @Column
    private String email;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private Type type;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private Status status;

    @OneToMany(mappedBy = "account")
    private List<Rental> rentals;

    public Account() {
    }

    public Account(long id, String password, String email, Type type, Status status, List<Rental> rentals) {
        this.id = id;
        this.password = password;
        this.email = email;
        this.type = type;
        this.status = status;
        this.rentals = rentals;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(List<Rental> rentals) {
        this.rentals = rentals;
    }
}
