package com.productsaleswebsitespringboot.model;

import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Users{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String image;

    @NotNull
    @Size(min = 2, message = "O nome deve ter no mínimo 2 caracteres")
    private String name;

    @NotNull
	@Size(min = 4, message = "Login com no minimo 4 caracteres")
	private String login;

    @Email(message = "Email invalido")
    private String email;

    @NotEmpty(message = "Senha não pode ser vazia")
    @Size(min = 3, message = "Senha deve ter no mínimo 3 caracteres")
    private String password;

    @Column(unique = true)
    @CPF(message = "CPF invalido")
    private String cpf;

    @NotEmpty(message = "CEP não pode ser vazio")
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "CEP invalido")
    private String cep;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    private long houseNumber;

    @CreatedDate
    private Date createAt;

    @OneToMany(mappedBy = "user")
    private List<Review> reviews;

    @OneToMany(mappedBy = "user")
    private List<Orders> orders;

    @OneToOne(mappedBy = "user")
    private Cart cart;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_userrole", joinColumns = @JoinColumn(name = "users_id"), inverseJoinColumns = @JoinColumn(name = "userrole_id"))
    private List<UserRole> userRoles;

    @PrePersist
    public void prePersist() {
        if (createAt == null) {
            createAt = new Date();
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public long getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(long houseNumber) {
        this.houseNumber = houseNumber;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public List<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UserRole> userRoles) {
        this.userRoles = userRoles;
    }
}
