package com.qa.PersonProject.controllers;

import com.qa.PersonProject.PersonNotFoundException;
import com.qa.PersonProject.entities.Person;
import com.qa.PersonProject.entities.PersonDTO;
import com.qa.PersonProject.services.PersonService;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController()
public class PersonController {

        private PersonService ps;

        public PersonController(PersonService ps){
                super();
                this.ps = ps;
        }

        @GetMapping("/test")
        public String test() {
                return "Testing 1, 2, 3";
        }

        @PostMapping("/create")
        public PersonDTO createPerson(@RequestBody Person person){
                return this.ps.createPerson(person);
        }

        @GetMapping("/getUser/{id}")
        public PersonDTO getById(@PathVariable Long id) throws PersonNotFoundException {
                return this.ps.getById(id);
        }

        @GetMapping("/getAll")
        public List<PersonDTO> getAll() {
                return this.ps.getAll();
        }

        @PutMapping("/update/{id}")
        public PersonDTO updatePerson(@PathVariable Long id, @RequestBody Person person){
                return this.ps.updatePerson(id, person);
        }

        @DeleteMapping("/delete")
        public boolean deletePerson(@PathParam("id") Long id){
                return this.ps.deletePerson(id);
        }
}
