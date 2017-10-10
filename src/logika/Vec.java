
/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;


import java.util.*;

/*******************************************************************************
 * Instance třídy Vec představují ...
 *
  * @author    Tomáš Vrzák
 * @version   LS 2016/17
 */
public class Vec
{
    //== Datové atributy (statické i instancí)======================================
    private String nazev;
    private boolean prenositelnost;
    private boolean jeProhledatelna;
    private Map<String, Vec> schovaneVeci;
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     */
    public Vec(String nazev, boolean prenositelnost, boolean jeProhledatelna)
    {
        this.nazev = nazev;
        this.prenositelnost = prenositelnost;
        this.jeProhledatelna = jeProhledatelna;
        schovaneVeci = new HashMap<>();
    }
    public void setSchovaneVeci(String nazev ,Vec vec) {
        schovaneVeci.put(nazev, vec);
    }
    public Map<String, Vec> getSchovaneVeci() {
        return this.schovaneVeci;
    }
    //== Nesoukromé metody (instancí i třídy) ======================================
    public String getNazev(){
    return nazev;
    }
    
    public boolean jePrenositelna()
    {
    return this.prenositelnost;
    }
    
    public boolean jeProhledatelna() {
        return jeProhledatelna;
    }

    //== Soukromé metody (instancí i třídy) ========================================

}
