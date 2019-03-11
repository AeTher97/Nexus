package org.urzednicza.nexus.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.urzednicza.nexus.models.Slave;
import org.urzednicza.nexus.models.SlaveDatabaseEntity;
import org.urzednicza.nexus.services.SlaveService;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/slave")
public class SlaveController {
    private RestTemplate restTemplate;
    private SlaveService slaveService;

    public SlaveController(SlaveService slaveService)
    {
        this.restTemplate = new RestTemplate();
        this.slaveService = slaveService;
    }

    @PostMapping("/register")
    public Long registerSlave(@RequestBody Slave slave){
        if(slaveService.getSlave(slave.getAddress())!=null)
            return slaveService.getSlave(slave.getAddress()).getId();

        SlaveDatabaseEntity slaveDatabaseEntity = new SlaveDatabaseEntity();
        slaveDatabaseEntity.setAddress(slave.getAddress());
        slaveService.addSlave(slaveDatabaseEntity);

        Slave slaveId = new Slave();
        slaveId.setAddress(slave.getAddress());
        return slaveService.getSlave(slave.getAddress()).getId();

    }


    @GetMapping("/unregister")
    public void unregisterSlave(@RequestBody Slave slave){
        slaveService.removeSlave(slave.getId());
    }

    @GetMapping("/list")
    public String slaveList(){
        try {
            return new ObjectMapper().writeValueAsString(slaveService.getAll());
        }catch (Exception e){
            e.printStackTrace();
        }
        return "No slaves";
    }

    @GetMapping
    public String getSlave(@RequestParam Long id){
        try {
            return new ObjectMapper().writeValueAsString(slaveService.getSlave(id));
        }catch (Exception e){
            e.printStackTrace();
        }
        return "Slave not found";
    }

}
