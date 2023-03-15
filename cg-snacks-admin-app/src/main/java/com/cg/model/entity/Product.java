package com.cg.model.entity;


import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product implements Validator {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;


    private String name;
    private String image;


    private String description;
    private double price;
    private double quantity;
    private String notes;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Product(int i, String springInAction) {
    }

    public Product(Long id, String name, String image, String description, double price,
                   double quantity, String notes, Category category) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.notes = notes;
        this.category = category;
    }

    public Product() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }



    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Product product = (Product) target;
        String name = product.getName();
        ValidationUtils.rejectIfEmpty(errors, "name", "name.empty");
//        if(name == null || "" .equals(name)){
        if(!name.matches("^[a-zA-Z]*")){
            errors.rejectValue("name", "name.matches");
        }

//        String description = product.getDescription();
//        ValidationUtils.rejectIfEmpty(errors, "description", "description.empty");
//        if (description.length()>4 ){
//            errors.rejectValue("description", "description.length");
//        }
//        String price = product.getDescription();
//        ValidationUtils.rejectIfEmpty(errors, "price", "price.empty");
//        if (!price.matches("(^$|[0-9]*$)")){
//            errors.rejectValue("price", "price.matches");
//        }
//
//        String quantity = product.getQuantity();
//        ValidationUtils.rejectIfEmpty(errors, "quantity", "quantity.empty");
//        if (!quantity.matches("(^$|[0-9]*$)")){
//            errors.rejectValue("quantity", "quantity.matches");
//        }

//        || regexp="^[a-zA-Z]*"
    }
}
