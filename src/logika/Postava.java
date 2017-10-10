/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 * Instance třídy Postava představují ...
 *
 * @author    Tomáš Vrzák
 * @version   LS 2016/17
 */
public class Postava
{
//== Datové atributy (statické i instancí)======================================
    private String nazev;
    private String popis;
    private boolean agresivni;
    private boolean konec;
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     */
    public Postava(String nazev, String popis, boolean agresivni, boolean konec)
    {
        this.nazev = nazev;
        this.popis = popis;
        this.agresivni = agresivni;
        this.konec = konec;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    public String getNazev(){
        return nazev;
    }
    
    public String getPopis(){
        return popis;
    }
    
    public boolean getAgresivni(){
        return this.agresivni;
    }
    public void setAgresivni(boolean agresivni){
        this.agresivni = agresivni;
    }
    public void setKonec(boolean konec){
        this.konec = konec;
    }
    public boolean getKonec(){
        return this.konec;
    }
    //== Soukromé metody (instancí i třídy) ========================================

}
