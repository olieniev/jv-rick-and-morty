package mate.academy.rickandmorty.dto.internal;

public record InternalCharacterDto(
        Long id,
        Long externalId,
        String name,
        String status,
        String gender
) {
}
