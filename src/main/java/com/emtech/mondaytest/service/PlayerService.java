package com.emtech.mondaytest.service;


import com.emtech.mondaytest.entity.Player;
import com.emtech.mondaytest.repository.PlayerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PlayerService {

    private final PlayerRepository playerRepository;


    @Autowired
    public PlayerService(PlayerRepository playerRepository){
        this.playerRepository=playerRepository;
    }

    public List<Player> getAllPlayers(){
        return playerRepository.findAll();
    }


    public List<Player> getPlayersFromTeam(String teamName){
        return playerRepository.findAll().stream()
                .filter(player -> teamName.equals(player.getTeam()))
                .collect(Collectors.toList());
    }



    public List<Player> getPlayerByName(String searchText){
        return playerRepository.findAll().stream()
                .filter(player -> player.getName().toLowerCase().contains(searchText.toLowerCase()))
                .collect(Collectors.toList());
    }


    public List<Player> getPlayersByPos(String searchText){
        return playerRepository.findAll().stream()
                .filter(player -> player.getPos().toLowerCase().contains(searchText.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Player> getPlayersByNation(String searchText){
        return playerRepository.findAll().stream()
                .filter(player -> player.getNation().toLowerCase().contains(searchText.toLowerCase()))
                .collect(Collectors.toList());
    }


    public List<Player> getPlayersByTeamAndNation(String team,String nation){


        return playerRepository.findAll().stream()
                .filter(player -> team.equals(player.getTeam()) && nation.equals(player.getNation()))
                .collect(Collectors.toList());
    }


    public List<Player> getPlayersByTeamAndPosition(String team,String position){


        return playerRepository.findAll().stream()
                .filter(player -> team.equals(player.getTeam()) && position.equals(player.getPos()))
                .collect(Collectors.toList());
    }

    public String  addPlayers(Player player){
        playerRepository.save(player);
        return "You added "+player.getName()+ "to the database. The team is "+player.getTeam();
    }

    public Player update(Player updatedplayer){
        Optional<Player> existingPlayer = playerRepository.findByName(updatedplayer.getName());


        if (existingPlayer.isPresent()){
            Player playerToUpdate =existingPlayer.get();
            playerToUpdate.setName(updatedplayer.getName());
            playerToUpdate.setTeam(updatedplayer.getTeam());
            playerToUpdate.setPos(updatedplayer.getPos());
            playerToUpdate.setNation(updatedplayer.getNation());

            playerRepository.save(playerToUpdate);

            return playerToUpdate;

        }
        return null;
    }
@Transactional
    public String deletePlayer(String playerName){
        playerRepository.deleteByName(playerName);
        return "player"+playerName+" deleted Successfully";
    }


}
