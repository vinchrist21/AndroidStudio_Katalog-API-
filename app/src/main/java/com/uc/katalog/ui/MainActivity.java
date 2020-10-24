package com.uc.katalog.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.customview.widget.Openable;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.uc.katalog.R;

public class MainActivity extends AppCompatActivity {

    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigationView = findViewById(R.id.nav_view);

        AppBarConfiguration configuration = new AppBarConfiguration
                .Builder(R.id.nav_movie, R.id.nav_tv)
                .build();

        //nav icon back
        navController = Navigation.findNavController(this,R.id.fragment);

        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            if (destination.getId()==R.id.nav_movie||destination.getId()==R.id.nav_tv){
                navigationView.setVisibility(View.VISIBLE);
            } else {
                navigationView.setVisibility(View.GONE);
            }
        });
        NavigationUI.setupActionBarWithNavController(this, navController, configuration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }


    //nav icon back
    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, (Openable)null);
    }
}