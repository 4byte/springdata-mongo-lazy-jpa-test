package com.example.demo.controller;


import com.example.demo.entity.Address;
import com.example.demo.entity.Child;
import com.example.demo.entity.Person;
import com.example.demo.entity.PersonWithManyKids;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.ChildRepository;
import com.example.demo.repository.PersonRepository;
import com.example.demo.repository.PersonWithManyKidsRepository;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Component
@Controller
@RequestMapping("/")
public class TestController {
	@Autowired
	ChildRepository childRepository;
	@Autowired
	PersonRepository personRepository;
	@Autowired
	PersonWithManyKidsRepository personWithManyKidsRepository;
	@Autowired
	AddressRepository addressRepository;


	Logger logger = LoggerFactory.getLogger(TestController.class);

	@RequestMapping(value = "putPerson", method = RequestMethod.GET)
	@ResponseBody
	public String putPerson() {
		Random random = new Random();
		Address alabama = new Address("5", "Alabama",49387583485L);
		Address florida = new Address("6", "Florida",238942374823742L);
		List<Address> addresses = new ArrayList<>();
		for (int i = 0; i < 100000; i++) {
			addresses.add(new Address(null, UUID.randomUUID().toString(), random.nextLong()));
		}
		Child childKevin = new Child("3","Kevin", addresses);
		Child childAmanda = new Child("4","Amanda", addresses);
		Person person = new Person("1", "Alex", childKevin);
		PersonWithManyKids personWithManyKids = new PersonWithManyKids("1", "Bob", Lists.newArrayList(
				childKevin,
				childAmanda
		));
		addressRepository.saveAll(addresses);
		addressRepository.saveAll(Lists.newArrayList(alabama,florida));
		childRepository.saveAll(Lists.newArrayList(childAmanda,childKevin));
		personRepository.save(person);
		personWithManyKidsRepository.save(personWithManyKids);
		return "ok";
	}

	@RequestMapping(value = "getPerson", method = RequestMethod.GET)
	@ResponseBody
	public Person getPerson(){
		logger.info("Fetching person");
		Optional<Person> person = personRepository.findById("1");
		return person.get();
	}

	@RequestMapping(value = "getPersonName", method = RequestMethod.GET)
	@ResponseBody
	public String getPersonName(){
		logger.info("Fetching person name");
		Optional<Person> person = personRepository.findById("1");
		return person.get().getName();
	}

	@RequestMapping(value = "getPersonWithManyKids", method = RequestMethod.GET)
	@ResponseBody
	public PersonWithManyKids getPersonWithManyKids(){
		logger.info("Fetching person with many kids");
		return personWithManyKidsRepository.findById("1").get();
	}
}
