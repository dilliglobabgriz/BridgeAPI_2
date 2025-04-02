package isaac.bridge.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import isaac.bridge.entity.Game;
import isaac.bridge.service.GameService;

@RestController 
@RequestMapping(path = "/api/games")
public class GameApiController {

    private GameService gameService;

    public GameApiController(GameService gameService) {
        this.gameService = gameService;
    }
    
    @GetMapping 
    public ResponseEntity<String> homepage() {
        String message = "Hello World";
        return ResponseEntity.ok().body(message);
    }

    /**
     * Creates a game with four default version bot players
     * 
     * @return the id of the game
     */
    @PostMapping("/create")  
    public ResponseEntity<Integer> createGame() {
        return ResponseEntity.ok().body(gameService.createGame().getId());
    }
    
}
