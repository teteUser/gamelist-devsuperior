package br.com.tete.gamelist.service;

import br.com.tete.gamelist.dto.GameListDTO;
import br.com.tete.gamelist.repository.GameListRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameListService {

    private final GameListRepository gameListRepository;

    public GameListService(GameListRepository gameListRepository) {
        this.gameListRepository = gameListRepository;
    }

    @Transactional(readOnly = true)
    public List<GameListDTO> findAll(){
        return gameListRepository.findAll().stream().map(list -> new GameListDTO(list)).collect(Collectors.toList());
    }

}
