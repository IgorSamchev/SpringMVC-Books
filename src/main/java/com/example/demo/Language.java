package com.example.demo;

class Language {
    private static String currentLanguage = "en";

    static String getCurrentLanguage() {
        return currentLanguage;
    }

    private static void setCurrentLanguageEE() {
        currentLanguage = "ee";
    }
    private static void setCurrentLanguageEN() {
        currentLanguage = "en";
    }
    private static void setCurrentLanguageRU() {
        currentLanguage = "ru";
    }

    static void shiftLanguage(String language) {
        switch (language) {
            case "changeCurrentLanguageEN":
                Language.setCurrentLanguageEN();
                break;
            case "changeCurrentLanguageEE":
                Language.setCurrentLanguageEE();
                break;
            case "changeCurrentLanguageRU":
                Language.setCurrentLanguageRU();
                break;
        }
    }
}
