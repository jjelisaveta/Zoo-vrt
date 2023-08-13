package rs.ac.bg.etf.pki.zoopandica.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import rs.ac.bg.etf.pki.zoopandica.MainActivity;
import rs.ac.bg.etf.pki.zoopandica.R;
import rs.ac.bg.etf.pki.zoopandica.data.User;
import rs.ac.bg.etf.pki.zoopandica.databinding.FragmentHomeBinding;
import rs.ac.bg.etf.pki.zoopandica.login.LoginActivity;

public class HomeFragment extends Fragment {

    private LoginActivity mainActivity;
    private FragmentHomeBinding binding;
    private NavController navController;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity = (LoginActivity) requireActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        binding.textRegister.setOnClickListener(l -> {
            navController.navigate(R.id.registerFragment);
        });

        binding.buttonLogin.setOnClickListener((l) -> {
            // login
            String username = binding.username.getEditText().getText().toString();
            String password = binding.password.getEditText().getText().toString();

            if (password.equals("") ||  username.equals("")) {
                Toast.makeText(mainActivity, "Prazna polja nisu dozvoljena.", Toast.LENGTH_SHORT).show();
                return;
            }

            for (User user : mainActivity.users) {
                System.out.println(user.getUsername() + " " + user.getPassword());
                if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                    Intent intent = new Intent(mainActivity, MainActivity.class);
                    intent.putExtra("username", username);
                    startActivity(intent);
                    mainActivity.finish();
                    return;
                }
            }
            Toast.makeText(mainActivity, "Neispravni kredencijali.", Toast.LENGTH_SHORT).show();
        });

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }


}