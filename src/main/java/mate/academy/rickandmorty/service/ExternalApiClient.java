package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
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

@Component
@RequiredArgsConstructor
@Transactional
public class ExternalApiClient {
    public static final String RESOURCE_URL = "https://rickandmortyapi.com/api/character";
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;
    private final ObjectMapper objectMapper;

    public void fetchAndSaveAllCharacters() {
        HttpClient httpClient = HttpClient.newHttpClient();
        List<Character> charactersList = new ArrayList<>();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(RESOURCE_URL))
                .build();
        try {
            HttpResponse<String> response = httpClient.send(
                    httpRequest, HttpResponse.BodyHandlers.ofString()
            );
            ExternalResponseDataDto dataDto = objectMapper.readValue(
                    response.body(), ExternalResponseDataDto.class
            );
            for (ExternalCharacterDataDto dto : dataDto.getCharacters()) {
                charactersList.add(characterMapper.toModel(dto));
            }
            characterRepository.saveAll(charactersList);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
