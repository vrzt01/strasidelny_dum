/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;


/*******************************************************************************
 * Instance třídy PrihazOdhod představují ...
 *
 * @author    Tomáš Vrzák
 * @version   LS 2016/17
 */
public class PrikazOdhod implements IPrikaz
{
    private static final String NAZEV = "odhoď";
    private HerniPlan plan;
    private Batoh batoh;
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     */
    public PrikazOdhod(HerniPlan plan, Batoh batoh)
    {
        this.plan = plan;
        this.batoh = batoh;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================

    /**
     *  Metoda pro provedení příkazu "odhoď"
     *  
     *  Prohledá batoh, zda odebíraná věc je v něm.
     *  Pokud ano, věc bude odhozena do aktuálního prostoru.
     *  Skóre se sníží o body odhazované věci.
     *  
     *  @param věc, která má být odhozena z batohu do aktuálního prostoru
     *  @return String vrácený text k hráči
     */
    public String proved(String... parametry){
        String nazevOdhazovaneVeci = null; 
        String vracenyText = null;
        Prostor aktualni = plan.getAktualniProstor();  
        
        if(parametry.length == 0){        
            vracenyText = "Co chceš odhodit?";
        } 
        if (parametry.length == 1) {
            nazevOdhazovaneVeci = parametry[0];           
        }
        if (parametry.length == 2) {
            nazevOdhazovaneVeci = parametry[0] + " " + parametry[1];           
        }
        if (parametry.length == 3) {
            nazevOdhazovaneVeci = parametry[0] + " " + parametry[1] + " " + parametry[2];           
        }

        if(parametry.length != 0){ 
            if(nazevOdhazovaneVeci.equals("šunka") && aktualni.getNazev().equals("půda")){      
            Vec odhazovanaVec = batoh.odeberVecZBatohu(nazevOdhazovaneVeci);
            aktualni.vlozVec(odhazovanaVec);
            aktualni.ziskejPostavu("ghůl").setAgresivni(false);
            vracenyText = "Ghůl je zaměstnán žraním šunky.";
            }
            else{
                if (batoh.ziskejVec(nazevOdhazovaneVeci) instanceof Vec) {
                    aktualni = plan.getAktualniProstor();        
                    Vec odhazovanaVec = batoh.odeberVecZBatohu(nazevOdhazovaneVeci);
                    aktualni.vlozVec(odhazovanaVec);
                    vracenyText = "Ze svého batohu jsi odhodil věc | " + nazevOdhazovaneVeci + " | do prostoru | " + aktualni.getNazev();
                } else {
                    vracenyText = "Neodhazuješ věc, nebo věc, kterou chceš odhodit, není v batohu.";
                }   
            }
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