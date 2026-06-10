package uidt.sn.eventservice.enumeration;

public enum EventStatus {
    PUBLISHED,  // publié, ouvert aux inscriptions
    CANCELLED,  // annulé (notifie les inscrits)
    COMPLETED   // terminé (événement passé)
}