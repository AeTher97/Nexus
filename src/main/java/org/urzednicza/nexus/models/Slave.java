package org.urzednicza.nexus.models;

public class Slave {

    private String address;
    private Long id;


    public String getAddress() {
        return address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
