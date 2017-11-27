package logika;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import main.PanelVychodu;
import util.ObserverZmenyProstoru;
import util.SubjektZmenyProstoru;

/**
 *  Class HerniPlan - třída představující mapu a stav adventury.
 * 
 *  Tato třída inicializuje prvky ze kterých se hra skládá:
 *  vytváří všechny prostory,
 *  propojuje je vzájemně pomocí východů 
 *  a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Tomáš Vrzák
 *@version    pro školní rok 2016/2017
 */
public class HerniPlan implements SubjektZmenyProstoru {

    private Prostor aktualniProstor;
    private Prostor vychod;
    private Postava ghul;
    private List<ObserverZmenyProstoru> seznamPozorovatelu;
    /**
     *  Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví halu.
     */
    public HerniPlan(int kod1, int kod2, int kod3, int kod4) {
        zalozProstoryHry(kod1,kod2,kod3,kod4);
        seznamPozorovatelu = new ArrayList<>();
    }

    /**
     *  Vytváří jednotlivé prostory, věci a postavu a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví chodbu.
     *  Schovává věci do věcí, které se nechají prohledat.
     *  
     *  @param jako parametr musime zadat casti kodu, pro názvy papírků ve hře
     */
    private void zalozProstoryHry(int kod1, int kod2, int kod3, int kod4) {
        //String nazev, String popis, boolean jeZamceny
        // vytvářejí se jednotlivé prostory
        Prostor chodba = new Prostor("chodba","Nacházíte se na chodbě domu.", false, 10, 50);     
        Prostor koupelna = new Prostor("koupelna", "Nacházíte se v koupelně", false,10, 50);
        Prostor loznice = new Prostor("ložnice","Nacházíte se v ložnici", false,10, 50);
        Prostor obyvak = new Prostor("obývák","Nacházíte se v obývaku", false,10, 50);
        Prostor sklep = new Prostor("sklep","Nachazíte se ve sklepě.", false,10, 50);
        Prostor puda = new Prostor("půda","Nacházíte se na půde.", false,10, 50);
        Prostor schodisteSklep = new Prostor("schodiště dolů","Tímto schodištem se dostanete do sklepa.", false,10, 50);
        Prostor schodistePuda = new Prostor("schodiště nahoru","Tímto schodištem se dostanete na půdu.", false,10, 50);
        vychod = new Prostor("východ","x", true,10, 50);
        // přiřazují se průchody mezi prostory (sousedící prostory)
        chodba.setVychod(koupelna);
        chodba.setVychod(loznice);
        chodba.setVychod(obyvak);
        chodba.setVychod(schodisteSklep);
        chodba.setVychod(schodistePuda);
        chodba.setVychod(vychod);

        schodisteSklep.setVychod(sklep);
        schodisteSklep.setVychod(chodba);

        schodistePuda.setVychod(puda);
        schodistePuda.setVychod(chodba);

        sklep.setVychod(schodisteSklep);
        puda.setVychod(schodistePuda);

        koupelna.setVychod(chodba);
        loznice.setVychod(chodba);
        obyvak.setVychod(chodba);

        aktualniProstor = chodba; // hra začíná v domečku   
        //Vec(String nazev, boolean prenositelnost, boolean jeProhledatelna)
        Vec stul_ch = new Vec("stůl", false, false);
        Vec obraz_ch = new Vec("obraz", false, false);
        Vec vesak = new Vec("vesak", false, false);
        Vec botnik = new Vec("botník", false, false);
        chodba.vlozVec(stul_ch);
        chodba.vlozVec(obraz_ch);
        chodba.vlozVec(vesak);
        chodba.vlozVec(botnik);

        Vec umyvadlo = new Vec("umyvadlo", false, true);
        Vec papirek2 = new Vec("část kódu "+kod2, true, false);
        umyvadlo.setSchovaneVeci("část kódu "+kod2, papirek2);        

        Vec skrinka_k = new Vec("skříňka", false, true);
        Vec kartacek = new Vec("kartáček", true, false);
        Vec mydlo = new Vec("mýdlo", true, false);
        skrinka_k.setSchovaneVeci("mýdlo", mydlo);
        skrinka_k.setSchovaneVeci("kartáček", kartacek);

        Vec zachod = new Vec("záchod", false, false);
        koupelna.vlozVec(umyvadlo);
        koupelna.vlozVec(skrinka_k);
        koupelna.vlozVec(zachod);

        Vec postel = new Vec("postel", false, false);
        Vec skrin_l = new Vec("skříň", false, true);
        Vec sako = new Vec("sako", true, false);
        Vec ponozky = new Vec("ponožky", true, false);
        skrin_l.setSchovaneVeci("sako", sako);
        skrin_l.setSchovaneVeci("ponožky", ponozky);

        Vec zrcadlo = new Vec("zrcadlo", false, true);
        Vec papirek3 = new Vec("část kódu "+kod3, true, false);
        zrcadlo.setSchovaneVeci("část kódu "+kod3, papirek3);
        loznice.vlozVec(postel);
        loznice.vlozVec(skrin_l);
        loznice.vlozVec(zrcadlo);

        Vec sejf = new Vec("sejf", false, true);
        Vec papirek4 = new Vec("část kódu "+kod4, true, false);
        Vec hotovost = new Vec("hotovost", true, false);
        sejf.setSchovaneVeci("část kódu "+kod4, papirek4);
        sejf.setSchovaneVeci("hotovost", hotovost);

        Vec stolek = new Vec("stolek", false, true);
        Vec blok = new Vec("blok", true, false);
        Vec propiska = new Vec("propiska", true, false);
        stolek.setSchovaneVeci("blok", blok);
        stolek.setSchovaneVeci("propiska", propiska);

        Vec gauc = new Vec("gauc", false, false);
        obyvak.vlozVec(sejf);
        obyvak.vlozVec(stolek);
        obyvak.vlozVec(gauc);

        Vec lednice = new Vec("lednice", false, true);
        Vec sunka = new Vec("šunka", true, false);
        Vec mleko = new Vec("mléko", true, false);
        obyvak.vlozVec(lednice);
        lednice.setSchovaneVeci("šunka", sunka);
        lednice.setSchovaneVeci("mléko", mleko);

        Vec bojler = new Vec("bojler", false, false);
        Vec pradlo = new Vec("prádlo", false, true);
        Vec prazdno = new Vec("nic", false, false);
        //Vec napoveda = new Vec("papírek na půdě je poslední částí kódu", false, false);
        Vec papirekNapoveda = new Vec("papírek s nápovědou", true, true);
        Vec napoveda = new Vec("papírek v sejfu je poslední částí kódu.", false, false);
        papirekNapoveda.setSchovaneVeci("papírek v sejfu je poslední částí kódu.", napoveda);
        sklep.vlozVec(bojler);
        sklep.vlozVec(pradlo);
        sklep.vlozVec(papirekNapoveda);
        pradlo.setSchovaneVeci("nic", prazdno);

        Vec baterka = new Vec("baterka", true, false);
        Vec papirek1 = new Vec("část kódu "+kod1, true, false);
        Vec kosti = new Vec("kosti", true, false);
        puda.vlozVec(baterka);
        puda.vlozVec(papirek1);
        puda.vlozVec(kosti);

        ghul = new Postava("ghůl", "Vidíte hladového a agresivního ghůla, pozor", true, false);
        puda.vlozPostavu(ghul);

    }

