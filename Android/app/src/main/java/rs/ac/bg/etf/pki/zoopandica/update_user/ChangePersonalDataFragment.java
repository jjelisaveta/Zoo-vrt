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
import rs.ac.bg.etf.pki.zoopandica.databinding.FragmentChangePersonalDataBinding;
import rs.ac.bg.etf.pki.zoopandica.databinding.FragmentHomeBinding;

public class ChangePersonalDataFragment extends Fragment {

    private MainActivity mainActivity;
    private FragmentChangePersonalDataBinding binding;
    private NavController navController;

    public ChangePersonalDataFragment() {
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
        binding = FragmentChangePersonalDataBinding.inflate(inflater, container, false);

        binding.firstName.getEditText().setText(mainActivity.loggedUser.getFirstName());
        binding.lastName.getEditText().setText(mainActivity.loggedUser.getLastName());
        binding.address.getEditText().setText(mainActivity.loggedUser.getAddress());
        binding.phone.getEditText().setText(mainActivity.loggedUser.getPhone());
        binding.username.getEditText().setText(mainActivity.loggedUser.getUsername());

        binding.buttonSave.setOnClickListener(l -> {
            String firstName = binding.firstName.getEditText().getText().toString();
            String lastName = binding.lastName.getEditText().getText().toString();
            String address = binding.address.getEditText().getText().toString();
            String phone = binding.phone.getEditText().getText().toString();
            String username = binding.username.getEditText().getText().toString();

            if (firstName.equals("") || lastName.equals("") || address.equals("") ||
                    phone.equals("") || username.equals("")) {
                Toast.makeText(mainActivity, "Prazna polja nisu dozvoljena.", Toast.LENGTH_SHORT).show();
                return;
            }

            for (User user : mainActivity.users) {
                if (mainActivity.loggedUser.getUsername().equals(user.getUsername())) {
                    user.setFirstName(firstName);
                    user.setLastName(lastName);
                    user.setAddress(address);
                    user.setPhone(phone);
                    user.setUsername(username);
                    mainActivity.loggedUser.setFirstName(firstName);
                    mainActivity.loggedUser.setLastName(lastName);
                    mainActivity.loggedUser.setAddress(address);
                    mainActivity.loggedUser.setPhone(phone);
                    mainActivity.loggedUser.setUsername(username);
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