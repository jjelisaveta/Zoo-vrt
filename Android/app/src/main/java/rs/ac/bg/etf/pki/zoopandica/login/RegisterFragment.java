package rs.ac.bg.etf.pki.zoopandica.login;

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

import rs.ac.bg.etf.pki.zoopandica.R;
import rs.ac.bg.etf.pki.zoopandica.data.User;
import rs.ac.bg.etf.pki.zoopandica.databinding.FragmentRegisterBinding;
import rs.ac.bg.etf.pki.zoopandica.login.LoginActivity;


public class RegisterFragment extends Fragment {

    private LoginActivity mainActivity;
    private FragmentRegisterBinding binding;
    private NavController navController;

    public RegisterFragment() {
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
        binding = FragmentRegisterBinding.inflate(inflater, container, false);

        binding.buttonRegister.setOnClickListener((l) -> {
            String firstName = binding.name.getEditText().getText().toString();
            String lastName = binding.surname.getEditText().getText().toString();
            String address = binding.address.getEditText().getText().toString();
            String phone = binding.phone.getEditText().getText().toString();
            String username = binding.username.getEditText().getText().toString();
            String password = binding.password.getEditText().getText().toString();
            String password2 = binding.password2.getEditText().getText().toString();

            if (firstName.equals("") || lastName.equals("") || address.equals("") ||
                    phone.equals("") || username.equals("") || password.equals("") || password2.equals("")) {
                Toast.makeText(mainActivity, "Prazna polja nisu dozvoljena.", Toast.LENGTH_SHORT).show();
                return;
            }

            for (User u : mainActivity.users) {
                if (u.getUsername().equals(username)) {
                    Toast.makeText(mainActivity, "Korisničko ime već postoji.", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            if (!password.equals(password2)) {
                Toast.makeText(mainActivity, "Lozinka i ponovljena lozinka nisu iste.", Toast.LENGTH_SHORT).show();
                return;
            }
            User user = new User(firstName, lastName, address, phone, username, password);
            mainActivity.addUser(user);
            Toast.makeText(mainActivity, "Registracija je uspešna.", Toast.LENGTH_SHORT).show();
            navController.navigate(R.id.homeFragment);
        });
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }
}