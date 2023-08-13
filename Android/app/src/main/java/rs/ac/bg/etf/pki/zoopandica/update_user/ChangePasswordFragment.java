package rs.ac.bg.etf.pki.zoopandica.update_user;

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
import rs.ac.bg.etf.pki.zoopandica.data.User;
import rs.ac.bg.etf.pki.zoopandica.databinding.FragmentChangePasswordBinding;
import rs.ac.bg.etf.pki.zoopandica.databinding.FragmentChangePersonalDataBinding;

public class ChangePasswordFragment extends Fragment {

    private MainActivity mainActivity;
    private FragmentChangePasswordBinding binding;
    private NavController navController;

    public ChangePasswordFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity = (MainActivity) requireActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentChangePasswordBinding.inflate(inflater, container, false);

        binding.buttonSave.setOnClickListener(l -> {
            String oldPassword = binding.oldPassword.getEditText().getText().toString();
            String password = binding.password.getEditText().getText().toString();
            String password2 = binding.password2.getEditText().getText().toString();

            if (oldPassword.equals("") || password2.equals("") || password.equals("")) {
                Toast.makeText(mainActivity, "Prazna polja nisu dozvoljena.", Toast.LENGTH_SHORT).show();
                return;
            }

            for (User user : mainActivity.users) {
                if (user.getUsername().equals(mainActivity.loggedUser.getUsername())) {
                    if (!user.getPassword().equals(oldPassword)) {
                        Toast.makeText(mainActivity, "Stara lozinka nije ispravna.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (!password.equals(password2)) {
                        Toast.makeText(mainActivity, "Nova lozinka i ponovljena lozinka nisu iste.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    user.setPassword(password);
                    mainActivity.loggedUser.setPassword(password);
                    mainActivity.saveUsers();
                    Toast.makeText(mainActivity, "Promene su sačuvane.", Toast.LENGTH_SHORT).show();
                }
            }

        });

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }
}