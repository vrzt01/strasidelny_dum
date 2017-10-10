/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída BatohTest slouží ke komplexnímu otestování třídy ... 
 *
  * @author    Tomáš Vrzák
 * @version   LS 2016/17
 */
public class BatohTest
{
    private logika.Hra hra1;
    //== KONSTRUKTORY A TOVÁRNÍ METODY =========================================
    //-- Testovací třída vystačí s prázdným implicitním konstruktorem ----------

    /***************************************************************************
     * Inicializace předcházející spuštění každého testu a připravující tzv.
     * přípravek (fixture), což je sada objektů, s nimiž budou testy pracovat.
     */
    @Before
    public void setUp()
    {
        hra1 = new logika.Hra();
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každého testu.
     */
    @After
    public void tearDown()
    {
    }

    //== VLASTNÍ TESTY =========================================================
    //
    //     /********************************************************************
    //      *
    //      */
    @Test
    public void testBatoh()
    {
        Vec vec1 = new Vec("věc1",true,true);  
        Vec vec2 = new Vec("věc2",false,false);
        Vec vec3 = new Vec("věc3",true,true);
        logika.Prostor prostor1 = new logika.Prostor("prostor", "popis", false);

        hra1.getBatoh().vlozVecDoBatohu(vec1);
        hra1.getBatoh().vlozVecDoBatohu(vec2);               
        assertSame(2, hra1.getBatoh().velikostBatohu());        
        hra1.getBatoh().odeberVecZBatohu("věc1");
        assertSame(1, hra1.getBatoh().velikostBatohu());       
        assertSame(null,hra1.getBatoh().ziskejVec("věc3"));
        assertSame(vec2,hra1.getBatoh().ziskejVec("věc2")); 

        hra1.getBatoh().odeberVecZBatohu("věc2");
        assertSame("Batoh je prázdný.", hra1.getBatoh().obsahBatohu());  
        hra1.getBatoh().vlozVecDoBatohu(vec2);
        assertEquals("V batohu máš tyto věci: | věc2 | ", hra1.getBatoh().obsahBatohu());
    }
}
