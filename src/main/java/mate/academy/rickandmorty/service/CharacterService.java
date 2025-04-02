package mate.academy.rickandmorty.service;

import mate.academy.rickandmorty.dto.internal.InternalCharacterDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CharacterService {
    InternalCharacterDto getRandomCharacter();

    Page<InternalCharacterDto> findByNameContains(String namePart, Pageable pageable);
}
