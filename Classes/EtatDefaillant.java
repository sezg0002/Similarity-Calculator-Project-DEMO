package Classes;

/**
 * The `EtatDefaillant` class represents a faulty state.
 */
public class EtatDefaillant extends Etat {
    private static final String NATURE_ETAT = "Defaillant";

    /**
     * Constructor for the `EtatDefaillant` class.
     */
    public EtatDefaillant() {
        super(NATURE_ETAT);
    }

    /**
     * Returns the string representation of the faulty state.
     *
     * @return The string representation of the faulty state.
     */
    @Override
    public String toString() {
        return NATURE_ETAT;  // Replace the display with the actual type of the state
    }
}
