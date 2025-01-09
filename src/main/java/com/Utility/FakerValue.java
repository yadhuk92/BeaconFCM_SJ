package com.Utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
//Auth Rajesh

public class FakerValue {
Faker fakedata = new Faker(new Locale("en-US"));
FakeValuesService fakeservice = new FakeValuesService(new Locale("en-US"), new RandomService());
public String getFirstName() {
	String firstName;
	do {
		firstName = fakedata.name().firstName().toString();
	} while(firstName.length() <= 4);
	
	return firstName;
	
}
public String getLastname() {
return fakedata.name().lastName().toString();
}
public String getEmail() {
return fakeservice.bothify("????##@yopmail.com");
}
public String getPhone() {
//fakedata.number().digits(10);
return "9"+fakedata.number().digits(9).toString();
}
public String getRandomWord() {
return fakedata.lorem().word();
}
public List<String> getRandomWords(int count) {
return fakedata.lorem().words(count);
}
public String getMessage() {
return fakedata.backToTheFuture().quote();
}
public String getSubject() {
return fakedata.book().title();
}
public String getCompany() {
return fakedata.company().name();
}
public String getCategory() {
return fakedata.witcher().location();
}
public String getQuery() {
return fakedata.lorem().sentence(1);
}
public List<String> getShortNames(int count) {
List<String> shortnames = new ArrayList<String>();
for(int i=0; i<count; i++) {
String value = "#"+(fakedata.lorem().fixedString(4).toLowerCase());
shortnames.add(value);
}
return shortnames;
}
public String getCountry() {
return fakedata.address().country();
}
public String getSubString(String value) {
String val = value.substring(value.lastIndexOf(" ")+1);
return val;
}
public static void main(String args[]) {
FakerValue data = new FakerValue();
String value = "FirstName : Sandhya";
String val = value.substring(value.lastIndexOf(" ")+1);
System.out.println(val);
}
}