package com.example.web.forms;

import java.time.LocalDate;
import java.util.List;
import javax.validation.constraints.Size;
import jwebform.field.SelectType;
import jwebform.field.SubmitType;
import jwebform.field.TextAreaType;
import jwebform.integration.annotations.IgnoreField;
import jwebform.integration.annotations.UseDecoration;
import jwebform.integration.annotations.UseFieldType;

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
  @UseFieldType(type = TextAreaType.class)
  @Size(min = 10, max = 200)
  public String adress = "";

  @UseFieldType(type = SelectType.class, keys = {"m", "f"}, vals = {"Male", "Female"})
  public String gender = "";


  @UseFieldType(type = SubmitType.class)
  public String ok;

}
