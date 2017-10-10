/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;

import java.util.*;

/*******************************************************************************
 * Instance třídy PrikazProhledat představují ...
 *
 * @author    Tomáš Vrzák
 * @version   LS 2016/17
 */
public class PrikazProhledat implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "prohledat";
    private HerniPlan plan;
    private Batoh batoh;
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     */
    public PrikazProhledat(HerniPlan plan, Batoh batoh)
    {
        this.plan = plan;
        this.batoh = batoh;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================

    /**
     *  Provádí příkaz "prohledat". 
     *  
     *  Aby mohlo dojít k prohledání Věci je nutné, aby byla prohledatelná.
     *  Můžeme použít příkaz "prohledat" s 0 až 2 parametry. 
     *  Nejdriv se zkusi, jestli je vec prohledatelna, pokud ano, tak se vrati text se seznamem veci uvnitr. Pokud 
     *  neni prohledatelna, tak se vrati text s chybou.
     *  Kdyz prohledame Vec, tak si muzeme vybrat co sebereme do batohu.
     *  Může se prohledat pouze Věc.
     *  
     *  @param parametry - jako parametr obsahuje věc, která se má použít.
     *  @return zpráva, kterou vypíše hra hráči
     */ 

    public String proved(String... parametry){
        String vracenyText = null;        
        Prostor aktualni = plan.getAktualniProstor();  
        String nazevProhledavaneVeci = null;
        Vec prohledavanaVec = null;

        if (parametry.length == 1) {
            nazevProhledavaneVeci = parametry[0];
        }
        if(parametry.length == 2){
            nazevProhledavaneVeci = parametry[0] +" " + parametry[1];
        }  
        if(parametry.length == 3){
            nazevProhledavaneVeci = parametry[0] +" " + parametry[1]+" "+ parametry[2];
        } 
        if(parametry.length != 0)
        {
            if(nazevProhledavaneVeci.equals("papírek s nápovědou")){
                prohledavanaVec = batoh.ziskejVec(nazevProhledavaneVeci);
            }
            else{
                prohledavanaVec = aktualni.ziskejVec(nazevProhledavaneVeci);
            }
        }
        if (parametry.length == 0){        
            vracenyText =  "Co mám prohledat?";
        }
        else{
            if (prohledavanaVec instanceof Vec) {

                if (prohledavanaVec.jeProhledatelna()) {
                    vracenyText = "Ve věci "+prohledavanaVec.getNazev()+" jsi našel: ";
                    for(String nazevSchovaneVeci: prohledavanaVec.getSchovaneVeci().keySet()){
                        vracenyText += nazevSchovaneVeci+", ";
                        aktualni.vlozVec(prohledavanaVec.getSchovaneVeci().get(nazevSchovaneVeci));
                    }
                } else {
                    vracenyText =  "Tato věc se nedá prohledat!";                        
                }

            } 
            else {
                vracenyText = "Můžeš prohledat pouze věc!";
            }
        }
        if (parametry.length > 3) { 
            vracenyText =  "Nemusíš zadávat tolik parametrů.";
        }                                                                         
        return vracenyText;
    }

    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @return nazev prikazu
     */
    public String getNazev(){
        return NAZEV;
    }
    //== Soukromé metody (instancí i třídy) ========================================

}
