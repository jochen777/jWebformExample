package com.example.web.forms;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jwebform.Form;
import jwebform.FormBuilder;
import jwebform.FormResult;
import jwebform.field.builder.BuildInType;
import jwebform.field.builder.FieldBuilder;
import jwebform.processor.FieldValdationResults;
import jwebform.processor.LoggingFormResult;
import jwebform.validation.Criterion;
import jwebform.validation.FormValidator;
import jwebform.validation.ValidationResult;
import jwebform.validation.criteria.Criteria;

import static jwebform.field.builder.BuildInType.*;


public class ExampleForm {

    private FieldBuilder[] getTypeBuildersForSampleForm() {
      Criterion req = Criteria.required();

      // @formatter:off

  return BuildInType.array(

    xsrfProtectionForTesting(),

    simple(),

    text                  ("textInput", "Peter\"Paul").
      criteria        (req).
      label           ("TextInputLabel"),

    textDate              ("dateInput", LocalDate.of(2017, 7, 4)).
      criteria        (req).
      label           ("Please insert date").
      helpText        ("datehelptext"),

    text                  ("textInput2", "Peter\"Paul").
      criteria        (req).
      label           ("TextInputLabel2").
      helpText        ("Help-Text").
      placeholder     ("Placeholder"),

    select                ("gender", "", new String[] {"m", "f"}, new String[] {"Male", "Female"}).
      label           ("Gender"),

    checkbox              ("chk", true).
      criteria        (req).
      label           ("chk-label").
      helpText        ("chk_help"),

    label                 ("lbl"),

    html                  ("<strong>HTML</strong>"),

    hidden                ("hddn", "hddn-value"),

    textArea              ("area", "Area-Prebuild").
      criteria        (req).
      label           ("Area").
      helpText        ("Area-Help").
      placeholder     ("Area-Placeholder"),

    number                ("nbr", 5).
      criteria        (req).
      label           ("nbr-label").
      helpText        ("nbr_help"),

    password              ("pssword").
      label           ("Password"),

    upload                ("upld").
      label           ("Upload"),

    radio                 ("radio", "1", new String[] {"1", "2"}, new String[] {"yes", "no"}).
      label          ("Radio"),

    submit("Submit").
      label("Submit")
  );

// @formatter:on
    }

    String formId;

    public ExampleForm(String formId) {
      this.formId = formId;
    }

    public LocalDate getDateValue(FormResult formResult) {
      return (LocalDate) formResult.getFieldResults().getObectValue("dateInput");
    }

    public Form buildForm() {
      List<FormValidator> formValidators = new ArrayList<>();
      formValidators.add(it -> {
        FieldValdationResults overridenValidationResults = new FieldValdationResults();
        String valueOfTextInput = it.getFieldStringValue("textInput");
        if (valueOfTextInput.length() > 3) {
          overridenValidationResults.put(it.getField("textInput"), ValidationResult.fail("not_ok"));
        }
        return overridenValidationResults;
      });

      // test here field-apis
      return FormBuilder.flexible(formId, LoggingFormResult::new).validation(formValidators)
        .typeBuilder(getTypeBuildersForSampleForm())

        .build();

    }


  }
