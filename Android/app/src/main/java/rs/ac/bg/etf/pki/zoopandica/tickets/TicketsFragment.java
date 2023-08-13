package rs.ac.bg.etf.pki.zoopandica.tickets;

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

import rs.ac.bg.etf.pki.zoopandica.MainActivity;
import rs.ac.bg.etf.pki.zoopandica.R;
import rs.ac.bg.etf.pki.zoopandica.data.Ticket;
import rs.ac.bg.etf.pki.zoopandica.databinding.FragmentTicketsBinding;

public class TicketsFragment extends Fragment {

    private MainActivity mainActivity;
    private FragmentTicketsBinding binding;
    private NavController navController;


    public TicketsFragment() {
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
        binding = FragmentTicketsBinding.inflate(inflater, container, false);

        String[] names = getResources().getStringArray(R.array.ticketNames);
        String[] descriptions = getResources().getStringArray(R.array.ticketDescriptions);
        String[] prices = getResources().getStringArray(R.array.ticketPrices);
        for (int i = 0; i < names.length; ++i) {
            Ticket t = new Ticket(names[i], descriptions[i], prices[i]);
            mainActivity.tickets.add(t);
        }
        TicketAdapter ticketAdapter = new TicketAdapter(mainActivity.tickets, mainActivity);

        binding.recyclerViewTicket.setHasFixedSize(true);
        binding.recyclerViewTicket.setAdapter(ticketAdapter);
        binding.recyclerViewTicket.setLayoutManager(new LinearLayoutManager(mainActivity));


        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }
}