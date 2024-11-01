package com.tiendaMaquillajes.tiendaOnline.services;


import com.tiendaMaquillajes.tiendaOnline.entities.makeUpEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class makeUpService {
    private final List<makeUpEntity> makeUp = new ArrayList<>();

    public makeUpService() {
        makeUp.add(new makeUpEntity(UUID.randomUUID(),"lapices de ojo", "Essence","Maquillaje", 7000, 15));
        makeUp.add(new makeUpEntity(UUID.randomUUID(),"pestañinas de cejas", "Covergirl","Maquillaje", 15000, 8));
    }

    public List<makeUpEntity> getAllMakeUp() {
        return makeUp;
    }

    public Optional<makeUpEntity> getmakeUpById(UUID id) {
        return makeUp.stream().filter(bike -> bike.getId().equals(id)).findFirst();
    }

    public void addMakeUp(makeUpEntity bike) {
        makeUp.add(bike);
    }

    public void updateMakeUp(UUID id, makeUpEntity updatedMakeUp) {
        getmakeUpById(id).ifPresent(makeUp -> {
            makeUp.setNombreArticulo(updatedMakeUp.getNombreArticulo());
        });
    }

    public void deleteMakeUp(UUID id) {
        makeUp.removeIf(makeUp -> makeUp.getId().equals(id));
    }
}