package isaac.bridge.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isaac.bridge.entity.Game;
import isaac.bridge.entity.Player;
import isaac.bridge.repository.GameRepository;
import isaac.bridge.repository.PlayerRepository;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private PlayerRepository playerRepository;



    public Game createGame() {
        List<Player> players = createBotPlayers();

        Game game = new Game(players.get(0).getPlayerId(), 
                             players.get(1).getPlayerId(),
                             players.get(2).getPlayerId(),
                             players.get(3).getPlayerId());

        return gameRepository.save(game);
    }

    public List<Player> createBotPlayers() {
        List<Player> botPlayers = new ArrayList<>();

        for (int i=0; i<4; i++) {
            botPlayers.add(createBotPlayer());
        }
        
        return botPlayers;
    }
    
    public Player createBotPlayer() {
        Player bot = new Player();

        // Can modify default player attributes here

        return playerRepository.save(bot);
    }

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }
}
