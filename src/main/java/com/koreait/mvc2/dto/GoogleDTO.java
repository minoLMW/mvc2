package com.koreait.mvc2.dto;

public class GoogleDTO {
    private String name;
    private String email;
    private String picture;

    public GoogleDTO() {}

    public GoogleDTO(String name, String email, String picture) {
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPicture() { return picture; }

    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPicture(String picture) { this.picture = picture; }
}
