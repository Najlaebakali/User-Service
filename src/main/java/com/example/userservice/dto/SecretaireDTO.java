package com.example.userservice.dto;



public class SecretaireDTO extends UserDTO{

    private String label;
    private MedecinDTO medecin;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public MedecinDTO getMedecin() {
        return medecin;
    }

    public void setMedecin(MedecinDTO medecin) {
        this.medecin = medecin;
    }

}
