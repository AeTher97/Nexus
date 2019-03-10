package org.urzednicza.nexus.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.urzednicza.nexus.models.Slave;
import org.urzednicza.nexus.models.SlaveDatabaseEntity;
import org.urzednicza.nexus.services.SlaveService;

@RestController
@RequestMapping("/slave")
public class SlaveController {

    private SlaveService slaveService;

    public SlaveController(SlaveService slaveService)
    {
        this.slaveService = slaveService;
    }

    @PostMapping("/register")
    public void registerSlave(@RequestBody Slave slave){
        SlaveDatabaseEntity slaveDatabaseEntity = new SlaveDatabaseEntity();
        slaveDatabaseEntity.setAddress(slave.getAddress());
        slaveService.addSlave(slaveDatabaseEntity);
    }

    @GetMapping("/unregister")
    public void deregisterSlave(@RequestBody Slave slave){
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
