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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import rs.ac.bg.etf.pki.zoopandica.MainActivity;
import rs.ac.bg.etf.pki.zoopandica.R;
import rs.ac.bg.etf.pki.zoopandica.data.Animal;
import rs.ac.bg.etf.pki.zoopandica.data.Comment;
import rs.ac.bg.etf.pki.zoopandica.databinding.FragmentAnimalDetailsBinding;

public class AnimalDetailsFragment extends Fragment {

    private MainActivity mainActivity;
    private FragmentAnimalDetailsBinding binding;
    private NavController navController;
    private Animal animal;

    public AnimalDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity = (MainActivity) requireActivity();
        animal = mainActivity.getCurrentAnimal();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAnimalDetailsBinding.inflate(inflater, container, false);


        List<Comment> comments = new ArrayList<>();
        for (int i = 0; i < mainActivity.comments.size(); ++i) {
            if (!mainActivity.comments.get(i).getAnimal().equals(mainActivity.getCurrentAnimal().getName()))
                continue;
            Comment comment = mainActivity.comments.get(i);
            comments.add(comment);
        }

        AnimalCommentAdapter animalAdapter = new AnimalCommentAdapter(comments, mainActivity);


        binding.image.setImageDrawable(animal.getImage());
        binding.name.setText(animal.getName());
        binding.description.setText(animal.getDescription());

        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(animalAdapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(mainActivity));

        binding.buttonAddComment.setOnClickListener(l -> {
            String username = mainActivity.loggedUser.getUsername();
            String text = binding.commentTextInner.getEditText().getText().toString();
            String animal = mainActivity.getCurrentAnimal().getName();

            if (text.equals("")) {
                Toast.makeText(mainActivity, "Morate uneti tekst komentara.", Toast.LENGTH_SHORT).show();
            }
            Comment comment = new Comment(username, text, animal);
            mainActivity.addComment(comment);
            animalAdapter.comments.add(comment);
            animalAdapter.notifyDataSetChanged();
            Toast.makeText(mainActivity, "Komentar je dodat.", Toast.LENGTH_SHORT).show();
        });

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }
}