package gyr.spring.lab.rest;

import gyr.spring.lab.domain.Country;
import gyr.spring.lab.domain.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CountryController {

    private final CountryRepository repository;

    @Autowired
    public CountryController(CountryRepository repository) {
        this.repository = repository;
    }
    @RequestMapping(
            value = "/country",
            method = RequestMethod.GET
    )
    public List<CountryDto> get() {
        return repository.findAll().stream()
                .map(CountryDto::toDto)
                .collect(Collectors.toList());
    }

    @RequestMapping(
            value = "/country/{id}",
            method = RequestMethod.GET
    )
    public CountryDto get(
            @PathVariable("id") int id
    ) {
        Country country = repository.findById(id).orElseThrow(NotFoundException::new);
        return CountryDto.toDto(country);
    }

    @RequestMapping(
            value = "/country/name/like/{name}",
            method = RequestMethod.GET
    )
    public CountryDto get(
            @PathVariable("name") String name
    ) {
        Country country = repository.findByNameContaining(name).orElse(null);
        return country != null ? CountryDto.toDto(country) : null;
    }

    @RequestMapping(
            value = "/country/",
            method = RequestMethod.POST
    )
    public @ResponseBody
    CountryDto create(
            @RequestBody CountryDto dto
    ) {
        Country country = CountryDto.toDomainObject(dto);
        Country accountWithId = repository.save(country);
        return CountryDto.toDto(accountWithId);
    }

    @DeleteMapping("/country/{id}")
    public void delete(@PathVariable("id") int id) {
        repository.deleteById(id);
    }

    @PutMapping("/country/{id}/holder")
    public void changeName(
            @PathVariable("id") int id,
            @RequestParam("name") String name
    ) {
        Country country = repository.findById(id).orElseThrow(NotFoundException::new);
        country.setName(name);
        repository.save(country);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotEnoughFunds(NotFoundException ex) {
        return ResponseEntity.badRequest().body("Not found");
    }

}
