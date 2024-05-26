package com.martin.tablesort;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    public Page<Person> getAllPersons(int page, int size, String sortBy, String sortDirection) {
    Sort sort = sortDirection.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy);
    Pageable pageable = PageRequest.of(page, size, sort);
    return personRepository.findAll(pageable);
}

    public Person getPersonById(Long id) {
        return personRepository.findById(id).orElse(null);
    }

    public Person createPerson(Person person) {
        return personRepository.save(person);
    }

    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }

    public Person updatePerson(Long id, Person person) {
        person.setId(id);
        return personRepository.save(person);
    }
}
