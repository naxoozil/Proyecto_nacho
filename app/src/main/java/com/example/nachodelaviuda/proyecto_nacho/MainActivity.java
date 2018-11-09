package com.example.nachodelaviuda.proyecto_nacho;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private TextView nombreUsuario, correoUsuario;
    private FirebaseAuth nombreAuth, correoAuth;


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

        nombreAuth = FirebaseAuth.getInstance();
        String string = nombreAuth.getCurrentUser().getEmail();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        //----------------------------------------------------------------------------------------------------------------------------------------------
        nombreUsuario = (TextView) findViewById(R.id.nombreDeUsuario);
        correoUsuario = (TextView) findViewById(R.id.correoUsuario);
        View hView = navigationView.getHeaderView(0);
        correoUsuario = (TextView) hView.findViewById(R.id.correoUsuario);
        correoUsuario.setText(string);
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
        switch(item.getItemId()){
            case R.id.nav_principal:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new FragmentoPrincipal()).commit();
                break;
            case R.id.nav_espana:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new FragmentoEspaña()).commit();
            break;
            case R.id.nav_portugal:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new FragmentoPortugal()).commit();
                break;
            case R.id.nav_bélgica:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new FragmentoBelgica()).commit();
                break;
            case R.id.nav_alemania:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new FragmentoAlemania()).commit();
                break;
            case R.id.nav_share:
                //Toast.makeText(this,"share", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, NuevoMainActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_send:
               // Toast.makeText(this,"send", Toast.LENGTH_SHORT).show();
                Intent intento = new Intent(this, Main2Activity.class);
                startActivity(intento);
                break;
            case R.id.help:
                Toast.makeText(this,"help", Toast.LENGTH_SHORT).show();
                break;
            case R.id.information:
                Toast.makeText(this,"information", Toast.LENGTH_SHORT).show();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
