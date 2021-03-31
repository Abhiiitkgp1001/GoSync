package com.example.gosyncv11;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class NavigationActivity extends AppCompatActivity {
    private FloatingActionButton fab_main, fab1_host, fab2_join;
    private Animation fab_open, fab_close, fab_clock, fab_anti;
    private TextView host, join;
    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;
    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    Boolean isOpen = false;
    Button form,response;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        auth = FirebaseAuth.getInstance();
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        fab_main = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        fab1_host = (FloatingActionButton) findViewById(R.id.floatingActionButton2);
        fab2_join = (FloatingActionButton) findViewById(R.id.floatingActionButton5);
        host = (TextView) findViewById(R.id.textView13);
        join = (TextView) findViewById(R.id.textView12);
        form = (Button) findViewById(R.id.dispay_form);
        response = (Button) findViewById(R.id.display_response);
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        fab_anti = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_rotate_anticlock);
        fab_clock = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_rotate_clock);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        nav = (NavigationView) findViewById(R.id.nav);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_home:
                        Toast.makeText(getApplicationContext(),"Home",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.menu_profile:
                        Intent intent = new Intent(NavigationActivity.this, ProfileActivity.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(),"Profile",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.menu_faq:
                        Toast.makeText(getApplicationContext(),"FAQs",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.menu_signout:
                        auth.signOut();
                        startActivity(new Intent(NavigationActivity.this, login.class));
                        Toast.makeText(getApplicationContext(),"SignOut",Toast.LENGTH_LONG).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
                return true;
            }
        });



        fab_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isOpen){
                    fab1_host.startAnimation(fab_close);
                    fab2_join.startAnimation(fab_close);
                    fab_main.startAnimation(fab_anti);
                    fab2_join.setClickable(false);
                    fab1_host.setClickable(false);
                    fab2_join.setVisibility(View.INVISIBLE);
                    fab1_host.setVisibility(View.INVISIBLE);
                    host.setVisibility(View.INVISIBLE);
                    join.setVisibility(View.INVISIBLE);
                    isOpen=false;
                }
                else{
                    fab1_host.startAnimation(fab_open);
                    fab2_join.startAnimation(fab_open);
                    fab_main.startAnimation(fab_clock);
                    fab2_join.setVisibility(View.VISIBLE);
                    fab1_host.setVisibility(View.VISIBLE);
                    fab2_join.setClickable(true);
                    fab1_host.setClickable(true);
                    host.setVisibility(View.VISIBLE);
                    join.setVisibility(View.VISIBLE);
                    isOpen=true;
                }
            }
        });
        fab2_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NavigationActivity.this, JoinActivity.class);
                startActivity(intent);
            }
        });
        fab1_host.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NavigationActivity.this, CreateFormActivity.class);
                startActivity(intent);
            }
        });
        form.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NavigationActivity.this, DisplayFormActivity.class);
                startActivity(intent);
            }
        });
        response.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NavigationActivity.this, ResponseActivity.class);
                startActivity(intent);
            }
        });
    }
}
