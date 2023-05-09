package br.com.tete.gamelist.service;

import br.com.tete.gamelist.dto.GameMinDTO;
import br.com.tete.gamelist.repository.GameRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameService {

    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<GameMinDTO> findAll(){
        return gameRepository.findAll().stream().map(game -> new GameMinDTO(game)).collect(Collectors.toList());
    }

}
