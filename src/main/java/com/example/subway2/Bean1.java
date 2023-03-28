package com.example.subway2;

public class Bean1 {
    private int ID;
    private String name;
    private String nol;

    public Bean1() {
    }

    private String nolstation;

    public Bean1(int ID, String name, String nol, String nolstation) {
        this.ID = ID;
        this.name = name;
        this.nol = nol;
        this.nolstation = nolstation;

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNol() {
        return nol;
    }

    public void setNol(String nol) {
        this.nol = nol;
    }

    public String getNolstation() {
        return nolstation;
    }

    public void setNolstation(String nolstation) {
        this.nolstation = nolstation;
    }
}
