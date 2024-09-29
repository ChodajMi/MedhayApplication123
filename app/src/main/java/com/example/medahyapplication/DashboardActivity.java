package com.example.medahyapplication;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DashboardActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private ActionBar actionBar;
    private BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Home");  // Set initial title
        }

        firebaseAuth = FirebaseAuth.getInstance();
        navigationView = findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(selectedListener);

        // Show home fragment on startup
        loadFragment(new HomeFragment());
    }

    private BottomNavigationView.OnNavigationItemSelectedListener selectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment fragment = null;

            // Use if-else statements to determine which fragment to load
            if (menuItem.getItemId() == R.id.nav_home) {
                actionBar.setTitle("Home");
                fragment = new HomeFragment();
            } else if (menuItem.getItemId() == R.id.nav_profile) {
                actionBar.setTitle("Profile");
                fragment = new ProfileFragment();
            } else if (menuItem.getItemId() == R.id.nav_users) {
                actionBar.setTitle("Users");
                fragment = new UsersFragment();
            } else if (menuItem.getItemId() == R.id.nav_chat) {
                actionBar.setTitle("Chats");
                fragment = new ChatListFragment();
            } else if (menuItem.getItemId() == R.id.nav_addblogs) {
                actionBar.setTitle("Add Blogs");
                fragment = new AddBlogsFragment();
            }

            // Load the selected fragment if it's not null
            if (fragment != null) {
                loadFragment(fragment);
                return true;
            }

            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content, fragment); // Ensure this matches your layout ID
        fragmentTransaction.commit();
    }
}
