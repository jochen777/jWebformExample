package com.example.web;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.jwebform_integration.DebuggingRequestEnv;
import com.example.jwebform_integration.RequestEnv;
import com.example.web.forms.ExampleForm;
import com.example.web.forms.FormcheckerCopyForm;

import jwebform.FormResult;
import jwebform.view.Theme;
import jwebform.view.theme.BootstrapTheme;

@Controller
public class ExampleController {


  private static final Logger log = LoggerFactory.getLogger(ExampleController.class);


  @RequestMapping("/example")
  public String example(Model model, HttpServletRequest request) {
    Theme theme = BootstrapTheme.instance(msg -> msg);
    FormResult formResult = ExampleForm.build("id").run(new DebuggingRequestEnv(request)
        .cloneWithMaxLenInput(500).cloneWithNullCheck().cloneWithTrim());
    model.addAttribute("form", formResult.getView(theme).getHtml("GET", true));
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


  @RequestMapping("/boostrap")
  public String bootstrap(Model model, HttpServletRequest request) {
    BootstrapTheme theme = BootstrapTheme.instance(msg -> msg);
    FormResult formResult =
        FormcheckerCopyForm.build("example").run(new DebuggingRequestEnv(request)
            .cloneWithMaxLenInput(500).cloneWithNullCheck().cloneWithTrim());
    model.addAttribute("form", formResult.getView(theme).getHtml("GET", true));
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


  @RequestMapping("/bootstrap2")
  public String bootstrap2(Model model, HttpServletRequest request) {
    BootstrapTheme theme = BootstrapTheme.instance(msg -> msg);
    FormResult formResult = ExampleForm.build("example").run(
        new RequestEnv(request).cloneWithMaxLenInput(500).cloneWithNullCheck().cloneWithTrim());
    model.addAttribute("form", formResult.getView());
    if (formResult.isOk()) {
      System.err.println("Everything is fine!");
    }
    // FormResult formResult2 = ExampleForm.build("id2").run(new RequestEnv(request));
    // model.addAttribute("form2", formResult2.getView().getHtml(theme));
    // if (formResult2.isOk()) {
    // System.err.println("Everything is fine!2");
    // }


    return "example";
  }


}
