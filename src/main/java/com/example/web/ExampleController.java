package com.example.web;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.jwebform_integration.RequestEnvBuilder;
import com.example.web.forms.ExampleForm;
import com.example.web.forms.FormcheckerCopyForm;

import jwebform.FormResult;
import jwebform.themes.sourcecode.ThemeJavaRenderer;
import jwebform.themes.sourcecode.mapper.StandardMapper;

@Controller
public class ExampleController {


  private static final Logger log = LoggerFactory.getLogger(ExampleController.class);

  @RequestMapping("/bootstrap2")
  public String bootstrap2(Model model, HttpServletRequest request) {
    FormResult formResult = ExampleForm.build().run(new RequestEnvBuilder().of(request));
    model.addAttribute("form", formResult.getView(false, "GET"));
    if (formResult.isOk()) {
      model.addAttribute("ok", true);
    }
    // FormResult formResult2 = ExampleForm.build("id2").run(new RequestEnv(request));
    // model.addAttribute("form2", formResult2.getView().getHtml(theme));
    // if (formResult2.isOk()) {
    // System.err.println("Everything is fine!2");
    // }


    return "example";
  }


  @RequestMapping("/example")
  public String example(Model model, HttpServletRequest request) {
    FormResult formResult = ExampleForm.build().run(new RequestEnvBuilder().of(request));
    ThemeJavaRenderer renderer = new ThemeJavaRenderer(formResult,
        new StandardMapper(jwebform.themes.sourcecode.BootstrapTheme.instance(msg -> msg)));

    model.addAttribute("form", renderer.render("POST", true));
    model.addAttribute("form_raw", formResult.getView(true, "GET"));
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
    FormResult formResult = FormcheckerCopyForm.build().run(new RequestEnvBuilder().of(request));
    ThemeJavaRenderer renderer = new ThemeJavaRenderer(formResult,
        new StandardMapper(jwebform.themes.sourcecode.BootstrapTheme.instance(msg -> msg)));
    model.addAttribute("form", renderer.render("GET", true));
    model.addAttribute("form_raw", formResult.getView(true, "GET"));
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
