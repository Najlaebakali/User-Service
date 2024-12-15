package com.example.userservice.dto;


public class MedecinDTO extends UserDTO{

    private String label;
    private AdminDTO admin;

    public AdminDTO getAdmin() {
        return admin;
    }

    public void setAdmin(AdminDTO admin) {
        this.admin = admin;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }


}
