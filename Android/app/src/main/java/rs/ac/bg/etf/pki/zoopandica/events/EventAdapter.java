package rs.ac.bg.etf.pki.zoopandica.events;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import rs.ac.bg.etf.pki.zoopandica.MainActivity;
import rs.ac.bg.etf.pki.zoopandica.R;
import rs.ac.bg.etf.pki.zoopandica.data.Event;
import rs.ac.bg.etf.pki.zoopandica.data.Like;
import rs.ac.bg.etf.pki.zoopandica.databinding.ViewHolderEventBinding;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    private List<Event> events;
    private MainActivity mainActivity;
    public EventAdapter(List<Event> events, MainActivity mainActivity) {
        this.events = events;
        this.mainActivity = mainActivity;
    }

    @NonNull
    @Override
    public EventAdapter.EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewHolderEventBinding viewHolderEventBinding = ViewHolderEventBinding.inflate(
                layoutInflater,
                parent,
                false);
        return new EventAdapter.EventViewHolder(viewHolderEventBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull EventAdapter.EventViewHolder holder, int position) {
        ViewHolderEventBinding binding = holder.binding;
        Event event = events.get(position);
        System.out.println(event.toString());
        binding.eventTitle.setText(event.getTitle());
        binding.eventDescription.setText(event.getDescription());
        binding.eventNumber.setText("" + event.getLikes());
        binding.eventImage.setImageDrawable(event.getImage());
        // Check if event is liked by current user.
        for (Like l : mainActivity.liked) {
            if (l.getEvent().equals(event.getTitle()) && l.getUsername().equals(mainActivity.loggedUser.getUsername())) {

                binding.eventLike.setImageDrawable(mainActivity.getDrawable(R.drawable.red_heart));
            }
        }
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public class EventViewHolder extends RecyclerView.ViewHolder {

        public ViewHolderEventBinding binding;

        public EventViewHolder(@NonNull ViewHolderEventBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.eventLike.setOnClickListener(l -> {
                Event event = events.get(getAdapterPosition());
                int ind = mainActivity.checkLikes(mainActivity.loggedUser.getUsername(),event.getTitle());
                if (ind == -1) {
                    binding.eventLike.setImageDrawable(mainActivity.getDrawable(R.drawable.red_heart));
                    mainActivity.addLike(event.getTitle(), 1);
                    mainActivity.addLike(mainActivity.loggedUser.getUsername(), event.getTitle());
                } else {
                    binding.eventLike.setImageDrawable(mainActivity.getDrawable(R.drawable.ic_baseline_favorite_border_24));
                    mainActivity.addLike(event.getTitle(), -1);
                    mainActivity.removeLiked(ind);
                }
                binding.eventNumber.setText("" + event.getLikes());
            });
        }
    }
}
