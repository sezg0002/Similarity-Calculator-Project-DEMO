package Classes;

/**
 * Cette classe représente un intervalle numérique défini par une valeur minimale et maximale.
 */
public class Intervalle {

    private static int nextIdIntervalle = 1;
    private int ID_Intervalle;

    private int intervalle_min;
    private int intervalle_max;

    /**
     * Constructeur de la classe Intervalle.
     *
     * @param intervalle_min Valeur minimale de l'intervalle.
     * @param intervalle_max Valeur maximale de l'intervalle.
     */
    public Intervalle(int intervalle_min, int intervalle_max) {
        this.intervalle_min = intervalle_min;
        this.intervalle_max = intervalle_max;
        this.ID_Intervalle = getNextIntervalleID();
    }

    // Méthode privée pour obtenir un identifiant d'intervalle unique de manière synchronisée.
    private synchronized int getNextIntervalleID() {
        return nextIdIntervalle++;
    }

    /**
     * Obtient l'identifiant de l'intervalle.
     *
     * @return L'identifiant de l'intervalle.
     */
    public int getID_intervalle() {
        return this.ID_Intervalle;
    }

    /**
     * Obtient la valeur minimale de l'intervalle.
     *
     * @return La valeur minimale de l'intervalle.
     */
    public int getMin() {
        return this.intervalle_min;
    }

    /**
     * Obtient la valeur maximale de l'intervalle.
     *
     * @return La valeur maximale de l'intervalle.
     */
    public int getMax() {
        return this.intervalle_max;
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères de l'intervalle.
     *
     * @return Une chaîne de caractères représentant l'intervalle.
     */
    @Override
    public String toString() {
        return "" +
        // "Intervalle ID: " + this.ID_Intervalle +
        //         ", Min: " + intervalle_min +
        //         ", Max: " + intervalle_max;
        ", [" + intervalle_min +
                ", " + intervalle_max+"]";
    }
}