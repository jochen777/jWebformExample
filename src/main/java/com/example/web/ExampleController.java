package com.example.web;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.jwebform_integration.RequestEnvBuilder;
import com.example.web.forms.ExampleBean;
import com.example.web.forms.ExampleForm;
import jwebform.FormResult;
import jwebform.View;
import jwebform.spring.JWebForm;
import jwebform.spring.SimpleJWebForm;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ExampleController {

  @Autowired
  Validator validator;

  @Autowired
  private MessageSource messageSource;


  @RequestMapping("/bootstrap2")
  public String bootstrap2(JWebForm form) {
    FormResult formResult = form.run(new ExampleForm("1"));

    if (formResult.isOk()) {
      // model.addAttribute("ok", true);
    }

    return "example";
  }

  @RequestMapping("/bootstrap3")
  public String bootstrap3(JWebForm form) {
    ExampleBean b = new ExampleBean();
    FormResult formResult = form.run(b);

    if (formResult.isOk()) {
      // model.addAttribute("ok", true);
      log.info("Name is: " + b.name);
      log.info("Birthday is: " + b.birthDay);
    }

    return "example";
  }

  @RequestMapping("/bootstrap4")
  public String bootstrap4(SimpleJWebForm<ExampleBean> form) {
    log.info("Validator: " + validator);
    if (form.isOk()) {
      log.info("Name is: " + form.getBean().name);
      log.info("Birthday is: " + form.getBean().birthDay);
    }
    return "example";
  }


  @RequestMapping("/example")
  public String example(Model model, HttpServletRequest request) {
    FormResult formResult = new ExampleForm("1").generateForm().run(RequestEnvBuilder.of(request));
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
    FormResult formResult = new ExampleForm("1").generateForm().run(RequestEnvBuilder.of(request));
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
