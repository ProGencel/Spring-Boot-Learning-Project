package com.works.controller;

import com.works.entity.Person;
import com.works.repository.PersonRepository;
import com.works.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("person")
@RequiredArgsConstructor
public class PersonRestController {

    final PersonService personService;

    @PostMapping("save")
    public Person save(@RequestBody Person person)
    {
        return personService.save(person);
    }

    @GetMapping("list")
    public List<Person> personList()
    {
        return personService.listPerson();
    }

}
