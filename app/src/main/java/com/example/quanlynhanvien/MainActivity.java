package com.example.quanlynhanvien;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    final String DATABASE_NAME = "NhanVienDB.sqlite";
    SQLiteDatabase database;
    ListView listView;
    ArrayList<NhanVien> list;
    AdapterNhanVien adapter;
    Button btnAdd;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;

    NavigationView navigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        database = Database.initDatabase(this, DATABASE_NAME);
        addControls();
        readData();


        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.navigation);

        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                switch (item.getItemId()){
                    case R.id.nav_home:
                        Toast.makeText(MainActivity.this,"Bạn chọn Home",Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.nav_share:
                        Toast.makeText(MainActivity.this,"Bạn chọn Share",Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.nav_exit:
                        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                        dialog.setTitle("Xác nhận thoát");
                        dialog.setMessage("Bạn có muốn thoát không?");
                        dialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(MainActivity.this, Login.class);
                                startActivity(intent);
                            }
                        });
                        dialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        dialog.show();
                        break;
                }

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        drawerToggle.syncState();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menuAboutUs:
                Intent intent = new Intent(MainActivity.this, AboutUs.class);
                startActivity(intent);
                break;

        }
        return super.onOptionsItemSelected(item);
    }


    private void addControls() {
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });
        listView = (ListView) findViewById(R.id.listView);
        list = new ArrayList<>();
        adapter = new AdapterNhanVien(this, list);
        listView.setAdapter(adapter);
    }


    private void readData(){
        database = Database.initDatabase(this, DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT * FROM NhanVien",null);
        list.clear();
        for(int i = 0; i < cursor.getCount(); i++){
            cursor.moveToPosition(i);
            int id = cursor.getInt(0);
            String ten = cursor.getString(1);
            String sdt = cursor.getString(2);
            byte[] anh = cursor.getBlob(3);
            list.add(new NhanVien(id, ten, sdt, anh));
        }
        adapter.notifyDataSetChanged();
    }




}