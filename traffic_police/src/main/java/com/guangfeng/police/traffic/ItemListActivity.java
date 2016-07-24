package com.guangfeng.police.traffic;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.guangfeng.police.traffic.mode.TrafficTicket;

import java.util.List;

/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class ItemListActivity extends AppCompatActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        setTitle("所有涉案记录");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                attemptCreate();
            }
        });

        View recyclerView = findViewById(R.id.item_list);
        assert recyclerView != null;

        setupRecyclerView((RecyclerView) recyclerView);

    }

    private void attemptCreate() {
        Intent homeIntent = new Intent(this, ScrollingActivity.class);
        startActivity(homeIntent);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        List<TrafficTicket> tickets = MainApplication.sTrafficModel.listTickets(true);
        RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> adapter;
        adapter = new SimpleItemRecyclerViewAdapter(tickets);
        recyclerView.setAdapter(adapter);
        MainApplication.sTrafficModel.setAdapter(adapter);
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private List<TrafficTicket> tickets;

        public SimpleItemRecyclerViewAdapter(List<TrafficTicket> items) {
            tickets = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            holder.mItem = tickets.get(position);
            Log.d("ItemList", "------onBindView " + holder.mItem);
            String id = holder.mItem.getEntryId();
            holder.mIdView.setText(id.substring(id.length()-5,id.length()));
            holder.mContentView.setText(holder.mItem.getCauseAction());

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                        Context context = v.getContext();
                        Intent intent = new Intent(context, ScrollingActivity.class);
                        intent.putExtra(ScrollingActivity.ARG_ITEM_ID, position);

                        context.startActivity(intent);

                }
            });
        }

        @Override
        public int getItemCount() {
            return tickets.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mIdView;
            public final TextView mContentView;
            public TrafficTicket mItem;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mIdView = (TextView) view.findViewById(R.id.ilc_id);
                mContentView = (TextView) view.findViewById(R.id.ilc_content);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mContentView.getText() + "'";
            }
        }
    }
}
