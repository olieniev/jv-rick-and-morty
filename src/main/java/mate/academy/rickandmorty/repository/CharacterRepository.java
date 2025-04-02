package mate.academy.rickandmorty.repository;

import mate.academy.rickandmorty.model.Character;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {
    @Query(value = "SELECT * FROM characters ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Character getRandomCharacter();

    Page<Character> findByNameContainsIgnoreCase(String namePart, Pageable pageable);
}
