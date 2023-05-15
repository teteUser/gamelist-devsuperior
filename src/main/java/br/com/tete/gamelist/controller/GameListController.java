package br.com.tete.gamelist.controller;

import br.com.tete.gamelist.dto.GameListDTO;
import br.com.tete.gamelist.dto.GameMinDTO;
import br.com.tete.gamelist.dto.ReplacementDTO;
import br.com.tete.gamelist.service.GameListService;
import br.com.tete.gamelist.service.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lists")
public class GameListController {

    private final GameListService gameListService;
    private final GameService gameService;

    public GameListController(GameListService gameListService, GameService gameService) {
        this.gameListService = gameListService;
        this.gameService = gameService;
    }

    @GetMapping
    public ResponseEntity<List<GameListDTO>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(gameListService.findAll());
    }

    @GetMapping("/{listId}/games")
    public ResponseEntity<List<GameMinDTO>> findByList(@PathVariable Long listId) {
        return ResponseEntity.status(HttpStatus.OK).body(gameService.findByList(listId));
    }

    @PostMapping("/{listId}/replacement")
    public void updatePositions(@PathVariable Long listId, @RequestBody ReplacementDTO replacementDTO) {
        gameListService.updatePositions(listId, replacementDTO.getSourceIndex(), replacementDTO.getDestinationIndex());
    }

}
