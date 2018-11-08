package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonWithManyKids {
	@Id
	public String id;
	public String name;
//	@DBRef
	@DBRef(lazy = true)
	public List<Child> kids;

	public PersonWithManyKids(String name) {
		this.name = name;
	}
}
