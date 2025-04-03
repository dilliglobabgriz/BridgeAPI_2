package isaac.bridge.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import isaac.bridge.entity.Game;
import isaac.bridge.entity.Hand;
import isaac.bridge.entity.Round;
import isaac.bridge.exception.ClientInvalidIdException;
import isaac.bridge.service.GameService;
import isaac.bridge.service.HandService;
import isaac.bridge.service.RoundService;

@RestController 
@RequestMapping(path = "/api")
public class GameApiController {

    private GameService gameService;
    private RoundService roundService;
    private HandService handService;

    public GameApiController(GameService gameService, RoundService roundService, HandService handService) {
        this.gameService = gameService;
        this.roundService = roundService;
        this.handService = handService;
    }
    

    /**
     * Creates a game with four default version bot players
     * 
     * @return the id of the game
     */
    @PostMapping(path = "games")  
    public ResponseEntity<Game> createGame() {
        return ResponseEntity.ok().body(gameService.createGame());
    }

    /**
     * Returns a list of all games in the db
     * 
     * @return
     */
    @GetMapping(path = "games")
    public ResponseEntity<List<Game>> getGames() {
        List<Game> games = gameService.getAllGames();
        return ResponseEntity.ok().body(games);
    }

    /**
     * Adds a new round to the game with the given id
     * 
     * @param gameId
     * @return
     */
    @PostMapping(path = "rounds/{gameId}")
    public ResponseEntity<Round> createRound(@PathVariable int gameId) {
        try {
            Game game = gameService.getGameById(gameId);
            Round round = roundService.createRound(game);
            return ResponseEntity.ok().body(round);
        } catch (ClientInvalidIdException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Returns a list of all rounds regardless of game id
     * 
     * @return
     */
    @GetMapping(path = "rounds")
    public ResponseEntity<List<Round>> getRounds() {
        List<Round> rounds = roundService.getAllRounds();
        return ResponseEntity.ok().body(rounds);
    }


    /**
     * Returns a list of all hands
     * 
     * @return
     */
    @GetMapping(path = "hands")
    public ResponseEntity<List<Hand>> getHands() {
        List<Hand> hands = handService.getAllHands();
        return ResponseEntity.ok().body(hands);
    }


    /**
     * Gets all hands associated with the round id provided
     * 
     * @return
     */
    @GetMapping(path = "hands/{roundId}")
    public ResponseEntity<List<Hand>> getHandsByRoundId(@PathVariable int roundId) {
        List<Hand> hands = handService.getHandsByRoundId(roundId);
        return ResponseEntity.ok().body(hands);
    }

    
}
