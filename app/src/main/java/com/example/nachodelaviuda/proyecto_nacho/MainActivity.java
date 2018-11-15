package com.example.nachodelaviuda.proyecto_nacho;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,Prueba.OnFragmentInteractionListener, ContenedorFragment.OnFragmentInteractionListener{

    private DrawerLayout drawer;
    private TextView nombreUsuario, correoUsuario;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        auth = FirebaseAuth.getInstance();
        //String string = nombreAuth.getCurrentUser().getEmail();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        //----------------------------------------------------------------------------------------------------------------------------------------------
        View hView = navigationView.getHeaderView(0);
        nombreUsuario = (TextView) hView.findViewById(R.id.nombreDeUsuario);
        correoUsuario = (TextView) hView.findViewById(R.id.correoUsuario);
        try{
            //nombreUsuario.setText(Objects.requireNonNull(auth.getCurrentUser()).getDisplayName());
            String str = auth.getCurrentUser().getDisplayName();
            if(Utilidades.toastero){
                Toast.makeText(this,"Bienvenido: " + str, Toast.LENGTH_SHORT).show();
                Utilidades.toastero = false;
            }
            //Toast.makeText(this,"Bienvenido: " + str, Toast.LENGTH_SHORT).show();
            nombreUsuario.setText(auth.getCurrentUser().getDisplayName());
            correoUsuario.setText(auth.getCurrentUser().getEmail());

        }catch (NullPointerException e){}
        //----------------------------------------------------------------------------------------------------------------------------------------------

        navigationView.setNavigationItemSelectedListener(this);
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new FragmentoPrincipal()).commit();
            navigationView.setCheckedItem(R.id.nav_principal);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Fragment miFragment = null;
        boolean fragmentoSeleccionado = false;
        switch(item.getItemId()){
            case R.id.nav_principal:
                miFragment = new FragmentoPrincipal();
                fragmentoSeleccionado = true;
                break;
            case R.id.nav_espana:
                miFragment = new FragmentoEspaña();
                fragmentoSeleccionado = true;
                break;
            case R.id.nav_portugal:
                miFragment = new FragmentoPortugal();
                fragmentoSeleccionado = true;
                break;
            case R.id.nav_bélgica:
                miFragment = new FragmentoBelgica();
                fragmentoSeleccionado = true;
                break;
            case R.id.nav_alemania:
                miFragment = new FragmentoAlemania();
                fragmentoSeleccionado = true;
                break;
            case R.id.nav_share:
                //Toast.makeText(this,"share", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,GestorDeTabs.class);
                startActivity(intent);
                break;
            case R.id.nav_send:
                //Toast.makeText(this,"send", Toast.LENGTH_SHORT).show();
                Intent intento = new Intent(MainActivity.this,NuevoMainActivity.class);
                startActivity(intento);
                break;
            case R.id.help:
                //Toast.makeText(this,"help", Toast.LENGTH_SHORT).show();
                miFragment = new Prueba();
                fragmentoSeleccionado = true;
                break;
            case R.id.information:
                //Toast.makeText(this,"information", Toast.LENGTH_SHORT).show();
                miFragment = new ContenedorFragment();
                fragmentoSeleccionado = true;
                break;
        }
        if (fragmentoSeleccionado) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,miFragment).commit();
        }



        drawer.closeDrawer(GravityCompat.START);
        return true;


    }
    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
