package com.martin.tablesort;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/react")
public class ReactPersonController {

    private final PersonService personService;

    @Autowired
    public ReactPersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/persons")
    public Page<Person> getAllPersons(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int size,
                                      @RequestParam(defaultValue = "firstName") String sortBy,
                                      @RequestParam(defaultValue = "asc") String sortDirection) {
        return personService.getAllPersons(page, size, sortBy, sortDirection);
    }

    @PostMapping("/add-person")
    public ResponseEntity<Person> addPerson(@RequestBody Person person) {
        Person createdPerson = personService.createPerson(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPerson);
    }

    @DeleteMapping("/delete-person/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/update-person/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody Person person) {
        Person updatedPerson = personService.updatePerson(id, person);
        return ResponseEntity.ok().body(updatedPerson);
    }
}
