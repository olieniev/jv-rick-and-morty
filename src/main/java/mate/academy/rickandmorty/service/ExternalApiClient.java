package mate.academy.rickandmorty.service;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.ExternalCharacterDataDto;
import mate.academy.rickandmorty.dto.external.ExternalResponseDataDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
@Transactional
public class ExternalApiClient {
    public static final String RESOURCE_URL = "https://rickandmortyapi.com/api/character/?page=";
    private final RestTemplate restTemplate;
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    public void fetchAndSaveAllCharacters() {
        String next = RESOURCE_URL;
        List<Character> charactersList = new ArrayList<>();
        while (next != null) {
            ExternalResponseDataDto dataDto = restTemplate.getForObject(
                    next, ExternalResponseDataDto.class
            );
            if (dataDto != null) {
                for (ExternalCharacterDataDto dto : dataDto.getCharacters()) {
                    charactersList.add(characterMapper.toModel(dto));
                }
                next = dataDto.getInfo().getNext();
            } else {
                next = null;
            }
        }
        characterRepository.saveAll(charactersList);
    }
}
