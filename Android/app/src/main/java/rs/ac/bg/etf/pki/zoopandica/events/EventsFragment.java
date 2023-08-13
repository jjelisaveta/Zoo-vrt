package rs.ac.bg.etf.pki.zoopandica.events;

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
import rs.ac.bg.etf.pki.zoopandica.data.Event;
import rs.ac.bg.etf.pki.zoopandica.databinding.FragmentEventsBinding;


public class EventsFragment extends Fragment {

    private MainActivity mainActivity;
    private FragmentEventsBinding binding;
    private NavController navController;

    public EventsFragment() {
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
        binding = FragmentEventsBinding.inflate(inflater, container, false);


        EventAdapter eventAdapter = new EventAdapter(mainActivity.events, mainActivity);

        System.out.println(mainActivity.events.size());
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(eventAdapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(mainActivity));

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }
}