package rs.ac.bg.etf.pki.zoopandica;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import rs.ac.bg.etf.pki.zoopandica.data.Animal;
import rs.ac.bg.etf.pki.zoopandica.data.Comment;
import rs.ac.bg.etf.pki.zoopandica.data.Event;
import rs.ac.bg.etf.pki.zoopandica.data.Like;
import rs.ac.bg.etf.pki.zoopandica.data.Promo;
import rs.ac.bg.etf.pki.zoopandica.data.PurchaseNotification;
import rs.ac.bg.etf.pki.zoopandica.data.Ticket;
import rs.ac.bg.etf.pki.zoopandica.data.User;
import rs.ac.bg.etf.pki.zoopandica.databinding.ActivityMainBinding;
import rs.ac.bg.etf.pki.zoopandica.login.LoginActivity;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    NavController navController;
    Animal currentAnimal;
    public List<User> users = new ArrayList<>();
    public List<PurchaseNotification> notifications = new ArrayList<>();
    public List<Comment> comments = new ArrayList<>();
    public List<Ticket> tickets = new ArrayList<>();
    public List<Event> events = new ArrayList<>();
    public List<Like> liked = new ArrayList<>();
    public List<Promo> promos = new ArrayList<>();

    public User loggedUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        NavHostFragment navHostFragment =(NavHostFragment)getSupportFragmentManager()
                .findFragmentById(R.id.navHostFragment);

        populateSharedPreferences();
        populateUsers();
        addComments();
        addEvents();
        addLikes();
        addLiked();
        addPromos();
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        for (User u : users) {
            if (u.getUsername().equals(username)) {
                loggedUser = u;
                break;
            }
        }
        navController = navHostFragment.getNavController();

        Toolbar toolbar = (Toolbar)findViewById(R.id.topAppBar);
        setSupportActionBar(toolbar);
        binding.topAppBar.setNavigationOnClickListener(l -> {
            Toolbar t = (Toolbar)findViewById(R.id.topAppBar);
            showPopup(t);
        });
    }



    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.navigation_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.homeItem:
                    navController.navigate(R.id.infoFragment);
                    break;
                case R.id.notificationsItem:
                    navController.navigate(R.id.notificationsFragment);
                    break;
                case R.id.ticketsItem:
                    navController.navigate(R.id.ticketsFragment);
                    break;
                case R.id.eventsItem:
                    navController.navigate(R.id.eventsFragment);
                    break;
                case R.id.animalsItem:
                    navController.navigate(R.id.animalsFragment);
                    break;
                case R.id.changeDataItem:
                    navController.navigate(R.id.changePersonalDataFragment);
                    break;
                case R.id.changePasswordItem:
                    navController.navigate(R.id.changePasswordFragment);
                    break;
                case R.id.logoutItem:
                    Intent intent = new Intent(this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                    break;
            }
            return true;
        });
        popup.show();
    }

    public Animal getCurrentAnimal() {
        return currentAnimal;
    }

    public void setAnimal(Animal animal) {
        currentAnimal = animal;
    }

    public void populateSharedPreferences() {

        String notificationss = "";
        PurchaseNotification notification = new PurchaseNotification("28.01.2023.",
                "odobreno", "jelisaveta", "Pojedinačna ulaznica");
        notificationss += "#" + notification.toString();
        notifications.add(notification);
        notification = new PurchaseNotification("28.01.2023.",
                 "odbijeno", "jelisaveta", "Novogodišnja promocija");
        notificationss += "#" + notification.toString();
        notifications.add(notification);
        notification = new PurchaseNotification("28.01.2023.",
                 "na čekanju", "jelisaveta", "Novogodišnja promocija");
        notificationss += "#" + notification.toString();
        notifications.add(notification);
        notification = new PurchaseNotification("28.01.2023.",
                 "na čekanju", "petar", "Januar promo");
        notificationss += "#" + notification.toString();
        notifications.add(notification);
        notification = new PurchaseNotification("28.01.2023.",
                 "odobreno", "jovana", "Pojedinačna ulaznica");
        notificationss += "#" + notification.toString();
        notifications.add(notification);


        SharedPreferences sharedPreferences = getSharedPreferences("zoo", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("notifications", notificationss);
        editor.commit();
    }

    public void addUser(User user) {
        users.add(user);
        SharedPreferences sharedPreferences = getSharedPreferences("zoo", MODE_PRIVATE);
        String s = sharedPreferences.getString("users","");
        SharedPreferences.Editor editor = sharedPreferences.edit();
        s+= "#" + user.toString();
        editor.putString("users",s);
        editor.commit();
    }

    public void addComment(Comment comment) {
        comments.add(comment);
        SharedPreferences sharedPreferences = getSharedPreferences("zoo", MODE_PRIVATE);
        String s = sharedPreferences.getString("comments","");
        SharedPreferences.Editor editor = sharedPreferences.edit();
        s+= "#" + comment.toString();
        editor.putString("comments",s);
        editor.commit();
    }

    public void addNotification(PurchaseNotification notification) {
        notifications.add(notification);
        SharedPreferences sharedPreferences = getSharedPreferences("zoo", MODE_PRIVATE);
        String s = sharedPreferences.getString("notifications","");
        SharedPreferences.Editor editor = sharedPreferences.edit();
        s+= "#" + notification.toString();
        editor.putString("notifications",s);
        editor.commit();
    }

    public void populateUsers() {
        SharedPreferences sharedPreferences = getSharedPreferences("zoo", MODE_PRIVATE);
        String[] s = sharedPreferences.getString("users", "").split("#");
        for (String userString : s) {
            if (userString.equals("")) continue;
            String[] attr = userString.split(",");
            User user = new User(attr[0], attr[1], attr[2], attr[3], attr[4], attr[5]);
            users.add(user);
        }
    }

    public void saveUsers() {
        String s = "";
        for (int i = 0; i < users.size(); ++i) {
            if (i > 0) {
                s += "#" +  users.get(i).toString();
            } else {
                s += users.get(i).toString();
            }
        }
        SharedPreferences.Editor editor = getSharedPreferences("zoo", MODE_PRIVATE).edit();
        editor.putString("users", s);
        editor.commit();
    }

    public void addComments() {
        SharedPreferences sharedPreferences = getSharedPreferences("zoo", MODE_PRIVATE);
        String c = sharedPreferences.getString("comments", "");
        String s = "";
        if (c.equals("")) {
            s += "pera,Najbolja zivotinja u celom vrtu.,Panda";
            s += "#jelisaveta,Moja omiljena zivotinja.,Leopard";
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("comments", s);
            editor.commit();
            c = s;
        }
        String[] splitC = c.split("#");
        for (int i = 0; i < splitC.length; ++i) {
            String[] oneComment = splitC[i].split(",");
            if (oneComment.length < 2) continue;
            Comment comment = new Comment(oneComment[0], oneComment[1], oneComment[2]);
            comments.add(comment);
        }

    }

    public void addTickets() {
        SharedPreferences sharedPreferences = getSharedPreferences("zoo", MODE_PRIVATE);
        String c = sharedPreferences.getString("tickets", "");
        String s = "";
        if (c.equals("")) {
            s += "Pojedinačna ulaznica,o,300";
            s += "#Grupna ulaznica,o,200";
            s += "#Promo paket za parove,o,500";
            s += "#Januar promo,o,1000";
            s += "#Novogodišnja promocija,o,0";
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("tickets", s);
            editor.commit();
            c = s;
        }
        String[] splitC = c.split("#");
        for (int i = 0; i < splitC.length; ++i) {
            String[] oneTicket = splitC[i].split(",");
            if (oneTicket.length < 2) continue;
            Ticket ticket = new Ticket(oneTicket[0], oneTicket[1], oneTicket[2]);
            tickets.add(ticket);
        }
    }

    public void addLikes() {
        SharedPreferences sharedPreferences = getSharedPreferences("zoo", MODE_PRIVATE);
        String c = sharedPreferences.getString("likes", "");
        String s = "";
        if (c.equals("")) {
            s += "Zimski vašar,51";
            s += "#Dani pčelarstva,10";
            s += "#Humanitarni vašar,76";
            s += "#Dan društvenih igara,34";
            s += "#Mesečna gratis tura,43";
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("likes", s);
            editor.commit();
            c = s;
        }
        String[] splitC = c.split("#");
        for (int i = 0; i < splitC.length; ++i) {
            String[] oneLike = splitC[i].split(",");
            if (oneLike.length < 2) continue;
            for (Event event : events) {
                if (event.getTitle().equals(oneLike[0])) {
                    event.setLikes(Integer.parseInt(oneLike[1]));
                    break;
                }
            }
        }
    }

    public void addEvents() {
        String[] titles = getResources().getStringArray(R.array.eventTitles);
        String[] descriptions = getResources().getStringArray(R.array.eventDescriptions);
        String[] images = getResources().getStringArray(R.array.eventImages);

        //String images = getResources().getStringArray(R.array.eventImages);
        //int[] likes = getResources().getIntArray(R.array.eventLikes);
        for (int i = 0; i < titles.length; ++i) {
            int drawableId = getResources().getIdentifier(images[i], "drawable", getPackageName());
            Event event = new Event(titles[i], descriptions[i], getResources().getDrawable(drawableId), 0);
            events.add(event);
        }
    }

    public void addLike(String title, int val) {
        String s = "";
        String es = "";
        for (int i = 0; i < events.size(); ++i) {
            int likes = events.get(i).getLikes();
            if (events.get(i).getTitle().equals(title)) {
                likes += val;
                events.get(i).setLikes(likes);
            }
            if (i != 0) {
                s += "#";
                es += "#";
            }
            s += events.get(i).getTitle() + "," + likes;
            es += events.get(i).toString();
        }
        SharedPreferences sharedPreferences = getSharedPreferences("zoo", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("likes", s);
        editor.putString("events", es);
        editor.commit();
    }

    public void addLiked() {
        SharedPreferences sharedPreferences = getSharedPreferences("zoo", MODE_PRIVATE);
        String c = sharedPreferences.getString("liked", "");
        String s = "";
        if (c.equals("")) {
            s += "jelisaveta,Zimski vašar";
            s += "#jeca,Zimski vašar";
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("liked", s);
            editor.commit();
            c = s;
        }
        String[] splitC = c.split("#");
        for (int i = 0; i < splitC.length; ++i) {
            String[] oneLike = splitC[i].split(",");
            if (oneLike.length < 2) continue;
            Like like = new Like(oneLike[0], oneLike[1]);
            liked.add(like);
        }
    }

    public void addLike(String username, String event) {
        Like like = new Like(username, event);
        liked.add(like);
        SharedPreferences sharedPreferences = getSharedPreferences("zoo", MODE_PRIVATE);
        String s = sharedPreferences.getString("liked","");
        SharedPreferences.Editor editor = sharedPreferences.edit();
        s+= "#" + username + "," + event;
        editor.putString("liked",s);
        editor.commit();
    }

    public int checkLikes(String username, String event) {
        for (int i = 0; i < liked.size(); ++i) {
            if (liked.get(i).getUsername().equals(username) && liked.get(i).getEvent().equals(event)) {
                return i;
            }
        }
        return -1;
    }

    public void removeLiked(int ind) {
        liked.remove(ind);
        String s = "";
        for (int i = 0; i < liked.size(); ++i) {
            if (i > 0) {
                s+= "#";
            }
            s += liked.get(i).getUsername() + " " + liked.get(i).getEvent();
        }
        SharedPreferences sharedPreferences = getSharedPreferences("zoo", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("liked",s);
        editor.commit();
    }

    public void addPromos() {
        Promo promo = new Promo("POPUST10", 0.9);
        promos.add(promo);
    }

}