package com.qa.PersonProject.services;

import com.qa.PersonProject.PersonNotFoundException;
import com.qa.PersonProject.entities.Person;
import com.qa.PersonProject.entities.PersonDTO;
import com.qa.PersonProject.entities.PersonRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private PersonRepo pr;
    private ModelMapper mapper;

    public PersonService(PersonRepo pr, ModelMapper mapper){
        super();
        this.pr = pr;
        this.mapper = mapper;
    }

    private PersonDTO mapToDTO(Person p){
        return this.mapper.map(p, PersonDTO.class);
    }

    public String test() {
        return "Testing 1, 2, 3";
    }

    public PersonDTO createPerson(Person person){
        return this.mapToDTO(this.pr.save(person));
    }

    public PersonDTO getById(Long id) throws PersonNotFoundException {
        Optional<Person> exist = this.pr.findById(id);

        if (exist.isEmpty()){
            throw new PersonNotFoundException("Person does not exist!");
        } else {
            Person p = exist.get();
            return this.mapToDTO(p);
        }
    }

    public List<PersonDTO> getAll() {
        return this.pr.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public PersonDTO updatePerson(Long id, Person person){

        Optional<Person> exist = this.pr.findById(id);
        Person p = exist.get();

        p.setAge(person.getAge());
        p.setFirstname(person.getFirstname());
        p.setLastname(person.getLastname());

        return this.mapToDTO(p);

    }

    public boolean deletePerson(Long id){
        this.pr.deleteById(id);
        return !this.pr.existsById(id);
    }

}