    /**
     *  Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     *@return     aktuální prostor
     */

    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }

    /**
     *  Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     *@param  prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
        aktualniProstor = prostor;
        upozorniPozorovatele();
    }

    /**
     *  Metoda testuje zda nastavala výhra.
     *  Vyhra nastane, kdyz se odemkne vychod.
     *
     *@return     nastal/nenastal konec hry
     */
    public boolean vyhra(){        
        if(vychod.getJeZamceny() == false){
            return true;
        } else {
            return false;       
        }
    }

    /**
     *  Metoda testuje zda nastavala prohra.
     *  Prohra nastane, kdyz parametr konec u postavy Ghul se nastavi na true.
     *
     *@return     nastal/nenastal konec hry
     */
    public boolean prohra(){
        if(ghul.getKonec() || vychod.getPopis().equals("konec")){        
            return true;
        }
        else{
            return false;
        }
    }

   public void zaregistrujPozorovatele(ObserverZmenyProstoru pozorovatel)
      {
        seznamPozorovatelu.add(pozorovatel);
      }

    public void odregistrujPozorovatele(ObserverZmenyProstoru pozorovatel)
      {
        seznamPozorovatelu.remove(pozorovatel);
      }

    public void upozorniPozorovatele()
      {
        for (ObserverZmenyProstoru pozorovatel : seznamPozorovatelu) {
            pozorovatel.aktualizuj();
        }
      }

    
}
