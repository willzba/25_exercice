package com.tiendaMaquillajes.tiendaOnline.services;


import com.tiendaMaquillajes.tiendaOnline.entities.makeUpItemsEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class makeUpItemsService {
    private final List<makeUpItemsEntity> items = new ArrayList<>();

    public makeUpItemsService() {
        items.add(new makeUpItemsEntity(UUID.randomUUID(),"Catrice", "mixta","Rubor Catrice Airblush Matt Tono 110 5.5gr","lavable", 30000, 10));
        items.add(new makeUpItemsEntity(UUID.randomUUID(), "Catrice", "mixta","Corrector Catrice True Skin High Cover Tono 010 4.5ml","lavable", 50000, 5));
    }

    public List<makeUpItemsEntity> getAllMakers() {
        return items;
    }

    public Optional<makeUpItemsEntity> getMakerById(UUID id) {
        return items.stream().filter(items -> items.getId().equals(id)).findFirst();
    }

    public void addmakeItems(makeUpItemsEntity makeItems) {
        items.add(makeItems);
    }

    public void updateItems(UUID id, makeUpItemsEntity updatedItems) {
        getMakerById(id).ifPresent(items -> {
            items.setNombreArticulo(updatedItems.getNombreArticulo());
            items.setPiel(updatedItems.getPiel());
            items.setDetalles(updatedItems.getDetalles());
            items.setResistencia(updatedItems.getResistencia());
            items.setPrice(updatedItems.getPrice());
            items.setStock(updatedItems.getStock());
        });
    }

    public void deleteMakeItems(UUID id) {
        items.removeIf(items -> items.getId().equals(id));
    }
}
