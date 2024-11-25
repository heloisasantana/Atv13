package com.example.atv13;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.example.atv13.fragments.FragmentLivro;
import com.example.atv13.fragments.FragmentRevista;
import com.example.atv13.model.MenuOption;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Carregar o FragmentLivro por padrão
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new FragmentLivro())
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Fragment fragment = null;

        try {
            MenuOption option = MenuOption.fromTitle(item.getTitle().toString());
            switch (option) {
                case LIVRO:
                    fragment = new FragmentLivro();
                    break;
                case REVISTA:
                    fragment = new FragmentRevista();
                    break;
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
        }

        return super.onOptionsItemSelected(item);
    }
}
