package com.example.web.forms;

import jwebform.field.SelectType;
import jwebform.field.SubmitType;
import jwebform.field.TextAreaType;
import jwebform.integration.annotations.IgnoreField;
import jwebform.integration.annotations.UseDecoration;
import jwebform.integration.annotations.UseFieldType;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ExampleBean {
  @UseFieldType(type = TextAreaType.class)
  @UseDecoration(label = "Dein Name", helpText = "Bitte gebe hier deinen Namen ein", placeholder = "Max")
  public String name="";

  @UseDecoration(label = "Dein Nachname", helpText = "Bitte gebe hier deinen Nachnamen ein", placeholder = "Mustermann")
  public String lastname="";
  public Integer age=5;
  public Boolean optin=true;
  @IgnoreField
  public List<Integer> ignoreMe;
  public LocalDate birthDay=LocalDate.now();
  public String adress="";

  @UseFieldType(type = SelectType.class, keys = {"m", "f"}, vals = {"Male", "Female"})
  public String gender="";


  @UseFieldType(type = SubmitType.class)
  public String ok;

}
