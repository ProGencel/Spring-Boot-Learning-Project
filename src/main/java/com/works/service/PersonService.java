package com.works.service;

import com.works.entity.Person;
import com.works.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    public Person save(Person person)
    {
        personRepository.save(person);
        return person;
    }

    public List<Person> listPerson()
    {
        return personRepository.findAll();
    }

}
