package com.example.demo;

class Language {
    private static String currentLanguage = "en";

    static String getCurrentLanguage() {
        return currentLanguage;
    }

    static void setCurrentLanguageEE() {
        currentLanguage = "ee";
    }
    static void setCurrentLanguageEN() {
        currentLanguage = "en";
    }
    static void setCurrentLanguageRU() {
        currentLanguage = "ru";
    }
}
