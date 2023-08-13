package rs.ac.bg.etf.pki.zoopandica.tickets;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import rs.ac.bg.etf.pki.zoopandica.MainActivity;
import rs.ac.bg.etf.pki.zoopandica.data.Ticket;
import rs.ac.bg.etf.pki.zoopandica.databinding.ViewHolderTicketBinding;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.TicketViewHolder> {

    private List<Ticket> tickets;
    private MainActivity mainActivity;


    public TicketAdapter(List<Ticket> tickets, MainActivity mainActivity) {
        this.tickets = tickets;
        this.mainActivity = mainActivity;
    }

    @NonNull
    @Override
    public TicketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewHolderTicketBinding viewHolderTicketBinding = ViewHolderTicketBinding.inflate(
                layoutInflater,
                parent,
                false);
        return new TicketViewHolder(viewHolderTicketBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketViewHolder holder, int position) {
        ViewHolderTicketBinding binding = holder.binding;
        Ticket ticket = tickets.get(position);
        binding.ticketTitle.setText(ticket.getName());
        binding.ticketDescription.setText(ticket.getDescription());
        binding.ticketPrice.setText("Cena: "+ ticket.getPrice());


    }

    @Override
    public int getItemCount() {
        return tickets.size();
    }

    public class TicketViewHolder extends RecyclerView.ViewHolder {

        public ViewHolderTicketBinding binding;

        public TicketViewHolder(@NonNull ViewHolderTicketBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.buttonBuyTicket.setOnClickListener(l -> {
                // Buy ticket.
                TicketDialog dialog = new TicketDialog();
                Bundle bundle = new Bundle();
                Ticket ticket = tickets.get(getAdapterPosition());
                String data = ticket.getName() + "#" + ticket.getPrice() + "#" + mainActivity.loggedUser.getUsername();
                bundle.putString("data", data);
                dialog.setArguments(bundle);
                dialog.show(
                        mainActivity.getSupportFragmentManager(),"TAG");

            });
        }
    }
}
