package nl.rocnijmegen.testing;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

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

        @Test
        public void testFullHypotheekBerekeningMetCorrecteInvoer() {
            // Simuleer gebruikersinvoer
            String input = "30000\nnee\nja\n6663\n10"; // Income, No partner, Yes to study debt, valid postal code, 10-year interest-fixed period
            InputStream inputStream = new ByteArrayInputStream(input.getBytes());
            System.setIn(inputStream);

            // Vang de standaarduitvoer op
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream originalOut = System.out;
            System.setOut(new PrintStream(outputStream));

            // Voer de applicatie uit
            App.main(new String[0]);

            // Zet de standaarduitvoer terug naar de oorspronkelijke staat
            System.setOut(originalOut);

            // Vang de uitvoer op en controleer deze
            String actualOutput = outputStream.toString();

            // Verwachte output controle - controleer op specifieke waarden
            String expectedMaxLenen = "Maximaal te lenen bedrag: €90000.00";  // Adjust this if necessary based on calculation
            String expectedMaandlasten = "Maandlasten voor hypotheek: €";       // Match partial to allow for dynamic value
            String expectedTotaal = "Totale bedrag betaald na 30 jaar: €";      // Match partial to allow for dynamic value

//            assertTrue(actualOutput.contains(expectedMaxLenen), "De output bevat niet het verwachte maximale leenbedrag.");
//            assertTrue(actualOutput.contains(expectedMaandlasten), "De output bevat niet het verwachte maandlastenbedrag.");
//            assertTrue(actualOutput.contains(expectedTotaal), "De output bevat niet het verwachte totale betaalde bedrag na 30 jaar.");
        }
}
