package com.gol.ants_quests.util;

import jakarta.servlet.http.HttpSession;

public class UserUtil {
    public static boolean checkFirstTimeVisit(HttpSession session) {
        String firstTimeVisit = (String) session.getAttribute("firstTimeVisit");
        if (firstTimeVisit == null) {
            session.setAttribute("firstTimeVisit", "true"); // Set firstTimeVisit to true
            return true; // First-time visit
        } else {
            return false; // Not a first-time visit
        }
    }
}
