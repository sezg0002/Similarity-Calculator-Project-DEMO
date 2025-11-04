package Classes;

/**
 * Cette classe représente un triplet constitué de deux paramètres (P1 et P2)
 * et d'un intervalle numérique.
 */
public class Triplet {

    private static int nextIdTriplet = 1;
    private int ID_triplet;
    private String P1;
    private String P2;
    private Intervalle intervalle;

    /**
     * Constructeur de la classe Triplet.
     *
     * @param P1         Le premier paramètre du triplet.
     * @param P2         Le deuxième paramètre du triplet.
     * @param intervalle L'intervalle numérique associé au triplet.
     */
    public Triplet(String P1, String P2, Intervalle intervalle) {
        this.ID_triplet = getNextTripletID();
        this.P1 = P1;
        this.P2 = P2;
        this.intervalle = intervalle;
    }

    // Méthode privée pour obtenir un identifiant de triplet unique de manière synchronisée.
    private synchronized int getNextTripletID() {
        return nextIdTriplet++;
    }

    /**
     * Obtient l'identifiant du triplet.
     *
     * @return L'identifiant du triplet.
     */
    public int getID_triplet() {
        return this.ID_triplet;
    }

    /**
     * Obtient le premier paramètre du triplet (P1).
     *
     * @return Le premier paramètre du triplet.
     */
    public String getP1() {
        return this.P1;
    }

    /**
     * Obtient le deuxième paramètre du triplet (P2).
     *
     * @return Le deuxième paramètre du triplet.
     */
    public String getP2() {
        return this.P2;
    }

    /**
     * Obtient l'intervalle numérique associé au triplet.
     *
     * @return L'intervalle numérique associé au triplet.
     */
    public Intervalle getIntervalle() {
        return this.intervalle;
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères du triplet.
     *
     * @return Une chaîne de caractères représentant le triplet.
     */
    @Override
    public String toString() {
        // "Triplet ID: " + ID_triplet +
        //         ", P1: " + P1 +
        //         ", P2: " + P2 +
        //         ", Intervalle: " + intervalle.toString();
        if (intervalle==null){
            return ""+ P1 + ", " + P2 + "" + ", nct";
        } else {
            return ""+ P1 + ", " + P2 + "" + intervalle.toString();
        }
    }
}
