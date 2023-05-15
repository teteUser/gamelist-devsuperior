package br.com.tete.gamelist.service;

import br.com.tete.gamelist.dto.GameListDTO;
import br.com.tete.gamelist.projection.GameMinProjection;
import br.com.tete.gamelist.repository.GameListRepository;
import br.com.tete.gamelist.repository.GameRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameListService {

    private final GameListRepository gameListRepository;
    private final GameRepository gameRepository;

    public GameListService(GameListRepository gameListRepository, GameRepository gameRepository) {
        this.gameListRepository = gameListRepository;
        this.gameRepository = gameRepository;
    }

    @Transactional(readOnly = true)
    public List<GameListDTO> findAll(){
        return gameListRepository.findAll().stream().map(list -> new GameListDTO(list)).collect(Collectors.toList());
    }

    @Transactional
    public void updatePositions(Long listId, int sourceIndex, int destinationIndex) {
        List<GameMinProjection> list = gameRepository.searchByList(listId);
        GameMinProjection sourceObj = list.remove(sourceIndex);
        list.add(destinationIndex, sourceObj);

        int min = sourceIndex <  destinationIndex ? sourceIndex : destinationIndex;
        int max = sourceIndex >  destinationIndex ? sourceIndex : destinationIndex;

        for(int i = min; i <= max; i++) {
            gameListRepository.updateBelongingPosition(listId, list.get(i).getId(), i);
        }

    }

}
