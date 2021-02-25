package gyr.spring.lab.domain.repository;

import gyr.spring.lab.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {

    Optional<Country> findByName(String name);

    Optional<Country> findByCode(String code);

    Optional<Country> findByNameContaining(String name);
}
