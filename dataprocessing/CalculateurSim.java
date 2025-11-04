package dataprocessing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Classes.Cas;
import Classes.Intervalle;
import Classes.Triplet;
import datacollection.Extraction;

/**
 * Cette classe contient des méthodes pour calculer la similarité entre des cas
 * en utilisant des formules mathématiques.
 */
public class CalculateurSim {

    /**
     * Calcule la similarité entre deux événements en utilisant la formule DEV.
     *
     * @param Eki Le premier événement.
     * @param Ekj Le deuxième événement.
     * @return La similarité calculée.
     * @throws IllegalArgumentException Si au moins l'un des noms d'événements ne respecte pas la norme.
     */
    public static int DEV(String Eki, String Ekj) throws IllegalArgumentException {
        Pattern r = Pattern.compile("^(RE|FE)_[A-Za-z0-9]+(_[A-Za-z0-9]+)?$");

        Matcher m1 = r.matcher(Eki);
        Matcher m2 = r.matcher(Ekj);

        if (!m1.matches() || !m2.matches()) {
            throw new IllegalArgumentException("Au moins l'un des noms d'évennements ne respecte pas la norme.");
        }

        int res;
        if (Eki.equals(Ekj)) {
            res = 0;
        } else {
            res = 1;
        }

        System.out.println("DEV res = " + res + " For : " + Eki + " And " + Ekj);
        return res;
    }

    /**
     * Calcule la similarité entre un point et un intervalle en utilisant la formule POS.
     *
     * @param x   Le point.
     * @param itv L'intervalle.
     * @return La similarité calculée.
     * @throws IllegalArgumentException Si l'intervalle ne respecte pas la norme.
     */
    public static int POS(int x, Intervalle itv) throws IllegalArgumentException {

        int a = itv.getMin();
        int b = itv.getMax();

        if (a > b) {
            throw new IllegalArgumentException("L'intervalle ne respecte pas la norme.");
        }

        int res;
        if (x >= a && x <= b) {
            res = 0;
        } else {
            res = Math.min(Math.abs(x - a), Math.abs(x - b));
        }
        System.out.println("POS res = " + res + " for x = " + x + " And interval = " + itv.toString());
        return res;
    }

    /**
     * Calcule la similarité entre deux intervalles en utilisant la formule IPOS.
     *
     * @param itv1 Le premier intervalle.
     * @param itv2 Le deuxième intervalle.
     * @return La similarité calculée.
     * @throws Exception Si une erreur se produit pendant le calcul.
     */
    public static int IPOS(Intervalle itv1, Intervalle itv2) throws Exception {
        int x = itv1.getMin();
        int y = itv1.getMax();
        int a = Math.max(x, itv2.getMin());
        int b = Math.min(y, itv2.getMax());

        int res;
        if (!(x >= a) && (x <= b)) {
            res = 0;
        } else {
            res = Math.min(POS(x, itv2), POS(y, itv2));
        }
        System.out.println("IPOS res = " + res + " for x = " + x + ", y = " + y + " And interval = " + itv2.toString());

        return res;
    }

    /**
     * Calcule la similarité entre deux triplets en utilisant la formule DT.
     *
     * @param trip1 Le premier triplet.
     * @param trip2 Le deuxième triplet.
     * @return La similarité calculée.
     * @throws Exception Si une erreur se produit pendant le calcul.
     */
    public static double DT(Triplet trip1, Triplet trip2) throws Exception {
        double res = DEV(trip1.getP1(), trip2.getP1()) + DEV(trip1.getP2(), trip2.getP2());
        res += IPOS(trip1.getIntervalle(), trip2.getIntervalle());

        System.out.println("DT res = " + res);

        return (res / Extraction.trouverIntervalleMax());
        // return (res/100);
    }

    /**
     * Calcule la distance entre deux cas en utilisant la formule Distance.
     *
     * @param cas1 Le premier cas.
     * @param cas2 Le deuxième cas.
     * @return La distance calculée.
     * @throws Exception Si une erreur se produit pendant le calcul.
     */
    public static double Distance(Cas cas1, Cas cas2) throws Exception {
        int mt = cas1.getNbTriplets();

        if (mt != cas2.getNbTriplets()) {
            throw new IllegalArgumentException("Les deux cas ne partagent pas le même nombre de triplets");
        }

        List<Triplet> ltC1 = cas1.getListTriplet();
        List<Triplet> ltC2 = cas2.getListTriplet();
        float sm = 0;
        for (int i = 0; i < mt; i++) {
            sm += DT(ltC1.get(i), ltC2.get(i));
            System.out.println("sm = " + sm + " for i = " + i);
        }

        System.out.println("D : sm = " + sm + " And mt = " + mt);
        return sm / (3 * mt);
    }

    /**
     * Fonction principale pour tester les calculs de similarité.
     *
     * @param args Les arguments de la ligne de commande (non utilisés ici).
     */
    public static void main(String[] args) {
        Extraction extraction = new Extraction();
        extraction.initData("dataBase\\reglesCN.txt");
        HashMap<Integer, Cas> casDictionary = extraction.getCasDictionary();

        Triplet trip1 = new Triplet("RE_but_ext", "FE_x_conv", new Intervalle(274000, 309000));
        Triplet trip2 = new Triplet("RE_XGLISS", "FE_x_conv", new Intervalle(274000, 309000));
        List<Triplet> listeT = new ArrayList<>();
        listeT.add(trip1);
        listeT.add(trip2);

        Cas casTest = new Cas(null, listeT);

        double result;
        try {
            result = Distance(casDictionary.get(1), casTest);
            System.out.println("Distance = " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
