package logika;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída ProstorTest slouží ke komplexnímu otestování
 * třídy Prostor
 *
 * @author    Jarmila Pavlíčková
 * @version   pro skolní rok 2016/2017
 */
public class ProstorTest
{
        private logika.Batoh batoh1;
        private logika.Hra hra1;
    //== Datové atributy (statické i instancí)======================================

    //== Konstruktory a tovární metody =============================================
    //-- Testovací třída vystačí s prázdným implicitním konstruktorem ----------

    //== Příprava a úklid přípravku ================================================

    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody. Používá se
     * k vytvoření tzv. přípravku (fixture), což jsou datové atributy (objekty),
     * s nimiž budou testovací metody pracovat.
     */
    @Before
    public void setUp() {
        batoh1 = new logika.Batoh();
        hra1 = new logika.Hra();
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @After
    public void tearDown() {
    }

    //== Soukromé metody používané v testovacích metodách ==========================

    //== Vlastní testovací metody ==================================================

    /**
     * Testuje, zda jsou správně nastaveny průchody mezi prostory hry. Prostory
     * nemusí odpovídat vlastní hře, 
     */
    @Test
    public  void testVeci() {      
     Vec vec1 = new Vec("věc1",true,true);  
     Vec vec2 = new Vec("věc2",false,false);
     Vec vec3 = new Vec("věc3",true,true);
     logika.Prostor prostor1 = new logika.Prostor("prostor", "popis", false);
     logika.Prostor prostor2 = new logika.Prostor("prostor", "popis", true);
     
     prostor1.vlozVec(vec1);
     prostor1.vlozVec(vec2);
     
     assertSame(vec1, prostor1.odeberVec("věc1"));        
     assertNull(prostor1.odeberVec("věc4"));
     assertNull(prostor1.ziskejVec("věc4"));
     assertSame(vec2, prostor1.ziskejVec("věc2"));
     
    }


    @Test
    public void testPostava1()
    {
     Postava strasidlo = new Postava ("strašidlo","popis",true,true);
     Postava clovek = new Postava ("člověk","popis",true,true);
     logika.Prostor prostor3 = new logika.Prostor("prostor", "popis", true);
     
     prostor3.vlozPostavu(strasidlo);
     prostor3.vlozPostavu(clovek);
     
     assertSame(strasidlo, prostor3.odeberPostavu("strašidlo"));  
     assertNull(prostor3.odeberPostavu("želva"));
     assertNull(prostor3.ziskejPostavu("kocour"));
     assertSame(clovek, prostor3.ziskejPostavu("člověk"));
    }
}

