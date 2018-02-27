package com.example.web.forms;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jwebform.Form;
import jwebform.element.CheckBoxType;
import jwebform.element.NumberType;
import jwebform.element.PasswordType;
import jwebform.element.RadioType;
import jwebform.element.SelectType;
import jwebform.element.SubmitType;
import jwebform.element.TextAreaType;
import jwebform.element.TextDateType;
import jwebform.element.TextType;
import jwebform.element.XSRFProtectionType;
import jwebform.element.structure.Decoration;
import jwebform.element.structure.ElementContainer;
import jwebform.validation.FormValidator;
import jwebform.validation.ValidationResult;
import jwebform.validation.Validator;
import jwebform.validation.criteria.Criteria;

public class ExampleForm {

  public static Form build(String id) {
    List<FormValidator> formValidators = new ArrayList<>();

    XSRFProtectionType xsrfProtection = new XSRFProtectionType();

    ElementContainer firstname =
        new TextType("firstname", new Decoration("Your firstname", "hilfe zum Vorname", ""), "Jochen")
            .of(new Validator());
    ElementContainer lastname =
        new TextType("lastname", new Decoration("Your lastname", "help", "placeholder"), "pie")
            .of(new Validator(Criteria.required(), Criteria.maxLength(3)));
    ElementContainer number =
        new TextType("number", new Decoration("Size", "help", "placeholder"), "2")
            .of(new Validator(Criteria.number()));
    ElementContainer birthday = new TextDateType("birthday",
        new Decoration("Birhtday", "Please insert your birhtday", "placeholder"),
        LocalDate.now()).of(new Validator());
    ElementContainer gender = new SelectType("gender", new Decoration("Gender", "help", ""),
        "", new String[] {"m", "f"}, new String[] {"Male", "Female"}).of();

    ElementContainer optin =
        new CheckBoxType("optin", new Decoration("Optin", "help", "placeholder"), true)
            .of();

    ElementContainer textArea = new TextAreaType("area", new Decoration("Area"), "").of();

    ElementContainer number2 =
        new NumberType("number2", new Decoration("Number", "help", "placeholder"), 5).of();

    ElementContainer password =
        new PasswordType("pwd", new Decoration("Passwoid", "help", "placeholder")).of();

    ElementContainer radio = new RadioType("radio", new Decoration("Your Prefix", "help", ""), "",
        new String[] {"m", "f"}, new String[] {"Mr", "Misses"})
            .of(new Validator(Criteria.required()));

    // ElementContainer checkoutDate = new SelectDateType("checkoutDate",
    // new Decoration("Checkout Date"), LocalDate.now(), 2020, 2015).of();


    ElementContainer submit = new SubmitType("Save").of();

    List<ElementContainer> elements = new ArrayList<>();
    elements.add(xsrfProtection.of());
    elements.add(firstname);
    elements.add(lastname);
    elements.add(birthday);
    elements.add(radio);
    elements.add(number);
    elements.add(gender);
    elements.add(optin);
    elements.add(textArea);
    elements.add(number2);
    elements.add(password);
    // elements.add(checkoutDate);
    elements.add(submit);
    formValidators.add(it -> {
      final Map<ElementContainer, ValidationResult> overridenValidationResults = new HashMap<>();
      if (it.get(firstname).getValue().length() > 8) {
        overridenValidationResults.put(firstname, ValidationResult.fail("not_ok"));
      }
      return overridenValidationResults;
    });
    return new Form(id, elements, formValidators);
  }

  // formchecker DateInput: 52 methods
  // jWebform DateInput: 3
}
