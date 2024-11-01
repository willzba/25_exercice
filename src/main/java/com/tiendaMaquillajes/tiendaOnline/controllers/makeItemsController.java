package com.tiendaMaquillajes.tiendaOnline.controllers;


import com.tiendaMaquillajes.tiendaOnline.entities.makeUpEntity;
import com.tiendaMaquillajes.tiendaOnline.entities.makeUpItemsEntity;
import com.tiendaMaquillajes.tiendaOnline.services.makeUpService;
import com.tiendaMaquillajes.tiendaOnline.services.makeUpItemsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/makeitems")
public class makeItemsController {

    private final makeUpItemsService itemsService;

    public makeItemsController(makeUpItemsService itemsService) {
        this.itemsService = itemsService;

    }

    // Endpoints para Articulos de maquillajes
    @GetMapping("/items")
    public List<makeUpItemsEntity> getAllMakers() {
        return itemsService.getAllMakers();
    }

    @GetMapping("/items/{id}")
    public makeUpItemsEntity getMakerById(@PathVariable UUID id) {
        return itemsService.getMakerById(id).orElse(null);
    }

    @PostMapping("/item")
    public void addMakeItems(@RequestBody makeUpItemsEntity items) {
        itemsService.addmakeItems(items);
    }

    @PutMapping("/items/{id}")
    public void updateMakeItems(@PathVariable UUID id, @RequestBody makeUpItemsEntity makeItems) {
        itemsService.updateItems(id, makeItems);
    }

    @DeleteMapping("/items/{id}")
    public void deleteMakeItems(@PathVariable UUID id) {
        itemsService.deleteMakeItems(id);
    }




}