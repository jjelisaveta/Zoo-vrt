package rs.ac.bg.etf.pki.zoopandica.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.content.SharedPreferences;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import rs.ac.bg.etf.pki.zoopandica.R;
import rs.ac.bg.etf.pki.zoopandica.data.Comment;
import rs.ac.bg.etf.pki.zoopandica.data.PurchaseNotification;
import rs.ac.bg.etf.pki.zoopandica.data.User;
import rs.ac.bg.etf.pki.zoopandica.databinding.ActivityLoginBinding;
import rs.ac.bg.etf.pki.zoopandica.databinding.ActivityMainBinding;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    NavController navController;
    List<User> users = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        populateSharedPreferences();
        NavHostFragment navHostFragment =(NavHostFragment)getSupportFragmentManager()
                .findFragmentById(R.id.navHostFragment);

        navController = navHostFragment.getNavController();
    }

    protected void populateSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("zoo", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        /*editor.clear();
        editor.commit();*/
        if (!sharedPreferences.getString("users", "").equals("")) {
            populateUsers();
            return;
        }
        String userss = "";
        User user = new User("Jelisaveta", "Jevtic", "Trg Svetog Save 1",
                "064123456","jelisaveta","12345678");
        userss+= "#" + user.toString();
        users.add(user);
        user = new User("Petar", "Petrovic", "Krunska 30",
                "064123456","pera","12345678");
        userss+= "#" + user.toString();
        users.add(user);
        user = new User("Jovana", "Jovanovic", "Omladinska 20",
                "064123456","jovana","12345678");
        userss+= "#" + user.toString();
        users.add(user);



        editor.putString("users", userss);
        editor.commit();
    }

    protected void addUser(User user) {
        users.add(user);
        SharedPreferences sharedPreferences = getSharedPreferences("zoo", MODE_PRIVATE);
        String s = sharedPreferences.getString("users","");
        SharedPreferences.Editor editor = sharedPreferences.edit();
        s+= "#" + user.toString();
        editor.putString("users",s);
        editor.commit();
    }

    protected void populateUsers() {
        SharedPreferences sharedPreferences = getSharedPreferences("zoo", MODE_PRIVATE);
        String[] s = sharedPreferences.getString("users", "").split("#");
        for (String userString : s) {
            if (userString.equals("")) continue;
            String[] attr = userString.split(",");
            User user = new User(attr[0], attr[1], attr[2], attr[3], attr[4], attr[5]);
            users.add(user);
        }
    }
}