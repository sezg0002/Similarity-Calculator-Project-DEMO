package Classes;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

/**
 * Cette classe représente un cas dans la base de cas.
 */
public final class Cas {

    private static int nextCasID = 1;

    private final int idCas;
    private final Etat etat;
    private final List<Triplet> triplets;

    /**
     * Constructeur de la classe Cas.
     *
     * @param etat     L'état du cas.
     * @param triplets Liste des triplets associés au cas.
     */
    public Cas(Etat etat, List<Triplet> triplets) {
        this.idCas = getNextCasID();
        this.etat = etat;
        this.triplets = new ArrayList<>(triplets);
    }

    // Méthode privée pour obtenir un identifiant de cas unique de manière synchronisée.
    private synchronized int getNextCasID() {
        return nextCasID++;
    }

    /**
     * Obtient l'identifiant du cas.
     *
     * @return L'identifiant du cas.
     */
    public int getIdCas() {
        return this.idCas;
    }

    /**
     * Obtient le nombre de triplets associés au cas.
     *
     * @return Le nombre de triplets.
     */
    public int getNbTriplets() {
        return this.triplets.size();
    }

    /**
     * Obtient l'état du cas.
     *
     * @return L'état du cas.
     */
    public Etat getEtat() {
        return this.etat;
    }

    /**
     * Obtient une liste immuable des triplets associés au cas.
     *
     * @return Liste immuable des triplets.
     */
    public List<Triplet> getListTriplet() {
        return Collections.unmodifiableList(this.triplets);
    }

    /**
     * Ajoute un triplet à la liste des triplets du cas.
     *
     * @param triplet Le triplet à ajouter.
     */
    public void addTriplet(Triplet triplet) {
        this.triplets.add(triplet);
    }

    /**
     * Supprime un triplet à partir de son index dans la liste des triplets.
     *
     * @param index L'index du triplet à supprimer.
     */
    public void removeTriplet(int index) {
        if (index >= 0 && index < this.triplets.size()) {
            this.triplets.remove(index);
        }
    }

    /**
     * Obtient le triplet à partir de son index dans la liste des triplets.
     *
     * @param index L'index du triplet à obtenir.
     * @return Le triplet correspondant à l'index ou null s'il n'existe pas.
     */
    public Triplet getTriplet(int index) {
        if (index >= 0 && index < this.triplets.size()) {
            return this.triplets.get(index);
        }
        return null;
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères du cas.
     *
     * @return Une chaîne de caractères représentant le cas.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cas ID: ").append(this.idCas)
                .append(", État: ").append(etat)
                // .append(", Nombre de triplets: ").append(triplets.size())
                // .append(", Triplets: [");
                .append(" (");

        for (int i = 0; i < triplets.size(); i++) {
            sb.append(triplets.get(i).toString());
            if (i < triplets.size() - 1) {
                sb.append(") * (");
            }
        }

        sb.append(")");

        return sb.toString();
    }
}
