package com.tiendaMaquillajes.tiendaOnline.controllers;

import com.tiendaMaquillajes.tiendaOnline.entities.makeUpEntity;
import com.tiendaMaquillajes.tiendaOnline.services.makeUpService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/makeup")
public class makeUpControlles {

    private final makeUpService makeUpService;

    public makeUpControlles(makeUpService makeUpService) {
        this.makeUpService = makeUpService;
    }

    // Endpoints para maquillajes
    @GetMapping("/makeUps")
    public List<makeUpEntity> getAllmakeUp() {
        return makeUpService.getAllMakeUp();
    }

    @GetMapping("/makeUp/{id}")
    public makeUpEntity getMakeUpById(@PathVariable UUID id) {
        return makeUpService.getmakeUpById(id).orElse(null);
    }

    @PostMapping("/makeUp")
    public void addMakeUp(@RequestBody makeUpEntity makeUp) {
        makeUpService.addMakeUp(makeUp);
    }

    @PutMapping("/makeUps/{id}")
    public void updateMakeUp(@PathVariable UUID id, @RequestBody makeUpEntity makeUp) {
        makeUpService.updateMakeUp(id, makeUp);
    }

    @DeleteMapping("/makeUps/{id}")
    public void deleteMakeUp(@PathVariable UUID id) {
        makeUpService.deleteMakeUp(id);
    }
}
