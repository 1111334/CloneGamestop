package com.example.CloneGamestop.notification.services;

import com.example.CloneGamestop.Model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MailNotificationService {
    // Definizione del logger per questa classe
    private static final Logger LOGGER = LoggerFactory.getLogger(MailNotificationService.class);

    // Iniezione di dipendenza del JavaMailSender per inviare email
    @Autowired
    private JavaMailSender emailSender;

    // Metodo per inviare una email di attivazione all'utente
    public void sendActivationService(User user) {
        // Creazione di un oggetto SimpleMailMessage per comporre l'email
        SimpleMailMessage sms = new SimpleMailMessage();
        sms.setTo(user.getEmail()); // Imposta il destinatario dell'email
        sms.setFrom("fij08780@fosiq.com"); // Imposta l'indirizzo email del mittente
        sms.setReplyTo("fij08780@fosiq.com"); // Imposta l'indirizzo email per le risposte
        sms.setSubject("ti sei iscritto alla piattaforma" + user.getActivationCode()); // Imposta l'oggetto dell'email
        sms.setText("il codice di attivazione e: " + user.getActivationCode()); // Imposta il corpo dell'email

        // Tentativo di inviare l'email
        try {
            emailSender.send(sms); // Invia l'email
        } catch (MailException e) { // Cattura eventuali eccezioni durante l'invio dell'email
            // Registra un errore nel logger specificando l'eccezione e l'indirizzo email del destinatario
            LOGGER.error("Errore durante l'invio dell'email di attivazione a {}", user.getEmail(), e);
            // Qui puoi gestire l'eccezione in modo appropriato, ad esempio, informando l'utente del problema
        }

        // Commento aggiuntivo per chiarire il contesto della risoluzione di un problema precedente
        // durante la richiesta su Postman per la creazione di uno user
        // Si è risolto grazie all'implementazione del logging e dell'utilizzo del blocco try-catch
        // In futuro, è consigliabile utilizzare sempre blocchi try-catch per gestire eventuali eccezioni


    }

    public void sendPasswordResetMail(Optional<User> user) {
        // Creazione di un oggetto SimpleMailMessage per comporre l'email
        SimpleMailMessage sms = new SimpleMailMessage();
        sms.setTo(user.get().getEmail()); // Imposta il destinatario dell'email
        sms.setFrom("development.develhope.co"); // Imposta l'indirizzo email del mittente
        sms.setReplyTo("development.develhope.co"); // Imposta l'indirizzo email per le risposte
        sms.setSubject("ti sei iscritto alla piattaforma" + user.get().getActivationCode()); // Imposta l'oggetto dell'email
        sms.setText("il codice di attivazione e: " + user.get().getPasswordResetCode());

        // Tentativo di inviare l'email
        try {
            emailSender.send(sms); // Invia l'email
        } catch (MailException e) { // Cattura eventuali eccezioni durante l'invio dell'email
            // Registra un errore nel logger specificando l'eccezione e l'indirizzo email del destinatario
            LOGGER.error("Errore durante l'invio dell'email di attivazione a {}", user.get().getEmail(), e);
            // Qui puoi gestire l'eccezione in modo appropriato, ad esempio, informando l'utente del problema
        }
    }
}