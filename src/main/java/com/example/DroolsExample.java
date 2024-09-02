package com.example;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieSession;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class DroolsExample {
    public static void main(String[] args) {
        try {
            // Load KIE services
            KieServices ks = KieServices.Factory.get();
            KieContainer kContainer = ks.getKieClasspathContainer();

            // Check if the KieContainer was successfully created
            if (kContainer == null) {
                System.out.println("KieContainer could not be created.");
                return;
            }

            // Load the KieBase by name
            KieBase kieBase = kContainer.getKieBase("rules");
            if (kieBase == null) {
                System.out.println("KieBase 'rules' could not be found. Check kmodule.xml.");
                return;
            }

            // Create a new KieSession from the KieBase
            KieSession kSession = kieBase.newKieSession();
            if (kSession == null) {
                System.out.println("KieSession could not be created from KieBase.");
                return;
            }

            // Insert a fact and fire rules
            kSession.insert(10);
            kSession.fireAllRules();

            // Dispose of the session
            kSession.dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}