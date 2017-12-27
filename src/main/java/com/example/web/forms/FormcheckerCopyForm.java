package com.example.web.forms;

import java.util.ArrayList;
import java.util.List;

import jwebform.Form;
import jwebform.element.TextType;
import jwebform.element.structure.Decoration;
import jwebform.validation.FormValidator;
import jwebform.validation.Validator;
import jwebform.validation.criteria.Criteria;

public class FormcheckerCopyForm {

  public static Form build(String id) {
    return new Form(id, buildFormValidator(),
        new TextType("textInput", new Decoration("SampleTextInput"), "Peter").of(),
        new TextType("firstname", new Decoration("Your Firstname", "Andreas"), "Peter")
            .of(new Validator(Criteria.required(), Criteria.accept("Peter", "Max"),
                Criteria.maxLength(10))));


    /*
     * 
     * add(TextInput.build("firstname").setDescription("Your Firstname").setPreSetValue("Peter")
     * .setRequired().setHelpText("Andreas") .setCriterias(Criteria.accept("Peter", "Max"),
     * Criteria.maxLength(10)));
     * 
     * add(ShortTextInput.build("lastname").setPlaceholerText("Mustermann!")
     * .setDescription("Your Lastname")
     * .setHelpText("This is an example Helptext for describing this lastname field")
     * .setCriterias(Criteria.accept("Pan", "Mustermann")));
     * 
     * add(TextInput.build("middelname").setDescription("Your Middelname") .setCriterias(new
     * CustomValidation()));
     * 
     * add(HTMLSnippet.build("headline").setHTML("<h1>Headline</h1>"));
     * 
     * add(HiddenInput.build("hidden").setPreSetValue("something to remember"));
     * 
     * add(ButtonInput.build("btn").setButtonText("Add...").setPreSetValue("add"));
     * 
     * add(DateInputSelectCompound.build("date",
     * YearRange.aroundNow(5)).setDescription("Birthday")); DateInputSelectCompound b =
     * DateInputSelectCompound.build("ksdlf", YearRange.birthday());
     * add(DateInputCompound.build("date2").setDescription("Mein Tag"));
     * 
     * add(PasswordInput.build("password1").setRequired().setDescription("Password"));
     * 
     * add(PasswordInput.build("password2").setRequired().setDescription("Repeat password"));
     * 
     * add(LongTextInput.build("description").setPlaceholerText("Placeholder").setRequired()
     * .setDescription("Your Description"));
     * 
     * // RFE: simple map-builder LinkedHashMap<String, String> radioEntries = createRadioMap();
     * 
     * add(RadioInput.build("rdio").setPossibleValues(radioEntries).setDescription("Your Choice"));
     * 
     * LinkedHashMap<String, String> selectEntries = createSelectMap();
     * 
     * add(SelectInput.build("select").setPossibleValues(selectEntries)
     * .setDescription("Your Selection"));
     * 
     * add(CheckboxInput.build("check").setDescription("I order everything"));
     * 
     * // dropdown with groups add(createSelectWithGroups());
     * 
     * 
     * addFormValidator(new PasswordFormValidator());
     * 
     * this.setId("example"); // this.hideSubmitButton();
     * 
     * this.disableHtml5Validation(); return new Form("id", elements);
     */
  }

  private static List<FormValidator> buildFormValidator() {
    return new ArrayList<FormValidator>();
  }
}
