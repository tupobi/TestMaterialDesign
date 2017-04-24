package com.example.administrator.testmaterialdesign;

import android.app.Activity;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.ViewDragHelper;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Friend[] friends;
    private List<Friend> friendList;
    private FriendAdapter friendAdapter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
//        setDrawerLeftEdgeSize(this, drawerLayout, 1);
        ActionBar actionBar = getSupportActionBar();
        navigationView = (NavigationView) findViewById(R.id.naView);
//        navigationView.setCheckedItem(R.id.nav_call);
//        设置默认选中项
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.image_menu1);
        }//显示菜单栏

        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "删除信息", Snackbar.LENGTH_SHORT)
                        .setAction("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(MainActivity.this, "取消删除！", Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawers();
                Toast.makeText(MainActivity.this, "关闭菜单！", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        friends = new Friend[]{new Friend(R.drawable.i1, "Jay"), new Friend(R.drawable.i2, "Lili"),
                new Friend(R.drawable.i3, "Srary"), new Friend(R.drawable.i4, "Farali"),
                new Friend(R.drawable.i4, "Kili"), new Friend(R.drawable.i5, "Xiaoming"),
                new Friend(R.drawable.i6, "Fangfang"), new Friend(R.drawable.i7, "Nuonuo"),
                new Friend(R.drawable.i8, "Juli"), new Friend(R.drawable.i9, "Dongmei"),
                new Friend(R.drawable.i10, "Gily")
        };

        friendList = new ArrayList<>();
        initFriends();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        friendAdapter = new FriendAdapter(friendList);
        recyclerView.setAdapter(friendAdapter);
        swipRefresh = (SwipeRefreshLayout) findViewById(R.id.swipRefresh);
        swipRefresh.setColorSchemeResources(R.color.colorPrimary);
        swipRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshFriends();
            }
        });
    }

    private void refreshFriends() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep((long) (1.5*1000));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initFriends();
                        friendAdapter.notifyDataSetChanged();
                        swipRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    private void initFriends() {
        friendList.clear();
        for (int i=0; i<40; i++){
            Random random = new Random();
            int index = random.nextInt(friends.length);
            friendList.add(friends[index]);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nihao:
                Toast.makeText(this, "你好！", Toast.LENGTH_SHORT).show();
                break;
            case R.id.backup:
                Toast.makeText(this, "购物车！", Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                //监听显示菜单
        }
        return true;
    }

    private void setDrawerLeftEdgeSize(Activity activity, DrawerLayout drawerLayout, float displayWidthPercentage) {
        if (activity == null || drawerLayout == null) return;
        try {
            // 找到 ViewDragHelper 并设置 Accessible 为true
            Field leftDraggerField =
                    drawerLayout.getClass().getDeclaredField("mLeftDragger");//Right
            leftDraggerField.setAccessible(true);
            ViewDragHelper leftDragger = (ViewDragHelper) leftDraggerField.get(drawerLayout);

            // 找到 edgeSizeField 并设置 Accessible 为true
            Field edgeSizeField = leftDragger.getClass().getDeclaredField("mEdgeSize");
            edgeSizeField.setAccessible(true);
            int edgeSize = edgeSizeField.getInt(leftDragger);

            // 设置新的边缘大小
            Point displaySize = new Point();
            activity.getWindowManager().getDefaultDisplay().getSize(displaySize);
            edgeSizeField.setInt(leftDragger, Math.max(edgeSize, (int) (displaySize.x *
                    displayWidthPercentage)));
        } catch (NoSuchFieldException e) {
        } catch (IllegalArgumentException e) {
        } catch (IllegalAccessException e) {
        }
    }
}


