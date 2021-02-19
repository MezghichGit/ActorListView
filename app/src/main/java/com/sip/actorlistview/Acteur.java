package com.sip.actorlistview;

public class Acteur {
    private String nom;
    private int age;
    private String pays;
    private String photo;

    public Acteur(String nom, int age, String pays, String photo) {
        this.nom = nom;
        this.age = age;
        this.pays = pays;
        this.photo = photo;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
