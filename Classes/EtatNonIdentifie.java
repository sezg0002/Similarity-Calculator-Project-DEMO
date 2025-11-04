package Classes;

/**
 * The `EtatNonIdentifie` class represents an unidentified state.
 */
class EtatNonIdentifie extends Etat {
    private static final String NATURE_ETAT = "NonIdentifier";

    /**
     * Constructor for the `EtatNonIdentifie` class.
     */
    public EtatNonIdentifie() {
        super(NATURE_ETAT);
    }

    /**
     * Returns the string representation of the unidentified state.
     *
     * @return The string representation of the unidentified state.
     */
    @Override
    public String toString() {
        return NATURE_ETAT;  // Replace the display with the actual type of the state
    }
}
