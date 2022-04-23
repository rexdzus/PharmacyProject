package Main;

import javafx.scene.control.RadioButton;

/**
 * Program with uses product of dicision tree to
 */
public class DecisionTree {
    /**
     *
     * @param s_weight
     * @param s_headaches
     * @param s_neck
     * @param s_face
     * @param s_blocked
     * @param s_throat
     * @param s_sneezing
     * @param s_earpain
     * @param s_mucus
     * @return
     */
    public static String[] analise(RadioButton s_weight, RadioButton s_headaches, RadioButton s_neck,
                                   RadioButton s_face, RadioButton s_blocked, RadioButton s_throat,
                                   RadioButton s_sneezing, RadioButton s_earpain, RadioButton s_mucus) {
        String medicalInstructions;
        String illness;
       if (s_weight.isSelected()) {
           medicalInstructions = "Seek immediate hospitalization, this is a highly contagious disease";
           illness = "tuberculosis";
       } else if (s_headaches.isSelected()){
             if (s_neck.isSelected()) {
                 illness = "meningitis";
                 medicalInstructions = "Seek immediate hospitalization, this is a highly contagious disease.";
             } else {
                 if (s_face.isSelected()){
                     if (s_blocked.isSelected()){
                         illness = "sinusitis";
                         medicalInstructions = "You may treat sinusitis at home by getting plenty of rest, \n" +
                                 "drinking plenty of fluids, cleansing your nose with salt water, \n" +
                                 "and taking your medication.";
                     } else {
                         illness = "sinus infection";
                         medicalInstructions = "You may treat a sinus infection at home by getting plenty of rest, \n" +
                                 "drinking plenty of fluids, cleansing your nose with salt water, \n" +
                                 "and taking your medication.";
                     }
                 } else {
                     illness = "scarlet fever";
                     medicalInstructions = "You may treat scarlet fever at home by getting plenty of rest, \n" +
                             "drinking cool fluids, using calamine lotion to stop itching, \n" +
                             "and taking your medication.";
                 }
             }
       } else if (s_throat.isSelected()){
            if (s_sneezing.isSelected()) {
                illness = "common cold";
                medicalInstructions = "You may treat the common cold at home by getting plenty of rest, \n" +
                        "drinking plenty of fluids to avoid dehydration, gargling with salt water, \n" +
                        "and taking your medication.";
            } else {
                illness = "flu";
                medicalInstructions = "You may treat the common cold at home by getting plenty of rest, \n" +
                        "drinking plenty of fluids to avoid dehydration, keeping warm, \n" +
                        "and taking your medication.";
            }
        } else if (s_earpain.isSelected()){
           illness = "ear infection";
           medicalInstructions = "You may treat the common cold at home by using painkillers, \n" +
                   "placing a flannel on your ear, removing any discharge from your \n" +
                   "ear with cotton wool.";
       } else if (s_mucus.isSelected()){
           illness = "chest cold";
           medicalInstructions = "You may treat the common cold at home by getting plenty of rest, \n" +
                   "drinking plenty of water, raising your head while sleeping, \n" +
                   "using painkillers to bring down fever, and drinking a hot water" +
                   "and lemon drink.";
       } else {
           illness = "urinary tract infection";
           medicalInstructions = "You may treat the common cold at home by getting plenty of rest, \n" +
                   "drinking plenty of fluids, placing a hot water bottle on \n" +
                   "your tummy, and taking your medication.";
       }
        String[] strings = {illness, medicalInstructions};
        return strings;
    }

    

}