package com.cloudingenieur.blueprint.starter.emailservice;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class SendEmailController {

    @Autowired
    private EmailSender smtpMailSender;

    @Value("${mail.to}")
    private String mailTo;

    @Value("${mail.subject}")
    private String mailSubject;

    @Value("${mail.body}")
    private String mailBody;


    List<String> emailList = new ArrayList<>();

    @RequestMapping("/api/mail/send")
    public ResponseEntity<String> sendMail() throws MessagingException, IOException {
        emailList.add(mailTo);
        emailList.add(mailSubject);
        emailList.add(mailBody + "CIB0134971");
        //System.out.println(emailList.get(2));

        smtpMailSender.sendMail(emailList.get(0).toLowerCase(), emailList.get(1).toLowerCase(), emailList.get(2).toLowerCase());

        return new ResponseEntity<>("Email Sent Successfully!", HttpStatus.OK);
    }



}
