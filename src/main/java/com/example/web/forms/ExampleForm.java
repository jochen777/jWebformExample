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
import jwebform.element.SelectDateType;
import jwebform.element.SelectType;
import jwebform.element.SubmitType;
import jwebform.element.TextAreaType;
import jwebform.element.TextType;
import jwebform.element.UploadType;
import jwebform.element.XSRFProtectionType;
import jwebform.element.builder.TextTypeBuilder;
import jwebform.element.structure.Decoration;
import jwebform.element.structure.ElementContainer;
import jwebform.validation.FormValidator;
import jwebform.validation.ValidationResult;
import jwebform.validation.Validator;
import jwebform.validation.criteria.Criteria;

public class ExampleForm {

  public static Form build() {
    String id = "id";
    List<FormValidator> formValidators = new ArrayList<>();

    XSRFProtectionType xsrfProtection = new XSRFProtectionType();

    ElementContainer firstname =
        new TextType("firstname", "Jochen").of(new Validator(Criteria.accept("Jochen", "Horst")),
            new Decoration("Your firstname", "hilfe zum Vorname", ""));
    ElementContainer lastname = new TextType("lastname", "pie").of(
        new Validator(Criteria.required(), Criteria.maxLength(3)),
        new Decoration("Your lastname", "help", "placeholder"));
    ElementContainer number = new TextType("number", "2").of(new Validator(Criteria.number()),
        new Decoration("Size", "help", "placeholder"));
    ElementContainer birthday = new SelectDateType("birthday", LocalDate.now(), 2018, 2020).of(
        new Validator(), new Decoration("Birhtday", "Please insert your birhtday", "placeholder"));
    ElementContainer gender =
        new SelectType("gender", "", new String[] {"m", "f"}, new String[] {"Male", "Female"})
            .of(new Decoration("Gender", "help", ""));


    ElementContainer optin =
        new CheckBoxType("optin", true).of(new Decoration("Optin", "help", "placeholder"));

    ElementContainer textArea = new TextAreaType("area", "").of(new Decoration("Area"));

    ElementContainer number2 =
        new NumberType("number2", 5).of(new Decoration("Number", "help", "placeholder"));

    ElementContainer password =
        new PasswordType("pwd").of(new Decoration("Passwoid", "help", "placeholder"));

    ElementContainer radio =
        new RadioType("radio", "", new String[] {"m", "f"}, new String[] {"Mr", "Misses"})
            .of(new Validator(Criteria.required()), new Decoration("Your Prefix", "help", ""));

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
    elements.add(new UploadType("upload").of());
    // elements.add(checkoutDate);
    elements.add(submit);
    elements.add(TextTypeBuilder.builder().withName("peter").withCriteria(Criteria.required())
        .withDecoration(Decoration.builder().withLabel("Horst").build()).build());

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
