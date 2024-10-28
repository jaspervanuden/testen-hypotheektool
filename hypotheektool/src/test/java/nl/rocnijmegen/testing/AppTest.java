package nl.rocnijmegen.testing;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for simple App.
 */
public class AppTest {

    /**
     * Rigorous Test :-)
     */
    @Test
    void testBerekenMaxTeLenenZonderPartnerEnZonderStudieschuld() {
        double inkomen = 60000;
        double partnerInkomen = 0;
        boolean heeftStudieschuld = false;

        double verwachtMaxTeLenen = 60000 * 4.25;
        double resultaat = App.berekenMaxTeLenen(inkomen, partnerInkomen, heeftStudieschuld);

        assertEquals(verwachtMaxTeLenen, resultaat, 0.01);
    }

    @Test
    void testBerekenMaxTeLenenMetPartnerZonderStudieschuld() {
        double inkomen = 60000;
        double partnerInkomen = 40000;
        boolean heeftStudieschuld = false;

        double verwachtMaxTeLenen = (60000 + 40000) * 4.25;
        double resultaat = App.berekenMaxTeLenen(inkomen, partnerInkomen, heeftStudieschuld);

        assertEquals(verwachtMaxTeLenen, resultaat, 0.01);
    }

    @Test
    void testBerekenMaxTeLenenMetStudieschuld() {
        double inkomen = 60000;
        double partnerInkomen = 0;
        boolean heeftStudieschuld = true;

        double verwachtMaxTeLenen = (60000 * 4.25) * 0.75;
        double resultaat = App.berekenMaxTeLenen(inkomen, partnerInkomen, heeftStudieschuld);

        assertEquals(verwachtMaxTeLenen, resultaat, 0.01);
    }

    @Test
    void testBerekenMaandlasten30Jaar() {
        double lening = 255000;
        int rentevastePeriode = 30;

        // Verwachte resultaten op basis van rentepercentage van 5% voor 30 jaar
        double rentePercentage = 5.0 / 100 / 12;
        double renteBedrag = lening * rentePercentage;
        double aflossing = lening / (30 * 12);
        double verwachtMaandlasten = renteBedrag + aflossing;

        double resultaat = App.berekenMaandlasten(lening, rentevastePeriode);

        assertEquals(verwachtMaandlasten, resultaat, 0.01);
    }

    @Test
    void testIsPostcodeUitgesloten() {
        assertTrue(App.isPostcodeUitgesloten(9679));
        assertTrue(App.isPostcodeUitgesloten(9681));
        assertTrue(App.isPostcodeUitgesloten(9682));
        assertFalse(App.isPostcodeUitgesloten(1000)); // Een geldige postcode
    }
}
