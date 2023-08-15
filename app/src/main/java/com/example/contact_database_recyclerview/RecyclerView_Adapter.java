package com.example.contact_database_recyclerview;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contact_database_recyclerview.Model.Contacts;

import java.util.ArrayList;

public class RecyclerView_Adapter extends RecyclerView.Adapter<RecyclerView_Adapter.ContactListHolder> {
    MainActivity mainActivity;
    ArrayList<Contacts> contactslist;
    public RecyclerView_Adapter(MainActivity mainActivity, ArrayList<Contacts> contactslist) {
        this.mainActivity = mainActivity;
        this.contactslist = contactslist;
    }

    @NonNull
    @Override
    public RecyclerView_Adapter.ContactListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mainActivity).inflate(R.layout.recyclerview_item,parent,false);
        ContactListHolder holder = new ContactListHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView_Adapter.ContactListHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.name.setText(contactslist.get(position).getName());
        holder.subname.setText(contactslist.get(position).getSubname());
        holder.number.setText(contactslist.get(position).getNumber());
        holder.image.setImageURI(Uri.parse(contactslist.get(position).getImguri()));
        holder.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(mainActivity,holder.more);
                popupMenu.getMenuInflater().inflate(R.menu.menu, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.update)
                        {
                            Intent intent = new Intent(mainActivity, Create_Contact_Activity.class);
                            intent.putExtra("id",contactslist.get(position).getId());
                            intent.putExtra("name",contactslist.get(position).getName());
                            intent.putExtra("subname",contactslist.get(position).getSubname());
                            intent.putExtra("number",contactslist.get(position).getNumber());
                            intent.putExtra("imageuri",contactslist.get(position).getImguri());
                            mainActivity.startActivity(intent);
                        }
                        if (item.getItemId() == R.id.delete)
                        {
                            MyDatabase myDatabase = new MyDatabase(mainActivity);
                            myDatabase.DeleteContact(contactslist.get(position).getId());
                            contactslist.remove(position);
                            notifyDataSetChanged();
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return contactslist.size();
    }

    public class ContactListHolder extends RecyclerView.ViewHolder{
        ImageView image,more;
        TextView name,subname,number;
        public ContactListHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            more = itemView.findViewById(R.id.more);
            name = itemView.findViewById(R.id.name);
            subname = itemView.findViewById(R.id.subname);
            number = itemView.findViewById(R.id.number);
        }
    }
}
