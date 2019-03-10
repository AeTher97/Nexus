package org.urzednicza.nexus.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SlaveDatabaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String address;

    public Long getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
