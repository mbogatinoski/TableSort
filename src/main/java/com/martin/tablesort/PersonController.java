package com.martin.tablesort;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Validated
@Log4j2
public class PersonController {

    private final PersonService personService;

    @GetMapping("/persons")
    public Page<Person> getAllPersons(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "firstName") String sortBy, @RequestParam(defaultValue = "asc") String sortDirection) {
        log.info("Getting all persons");
        return personService.getAllPersons(page, size, sortBy, sortDirection);
    }

    public Person getPersonById(Long id) {
        return personService.getPersonById(id);
    }

    @PostMapping("/add-person")
    public String addPerson(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("age") int age) {
        Person person = new Person();
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setAge(age);
        personService.createPerson(person);
        return "redirect:/people-table";
    }

    @GetMapping("/delete-person/{id}")
    public String deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
        return "redirect:/people-table";
    }

    @PostMapping("/update-person/{id}")
    public String updatePerson(@PathVariable Long id, @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("age") int age) {
        Person person = personService.getPersonById(id);
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setAge(age);
        personService.updatePerson(id, person);
        return "redirect:/people-table";
    }
}
