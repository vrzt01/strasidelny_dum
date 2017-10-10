package logika;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

/*******************************************************************************
 * Testovací třída HraTest slouží ke komplexnímu otestování
 * třídy Hra
 *
  * @author    Tomáš Vrzák
 * @version   LS 2016/17
 */
public class HraTest {
    private Hra hra1;

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
        hra1 = new Hra();
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @After
    public void tearDown() {
    }

    //== Soukromé metody používané v testovacích metodách ==========================

    //== Vlastní testovací metody ==================================================

    /***************************************************************************
     * Testuje průběh hry, po zavolání každěho příkazu testuje, zda hra končí
     * a v jaké aktuální místnosti se hráč nachází.
     * Při dalším rozšiřování hry doporučujeme testovat i jaké věci nebo osoby
     * jsou v místnosti a jaké věci jsou v batohu hráče.
     * 
     */
    @Test
    public void testPrubehHry() {
        int kod1 = hra1.getKod1();
        int kod2= hra1.getKod2();
        int kod3= hra1.getKod3();
        int kod4= hra1.getKod4();
        String celyKod = hra1.getCelyKod();
        System.out.println(celyKod);
        assertEquals("chodba", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi koupelna");
        assertEquals("koupelna", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("prohledat umyvadlo"); 
        hra1.zpracujPrikaz("seber část kódu "+kod2);
        hra1.zpracujPrikaz("jdi chodba");
        hra1.zpracujPrikaz("jdi obývák");
        hra1.zpracujPrikaz("prohledat sejf");
        hra1.zpracujPrikaz("seber část kódu "+kod4);
        hra1.zpracujPrikaz("prohledat lednice");
        hra1.zpracujPrikaz("seber šunka");
        hra1.zpracujPrikaz("jdi chodba");
        hra1.zpracujPrikaz("jdi ložnice");
        hra1.zpracujPrikaz("prohledat zrcadlo");
        hra1.zpracujPrikaz("seber část kódu "+kod3);
        hra1.zpracujPrikaz("jdi chodba");
        hra1.zpracujPrikaz("jdi schodiště dolů");
        hra1.zpracujPrikaz("jdi sklep");
        hra1.zpracujPrikaz("seber Papírek s nápovědou");
        hra1.zpracujPrikaz("jdi schodiště dolů");
        hra1.zpracujPrikaz("jdi chodba");
        hra1.zpracujPrikaz("jdi schodiště nahoru");
        hra1.zpracujPrikaz("jdi půda");
        hra1.zpracujPrikaz("odhoď šunka");
        hra1.zpracujPrikaz("část kódu "+kod1);
        hra1.zpracujPrikaz("jdi schodiště nahoru");
        hra1.zpracujPrikaz("jdi chodba");
        hra1.zpracujPrikaz("odemkni východ");
        hra1.zpracujPrikaz("zpět");
        hra1.zpracujPrikaz("odemkni východ");
        hra1.zpracujPrikaz("konec");
        assertEquals(true, hra1.konecHry());
    }

    @Test
    public  void testJdi() {    
        //Vec klic = new Vec("klíč",true,false,null,null,200,false,0);
        //Prostor prostor1 = new Prostor("hala", "vstupní hala budovy VŠE na Jižním městě", true, klic);
        Prostor prostor1 = new Prostor("jídelna", "jídlo", true);
        Prostor prostor2 = new Prostor("bufet", "bufet, kam si můžete zajít na svačinku", false);

        prostor1.setVychod(prostor2);
        prostor2.setVychod(prostor1);
        hra1.getHerniPlan().setAktualniProstor(prostor2);

        assertEquals(prostor2, prostor1.vratSousedniProstor("bufet"));
        assertEquals(null, prostor2.vratSousedniProstor("pokoj"));

        assertEquals("Kam mám jít? Musíš zadat jméno východu.", hra1.zpracujPrikaz("jdi"));
        assertEquals("Tam se odsud jít nedá!", hra1.zpracujPrikaz("jdi komárov"));
        assertEquals(prostor1.dlouhyPopis(), hra1.zpracujPrikaz("jdi jídelna"));
    }

    @Test
    public  void testSeber() {  
        Vec umyvadlo = new Vec("umyvadlo", false, true);
        Vec botnik = new Vec("botník", false, false);
        Vec baterka = new Vec("baterka", true, false);

        Prostor aktualni = hra1.getHerniPlan().getAktualniProstor();
        aktualni.vlozVec(umyvadlo);
        aktualni.vlozVec(botnik);
        aktualni.vlozVec(baterka);

        assertEquals("Sebráno do batohu!\n", hra1.zpracujPrikaz("seber baterka"));
        assertEquals("Tato věc tu není nebo nemůžeš sebrat tento objekt.", hra1.zpracujPrikaz("seber kokos"));
        assertEquals("Co chceš sebrat?", hra1.zpracujPrikaz("seber"));
        assertEquals("To se nedá nést sebou.", hra1.zpracujPrikaz("seber umyvadlo"));
    }

    /**
     * Testuje příkaz odhoď
     */
    @Test
    public  void testOdhod() {	
        Vec baterka = new Vec("baterka", true, false);
        Prostor aktualni = hra1.getHerniPlan().getAktualniProstor();
        aktualni.vlozVec(baterka);
        hra1.zpracujPrikaz("seber baterka");

        assertEquals("Ze svého batohu jsi odhodil věc | baterka | do prostoru | chodba", hra1.zpracujPrikaz("odhoď baterka"));
        assertEquals("Neodhazuješ věc, nebo věc, kterou chceš odhodit, není v batohu.", hra1.zpracujPrikaz("odhoď limonáda"));
        assertEquals("Co chceš odhodit?", hra1.zpracujPrikaz("odhoď"));
    }

    @Test
    public  void testZobrazeniBatohu() {	        
        assertEquals("Stačí zadat pouze název příkazu.", hra1.zpracujPrikaz("batoh cokoliv"));
        Vec baterka = new Vec("baterka",true,false); 
        Prostor prostor1 = new Prostor("prostor1", "tady", false);
        hra1.getHerniPlan().setAktualniProstor(prostor1);
        prostor1.vlozVec(baterka);
        assertEquals("Batoh je prázdný.", hra1.zpracujPrikaz("batoh"));
        hra1.zpracujPrikaz("seber baterka");
        assertEquals("V batohu máš tyto věci: | baterka | ", hra1.zpracujPrikaz("batoh"));
    }   

    @Test
    public  void testProhledat() {	
        Prostor prostor1 = new Prostor("prostor1", "tady", false);
        Vec skrinka_k = new Vec("skříňka", false, true);
        Vec kartacek = new Vec("kartáček", true, false);
        Vec mydlo = new Vec("mýdlo", true, false);
        Vec botnik = new Vec("botník", false, false);
        Vec baterka = new Vec("baterka", true, false);
        
        prostor1.vlozVec(skrinka_k);
        skrinka_k.setSchovaneVeci("kartáček", kartacek);
        skrinka_k.setSchovaneVeci("mýdlo", mydlo);
        prostor1.vlozVec(botnik);
        prostor1.vlozVec(baterka);
        hra1.getHerniPlan().setAktualniProstor(prostor1);
        
        assertEquals("Co mám prohledat?", hra1.zpracujPrikaz("prohledat")); 
        assertEquals("Tato věc se nedá prohledat!", hra1.zpracujPrikaz("prohledat baterka"));
        assertEquals("Můžeš prohledat pouze věc!", hra1.zpracujPrikaz("prohledat kokos"));
        assertEquals("Ve věci skříňka jsi našel: mýdlo, kartáček, ", hra1.zpracujPrikaz("prohledat skříňka"));
        

    }    
    @Test
    public  void testOdemkni() {	
    Prostor vychod = new Prostor("východ","Opustili jste dům. Jste zachraneni!", true);
    Prostor chodba = new Prostor("chodba","Nacházíte se na chodbě domu.", false); 
    Prostor obyvak = new Prostor("obývák","Nacházíte se v obývaku", false);
    Prostor puda = new Prostor("půda","Nacházíte se na půde.", false);
    obyvak.setVychod(puda);
    chodba.setVychod(vychod);
    chodba.setVychod(obyvak);
    obyvak.setVychod(chodba);
    puda.setVychod(obyvak);
    hra1.getHerniPlan().setAktualniProstor(chodba);
    
    assertEquals("Odemknout můžeš pouze východ. Ostatní prostory jsou odemčené.", hra1.zpracujPrikaz("odemkni obývák"));
    assertEquals("Můžeš odemknout pouze východ!", hra1.zpracujPrikaz("odemkni kokos"));
    assertEquals("Můžeš odemykat pouze sousední prostory!", hra1.zpracujPrikaz("odemkni chodba"));    
    }
}

