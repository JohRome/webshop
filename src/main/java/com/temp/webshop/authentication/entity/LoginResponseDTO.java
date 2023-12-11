package com.temp.webshop.authentication.entity;

public class LoginResponseDTO {
    private Customer user;
    private String jwt;


    public LoginResponseDTO(){
        super();
    }

    public LoginResponseDTO(Customer user, String jwt){
        this.user = user;
        this.jwt = jwt;
    }

    public Customer getUser(){
        return this.user;
    }

    public void setUser(Customer user){
        this.user = user;
    }

    public String getJwt(){
        return this.jwt;
    }

    public void setJwt(String jwt){
        this.jwt = jwt;
    }

}
