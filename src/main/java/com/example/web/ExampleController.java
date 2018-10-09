package com.example.web;

import javax.servlet.http.HttpServletRequest;

import jwebform.View;
import jwebform.field.SubmitType;
import jwebform.field.TextAreaType;
import jwebform.integration.annotations.IgnoreField;
import jwebform.integration.annotations.UseDecoration;
import jwebform.integration.annotations.UseFieldType;
import jwebform.spring.JWebForm;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.jwebform_integration.RequestEnvBuilder;
import com.example.web.forms.ExampleForm;
import jwebform.FormResult;

import java.time.LocalDate;
import java.util.List;

@Controller
public class ExampleController {


  private static final Logger log = LoggerFactory.getLogger(ExampleController.class);

  @Autowired
  private MessageSource messageSource;


  @RequestMapping("/bootstrap2")
  public String bootstrap2(JWebForm form) {
    FormResult formResult = form.run(new ExampleForm("1").buildForm());

    if (formResult.isOk()) {
      //model.addAttribute("ok", true);
    }

    return "example";
  }

  @RequestMapping("/bootstrap3")
  public String bootstrap3(JWebForm form) {
    FormResult formResult = form.run(new Bean());

    if (formResult.isOk()) {
      //model.addAttribute("ok", true);
    }

    return "example";
  }

  public class Bean {
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

    @UseFieldType(type = SubmitType.class)
    public String ok;
  }



  @RequestMapping("/example")
  public String example(Model model, HttpServletRequest request) {
    FormResult formResult = new ExampleForm("1").buildForm().run(RequestEnvBuilder.of(request));
    // ThemeJavaRenderer renderer = new ThemeJavaRenderer(
    // new StandardMapper(jwebform.themes.sourcecode.BootstrapTheme.instance(msg -> msg)));
    //
    // model.addAttribute("form", renderer.render(formResult, "POST", true));
    model.addAttribute("form_raw", formResult.getView(View.Html5Validation.ON, View.Method.POST));
    if (formResult.isOk()) {
      System.err.println("Everything is fine!");
    }
    // FormResult formResult2 = ExampleForm.build("id2").run(new RequestEnv(request));
    // model.addAttribute("form2", formResult2.getView().getHtml(theme));
    // if (formResult2.isOk()) {
    // System.err.println("Everything is fine!2");
    // }


    return "bootstrap";
  }


  @RequestMapping("/boostrap")
  public String bootstrap(Model model, HttpServletRequest request) {
    FormResult formResult = new ExampleForm("1").buildForm().run(RequestEnvBuilder.of(request));
    // ThemeJavaRenderer renderer = new ThemeJavaRenderer(
    // new StandardMapper(jwebform.themes.sourcecode.BootstrapTheme.instance(msg -> msg)));
    // model.addAttribute("form", renderer.render(formResult, "GET", true));
    model.addAttribute("form_raw", formResult.getView());
    if (formResult.isOk()) {
      System.err.println("Everything is fine!");
    }
    // FormResult formResult2 = ExampleForm.build("id2").run(new RequestEnv(request));
    // model.addAttribute("form2", formResult2.getView().getHtml(theme));
    // if (formResult2.isOk()) {
    // System.err.println("Everything is fine!2");
    // }


    return "bootstrap";
  }


}
