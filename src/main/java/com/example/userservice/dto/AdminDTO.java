package com.example.userservice.dto;


import com.example.userservice.entities.Admin;

public class AdminDTO extends UserDTO{
    private String label;


    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }


}
