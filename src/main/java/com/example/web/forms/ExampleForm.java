package com.example.web.forms;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jwebform.Form;
import jwebform.FormBuilder;
import jwebform.FormResult;
import jwebform.field.builder.FieldBuilder;
import jwebform.processor.FieldValdationResults;
import jwebform.processor.LoggingFormResult;
import jwebform.validation.Criterion;
import jwebform.validation.FormValidator;
import jwebform.validation.ValidationResult;
import jwebform.validation.Validator;
import jwebform.validation.criteria.Criteria;

import static jwebform.field.builder.BuildInType.*;


public class ExampleForm {

    private FieldBuilder[] getTypeBuildersForSampleForm() {
      Criterion req = Criteria.required();

      // @formatter:off

  return FormBuilder.array(

    xsrfProtectionForTesting(),

    simple(),

    text                  ("textInput", "Peter\"Paul").
      withCriteria        (req).
      withLabel           ("TextInputLabel"),

    textDate              ("dateInput", LocalDate.of(2017, 7, 4)).
      withCriteria        (req).
      withLabel           ("Please insert date").
      withHelptext        ("datehelptext"),

    text                  ("textInput2", "Peter\"Paul").
      withCriteria        (req).
      withLabel           ("TextInputLabel2").
      withHelptext        ("Help-Text").
      withPlaceholder     ("Placeholder"),

    select                ("gender", "", new String[] {"m", "f"}, new String[] {"Male", "Female"}).
      withLabel           ("Gender"),

    submit("Submit"),

    checkbox              ("chk", true).
      withCriteria        (req).
      withLabel           ("chk-label").
      withHelptext        ("chk_help"),

    label                 ("lbl"),

    html                  ("<strong>HTML</strong>"),

    hidden                ("hddn", "hddn-value"),

    textArea              ("area", "Area-Prebuild").
      withCriteria        (req).
      withLabel           ("Area").
      withHelptext        ("Area-Help").
      withPlaceholder     ("Area-Placeholder"),

    number                ("nbr", 5).
      withCriteria        (req).
      withLabel           ("nbr-label").
      withHelptext        ("nbr_help"),

    password              ("pssword").
      withLabel           ("Password"),

    upload                ("upld").
      withLabel           ("Upload"),

    radio                 ("radio", "1", new String[] {"1", "2"}, new String[] {"yes", "no"}).
      withLabel          ("Radio")
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
