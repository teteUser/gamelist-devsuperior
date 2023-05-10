package br.com.tete.gamelist.service;

import br.com.tete.gamelist.dto.GameDTO;
import br.com.tete.gamelist.dto.GameMinDTO;
import br.com.tete.gamelist.projection.GameMinProjection;
import br.com.tete.gamelist.repository.GameRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameService {

    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Transactional(readOnly = true)
    public List<GameMinDTO> findAll(){
        return gameRepository.findAll().stream().map(game -> new GameMinDTO(game)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public GameDTO findById(Long id) {
        return new GameDTO(gameRepository.findById(id).get());
    }

    @Transactional(readOnly = true)
    public List<GameMinDTO> findByList(Long listId){
        return gameRepository.searchByList(listId).stream().map(game -> new GameMinDTO(game)).collect(Collectors.toList());
    }

}
