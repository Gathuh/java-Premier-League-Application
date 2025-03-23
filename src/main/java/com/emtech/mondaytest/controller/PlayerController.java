package com.emtech.mondaytest.controller;


import com.emtech.mondaytest.entity.Player;
import com.emtech.mondaytest.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/player")
public class PlayerController {

    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService){
        this.playerService=playerService;
    }

    @GetMapping
    public List<Player> getPlayers(
            @RequestParam(required = false) String team,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String position,
            @RequestParam(required = false) String  nation

    ){
        if (team != null && nation != null) {
            return playerService.getPlayersByTeamAndNation(team, nation);
        }
        if (team != null && position != null) {
            return playerService.getPlayersByTeamAndPosition(team, position);  // Fixed: Added parameters & return statement
        }
        if (team != null) {
            return playerService.getPlayersFromTeam(team);
        }
        if (name != null) {
            return playerService.getPlayerByName(name);  // Fixed: Added return statement
        }
        if (position != null) {
            return playerService.getPlayersByPos(position);
        }
        if (nation != null) {
            return playerService.getPlayersByNation(nation);
        }

        return playerService.getAllPlayers();  // If no filter is provided, return all players
    }






    @PostMapping
    public ResponseEntity<String > addPlayer(@RequestBody Player player){
        String message = playerService.addPlayers(player);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }


    @PutMapping
    public ResponseEntity<Player> putPlayers(@RequestBody  Player player){
        Player putPlayers=playerService.update(player);

        if (putPlayers!=null){
            return ResponseEntity.status(HttpStatus.OK).body(putPlayers);
        }else{
            return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/{player-name}")
    public ResponseEntity<String > deletePlayer4(@PathVariable String playerName){
        playerService.deletePlayer(playerName);

        return ResponseEntity.ok("Deleted Successfully");
    }


}

