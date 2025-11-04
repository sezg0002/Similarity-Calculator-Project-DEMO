package Classes;

/**
 * The `EtatNormal` class represents a normal state.
 */
public class EtatNormal extends Etat {
    private static final String NATURE_ETAT = "Normal";

    /**
     * Constructor for the `EtatNormal` class.
     */
    public EtatNormal() {
        super(NATURE_ETAT);
    }

    /**
     * Returns the string representation of the normal state.
     *
     * @return The string representation of the normal state.
     */
    @Override
    public String toString() {
        return NATURE_ETAT;  // Replace the display with the actual type of the state
    }
}

