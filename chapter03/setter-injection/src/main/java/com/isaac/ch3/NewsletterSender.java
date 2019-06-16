package com.isaac.ch3;

public interface NewsletterSender {
    void setSmtpServer(String smtpServer);
    String getSmtpSserver();

    void setFromAddress(String fromAddress);
    String getFromAddress();

    void send();
}
