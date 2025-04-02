package mate.academy.rickandmorty.service;

import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.InternalCharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    @Override
    public InternalCharacterDto getRandomCharacter() {
        return characterMapper.toDto(characterRepository.getRandomCharacter());
    }

    @Override
    public Page<InternalCharacterDto> findByNameContains(String namePart, Pageable pageable) {
        return characterRepository
            .findByNameContainsIgnoreCase(namePart, pageable)
            .map(characterMapper::toDto);
    }
}
