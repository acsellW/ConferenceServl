package ua.epam.liepin.servl.conference.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class User {
    private int id;
    private String username;
    private String email;
    private String password;
    private LocalDate regDate;
    private Role role;
    private String name;
    private String surname;
    private List<Conference> conferences;

    public User() { }

    public User(String username, String email, String password, LocalDate regDate, Role role, String name, String surname) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.regDate = regDate;
        this.role = role;
        this.name = name;
        this.surname = surname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<Conference> getConferences() {
        return conferences;
    }

    public void setConferences(List<Conference> conferences) {
        this.conferences = conferences;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
