package pmdm.mviel.debugging;

/* Aquest projecte té la finalitat de practicar la depuració de codi amb Android Studio.
    Introdueix a l'alumne en l'ús de la classe Log d'Android,
    el seguiment de missatges al log del sistema (Log Cat),
    i la depuració pas a pas per trobar errades en temps d'execució.
    Agost'2015
 */



import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends Activity {

    private int comptadorDeClicks; // atribut que ens servirà per comptar els clicks que fa l'usuari
    private TextView marcador_polsacions;  // atribut que referencia el 'label' on mostrem el numero
                                           // de polsacions que ha fet l'usuari

    // El mètode onCreate, és dels primers mètodes que s'executen quan creem un activity
    // Fent un paral·lelisme amb Swing, seria com el constructor d'una classe Frame
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Estes dues línies les inclourem SEMPRE al principi del mètode onCreate!!!!
        super.onCreate(savedInstanceState);  // Cridarem SEMPRE, primer que res, al onCreate de la classe Activity
        setContentView(R.layout.activity_main);  //Ací llegim l'xml (activity_main.xml) on hem definit
                                                // el contingut que tindrà MainActivity

        marcador_polsacions = (TextView)findViewById(R.id.marcadorPolsacions); //Ací enllacem l'objecte xml macadorPolsacions
        // amb un objecte JAVA de la classe TextView, per poder manipular-lo després.
        comptadorDeClicks=0; // Inicialitzem el comptador de clicks a 0

        marcador_polsacions.setText(""+comptadorDeClicks);  // Posem el valor 0 dins l'objecte TextView
    }


    //el setter de l'atribut comptadorDeClicks
    public void setComptadorDeClicks(int valor){
        this.comptadorDeClicks=valor;
    }


    //el getter de l'atribut comptadorDeClicks
    public int getComptadorDeClicks(){
        return this.comptadorDeClicks;
    }



    //Mètode que hem definit a fitxer activity_main.xml, i que s'executarà quan l'usuari faça click
    //sobre el botó que té el text "Polsar"
    public void boto_Polsat(View v){

        int valor_comptador= this.getComptadorDeClicks();  //agafem el valor de l'atribut comptadorDeClicks
        valor_comptador++;      // l'incrementem en una unitat
        this.setComptadorDeClicks(valor_comptador);  //Establim el nou valor de comptadorDeClicks
        marcador_polsacions.setText("" + valor_comptador);  //A l'objecte TextView, l'indiquem que mostre el nou valor de
                                                            // comptadorDeClicks
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
