package net.reywob.grizzcycle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SearchActivity extends AppCompatActivity {

    RelativeLayout menuHomeBtn, menuScanBtn;
    Button searchButton;
    EditText searchBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        // Assign the menu buttons to variables
        menuHomeBtn = findViewById(R.id.menu_home_button);
        menuScanBtn = findViewById(R.id.menu_scan_button);
        // Assign the UI elements to variables
        searchButton = findViewById(R.id.searchButton);
        searchBar = findViewById(R.id.searchBar);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Make sure the search bar is not empty
                String search = searchBar.getText().toString().trim();
                if (!search.isEmpty()) {
                    // Make a request to the database with the search criteria
                    String apiUrl = "https://csi4999.reywob.net/api/data/" + search;
                    new HttpAsyncTask(SearchActivity.this).execute(apiUrl);
                } else {
                    // Notify the user that the search bar is empty
                    Toast.makeText(SearchActivity.this, "Empty search query!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        menuHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go to the Home page
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        menuScanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go to the Barcode Scanner page
                Intent intent = new Intent(getApplicationContext(), BarcodeScannerActivity.class);
                startActivity(intent);
            }
        });
    }
}
