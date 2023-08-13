package rs.ac.bg.etf.pki.zoopandica.animals;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import rs.ac.bg.etf.pki.zoopandica.MainActivity;
import rs.ac.bg.etf.pki.zoopandica.data.Comment;
import rs.ac.bg.etf.pki.zoopandica.databinding.ViewHolderAnimalCommentBinding;

public class AnimalCommentAdapter extends RecyclerView.Adapter<AnimalCommentAdapter.AnimalCommentViewHolder> {

    public List<Comment> comments;
    private MainActivity mainActivity;

    public AnimalCommentAdapter(List<Comment> comments, MainActivity mainActivity) {
        this.comments = comments;
        this.mainActivity = mainActivity;
    }

    @NonNull
    @Override
    public AnimalCommentAdapter.AnimalCommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewHolderAnimalCommentBinding viewHolderAnimalCommentBinding = ViewHolderAnimalCommentBinding.inflate(
                layoutInflater,
                parent,
                false);
        return new AnimalCommentAdapter.AnimalCommentViewHolder(viewHolderAnimalCommentBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimalCommentAdapter.AnimalCommentViewHolder holder, int position) {
        ViewHolderAnimalCommentBinding binding = holder.binding;
        Comment comment = comments.get(position);
        binding.commentAuthor.setText(comment.getUser());
        binding.commentText.setText(comment.getText());
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public class AnimalCommentViewHolder extends RecyclerView.ViewHolder {

        public ViewHolderAnimalCommentBinding binding;

        public AnimalCommentViewHolder(@NonNull ViewHolderAnimalCommentBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
