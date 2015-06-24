package net.gongmingqm10.training.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import net.gongmingqm10.training.R;
import net.gongmingqm10.training.adapter.MaterialRecyclerAdapter;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static com.google.common.collect.Lists.newArrayList;

public class MaterialRecyclerActivity extends AppCompatActivity {

    @InjectView(R.id.recycler_view)
    protected RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_design);
        ButterKnife.inject(this);

        initView();
    }

    private void initView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<String> data = newArrayList("This is RecyclerView",
                "Learn Android material design",
                "Design your App",
                "Forever young in the world",
                "This is RecyclerView",
                "Learn Android material design",
                "Design your App",
                "This is RecyclerView",
                "Learn Android material design",
                "Learn Android material design",
                "Design your App",
                "Forever young in the world",
                "This is RecyclerView",
                "Learn Android material design",
                "Design your App",
                "This is RecyclerView",
                "Learn Android material design",
                "Design your App");

        recyclerView.setAdapter(new MaterialRecyclerAdapter(this, data));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_material_design, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }
}
