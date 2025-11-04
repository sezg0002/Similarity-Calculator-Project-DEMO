package Classes;

import java.util.Objects;

/**
 * Classe de base représentant un état.
 */
public class Etat {
    private static int nextEtatID = 1;
    protected final int id_etat;
    protected final String nature_etat;

    /**
     * Constructeur de la classe Etat.
     *
     * @param nature_etat La nature de l'état.
     */
    public Etat(String nature_etat) {
        this.id_etat = getNextEtatID();
        this.nature_etat = nature_etat;
    }

    private static synchronized int getNextEtatID() {
        return nextEtatID++;
    }

    /**
     * Obtient l'identifiant de l'état.
     *
     * @return L'identifiant de l'état.
     */
    public int getIdEtat() {
        return this.id_etat;
    }

    /**
     * Obtient la nature de l'état.
     *
     * @return La nature de l'état.
     */
    public String getNatureEtat() {
        return this.nature_etat;
    }

    // Implémentation des méthodes equals() et hashCode() pour une utilisation dans des collections.
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Etat other = (Etat) obj;
        return id_etat == other.id_etat && nature_etat.equals(other.nature_etat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_etat, nature_etat);
    }
}
