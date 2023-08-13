package rs.ac.bg.etf.pki.zoopandica.animals;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import rs.ac.bg.etf.pki.zoopandica.MainActivity;
import rs.ac.bg.etf.pki.zoopandica.data.Animal;
import rs.ac.bg.etf.pki.zoopandica.databinding.ViewHolderAnimalBinding;

public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder> {

    public interface Callback<T> {
        void invoke(T parameter);
    }

    private Callback<Animal> callback;
    private MainActivity mainActivity;
    private List<Animal> animals;


    public AnimalAdapter(List<Animal> animals, MainActivity mainActivity, Callback<Animal> c) {
        this.callback = c;
        this.mainActivity = mainActivity;
        this.animals = animals;
    }

    @NonNull
    @Override
    public AnimalAdapter.AnimalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewHolderAnimalBinding viewHolderAnimalBinding = ViewHolderAnimalBinding.inflate(
                layoutInflater,
                parent,
                false);
        return new AnimalAdapter.AnimalViewHolder(viewHolderAnimalBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimalAdapter.AnimalViewHolder holder, int position) {
        ViewHolderAnimalBinding binding = holder.binding;
        Animal animal = animals.get(position);
        binding.animalName.setText(animal.getName());
        binding.animalImage.setImageDrawable(animal.getImage());
    }

    @Override
    public int getItemCount() {
        return animals.size();
    }

    public class AnimalViewHolder extends RecyclerView.ViewHolder {

        public ViewHolderAnimalBinding binding;

        public AnimalViewHolder(@NonNull ViewHolderAnimalBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.animalImage.setOnClickListener(l -> {
                Animal animal = animals.get(getAdapterPosition());
                callback.invoke(animal);
            });
            binding.animalName.setOnClickListener(l -> {
                Animal animal = animals.get(getAdapterPosition());
                callback.invoke(animal);
            });
        }
    }
}
