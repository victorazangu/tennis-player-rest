package com.shemi.tennisplayerrest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class PlayerController {
    @Autowired
    private PlayerService service;

    @GetMapping("/welcome")
    public String welcome() {
        return "Tennis Player REST API";
    }

    @GetMapping("/players")
    public List<Player> getAllPlayers() {
        return service.getAllPlayers();
    }

    @GetMapping("players/{id}")
    public Player getPlayerById(@PathVariable int id) {
        return service.getPlayerById(id);
    }

    @PostMapping("/players")
    public Player addPlayer(@RequestBody Player player) {
        player.setId(0);
        return service.createPlayer(player);
    }

    @PutMapping("/players/{id}")
    public Player updatePlayer(@RequestBody Player player, @PathVariable int id) {
        return service.updatePlayer(player, id);
    }

    @PatchMapping("/players/{id}")
    public Player patchPlayer(@PathVariable int id, @RequestBody Map<String, Object> player) {
        return service.patch(id, player);
    }

    @DeleteMapping("/players/{id}")
    public String deletePlayer(@PathVariable int id) {
        return service.deletePlayer(id);
    }
}
