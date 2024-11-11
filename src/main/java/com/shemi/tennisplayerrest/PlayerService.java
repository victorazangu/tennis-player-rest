package com.shemi.tennisplayerrest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository repository;

    public List<Player> getAllPlayers() {
        return repository.findAll();
    }

    public Player getPlayerById(int id) {
        Optional<Player> player = repository.findById(id);
        Player p = null;
        if (player.isPresent()) {
            p = player.get();
        } else {
            throw new PlayerNotFoundException("Player with the id " + id + " not found ");
        }
        return p;
    }

    public Player createPlayer(Player player) {
        return repository.save(player);
    }

    public Player updatePlayer(Player player, int id) {
        Optional<Player> playerOptional = repository.findById(id);
        if (playerOptional.isPresent()) {
            Player p = playerOptional.get();
            p.setId(id);
            if(player.getName() != null){
                p.setName(player.getName());
            }
            p.setName(player.getName());
            p.setTitles(player.getTitles());
            p.setNationality(player.getNationality());
            p.setDob(player.getDob());
            return repository.save(p);
        }
        throw new PlayerNotFoundException("Player with the id " + id + " not found ");
    }
    public Player patch( int id, Map<String, Object> partialPlayer) {
        Optional<Player> player = repository.findById(id);
        if(player.isPresent()) {
            partialPlayer.forEach( (key, value) -> {
                System.out.println("Key: " + key + " Value: " + value);
                Field field = ReflectionUtils.findField(Player.class, key);
                ReflectionUtils.makeAccessible(field);
                ReflectionUtils.setField(field, player.get(), value);
            });
        }
        return repository.save(player.get());
    }

    public String deletePlayer(int id) {
        Optional<Player> player = repository.findById(id);
        if(player.isPresent()) {
            repository.deleteById(id);
            return "Player with id " + id + " deleted";
        }
        throw new PlayerNotFoundException("Player with the id " + id + " not found ");
    }
}
