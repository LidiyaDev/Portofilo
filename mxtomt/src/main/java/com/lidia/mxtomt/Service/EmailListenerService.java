package com.lidia.mxtomt.Service;

import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class EmailListenerService {
   @Value("${spring.mail.host}")
    private String emailHost;

    @Value("${spring.mail.port}")
    private int emailPort;

    @Value("${spring.mail.username}")
    private String emailUsername;

    @Value("${spring.mail.password}")
    private String emailPassword;

    @Scheduled(fixedRate = 60000) // Run every 60 seconds
    public void checkEmail() {
        Properties properties = new Properties();
        properties.put("mail.store.protocol", "imaps"); // Use 'imaps' for IMAP

        try {
            Session session = Session.getInstance(properties, null);
            Store store = session.getStore();
            store.connect(emailHost, emailUsername, emailPassword);

            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);

            Message[] messages = inbox.getMessages();
            for (Message message : messages) {
                System.out.println("Subject: " + message.getSubject());
                System.out.println("From: " + InternetAddress.toString(message.getFrom()));
                System.out.println("Sent Date: " + message.getSentDate());
                System.out.println("Received Date: " + message.getReceivedDate());

                // You can implement your own logic to process the content of the email
                // For example, you can extract attachments, read the body, etc.

                // Note: This is a basic example, and you might need to handle different types of messages and content.
            }

            // Close resources
            inbox.close(false);
            store.close();
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions appropriately based on your application's requirements
        }
    }
}
