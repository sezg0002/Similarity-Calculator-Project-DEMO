package datacollection;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Classes.Triplet;
import Classes.Cas;
import Classes.Intervalle;

/**
 * Cette classe gère l'extraction des données à partir d'un fichier
 * et initialise la structure de données appropriée.
 */
public class Extraction {

    private static HashMap<Integer, Cas> casDictionary;

    /**
     * Constructeur de la classe Extraction.
     * Initialise le dictionnaire des cas.
     */
    public Extraction() {
        casDictionary = new HashMap<>();
    }

    /**
     * Initialise les données à partir du fichier spécifié.
     *
     * @param filePath Le chemin du fichier contenant les données.
     */
    public void initData(String filePath) {
        int casId = 1;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] tripletsString = line.split("\\*");

                List<Triplet> triplets = new ArrayList<>();
                for (String tripletString : tripletsString) {
                    String[] tripletParts = tripletString.trim().replaceAll("\\s*,\\s*", ",").replace("(", "").replace(")", "").replace("[", "").replace("]", "").split(",");
                    String P1 = tripletParts[0].trim();
                    String P2 = tripletParts[1].trim();
                    if(tripletParts[2].equals("nct")){
                        Triplet triplet = new Triplet(P1, P2, null);
                        triplets.add(triplet);
                    } 
                    else{                    
                        int intervalleMin = Integer.parseInt(tripletParts[2].trim());
                        int intervalleMax = Integer.parseInt(tripletParts[3].trim());

                        Triplet triplet = new Triplet(P1, P2, new Intervalle(intervalleMin, intervalleMax));
                        triplets.add(triplet);
                    }
                }

                // Crée un nouvel objet Cas avec l'ID, l'état et la liste de triplets
                Cas cas = new Cas(null, triplets);
                casDictionary.put(casId, cas);
                casId++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Obtient le dictionnaire des cas.
     *
     * @return Le dictionnaire des cas.
     */
    public HashMap<Integer, Cas> getCasDictionary() {
        return casDictionary;
    }

    /**
     * Trouve la valeur maximale de l'intervalle parmi tous les triplets.
     *
     * @return La valeur maximale de l'intervalle.
     */
    public static int trouverIntervalleMax() {
        int intervalleMaxGlobal = Integer.MIN_VALUE;

        for (Cas cas : casDictionary.values()) {
            List<Triplet> triplets = cas.getListTriplet();
            for (Triplet triplet : triplets) {
                int intervalleMaxLocal = triplet.getIntervalle().getMax();
                intervalleMaxGlobal = Math.max(intervalleMaxGlobal, intervalleMaxLocal);
            }
        }

        return intervalleMaxGlobal;
    }
}
