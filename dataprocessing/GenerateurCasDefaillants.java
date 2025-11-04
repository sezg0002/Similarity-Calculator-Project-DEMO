package dataprocessing;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Classes.Cas;
import Classes.EtatDefaillant;
import Classes.Intervalle;
import Classes.Triplet;
import datacollection.Extraction;

import java.util.HashMap;

/**
 * The `GenerateurCasDefaillants` class is responsible for generating and saving
 * faulty cases based on normal cases extracted from a data source.
 */
public class GenerateurCasDefaillants {

    /**
     * Generates faulty cases for a given normal case.
     *
     * @param casNormal The normal case for which faulty cases are generated.
     * @return A list of faulty cases generated from the provided normal case.
     */
    public static List<Cas> genererCasDefaillants(Cas casNormal) {
        List<Cas> casDefaillants = new ArrayList<>();

        for (Triplet tripletNormal : casNormal.getListTriplet()) {
            if(tripletNormal.getIntervalle() == null){
                continue;
            }
            
            Triplet tripletDefaillant = genererTripletDefaillant(tripletNormal);
            List<Triplet> tripletsDefaillants = new ArrayList<>(casNormal.getListTriplet());
            int index = tripletsDefaillants.indexOf(tripletNormal);
            tripletsDefaillants.set(index, tripletDefaillant);

            Cas casDefaillant = new Cas(new EtatDefaillant(), tripletsDefaillants);
            casDefaillants.add(casDefaillant);
        }

        return casDefaillants;
    }

    /**
     * Generates a faulty triplet based on a normal triplet.
     *
     * @param tripletNormal The normal triplet for which a faulty triplet is generated.
     * @return A faulty triplet generated from the provided normal triplet.
     */
    private static Triplet genererTripletDefaillant(Triplet tripletNormal) {
        String event1 = tripletNormal.getP1();
        String event2 = "S(" + tripletNormal.getP2() + ")";
        Intervalle intervalle = new Intervalle(tripletNormal.getIntervalle().getMax(), tripletNormal.getIntervalle().getMax());

        return new Triplet(event1, event2, intervalle);
    }

    /**
     * Generates and saves faulty cases for each normal case in a given extraction.
     *
     * @param extraction      The data extraction containing normal cases.
     * @param outputDirectory The directory where the faulty cases file will be saved.
     */
    public static void genererEtSauvegarderCasDefaillants(Extraction extraction, String outputDirectory) {
        HashMap<Integer, Cas> casDictionary = extraction.getCasDictionary();
        HashMap<Integer, List<Cas>> casDefaillantsDictionary = new HashMap<>();

        for (Cas casNormal : casDictionary.values()) {
            List<Cas> casDefaillants = genererCasDefaillants(casNormal);
            casDefaillantsDictionary.put(casNormal.getIdCas(), casDefaillants);
        }

        // Writing the dictionary of faulty cases to the file
        String outputFilePath = outputDirectory + "/casDefaillants.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            for (Integer casNormalId : casDefaillantsDictionary.keySet()) {
                writer.write("Cas normal ID: " + casNormalId);
                writer.newLine();

                List<Cas> casDefaillants = casDefaillantsDictionary.get(casNormalId);
                for (int i = 0; i < casDefaillants.size(); i++) {
                    writer.write("  Cas défaillant " + (i + 1) + ": " + casDefaillants.get(i).toString());
                    writer.newLine();
                }
            }
            System.out.println("Les cas défaillants ont été sauvegardés dans " + outputFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The main method demonstrates the usage of the `GenerateurCasDefaillants` class.
     *
     * @param args Command line arguments (not used in this context).
     */
    public static void main(String[] args) {
        Extraction extraction = new Extraction();
        extraction.initData("dataBase\\reglesCNOriginal.txt");
        genererEtSauvegarderCasDefaillants(extraction, "dataBase");
    }
}
