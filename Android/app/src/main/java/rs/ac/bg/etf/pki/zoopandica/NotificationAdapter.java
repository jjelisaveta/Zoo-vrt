package rs.ac.bg.etf.pki.zoopandica;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import rs.ac.bg.etf.pki.zoopandica.data.PurchaseNotification;
import rs.ac.bg.etf.pki.zoopandica.databinding.ViewHolderNotificationBinding;


public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {
    List<PurchaseNotification> notifications;

    public NotificationAdapter(List<PurchaseNotification> n) {
        notifications = n;
    }

    @NonNull
    @Override
    public NotificationAdapter.NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewHolderNotificationBinding viewHolderTicketBinding = ViewHolderNotificationBinding.inflate(
                layoutInflater,
                parent,
                false);
        return new NotificationAdapter.NotificationViewHolder(viewHolderTicketBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationAdapter.NotificationViewHolder holder, int position) {
        ViewHolderNotificationBinding binding = holder.binding;
        PurchaseNotification pn = notifications.get(position);
        binding.notificationTitle.setText(pn.getType());
        binding.notificationDate.setText(pn.getDate());
        binding.notificationStatus.setText(pn.getStatus());
        if (pn.getStatus().equals("odobreno")) {
            binding.notificationStatus.setTextColor(Color.GREEN);
        } else if (pn.getStatus().equals("odbijeno")) {
            binding.notificationStatus.setTextColor(Color.RED);
        } else {
            binding.notificationStatus.setTextColor(Color.YELLOW);
        }

    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }

    public class NotificationViewHolder extends RecyclerView.ViewHolder {

        public ViewHolderNotificationBinding binding;

        public NotificationViewHolder(@NonNull ViewHolderNotificationBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
