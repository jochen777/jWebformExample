package com.example.web.forms;

import java.time.LocalDate;
import java.util.List;
import javax.validation.constraints.Size;
import jwebform.field.SelectType;
import jwebform.field.SubmitType;
import jwebform.field.TextAreaType;
import jwebform.integration.bean2form.annotations.IgnoreField;
import jwebform.integration.bean2form.annotations.UseDecoration;
import jwebform.integration.bean2form.annotations.UseFieldType;

public class ExampleBean {

  @UseDecoration(label = "Dein Name", helpText = "Bitte gebe hier deinen Namen ein",
      placeholder = "Max")
  public String name = "";

  @UseDecoration(label = "Bitte dein Nachname", helpText = "Bitte gebe hier deinen Nachnamen ein",
      placeholder = "Mustermann")
  public String lastname = "";

  @UseDecoration(label = "Dein Alter")
  public Integer age = 14;

  public Boolean optin = false;

  @IgnoreField
  public List<Integer> ignoreMe;
  public LocalDate birthDay = LocalDate.now();

  // this is bean validation!!
  @UseFieldType(TextAreaType.class)
  @Size(min = 10, max = 200)
  public String adress = "";

  @UseFieldType(value = SelectType.class, keys = {"m", "f"}, vals = {"Male", "Female"})
  public String gender = "";


  @UseFieldType(SubmitType.class)
  public String ok;

}
