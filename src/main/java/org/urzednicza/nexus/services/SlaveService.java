package org.urzednicza.nexus.services;

import org.springframework.stereotype.Service;
import org.urzednicza.nexus.Repositories.SlaveRepository;
import org.urzednicza.nexus.models.SlaveDatabaseEntity;

import java.util.List;

@Service
public class SlaveService {
    private SlaveRepository slaveRepository;

    public SlaveService(SlaveRepository slaveRepository){
        this.slaveRepository = slaveRepository;
    }

    public void addSlave(SlaveDatabaseEntity slave){
        slaveRepository.save(slave);
    }

    public void removeSlave(Long id){
        slaveRepository.deleteById(id);
    }

    public SlaveDatabaseEntity getSlave(Long id) throws Exception{
        return slaveRepository.findById(id).orElseThrow(() -> new RuntimeException());
    }

    public SlaveDatabaseEntity getSlave(String address) {
        return slaveRepository.findByAddress(address).orElse(null);
    }

    public List<SlaveDatabaseEntity> getAll(){
        return slaveRepository.findAll();

    }



}
