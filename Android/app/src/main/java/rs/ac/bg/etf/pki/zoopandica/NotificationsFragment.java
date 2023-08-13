package rs.ac.bg.etf.pki.zoopandica;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import rs.ac.bg.etf.pki.zoopandica.data.PurchaseNotification;
import rs.ac.bg.etf.pki.zoopandica.databinding.FragmentNotificationsBinding;
import rs.ac.bg.etf.pki.zoopandica.databinding.FragmentTicketsBinding;

public class NotificationsFragment extends Fragment {

    private MainActivity mainActivity;
    private FragmentNotificationsBinding binding;
    private NavController navController;

    public NotificationsFragment() {
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
        binding = FragmentNotificationsBinding.inflate(inflater, container, false);

        List<PurchaseNotification> list = new ArrayList<>();
        for (PurchaseNotification pn : mainActivity.notifications) {
            if (pn.getUser().equals(mainActivity.loggedUser.getUsername())) {
                list.add(pn);
            }
        }
        NotificationAdapter notificationAdapter = new NotificationAdapter(list);

        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(notificationAdapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(mainActivity));

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }
}