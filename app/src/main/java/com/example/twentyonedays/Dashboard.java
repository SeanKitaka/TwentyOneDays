package com.example.twentyonedays;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.twentyonedays.databinding.ActivityDashboardBinding;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class Dashboard extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityDashboardBinding binding;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarDashboard.toolbar);
        binding.appBarDashboard.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_dashboard);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        View headerView = navigationView.getHeaderView(0);

        ImageView imageView = headerView.findViewById(R.id.imageView); // Replace imageView with the ID of your ImageView
        TextView emailHeader = headerView.findViewById(R.id.textView); // Replace textView with the ID of your TextView
        TextView welcome = headerView.findViewById(R.id.welcomeText);

// Update the image and text
/*
        imageView.setImageResource(); // Replace your_image with the drawable resource ID of your image
*/
        try {
            String name = getIntent().getStringExtra("firstName");
            String email = getIntent().getStringExtra("email");
            String uid = getIntent().getStringExtra("googleId");
            String image = getIntent().getStringExtra("image");
            if (name != null) {
                welcome.setText("Welcome back " + name);
            }
            if (email != null) {
                emailHeader.setText(email);
            }
            if (image != null) {
                Picasso.get().load(image).resize(200, 200).centerCrop().into(imageView);
            }

        } catch (NullPointerException e) {
            // Handle null pointer exception
            e.printStackTrace(); // For debugging purposes
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_dashboard);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}