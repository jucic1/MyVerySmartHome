package com.example.myverysmarthome.favorites;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myverysmarthome.databinding.ActivityFavoritesBinding;

public class FavoritesActivity extends AppCompatActivity {

    ActivityFavoritesBinding activityFavoritesBinding;
    FavoritesViewModel favoritesViewModel;

    FavoriteDevicesAdapter FavoriteDevicesAdapter = new FavoriteDevicesAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityFavoritesBinding = ActivityFavoritesBinding.inflate(getLayoutInflater());
        setContentView(activityFavoritesBinding.getRoot());
        favoritesViewModel = new ViewModelProvider(this).get(FavoritesViewModel.class);

    }

}
