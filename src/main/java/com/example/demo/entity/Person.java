package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
	@Id
	public String id;
	public String name;
	@Field("child_id")
//	@DBRef
	@DBRef(lazy = true)
	public Child child;

	public Person(String name) {
		this.name = name;
	}
}
