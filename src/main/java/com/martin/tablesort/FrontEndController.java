package com.martin.tablesort;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@Validated
public class FrontEndController {

    private final PersonService personService;

    @GetMapping("/people-table")
    public String getPeopleTable(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "firstName") String sortBy, @RequestParam(defaultValue = "asc") String sortDirection) {
        Page<Person> people = personService.getAllPersons(page, size, sortBy, sortDirection);
        model.addAttribute("people", people.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", people.getTotalPages());
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortDirection", sortDirection);
        return "people-table";
    }

}
