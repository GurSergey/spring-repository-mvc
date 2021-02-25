package gyr.spring.lab;

import gyr.spring.lab.domain.Country;
import gyr.spring.lab.domain.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }

    @Autowired
    private CountryRepository repository;

    @PostConstruct
    public void init() {
        Country country =  new Country("Russia", "123");
        repository.save(country);
    }
}
