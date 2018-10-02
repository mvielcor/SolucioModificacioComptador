package pmdm.mviel.debugging;

/* Aquest projecte té la finalitat de practicar la depuració de codi amb Android Studio.
    Introdueix a l'alumne en l'ús de la classe Log d'Android,
    el seguiment de missatges al log del sistema (Log Cat),
    i la depuració pas a pas per trobar errades en temps d'execució.
    Agost'2015
    Última modificació: Setembre'18
 */



import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class MainActivity extends Activity {

    private int comptadorDeClicks; // atribut que ens servirà per comptar els clicks que fa l'usuari
    private int comptadorClicksRestants;
    private TextView marcador_polsacions;  // atribut que referencia el 'label' on mostrem el numero
                                           // de polsacions que ha fet l'usuari
    private TextView marcador_clicks_restants;
    private final boolean DEBUG=true;
    private final String ETIQUETA="depurant";
    private final int CLIKS_RESTANTS=5;

    // El mètode onCreate, és dels primers mètodes que s'executen quan creem un activity
    // Fent un paral·lelisme amb Swing, seria com el constructor d'una classe Frame
    @Override
    protected void onCreate(Bundle savedInstanceState) {



        //Estes dues línies les inclourem SEMPRE al principi del mètode onCreate!!!!
        super.onCreate(savedInstanceState);  // Cridarem SEMPRE, primer que res, al onCreate de la classe Activity
        setContentView(R.layout.activity_main);  //Ací llegim l'xml (activity_main.xml) on hem definit
                                                // el contingut que tindrà MainActivity

        marcador_polsacions = (TextView)findViewById(R.id.marcadorPolsacions); //Ací enllacem l'objecte xml macadorPolsacions
        marcador_clicks_restants = (TextView)findViewById(R.id.marcadorClicksRestants);
        if (DEBUG) Log.d(ETIQUETA,"Abans d'inicialitzar comptadorDeClics val:"+comptadorDeClicks);
        // amb un objecte JAVA de la classe TextView, per poder manipular-lo després.
        comptadorDeClicks=0; // Inicialitzem el comptador de clicks a 0
        comptadorClicksRestants=CLIKS_RESTANTS;
        marcador_polsacions.setText(""+comptadorDeClicks);  // Posem el valor 0 dins l'objecte TextView
        marcador_clicks_restants.setText(""+comptadorClicksRestants);
    }

    public void setComptadorClicksRestants(int comptadorClicksRestants) {
        this.comptadorClicksRestants = comptadorClicksRestants;
    }

    public int getComptadorClicksRestants() {
        return comptadorClicksRestants;
    }

    //el setter de l'atribut comptadorDeClicks
    public void setComptadorDeClicks(int valor){
        this.comptadorDeClicks=valor;
        if (DEBUG) Log.d(ETIQUETA,"S’ha incrementat el comptador a:"+valor);
    }


    //el getter de l'atribut comptadorDeClicks
    public int getComptadorDeClicks(){
        if (DEBUG) Log.d(ETIQUETA,"Estic al getComptadorDeClicks");
        return this.comptadorDeClicks;
    }



    //Mètode que hem definit a fitxer activity_main.xml, i que s'executarà quan l'usuari faça click
    //sobre el botó que té el text "Polsar"
    public void boto_Polsat(View v){
        if (DEBUG) Log.d(ETIQUETA,"S'ha polsat el botó!!!");

        int valor_comptador= this.getComptadorDeClicks();  //agafem el valor de l'atribut comptadorDeClicks
        valor_comptador++;      // l'incrementem en una unitat
        int valor_clicks_restants = this.getComptadorClicksRestants();
        valor_clicks_restants--;
        this.setComptadorDeClicks(valor_comptador);  //Establim el nou valor de comptadorDeClicks
        this.setComptadorClicksRestants(valor_clicks_restants);
        marcador_polsacions.setText("" + valor_comptador);  //A l'objecte TextView, l'indiquem que mostre el nou valor de
                                                            // comptadorDeClicks
        marcador_clicks_restants.setText(""+valor_clicks_restants);
        if (valor_clicks_restants==0){
            if(v.getId()==R.id.button){
                Button boto = (Button)findViewById(R.id.button);
                boto.setEnabled(false);
            }
        }
    }


    public void boto_Resetejar(View v){
        this.setComptadorDeClicks(0);
        this.setComptadorClicksRestants(CLIKS_RESTANTS);
        Button boto = (Button)findViewById(R.id.button);
        boto.setEnabled(true);
        marcador_polsacions.setText(""+this.getComptadorDeClicks());  // Posem el valor 0 dins l'objecte TextView
        marcador_clicks_restants.setText(""+this.getComptadorClicksRestants());
    }

    // Aquest mètode ens crea el menú d'opcions. Ho vorem més endavant!!!
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //Aquest mètode s'executa sempre que seleccionem alguna opció del menú
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    }
