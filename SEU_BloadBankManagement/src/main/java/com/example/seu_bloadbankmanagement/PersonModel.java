package com.example.seu_bloadbankmanagement;

public class PersonModel {

    private String name;
    private int age;
    private BloodGroup bloodGroup;
    private String address;
    private String phoneNumber;
    private int quantity;
    private boolean searchEnabled;

    // Constructors
    public PersonModel() {
        // Default constructor
    }

    public PersonModel(String name, int age, BloodGroup bloodGroup, String address,
                       String phoneNumber, int quantity, boolean searchEnabled) {
        this.name = name;
        this.age = age;
        this.bloodGroup = bloodGroup;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.quantity = quantity;
        this.searchEnabled = searchEnabled;
    }

    // Getters and Setters (you can generate these methods in most IDEs)

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public BloodGroup getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(BloodGroup bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isSearchEnabled() {
        return searchEnabled;
    }

    public void setSearchEnabled(boolean searchEnabled) {
        this.searchEnabled = searchEnabled;
    }
}

