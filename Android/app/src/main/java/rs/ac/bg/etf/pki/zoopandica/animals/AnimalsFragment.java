package rs.ac.bg.etf.pki.zoopandica.animals;

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
import rs.ac.bg.etf.pki.zoopandica.data.Animal;
import rs.ac.bg.etf.pki.zoopandica.databinding.FragmentAnimalsBinding;

public class AnimalsFragment extends Fragment {

    private MainActivity mainActivity;
    private FragmentAnimalsBinding binding;
    private NavController navController;

    public AnimalsFragment() {
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
        binding = FragmentAnimalsBinding.inflate(inflater, container, false);

        String[] names = getResources().getStringArray(R.array.animalNames);
        String[] descriptions = getResources().getStringArray(R.array.animalDescriptions);
        String[] images = getResources().getStringArray(R.array.animalImages);

        List<Animal> animals = new ArrayList<>();
        for (int i = 0; i < names.length; ++i) {
            int drawableId = mainActivity.getResources().getIdentifier(images[i], "drawable", mainActivity.getPackageName());
            Animal animal = new Animal(names[i], getResources().getDrawable(drawableId), descriptions[i]);
            animals.add(animal);
        }

        AnimalAdapter animalAdapter = new AnimalAdapter(animals, mainActivity, animalName -> {
            mainActivity.setAnimal(animalName);
            navController.navigate(R.id.animalDetailsFragment);
        });

        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(animalAdapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(mainActivity));

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }
}